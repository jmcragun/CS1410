package inheritance;

/**
 * Represents a Dog
 */
public class Dog extends Animal
{
    /** The breed of this dog */
    private String breed;

    /**
     * Creates a Dog of the specified breed
     */
    public Dog (String breed)
    {
        // The constructor for a base class must begin by calling a constructor of the
        // super class. If no super constructor call is made, the call super() is
        // inserted automatically. This can cause a compilation error if the super
        // class has no zero-argument constructor. Try commenting out the call below
        // to see what happens.
        super(4);
        this.breed = breed;
    }

    /**
     * Returns the breed of this dog
     */
    public String getBreed ()
    {
        return breed;
    }

    /**
     * Returns what this dog says when it speaks
     */
    @Override
    public String speak ()
    {
        return "woof";
    }
}
