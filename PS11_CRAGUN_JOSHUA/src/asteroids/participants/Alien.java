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

    public Alien (int size, Controller controller)
    {
        this.controller = controller;
        int side = RANDOM.nextInt(2);
        this.size = size;
        // Initialize SoundDemo
        clip = new SoundDemo();
        if (side == 1 && size == 1)
        {
            setPosition(SIZE, 0);
            setVelocity(4, Math.PI);
            new ParticipantCountdownTimer(this, "down", 500);
        }
        else if (side == 0 && size == 1)
        {
            setPosition(SIZE, SIZE);
            setVelocity(4, Math.PI);
            new ParticipantCountdownTimer(this, "up", 500);
        }

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
        if (p instanceof ShipDestroyer || p instanceof AsteroidDestroyer)
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
            setVelocity(6, Math.PI - 0.75 - ((double) RANDOM.nextInt(51) / 100.0));
            new ParticipantCountdownTimer(this, "normUpNext", 1000 + RANDOM.nextInt(501) - RANDOM.nextInt(501));
        }
        else if (payload.equals("up"))
        {
            setVelocity(6, Math.PI + 0.75 + ((double) RANDOM.nextInt(51) / 100.0));
            new ParticipantCountdownTimer(this, "normDownNext", 1000 + RANDOM.nextInt(501) - RANDOM.nextInt(501));
        }
        else if (payload.equals("normDownNext"))
        {
            setVelocity(4, Math.PI);
            new ParticipantCountdownTimer(this, "down", 1000 + RANDOM.nextInt(1501) - RANDOM.nextInt(501));
        }
        else if (payload.equals("normUpNext"))
        {
            setVelocity(4, Math.PI);
            new ParticipantCountdownTimer(this, "up", 1000 + RANDOM.nextInt(1501) - RANDOM.nextInt(501));
        }
    }
}
