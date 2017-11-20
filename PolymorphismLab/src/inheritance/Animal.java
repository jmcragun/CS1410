package inheritance;

/**
 * Represents an animal
 */
public class Animal
{
    /** Number of legs animal has */
    private int nLegs;
    
    /**
     * Creates an Animal that has the specified number of legs
     */
    public Animal (int nLegs)
    {
        this.nLegs = nLegs;
    }
    
    /**
     * Returns the number of legs this Animal has
     */
    public int getLegCount ()
    {
        return nLegs;
    }
    
    /**
     * Returns what this animal says when it speaks
     */
    public String speak ()
    {
        return "umm";
    }
    
    /**
     * Returns what this animal says when it shouts
     */
    public String shout ()
    {
        return speak().toUpperCase();
    }
}

