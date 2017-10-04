package cs1410;

public class Main
{
    @SuppressWarnings("unused")
    public static void main (String[] args)
    {
        // Create a Counter object with a delta of 5
        Counter c1 = new Counter(5);
        
        // Create another Counter object with a delta of 100
        Counter c2 = new Counter(100);
        
        // Get the current count from c1
        int count = c1.getCount();
        
        // Tick the first counter
        c1.tick();
        
        // Change the delta of the second counter and tick it
        c2.setDelta(10);
        c2.tick();

        // Assign c2 to c1
        c1 = c2;
        
        // Store a new Counter into c2
        c2 = new Counter(8);
    }
}
