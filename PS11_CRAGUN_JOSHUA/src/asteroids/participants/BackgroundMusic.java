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

    private double delta;

    private final double delta2 = 5.0f;

    public BackgroundMusic ()
    {
        clip = new SoundDemo();
        this.musicIsPlaying = false;
        delta = 0;
    }

    public void stop ()
    {
        this.musicIsPlaying = false;
        delta = 0;
    }

    public void pause ()
    {
        this.musicIsPlaying = false;
    }

    public void resume ()
    {
        this.musicIsPlaying = true;
        new ParticipantCountdownTimer(this, "Beat1", 1000);
    }

    public void begin ()
    {
        musicIsPlaying = false;
        delta = 0;
        new ParticipantCountdownTimer(this, "Begin", 1000);
    }

    public void remove ()
    {
        Participant.expire(this);
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
            this.musicIsPlaying = true;
            clip.playBeat1Clip();
            new ParticipantCountdownTimer(this, "Beat2", 1000);
        }
        else if (payload.equals("Beat1"))
        {
            if (this.musicIsPlaying && ((1000 - delta) > 500))
            {
                clip.playBeat1Clip();
                new ParticipantCountdownTimer(this, "Beat2", (int) (1000 - delta));
                delta += delta2;
            }
            else if (this.musicIsPlaying)
            {
                clip.playBeat1Clip();
                new ParticipantCountdownTimer(this, "Beat2", (int) (1000 - delta));
            }
        }
        else if (payload.equals("Beat2"))
        {
            if (this.musicIsPlaying && ((1000 - delta) > 500))
            {
                clip.playBeat2Clip();
                new ParticipantCountdownTimer(this, "Beat1", (int) (1000 - delta));
                delta += delta2;
            }
            else if (this.musicIsPlaying)
            {
                clip.playBeat2Clip();
                new ParticipantCountdownTimer(this, "Beat1", (int) (1000 - delta));
            }
        }
    }
}
