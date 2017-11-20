package interfaces;

/**
 * An A object knows how to speak and sing.
 */
public class A implements Speaker, Singer
{
    /** What the object says when it speaks */
    private String greeting;
    
    /** What the object says when it sings */
    private String verse;
    
    /** 
     * Creates an A object that speaks the greeting and sings the verse
     */
    public A (String greeting, String verse)
    {
        this.greeting = greeting;
        this.verse = verse;
    }
    
    @Override
    public String sing ()
    {
        return verse;
    }

    @Override
    public String speak ()
    {
        return greeting;
    } 
}
