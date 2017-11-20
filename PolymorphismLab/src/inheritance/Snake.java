package inheritance;

/**
 * Represents a Snake
 */
public class Snake extends Animal
{
    /**
     * Creates a Snake
     */
    public Snake ()
    {
        // The constructor for a base class must begin by calling a constructor of the
        // super class. If no super constructor call is made, the call super() is
        // inserted automatically. This can cause a compilation error if the super
        // class has no zero-argument constructor. Try commenting out the call below
        // to see what happens.
        super(0);
    }
}
