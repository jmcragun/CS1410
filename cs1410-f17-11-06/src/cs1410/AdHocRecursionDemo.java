package cs1410;

/**
 * Examples of ad-hoc recursion
 */
public class AdHocRecursionDemo
{
    /**
     * Runs the method below.
     */
    public static void main (String[] args)
    {
        System.out.println("fact(7) = " + factorial(7));
    }
    
    /**
     * Returns n!.  Requires n >= 0
     */
    public static long factorial (long n)
    {
        if (n == 0)
        {
            return 1;
        }
        else
        {
            return n * factorial(n-1);
        }
    }
}
