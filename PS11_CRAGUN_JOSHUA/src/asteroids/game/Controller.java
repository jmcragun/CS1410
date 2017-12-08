package asteroids.game;

import static asteroids.game.Constants.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;
import asteroids.participants.Alien;
import asteroids.participants.AlienBullet;
import asteroids.participants.Asteroid;
import asteroids.participants.Ship;
import asteroids.participants.Bullet;
import asteroids.participants.Debris;
import asteroids.participants.Dust;
import asteroids.participants.Life;
import asteroids.game.ParticipantCountdownTimer;
import sounds.SoundDemo;

/**
 * Controls a game of Asteroids.
 */
public class Controller implements KeyListener, ActionListener
{
    /** The state of all the Participants */
    private ParticipantState pstate;

    /** The ship (if one is active) or null (otherwise) */
    private Ship ship;

    /** The Alien ship (if one is active) or null (otherwise) */
    private Alien alien;

    /** When this timer goes off, it is time to refresh the animation */
    private Timer refreshTimer;

    /**
     * The time at which a transition to a new stage of the game should be made. A transition is scheduled a few seconds
     * in the future to give the user time to see what has happened before doing something like going to a new level or
     * resetting the current level.
     */
    private long transitionTime;

    /** Number of lives left */
    private int lives;

    /** Current level of the game */
    private int level;

    /** The Player's score */
    private int score;

    /** The game display */
    private Display display;

    /** Array of the game's lives */
    private Life[] livesArray;

    /** Indicates if an up arrow equivalent is being made */
    private boolean up;
    /** Indicates if the ship is turning counter-clockwise */
    private boolean left;
    /** Indicates if the ship is turning clockwise */
    private boolean right;
    /** Indicates the if the alien ship can shoot */
    private boolean alienCanShoot;
    /** Sound clips */
    private SoundDemo clip;

    /**
     * Constructs a controller to coordinate the game and screen
     */
    public Controller ()
    {
        // Initialize the ParticipantState
        pstate = new ParticipantState();

        // Set up the refresh timer.
        refreshTimer = new Timer(FRAME_INTERVAL, this);

        // Clear the transitionTime
        transitionTime = Long.MAX_VALUE;

        // Record the display object
        display = new Display(this);

        // Bring up the splash screen and start the refresh timer
        splashScreen();
        display.setVisible(true);
        refreshTimer.start();

        // Make sure no bullets are made from the get-go
        alienCanShoot = false;

        // Initialize SoundDemo
        clip = new SoundDemo();
    }

    /**
     * Returns the ship, or null if there isn't one
     */
    public Ship getShip ()
    {
        return ship;
    }

    /**
     * Returns the ship, or null if there isn't one
     */
    public Alien getAlien ()
    {
        return alien;
    }

    /**
     * Configures the game screen to display the splash screen
     */
    private void splashScreen ()
    {
        // Clear the screen, reset the level, and display the legend
        clear();
        display.setLegend("ASTEROIDS");

        // Place four asteroids near the corners of the screen.
        placeAsteroids(this.level + 1);
    }

    /**
     * The game is over. Displays a message to that effect.
     */
    private void finalScreen ()
    {
        display.setLegend(GAME_OVER);
        display.removeKeyListener(this);
    }

    /**
     * Place a new ship in the center of the screen. Remove any existing ship first.
     */
    private void placeShip ()
    {
        // Place a new ship
        Participant.expire(ship);
        ship = new Ship(SIZE / 2, SIZE / 2, -Math.PI / 2, this);
        addParticipant(ship);
        display.setLegend("");
    }

    private void placeLives (int lives)
    {
        int increment = 0;
        for (int i = 0; i < lives; i++)
        {
            addParticipant(new Life((SIZE / 12) + increment, SIZE / 8, i));
            increment += 30;
        }
    }

    private void makeShipDebris ()
    {
        for (int i = 0; i < 7; i++)
        {
            addParticipant(new Debris(1, i, this));
        }
        for (int i = 0; i < (RANDOM.nextInt(3) + 3); i++)
        {
            this.addParticipant(new Dust(ship.getX(), ship.getY(), 2, RANDOM.nextInt(4)));
        }
    }

