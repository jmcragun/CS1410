package cs1410;

import static org.junit.Assert.*;
import org.junit.Test;

public class CacheTests
{
    @Test
    public void test ()
    {
        Cache c = new Cache("GCR5WK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("GCR5WK", c.getGcCode());
        assertEquals("Old Three Tooth", c.getTitle());
        assertEquals("geocadet", c.getOwner());
        if (3.5 != c.getDifficulty())
        {
            fail();
        }
        if (3 != c.getTerrain())
        {
            fail();
        }
        assertEquals("N40 45.850", c.getLatitude());
        assertEquals("W111 48.045", c.getLongitude());
        assertEquals("Old Three Tooth by geocadet", c.toString());

    }

    @Test(expected = IllegalArgumentException.class)
    public void test1 ()
    {
        new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\tlol I cannot into formatting");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test2 ()
    {
        new Cache("GCRQWK\t \tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test3 ()
    {
        new Cache("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test4 ()
    {
        new Cache("\t  \t \t \t  \t       \t    ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test5 ()
    {
        new Cache("GCrQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test6 ()
    {
        new Cache("GCR QWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test7 ()
    {
        new Cache("GC$QWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
    }
}
