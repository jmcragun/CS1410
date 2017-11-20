package interfaces;

/**
 * A C object knows how to sing
 */
public class C implements Singer
{
    /** What the object says when it sings */
    private String verse;
    
    /**
     * Creates a C object that sings the verse
     */
    public C (String verse)
    {
        this.verse = verse;
    }
    
    @Override
    public String sing ()
    {
        return verse;
    }
}
