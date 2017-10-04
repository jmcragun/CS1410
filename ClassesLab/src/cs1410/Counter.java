package cs1410;

/**
 * Provides Counter objects. A Counter has a current count and a delta, both integers. Each time a Counter is ticked,
 * its current count increases by delta.
 */
public class Counter
{
    /**
     * The current count of the counter. THIS IS AN INSTANCE VARIABLE.
     */
    private int currentCount;

    /**
     * The amount by which current count increases after each tick. THIS IS AN INSTANCE VARIABLE.
     */
    private int delta;

    /**
     * Creates a Counter with a current count of 1 that changes by changePerTick each time the Counter is ticked. THIS
     * IS A CONSTRUCTOR.
     */
    public Counter (int changePerTick)
    {
        currentCount = 1;
        delta = changePerTick;
    }

    /**
     * Gets the current count of this Counter. THIS IS A METHOD.
     */
    public int getCount ()
    {
        return currentCount;
    }

    /**
     * Gets the amount by which the current count changes per tick. THIS IS A METHOD.
     */
    public int getDelta ()
    {
        return delta;
    }

    /**
     * Changes this Counter's delta. THIS IS A METHOD.
     */
    public void setDelta (int newDelta)
    {
        delta = newDelta;
    }

    /**
     * Ticks this Counter, increasing its current count by the its delta value. THIS IS A METHOD.
     */
    public void tick ()
    {
        currentCount += delta;
    }
}
