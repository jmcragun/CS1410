package asteroids.participants;

import static asteroids.game.Constants.*;
import java.awt.Shape;
import java.awt.geom.*;
import asteroids.destroyers.*;
import asteroids.game.Controller;
import asteroids.game.Participant;
import sounds.SoundDemo;

/**
 * Represents ships
 */
public class Ship extends Participant implements AsteroidDestroyer
{
    /** The outline of the ship */
    private Shape outline;

    /** Game controller */
    private Controller controller;

    /** direction the ship is pointing */
    private double theta;

    /** Sound clips */
    private SoundDemo clip;

    /**
     * Constructs a ship at the specified coordinates that is pointed in the given direction.
     */
    public Ship (int x, int y, double direction, Controller controller)
    {
        this.controller = controller;
        setPosition(x, y);
        setRotation(direction);
        this.theta = Math.PI / -2;

        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(21, 0);
        poly.lineTo(-21, 12);
        poly.lineTo(-14, 10);
        poly.lineTo(-14, -10);
        poly.lineTo(-21, -12);
        poly.closePath();
        outline = poly;
        // Initialize SoundDemo
        clip = new SoundDemo();
    }

    /**
     * Returns the x-coordinate of the point on the screen where the ship's nose is located.
     */
    public double getXNose ()
    {
        Point2D.Double point = new Point2D.Double(20, 0);
        transformPoint(point);
        return point.getX();
    }

    /**
     * Returns the x-coordinate of the point on the screen where the ship's nose is located.
     */
    public double getYNose ()
    {
        Point2D.Double point = new Point2D.Double(20, 0);
        transformPoint(point);
        return point.getY();
    }

    /**
     * Changes the ship outline to one with the backburner flaming
     */
    public void switchOutlineFire ()
    {
        if (this != null)
        {
            Path2D.Double poly = new Path2D.Double();
            poly.moveTo(21, 0);
            poly.lineTo(-21, 12);
            poly.lineTo(-14, 10);
            poly.lineTo(-14, -10);
            poly.lineTo(-25, 0);
            poly.lineTo(-14, 10);
            poly.lineTo(-14, -10);
            poly.lineTo(-21, -12);

            poly.closePath();

            this.outline = poly;
        }
    }

    /**
     * Changes the ship outline back to the original ship shape
     */
    public void switchOutlineNormal ()
    {
        if (this != null)
        {
            Path2D.Double poly = new Path2D.Double();
            poly.moveTo(21, 0);
            poly.lineTo(-21, 12);
            poly.lineTo(-14, 10);
            poly.lineTo(-14, -10);
            poly.lineTo(-21, -12);
            poly.closePath();
            this.outline = poly;
        }
    }

    @Override
    protected Shape getOutline ()
    {
        return outline;
    }

    /**
     * Customizes the base move method by imposing friction
     */
    @Override
    public void move ()
    {
        applyFriction(SHIP_FRICTION);
        super.move();
    }

    /**
     * Turns right by Pi/16 radians
     */
    public void turnRight ()
    {
        rotate(Math.PI / 16);
        this.theta += Math.PI / 16;
    }

    /**
     * Turns left by Pi/16 radians
     */
    public void turnLeft ()
    {
        rotate(-Math.PI / 16);
        this.theta -= Math.PI / 16;
    }

    /**
     * Accelerates by SHIP_ACCELERATION
     */
    public void accelerate ()
    {
        accelerate(SHIP_ACCELERATION);
    }

    /**
     * returns the direction the ship nose it pointed
     * 
     * @return
     */
    public double whatIsTheta ()
    {
        return theta;
    }

    /**
     * When a Ship collides with a ShipDestroyer, it expires
     */
    @Override
    public void collidedWith (Participant p)
    {

        if (p instanceof ShipDestroyer)
        {
            // Expire the ship from the game
            Participant.expire(this);

            // Tell the controller the ship was destroyed
            controller.shipDestroyed();
            clip.playBangShipClip();
        }
        
    }

    @Override
    public Life getLife ()
    {
        return null;
    }
}
