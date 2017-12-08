package asteroids.participants;

import static asteroids.game.Constants.BULLET_SPEED;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import asteroids.destroyers.AsteroidDestroyer;
import asteroids.destroyers.ShipDestroyer;
import asteroids.game.Controller;
import asteroids.game.Participant;
import asteroids.game.ParticipantCountdownTimer;

public class AlienBullet extends Participant implements AsteroidDestroyer, ShipDestroyer
{

    /** The outline of the bullet */
    private Shape outline;

    public AlienBullet (double x, double y, double direction)
    {
        Ellipse2D.Double bullet = new Ellipse2D.Double(x, y, 5.0, 5.0);

        setRotation(0);
        setVelocity(BULLET_SPEED, direction);
        outline = bullet;

        // Schedule expiration
        new ParticipantCountdownTimer(this, "expire", 1125);
    }

    @Override
    protected Shape getOutline ()
    {
        return outline;
    }

    @Override
    public void collidedWith (Participant p)
    {
        if (!(p instanceof Alien) && (p instanceof ShipDestroyer || p instanceof AsteroidDestroyer))
        {
            // Expire the ship from the game
            Participant.expire(this);
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
        if (payload.equals("expire"))
        {
            Participant.expire(this);
        }
    }

}
