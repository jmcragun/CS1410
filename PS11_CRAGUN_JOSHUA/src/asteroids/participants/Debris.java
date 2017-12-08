package asteroids.participants;

import static asteroids.game.Constants.*;
import java.awt.Shape;
import java.awt.geom.*;
import asteroids.game.Controller;
import asteroids.game.Participant;
import asteroids.game.ParticipantCountdownTimer;

public class Debris extends Participant
{
    /** The outline of the ship */
    private Shape outline;

    private int variety;

    public Debris (int speed, int variety, Controller controller)
    {
        this.variety = variety;
        setVelocity(speed, RANDOM.nextDouble() * 2 * Math.PI);
        setRotation(2 * Math.PI * RANDOM.nextDouble());
        setPosition(controller.getShip().getX(), controller.getShip().getY());

        // Schedule expiration
        new ParticipantCountdownTimer(this, "expire", 1333);
    }

    @Override
    protected Shape getOutline ()
    {
        if (variety > 6 || variety < 0)
        {
            return null;
        }
        Path2D.Double poly = new Path2D.Double();
        switch (variety)
        {
            case 0:
                poly.moveTo(21, 0);
                poly.lineTo(0, 6);
                break;
            case 1:
                poly.moveTo(-21, 12);
                poly.lineTo(-14, 10);
                break;
            case 2:
                poly.moveTo(-14, 10);
                poly.lineTo(-14, -10);
                break;
            case 3:
                poly.moveTo(-14, -10);
                poly.lineTo(-21, -12);
                break;
            case 4:
                poly.moveTo(-21, -12);
                poly.lineTo(0, -6);
                break;
            case 5:
                poly.moveTo(0, 6);
                poly.lineTo(-21, 12);
                break;
            case 6:
                poly.moveTo(0, -6);
                poly.lineTo(21, 0);
                break;
        }
        poly.closePath();
        outline = poly;
        return outline;
    }

    @Override
    public void collidedWith (Participant p)
    {
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
        if (payload.equals("expire"))
        {
            Participant.expire(this);
        }
    }
}
