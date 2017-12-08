package sounds;

import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * Creates and plays sound clips
 */
public class SoundDemo
{
    /** Clips for each sound used in the game */
    private Clip bangAlienClip;
    private Clip bangLClip;
    private Clip bangMClip;
    private Clip bangSClip;
    private Clip bangShipClip;
    private Clip beat1Clip;
    private Clip beat2Clip;
    private Clip fireClip;
    private Clip saucerBClip;
    private Clip saucerSClip;
    private Clip thrustClip;

    /**
     * Creates the demo.
     */
    public SoundDemo ()
    {
        // We create the clips in advance so that there will be no delay
        // when we need to play them back. Note that the actual wav
        // files are stored in the "sounds" project.
        bangAlienClip = createClip("/sounds/bangAlienShip.wav");
        bangLClip = createClip("/sounds/bangLarge.wav");
        bangMClip = createClip("/sounds/bangMedium.wav");
        bangSClip = createClip("/sounds/bangSmall.wav");
        bangShipClip = createClip("/sounds/bangShip.wav");
        beat1Clip = createClip("/sounds/beat1.wav");
        beat2Clip = createClip("/sounds/beat2.wav");
        fireClip = createClip("/sounds/fire.wav");
        saucerBClip = createClip("/sounds/saucerBig.wav");
        saucerSClip = createClip("/sounds/saucerSmall.wav");
        thrustClip = createClip("/sounds/thrust.wav");
    }

    /**
     * Creates an audio clip from a sound file.
     */
    public Clip createClip (String soundFile)
    {
        // Opening the sound file this way will work no matter how the
        // project is exported. The only restriction is that the
        // sound files must be stored in a package.
        try (BufferedInputStream sound = new BufferedInputStream(getClass().getResourceAsStream(soundFile)))
        {
            // Create and return a Clip that will play a sound file. There are
            // various reasons that the creation attempt could fail. If it
            // fails, return null.
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            return clip;
        }
        catch (LineUnavailableException e)
        {
            return null;
        }
        catch (IOException e)
        {
            return null;
        }
        catch (UnsupportedAudioFileException e)
        {
            return null;
        }
    }
    
    /**
     * plays clip of alien ship being destroyed
     */
    public void playBangAlienClip ()
    {
        if (bangAlienClip != null)
        {
            if (bangAlienClip.isRunning())
            {
                bangAlienClip.stop();
            }
            bangAlienClip.setFramePosition(0);
            bangAlienClip.start();
        }
    }
    
    /**
     * plays clip of large asteroid being destroyed
     */
    public void playBangLClip ()
    {
        if (bangLClip != null)
        {
            if (bangLClip.isRunning())
            {
                bangLClip.stop();
            }
            bangLClip.setFramePosition(0);
            bangLClip.start();
        }
    }
    
    /**
     * plays clip of medium asteroid being destroyed
     */
    public void playBangMClip ()
    {
        if (bangMClip != null)
        {
            if (bangMClip.isRunning())
            {
                bangMClip.stop();
            }
            bangMClip.setFramePosition(0);
            bangMClip.start();
        }
    }
    
    /**
     * plays clip of small asteroid being destroyed
     */
    public void playBangSClip ()
    {
        if (bangSClip != null)
        {
            if (bangSClip.isRunning())
            {
                bangSClip.stop();
            }
            bangSClip.setFramePosition(0);
            bangSClip.start(); 
        }
    }
    
    /**
     * plays clip of player's ship being destroyed
     */
    public void playBangShipClip ()
    {
        if (bangShipClip != null)
        {
            if (bangShipClip.isRunning())
            {
                bangShipClip.stop();
            }
            bangShipClip.setFramePosition(0);
            bangShipClip.start();
        }
    }
    
    /**
     * plays the first beat that gets faster as more asteroids are destroyed
     */
    public void playBeat1Clip ()
    {
        if (beat1Clip != null)
        {
            if (beat1Clip.isRunning())
            {
                beat1Clip.stop();
            }
            beat1Clip.setFramePosition(0);
            beat1Clip.start(); 
        }
    }
    /**
     * plays the second beat that gets faster as more asteroids are destroyed
     */
    public void playBeat2Clip ()
    {
        if (beat2Clip != null)
        {
            if (beat2Clip.isRunning())
            {
                beat2Clip.stop();
            }
            beat2Clip.setFramePosition(0);
            beat2Clip.start(); 
        }
    }
    
    /**
     * plays clip of bullet firing
     */
    public void playFireClip ()
    {
        if (fireClip != null)
        {
            if (fireClip.isRunning())
            {
                fireClip.stop();
            }
            fireClip.setFramePosition(0);
            fireClip.start();
        }
    }
    
    /**
     * plays clip of a big saucer
     */
    public void playSaucerBClip ()
    {
        if (saucerBClip != null)
        {
            saucerBClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    /**
     * stops clip of a big saucer
     */
    public void stopSaucerBClip ()
    {
        if (saucerBClip != null)
        {
            saucerBClip.stop();
        }
    }
    
    /**
     * plays clip of a small saucer
     */
    public void playSaucerSClip ()
    {
        if (saucerSClip != null)
        {
            saucerSClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    /**
     * Stops clip of a small saucer
     */
    public void stopSaucerSClip ()
    {
        if (saucerSClip != null)
        {
            saucerSClip.stop();
        }
    }
    
    /**
     * plays clip of ship thrusters
     */
    public void playThrustClip ()
    {
        if (thrustClip != null)
        {
            thrustClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    /**
     * Stops clip of Ships thrusters
     */
    public void stopThrustClip ()
    {
        thrustClip.stop();
    }
}
