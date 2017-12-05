package asteroids.participants;

import static asteroids.game.Constants.*;
import java.awt.Shape;
import java.awt.geom.*;
import asteroids.destroyers.*;
import asteroids.game.Participant;

public class Alien extends Participant implements AsteroidDestroyer
{

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

    @Override
    public Life getLife ()
    {
        return null;
    }

}
