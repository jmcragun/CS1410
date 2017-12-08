package asteroids.participants;

import static asteroids.game.Constants.ASTEROID_SCALE;
import static asteroids.game.Constants.RANDOM;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import asteroids.game.Participant;
import asteroids.game.ParticipantCountdownTimer;

public class AlienDebris extends Participant
{
    /** The outline of the ship */
    private Shape outline;

    private int variety;

    private int size;

    public AlienDebris (double x, double y, int speed, int size, int variety)
    {
        this.variety = variety;
        this.size = size;
        setVelocity(speed, RANDOM.nextDouble() * 2 * Math.PI);
        setRotation(2 * Math.PI * RANDOM.nextDouble());
        setPosition(x, y);

        // Schedule expiration
        new ParticipantCountdownTimer(this, "expire", 1333);
    }

    @Override
    protected Shape getOutline ()
    {
        if (variety > 13 || variety < 0)
        {
            return null;
        }
        Path2D.Double poly = new Path2D.Double();
        switch (variety)
        {
            case 1:
                poly.moveTo(20, 0);
                poly.lineTo(10, 8);
                poly.closePath();
                break;
            case 2:
                poly.moveTo(10, 8);
                poly.lineTo(-10, 8);
                poly.closePath();
                break;
            case 3:
                poly.moveTo(-10, 8);
                poly.lineTo(-20, 0);
                poly.closePath();
                break;
            case 4:
                poly.moveTo(-20, 0);
                poly.lineTo(20, 0);
                poly.closePath();
                break;
            case 5:
                poly.moveTo(20, 0);
                poly.lineTo(-20, 0);
                poly.closePath();
                break;
            case 6:
                poly.moveTo(-20, 0);
                poly.lineTo(-10, -8);
                poly.closePath();
                break;
            case 7:
                poly.moveTo(-10, -8);
                poly.lineTo(10, -8);
                poly.closePath();
                break;
            case 8:
                poly.moveTo(10, -8);
                poly.lineTo(-8, -8);
                poly.closePath();
                break;
            case 9:
                poly.moveTo(-8, -8);
                poly.lineTo(-6, -15);
                poly.closePath();
                break;
            case 10:
                poly.moveTo(-6, -15);
                poly.lineTo(6, -15);
                poly.closePath();
                break;
            case 11:
                poly.moveTo(6, -15);
                poly.lineTo(8, -8);
                poly.closePath();
                break;
            case 12:
                poly.moveTo(8, -8);
                poly.lineTo(10, -8);
                poly.closePath();
                break;
            case 13:
                poly.moveTo(10, -8);
                poly.moveTo(20, 0);
                poly.closePath();
                break;
        }
        // Scale to the desired size
        double scale = ASTEROID_SCALE[size];
        poly.transform(AffineTransform.getScaleInstance(scale - 0.25, scale - 0.25));
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
