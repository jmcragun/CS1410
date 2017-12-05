package asteroids.participants;

import static asteroids.game.Constants.RANDOM;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import asteroids.game.Participant;
import asteroids.game.ParticipantCountdownTimer;

public class Dust extends Participant
{

    /** The shape of the dust particle */
    private Shape particle;

    public Dust (double x, double y, int speed, int variety)
    {
        setPosition(x, y);
        setVelocity(speed, RANDOM.nextDouble() * 2 * Math.PI);
        setRotation(2 * Math.PI * RANDOM.nextDouble());
        createDustOutline(variety);

        // Schedule expiration
        new ParticipantCountdownTimer(this, "expire", 1333);
    }

    public Dust (double x, double y)
    {
        setPosition(x, y);
        setVelocity(2, RANDOM.nextDouble() * 2 * Math.PI);
        setRotation(2 * Math.PI * RANDOM.nextDouble());
        createDustOutline();
        
        // Schedule expiration
        new ParticipantCountdownTimer(this, "expire", 1450);
    }

    /**
     * Creates the outline of the particle based on its variety.
     */
    private void createDustOutline (int variety)
    {
        // This will contain the outline
        Path2D.Double poly = new Path2D.Double();

        // Fill out according to variety
        if (variety == 0)
        {
            poly.moveTo(0, -30);
            poly.lineTo(28, -15);
            poly.lineTo(20, 20);
            poly.lineTo(4, 8);
            poly.lineTo(-1, 30);
            poly.lineTo(-12, 15);
            poly.lineTo(-5, 2);
            poly.lineTo(-25, 7);
            poly.lineTo(-10, -25);
            poly.closePath();
        }
        else if (variety == 1)
        {
            poly.moveTo(10, -28);
            poly.lineTo(7, -16);
            poly.lineTo(30, -9);
            poly.lineTo(30, 9);
            poly.lineTo(10, 13);
            poly.lineTo(5, 30);
            poly.lineTo(-8, 28);
            poly.lineTo(-6, 6);
            poly.lineTo(-27, 12);
            poly.lineTo(-30, -11);
            poly.lineTo(-6, -15);
            poly.lineTo(-6, -28);
            poly.closePath();
        }
        else if (variety == 2)
        {
            poly.moveTo(10, -30);
            poly.lineTo(30, 0);
            poly.lineTo(15, 30);
            poly.lineTo(0, 15);
            poly.lineTo(-15, 30);
            poly.lineTo(-30, 0);
            poly.lineTo(-10, -30);
            poly.closePath();
        }
        else
        {
            poly.moveTo(30, -18);
            poly.lineTo(5, 5);
            poly.lineTo(30, 15);
            poly.lineTo(15, 30);
            poly.lineTo(0, 25);
            poly.lineTo(-15, 30);
            poly.lineTo(-25, 8);
            poly.lineTo(-10, -25);
            poly.lineTo(0, -30);
            poly.lineTo(10, -30);
            poly.closePath();
        }
        // Scale to the desired size
        double scale = .15;
        poly.transform(AffineTransform.getScaleInstance(scale, scale));

        // Save the outline
        particle = poly;
    }

    /**
     * Creates the outline of the particle based on its variety.
     */
    private void createDustOutline ()
    {
        
    }

    @Override
    protected Shape getOutline ()
    {
        return particle;
    }

    @Override
    public void collidedWith (Participant p)
    {
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

    @Override
    public Life getLife ()
    {
        return null;
    }

}
