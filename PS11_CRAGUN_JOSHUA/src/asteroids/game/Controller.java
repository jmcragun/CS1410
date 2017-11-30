package asteroids.game;

import static asteroids.game.Constants.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;
import asteroids.participants.Asteroid;
import asteroids.participants.Ship;

/**
 * Controls a game of Asteroids.
 */
public class Controller implements KeyListener, ActionListener
{
    /** The state of all the Participants */
    private ParticipantState pstate;

    /** The ship (if one is active) or null (otherwise) */
    private Ship ship;

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

    /** The game display */
    private Display display;

    private boolean up;
    private boolean left;
    private boolean right;

    /**
     * Constructs a controller to coordinate the game and screen
     */
    public Controller ()
    {
        // Initialize the ParticipantState
        pstate = new ParticipantState();

        // Set up the refresh timer.
        refreshTimer = new Timer(FRAME_INTERVAL, this);

        up = false;
        left = false;
        right = false;

        // Clear the transitionTime
        transitionTime = Long.MAX_VALUE;

        // Record the display object
        display = new Display(this);

        // Bring up the splash screen and start the refresh timer
        splashScreen();
        display.setVisible(true);
        refreshTimer.start();
    }

    /**
     * Returns the ship, or null if there isn't one
     */
    public Ship getShip ()
    {
        return ship;
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
    }

    /**
     * Sets things up and begins a new game.
     */
    private void initialScreen ()
    {
        // Clear the screen
        clear();

        // Place the ship
        placeShip();

        // Reset statistics
        lives = 3;
        level = 1;

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
        // Null out the ship
        ship = null;

        // Display a legend
        display.setLegend("Ouch!");

        // Decrement lives
        lives--;

        // Since the ship was destroyed, schedule a transition
        scheduleTransition(END_DELAY);
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

            // If there are no lives left, the game is over. Show the final
            // screen.
            if (lives <= 0)
            {
                finalScreen();
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
        if (ship != null && e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd')
        {
            right = true;
        }
        if (ship != null && e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'w')
        {
            up = true;
            ship.switchOutlineFire();
        }
        if (ship != null && e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a')
        {
            left = true;
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
        if (ship != null && e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd')
        {
            right = false;
        }
        if (ship != null && e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'w')
        {
            up = false;
            ship.switchOutlineNormal();
        }
        if (ship != null && e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a')
        {
            left = false;
        }
    }
}