    /**
     * Places an asteroid near one corner of the screen. Gives it a random velocity and rotation.
     */
    private void placeAsteroids (int level)
    {
        Random rand = new Random();
        // Create the appropriate amount of asteroids of random variety and position
        for (int i = 0; i < level + 3; i++)
        {
            int x = rand.nextInt(450);
            int y = rand.nextInt(450);
            int v = rand.nextInt(4);
            while (Math.abs((EDGE_OFFSET + x) - (SIZE / 2)) < 175 && Math.abs((EDGE_OFFSET + y) - (SIZE / 2)) < 175)
            {
                x = rand.nextInt(450);
                y = rand.nextInt(450);
            }
            addParticipant(new Asteroid(v, 2, EDGE_OFFSET + x, EDGE_OFFSET + y,
                    RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this));
        }
    }

    /**
     * Clears the screen so that nothing is displayed
     */
    private void clear ()
    {
        pstate.clear();
        display.setLegend("");
        ship = null;
        alien = null;
    }

    /**
     * Sets things up and begins a new game.
     */
    private void initialScreen ()
    {
        // Clear the screen
        clear();

        // Clear the transition time
        transitionTime = Long.MAX_VALUE;

        // Place the ship
        placeShip();
        up = false;
        left = false;
        right = false;

        // Reset statistics
        lives = 3;
        level = 1;
        placeLives(lives);
        livesArray = pstate.getLives(lives);

        // Sets glass pane visible
        display.glassSwitch();

        // Place asteroids
        placeAsteroids(this.level);

        // Start listening to events (but don't listen twice)
        display.removeKeyListener(this);
        display.addKeyListener(this);

        // Give focus to the game screen
        display.requestFocusInWindow();
    }

    /**
     * Adds a new Participant
     */
    public void addParticipant (Participant p)
    {
        pstate.addParticipant(p);
    }

    /**
     * The ship has been destroyed
     */
    public void shipDestroyed ()
    {
        // create the ship debris
        this.makeShipDebris();

        // Null out the ship
        ship = null;
        up = false;
        left = false;
        right = false;

        // Decrement lives
        lives--;

        // Since the ship was destroyed, schedule a transition
        scheduleTransition(END_DELAY);

        // Stop clip of ship thrusters
        clip.stopThrustClip();
    }

    /**
     * The Alien ship has been destroyed
     */
    public void alienDestroyed ()
    {
        int i = alien.getAlienSize();
        alien = null;
        scheduleTransition(RANDOM.nextInt(ALIEN_DELAY));
        if (i == 1)
        {
            clip.stopSaucerBClip();
        }
        else
        {
            clip.stopSaucerSClip();
        }
        score += ALIENSHIP_SCORE[i];
        alienCanShoot = false;
    }

    /**
     * Makes the display match the number of lives in the back end
     */
    private void updateLives ()
    {
        for (Life life : livesArray)
        {
            life.loseLife(lives);
        }
    }

    /**
     * Causes the ship to shoot a bullet
     */
    private void shoot ()
    {
        if (pstate.countBullets() < 8)
        {
            addParticipant(new Bullet(this.ship.getXNose() - 1.9, this.ship.getYNose(), this));
            clip.playFireClip();
        }
    }

    /**
     * Causes an alien ship (if there is one) to shoot in a random direction
     */
    private void alienShootDumb ()
    {
        if (alien != null)
        {
            addParticipant(new AlienBullet(alien.getX(), alien.getY(), RANDOM.nextDouble() * 2 * Math.PI, this));
            clip.playFireClip();
        }
        scheduleTransition(RANDOM.nextInt(2000));
    }

    /**
     * Causes an alien ship (if there is one) to shoot in at the ship
     */
    private void alienShootSmart ()
    {
        if ((ship != null) && (alien != null))
        {
            addParticipant(new AlienBullet(alien.getX(), alien.getY(), getPlayerTheta(), this));
            clip.playFireClip();
        }
        scheduleTransition(RANDOM.nextInt(2000));
    }

    private double getPlayerTheta ()
    {
        double theta = 0.0;
        if ((ship != null) && (alien != null))
        {
            double deltax = ship.getX() - alien.getX();
            double deltay = ship.getY() - alien.getY();
            theta = Math.atan2(deltax, deltay);
            return theta;
        }
        else
        {
            return -10.0;
        }
    }

    /**
     * Allows the score to be changed
     * 
     * @param score
     */
    public void setScore (int score)
    {
        this.score = score;
    }

