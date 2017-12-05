package asteroids.participants;

import java.awt.Shape;
import asteroids.game.Participant;
import static asteroids.game.Constants.*;
import java.awt.geom.*;
import asteroids.destroyers.*;
import asteroids.game.Controller;
import asteroids.game.ParticipantCountdownTimer;

public class Bullet extends Participant implements AsteroidDestroyer
{
    /** The outline of the ship */
    private Shape outline;

    /** Game controller */
    private Controller controller;

    public Bullet (double x, double y, Controller controller)
    {
        this.controller = controller;
        Ellipse2D.Double bullet = new Ellipse2D.Double(x, y, 2.5, 2.5);

        setRotation(0);
        setVelocity(BULLET_SPEED, this.controller.getShip().whatIsTheta());
        outline = bullet;

        // Schedule expiration
        new ParticipantCountdownTimer(this, "expire", 1250);
    }

    @Override
    protected Shape getOutline ()
    {
        return outline;
    }

    @Override
    public void collidedWith (Participant p)
    {
        if (p instanceof ShipDestroyer)
        {
            // Expire the bullet from the game
            Participant.expire(this);
        }
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
