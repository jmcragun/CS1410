package cs1410;

import static org.junit.Assert.*;
import org.junit.Test;

public class CacheTests
{
    /**
     * An example test for Cache objects
     */
    @Test
    public void test ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("GCRQWK", c.getGcCode());
    }
}
