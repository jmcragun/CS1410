package asteroids.participants;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import asteroids.game.Participant;
import asteroids.game.ParticipantCountdownTimer;
import sounds.SoundDemo;

public class BackgroundMusic extends Participant
{
    /** Sound clips */
    private SoundDemo clip;

    /** Indicates if music should be playing */
    private boolean musicIsPlaying;

    /** Indicates if the music should be fast */
    private boolean musicIsFast;

    /** how long before the music starts to speed up */
    private final int SPEED_UP = 20000;

    public BackgroundMusic ()
    {
        clip = new SoundDemo();
        musicIsPlaying = false;
        musicIsFast = false;
        new ParticipantCountdownTimer(this, "Begin", 1000);
        new ParticipantCountdownTimer(this, "Speed up", SPEED_UP);
    }

    public void stop ()
    {
        musicIsPlaying = false;
        musicIsFast = false;
    }

    public void pause ()
    {
        musicIsPlaying = false;
    }

    public void resume ()
    {
        musicIsPlaying = true;
        new ParticipantCountdownTimer(this, "Beat1", 1000);
    }

    public void begin ()
    {
        musicIsPlaying = false;
        musicIsFast = false;
        new ParticipantCountdownTimer(this, "Begin", 1000);
        new ParticipantCountdownTimer(this, "Speed up", SPEED_UP);
    }

    @Override
    protected Shape getOutline ()
    {
        Ellipse2D.Double dot = new Ellipse2D.Double(0, 0, 1.0, 1.0);
        return dot;
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
        if (payload.equals("Begin"))
        {
            musicIsPlaying = true;
            clip.playBeat1Clip();
            new ParticipantCountdownTimer(this, "Beat2", 1000);
        }
        else if (payload.equals("Beat1"))
        {
            if (musicIsPlaying && musicIsFast)
            {
                clip.playBeat1Clip();
                new ParticipantCountdownTimer(this, "Beat2", 500);
            }
            else if (musicIsPlaying)
            {
                clip.playBeat1Clip();
                new ParticipantCountdownTimer(this, "Beat2", 1000);
            }
        }
        else if (payload.equals("Beat2"))
        {
            if (musicIsPlaying && musicIsFast)
            {
                clip.playBeat2Clip();
                new ParticipantCountdownTimer(this, "Beat1", 500);
            }
            else if (musicIsPlaying)
            {
                clip.playBeat2Clip();
                new ParticipantCountdownTimer(this, "Beat1", 1000);
            }
        }
        else if (payload.equals("Speed up"))
        {
            musicIsFast = true;
        }
    }
}
