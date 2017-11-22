package asteroids.participants;

import static asteroids.game.Constants.*;
import java.awt.Shape;
import java.awt.geom.*;
import asteroids.destroyers.*;
import asteroids.game.Participant;

public class Mine extends Participant implements AsteroidDestroyer
{
    /** The outline of the asteroid */
    private Shape outline;

    /**
     * Help I don't know what I'm doing
     */
    public Mine ()
    {
        setPosition(200, 200);
        setRotation(2 * Math.PI * RANDOM.nextDouble());
        setVelocity(0, 0);

        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(0, 0);
        poly.lineTo(-21, 12);
        poly.lineTo(-14, 10);
        poly.lineTo(-14, -10);
        poly.lineTo(-21, -12);
        poly.closePath();
        outline = poly;
    }

    @Override
    protected Shape getOutline ()
    {
        return outline;
    }

    @Override
    public void collidedWith (Participant p)
    {
        // TODO Auto-generated method stub

    }

}
