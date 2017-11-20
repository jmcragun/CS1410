package inheritance;

/**
 * Represents a Cat
 */
public class Cat extends Animal
{
    /** The name of this Cat */
    private String name;
    
    /**
     * Creates a Cat with the provided name
     */
    public Cat (String name)
    {
        // The constructor for a base class must begin by calling a constructor of the
        // super class.  If no super constructor call is made, the call super() is 
        // inserted automatically.  This can cause a compilation error if the super
        // class has no zero-argument constructor.  Try commenting out the call below
        // to see what happens.
        super(4);
        this.name = name;
    }
    
    /**
     * Returns the name of this cat
     */
    public String getName ()
    {
        return name;
    }
    
    /**
     * Returns what this cat says when it speaks
     */
    @Override
    public String speak ()
    {
        return "meow";
    }
}