    /**
     * Returns the current score
     * 
     * @return
     */
    public int getScore ()
    {
        return this.score;
    }

    public int getLevel ()
    {
        return this.level;
    }

    /**
     * An asteroid has been destroyed
     */
    public void asteroidDestroyed ()
    {
        // If all the asteroids are gone, schedule a transition
        if (pstate.countAsteroids() == 0)
        {
            scheduleTransition(END_DELAY);
        }
    }

    /**
     * Schedules a transition m msecs in the future
     */
    private void scheduleTransition (int m)
    {
        transitionTime = System.currentTimeMillis() + m;
    }

    /**
     * This method will be invoked because of button presses and timer events.
     */
    @Override
    public void actionPerformed (ActionEvent e)
    {
        // The start button has been pressed. Stop whatever we're doing
        // and bring up the initial screen
        if (e.getSource() instanceof JButton)
        {
            initialScreen();
        }

        // Time to refresh the screen and deal with keyboard input
        else if (e.getSource() == refreshTimer)
        {
            // It may be time to make a game transition
            performTransition();

            // Move the participants to their new locations
            pstate.moveParticipants();

            // Refresh screen
            display.refresh();

            if (up && ship != null)
            {
                ship.accelerate();
            }
            if (right && ship != null)
            {
                ship.turnRight();
            }
            if (left && ship != null)
            {
                ship.turnLeft();
            }
        }
    }

    /**
     * Returns an iterator over the active participants
     */
    public Iterator<Participant> getParticipants ()
    {
        return pstate.getParticipants();
    }

    /**
     * If the transition time has been reached, transition to a new state
     */
    private void performTransition ()
    {
        // Do something only if the time has been reached
        if (transitionTime <= System.currentTimeMillis())
        {
            // Clear the transition time
            transitionTime = Long.MAX_VALUE;

            // Update the number of lives to be displayed
            this.updateLives();

            // If there are no lives left, the game is over. Show the final
            // screen.
            if (lives <= 0)
            {
                finalScreen();
            }
            // If a level is over, add the new asteroids
            // If the level is 2 or higher, schedule adding an alien
            else if (pstate.countAsteroids() == 0)
            {
                level++;
                placeAsteroids(level);
                placeShip();
                if (level >= 2)
                {
                    scheduleTransition(RANDOM.nextInt(ALIEN_DELAY));
                }
            }
            // If it's level 2, and alienCanShoot is false, add a big alien, let him shoot, and schedule its first shot
            else if (level == 2 && !alienCanShoot)
            {
                alien = new Alien(1, this);
                addParticipant(alien);
                alienCanShoot = true;
                clip.playSaucerBClip();
                scheduleTransition(RANDOM.nextInt(2000));
            }
            // If it's level 2, and alienCanShoot is false, add a small alien, let him shoot, and schedule its first
            // shot
            else if (level >= 3 && !alienCanShoot)
            {
                alien = new Alien(0, this);
                addParticipant(alien);
                alienCanShoot = true;
                clip.playSaucerSClip();
                scheduleTransition(RANDOM.nextInt(2000));
            }
            else if (alienCanShoot && alien.getAlienSize() == 1)
            {
                alienShootDumb();
            }
            else if (alienCanShoot && alien.getAlienSize() == 0)
            {
                alienShootSmart();
            }
            else
            {
                placeShip();
            }
        }
    }

    /**
     * If a key of interest is pressed, record that it is down.
     */
    @Override
    public void keyPressed (KeyEvent e)
    {
        if (ship != null && (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd'))
        {
            right = true;
        }
        if (ship != null && (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'w'))
        {
            up = true;
            ship.switchOutlineFire();
            clip.playThrustClip();
        }
        if (ship != null && (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a'))
        {
            left = true;
        }
        if (ship != null
                && (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == 's'))
        {
            shoot();
        }
    }

    /**
     * These events are ignored.
     */
    @Override
    public void keyTyped (KeyEvent e)
    {
    }

    /**
     * These events are ignored.
     */
    @Override
    public void keyReleased (KeyEvent e)
    {
        if (ship != null && (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd'))
        {
            right = false;
        }
        if (ship != null && (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'w'))
        {
            up = false;
            ship.switchOutlineNormal();
            clip.stopThrustClip();
        }
        if (ship != null && (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a'))
        {
            left = false;
        }
    }
}
