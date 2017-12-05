package asteroids.participants;

import java.awt.Shape;
import java.awt.geom.*;
import asteroids.game.Participant;

public class Life extends Participant
{
    /** Shape of the life */
    private Shape outline;
    /** The controller's ship */
    private int life;

    public Life (int x, int y, int life)
    {
        this.life = life;
        setPosition(x, y);

        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(0, -21);
        poly.lineTo(12, 21);
        poly.lineTo(10, 14);
        poly.lineTo(-10, 14);
        poly.lineTo(-12, 21);
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
    }

    /** If a life is lost, expire the proper life representation */
    public void loseLife (int life)
    {
        if (this.life == life)
        {
            Participant.expire(this);
        }
    }

    @Override
    public Life getLife ()
    {
        return this;
    }
}
