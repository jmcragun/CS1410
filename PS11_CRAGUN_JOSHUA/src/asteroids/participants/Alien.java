package asteroids.participants;

import static asteroids.game.Constants.*;
import java.awt.Shape;
import java.awt.geom.*;
import asteroids.destroyers.*;
import asteroids.game.Controller;
import asteroids.game.Participant;
import asteroids.game.ParticipantCountdownTimer;
import sounds.SoundDemo;

public class Alien extends Participant implements AsteroidDestroyer, ShipDestroyer
{
    /** The outline of the ship */
    private Shape outline;

    /** Game controller */
    private Controller controller;

    /** size of the alien ship */
    private int size;

    /** Sound clips */
    private SoundDemo clip;

    /** alien ship speed */
    private int speed;

    /** alien ship primary direction of travel. Is either pi or 0 */
    private double dir;

    public Alien (int size, Controller controller)
    {
        this.controller = controller;
        int side = RANDOM.nextInt(2);
        int corner = RANDOM.nextInt(2);
        this.size = size;
        // Initialize SoundDemo
        clip = new SoundDemo();
        if ((side == 1 && corner == 0) && size == 1)
        {
            speed = 4;
            dir = Math.PI;
            setPosition(SIZE, 0);
            setVelocity(speed, dir);
            new ParticipantCountdownTimer(this, "down", 500);
        }
        else if ((side == 1 && corner == 1) && size == 1)
        {
            speed = 4;
            dir = Math.PI;
            setPosition(SIZE, SIZE);
            setVelocity(speed, dir);
            new ParticipantCountdownTimer(this, "up", 500);
        }
        else if ((side == 0 && corner == 0) && size == 1)
        {
            speed = 4;
            dir = 0;
            setPosition(0, 0);
            setVelocity(speed, dir);
            new ParticipantCountdownTimer(this, "down", 500);
        }
        else if ((side == 0 && corner == 1) && size == 1)
        {
            speed = 4;
            dir = 0;
            setPosition(0, SIZE);
            setVelocity(speed, dir);
            new ParticipantCountdownTimer(this, "up", 500);
        }
        else if ((side == 1 && corner == 0) && size == 0)
        {
            speed = 6;
            dir = Math.PI;
            setPosition(SIZE, 0);
            setVelocity(speed, dir);
            new ParticipantCountdownTimer(this, "down", 500);
        }
        else if ((side == 1 && corner == 1) && size == 0)
        {
            speed = 6;
            dir = Math.PI;
            setPosition(SIZE, SIZE);
            setVelocity(speed, dir);
            new ParticipantCountdownTimer(this, "up", 500);
        }
        else if ((side == 0 && corner == 0) && size == 0)
        {
            speed = 6;
            dir = 0;
            setPosition(0, 0);
            setVelocity(speed, dir);
            new ParticipantCountdownTimer(this, "down", 500);
        }
        else if ((side == 0 && corner == 1) && size == 0)
        {
            speed = 6;
            dir = 0;
            setPosition(0, SIZE);
            setVelocity(speed, dir);
            new ParticipantCountdownTimer(this, "up", 500);
        }

    }

    public int getAlienSize ()
    {
        return this.size;
    }

    @Override
    protected Shape getOutline ()
    {
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(20, 0);
        poly.lineTo(10, 8);
        poly.lineTo(-10, 8);
        poly.lineTo(-20, 0);
        poly.lineTo(20, 0);
        poly.lineTo(-20, 0);
        poly.lineTo(-10, -8);
        poly.lineTo(10, -8);
        poly.lineTo(-8, -8);
        poly.lineTo(-6, -15);
        poly.lineTo(6, -15);
        poly.lineTo(8, -8);
        poly.lineTo(10, -8);
        poly.closePath();

        // Scale to the desired size
        double scale = ASTEROID_SCALE[size];
        poly.transform(AffineTransform.getScaleInstance(scale, scale));

        outline = poly;
        return outline;
    }

    @Override
    public void collidedWith (Participant p)
    {
        if (!(p instanceof AlienBullet) && (p instanceof ShipDestroyer || p instanceof AsteroidDestroyer))
        {
            // Expire the ship from the game
            Participant.expire(this);
            clip.playBangAlienClip();

            // Tell the controller the alien was destroyed
            controller.alienDestroyed();
        }

    }

    @Override
    public Life getLife ()
    {
        return null;
    }

    /**
     * This method is invoked when a ParticipantCountdownTimer completes its countdown.
     */
    @Override
    public void countdownComplete (Object payload)
    {
        // Make the dust go away
        if (payload.equals("down"))
        {
            int choice = RANDOM.nextInt(2);
            if (choice == 0)
            {
                setVelocity(speed + 2, Math.PI / 2);
            }
            else
            {
                setVelocity(speed + 2, dir - 1);
            }
            new ParticipantCountdownTimer(this, "normUpNext", 1500 + RANDOM.nextInt(501) - RANDOM.nextInt(501));
        }
        else if (payload.equals("up"))
        {
            int choice = RANDOM.nextInt(2);
            if (choice == 0)
            {
                setVelocity(speed + 2, -Math.PI / 2);
            }
            else
            {
                setVelocity(speed + 2, dir + 1);
            }
            new ParticipantCountdownTimer(this, "normDownNext", 1500 + RANDOM.nextInt(501) - RANDOM.nextInt(501));
        }
        else if (payload.equals("normDownNext"))
        {
            setVelocity(speed, dir);
            new ParticipantCountdownTimer(this, "down", 1000 + RANDOM.nextInt(1501) - RANDOM.nextInt(501));
        }
        else if (payload.equals("normUpNext"))
        {
            setVelocity(speed, dir);
            new ParticipantCountdownTimer(this, "up", 1000 + RANDOM.nextInt(1501) - RANDOM.nextInt(501));
        }
    }
}
