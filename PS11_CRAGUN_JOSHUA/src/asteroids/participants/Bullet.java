package asteroids.participants;

import java.awt.Shape;
import asteroids.destroyers.AsteroidDestroyer;
import asteroids.game.Participant;
import static asteroids.game.Constants.*;
import java.awt.geom.*;
import asteroids.destroyers.*;
import asteroids.game.Controller;
import asteroids.game.ParticipantCountdownTimer;

public class Bullet  extends Participant implements AsteroidDestroyer
{
    public Bullet (double x, double y, Controller controller) {
        
    }
    @Override
    protected Shape getOutline ()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void collidedWith (Participant p)
    {
        // TODO Auto-generated method stub
        
    }

}
