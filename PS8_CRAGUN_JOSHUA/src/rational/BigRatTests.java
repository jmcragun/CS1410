package rational;

import static org.junit.Assert.*;
import java.math.BigInteger;
import org.junit.Test;

public class BigRatTests
{
    @Test
    public void testConstructor1 ()
    {
        BigRat r = new BigRat();
        assertEquals("0", r.toString());
        BigRat r2 = new BigRat();
        assertEquals("0", r2.toString());
    }

    @Test
    public void testConstructor2 ()
    {
        BigRat r = new BigRat(new BigInteger("5"));
        assertEquals("5", r.toString());
        BigRat r2 = new BigRat(5);
        assertEquals("5", r2.toString());
    }

    @Test
    public void testConstructor3 ()
    {
        BigRat r1 = new BigRat(new BigInteger("1"), new BigInteger("2"));
        assertEquals("1/2", r1.toString());

        BigRat r2 = new BigRat(new BigInteger("5"), new BigInteger("15"));
        assertEquals("1/3", r2.toString());

        BigRat r3 = new BigRat(new BigInteger("-4"), new BigInteger("-2"));
        assertEquals("2", r3.toString());

        BigRat r4 = new BigRat(new BigInteger("6"), new BigInteger("-8"));
        assertEquals("-3/4", r4.toString());

        BigRat r5 = new BigRat(new BigInteger("3"), new BigInteger("-15"));
        assertEquals("-1/5", r5.toString());
        
        try {
            new BigRat(new BigInteger("4"), new BigInteger("0"));
            fail("No exception thrown");
        }
        catch (IllegalArgumentException e)
        {           
        }
        
        BigRat r6 = new BigRat(1, 2);
        assertEquals("1/2", r6.toString());

        BigRat r7 = new BigRat(5, 15);
        assertEquals("1/3", r7.toString());

        BigRat r8 = new BigRat(-4, -2);
        assertEquals("2", r8.toString());

        BigRat r9 = new BigRat(6, -8);
        assertEquals("-3/4", r9.toString());

        BigRat r10 = new BigRat(3, -15);
        assertEquals("-1/5", r10.toString());
        
        try {
            new BigRat(4, 0);
            fail("No exception thrown");
        }
        catch (IllegalArgumentException e)
        {           
        }
    }

    @Test
    public void testAdd ()
    {
        BigRat r1 = new BigRat(2,5);
        BigRat r2 = new BigRat(3,4);
        assertEquals("23/20", r1.add(r2).toString());
        
        r1 = new BigRat(1, 7);
        r2 = new BigRat(-1, 5);
        assertEquals("-2/35", r1.add(r2).toString());
        
        BigRat r3 = new BigRat(new BigInteger("2"), new BigInteger("5"));
        BigRat r4 = new BigRat(new BigInteger("3"), new BigInteger("4"));
        assertEquals("23/20", r3.add(r4).toString());
        
        r3 = new BigRat(new BigInteger("1"), new BigInteger("7"));
        r4 = new BigRat(new BigInteger("-1"), new BigInteger("5"));
        assertEquals("-2/35", r3.add(r4).toString());
    }

    @Test
    public void testSub ()
    {
        BigRat r1 = new BigRat(2,5);
        BigRat r2 = new BigRat(3,4);
        assertEquals("-7/20", r1.sub(r2).toString());
        
        r1 = new BigRat(1, 7);
        r2 = new BigRat(-1, 5);
        assertEquals("12/35", r1.sub(r2).toString());
        
        BigRat r3 = new BigRat(new BigInteger("2"), new BigInteger("5"));
        BigRat r4 = new BigRat(new BigInteger("3"), new BigInteger("4"));
        assertEquals("-7/20", r3.sub(r4).toString());
        
        r3 = new BigRat(new BigInteger("1"), new BigInteger("7"));
        r4 = new BigRat(new BigInteger("-1"), new BigInteger("5"));
        assertEquals("12/35", r3.sub(r4).toString());
    }

    @Test
    public void testMul ()
    {
        BigRat r1 = new BigRat(2,5);
        BigRat r2 = new BigRat(3,4);
        assertEquals("3/10", r1.mul(r2).toString());
        
        r1 = new BigRat(1, 7);
        r2 = new BigRat(-1, 5);
        assertEquals("-1/35", r1.mul(r2).toString());
        
        BigRat r3 = new BigRat(new BigInteger("2"), new BigInteger("5"));
        BigRat r4 = new BigRat(new BigInteger("3"), new BigInteger("4"));
        assertEquals("3/10", r3.mul(r4).toString());
        
        r3 = new BigRat(new BigInteger("1"), new BigInteger("7"));
        r4 = new BigRat(new BigInteger("-1"), new BigInteger("5"));
        assertEquals("-1/35", r3.mul(r4).toString());
    }

    @Test
    public void testDiv ()
    {
        BigRat r1 = new BigRat(2,5);
        BigRat r2 = new BigRat(3,4);
        assertEquals("8/15", r1.div(r2).toString());
        
        r1 = new BigRat(1, 7);
        r2 = new BigRat(-1, 5);
        assertEquals("-5/7", r1.div(r2).toString());
        
        try {
            r1 = new BigRat(3,4);
            r2 = new BigRat(0);
            r1.div(r2);
            fail("No exception thrown");
        }
        catch (IllegalArgumentException e)
        {
        }
        
        BigRat r3 = new BigRat(new BigInteger("2"), new BigInteger("5"));
        BigRat r4 = new BigRat(new BigInteger("3"), new BigInteger("4"));
        assertEquals("8/15", r3.div(r4).toString());
        
        r3 = new BigRat(new BigInteger("1"), new BigInteger("7"));
        r4 = new BigRat(new BigInteger("-1"), new BigInteger("5"));
        assertEquals("-5/7", r3.div(r4).toString());
        
        try {
            r3 = new BigRat(new BigInteger("3"), new BigInteger("4"));
            r4 = new BigRat(new BigInteger("0"));
            r3.div(r4);
            fail("No exception thrown");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    @Test
    public void testCompareTo ()
    {
        BigRat r1 = new BigRat(3,4);
        BigRat r2 = new BigRat(6,8);
        BigRat r3 = new BigRat(1,2);
        assertEquals(0, r1.compareTo(r2));
        assertTrue(r1.compareTo(r3) > 0);
        assertTrue(r3.compareTo(r1) < 0);
        
        BigRat r4 = new BigRat(new BigInteger("3"), new BigInteger("4"));
        BigRat r5 = new BigRat(new BigInteger("6"), new BigInteger("8"));
        BigRat r6 = new BigRat(new BigInteger("1"), new BigInteger("2"));
        assertEquals(0, r4.compareTo(r5));
        assertTrue(r4.compareTo(r6) > 0);
        assertTrue(r6.compareTo(r4) < 0);
    }
    
    @Test
    public void testBigExample ()
    {
        BigRat r1 = new BigRat(
                new BigInteger("987876765674565435423544657879080989878678676"),
                new BigInteger("89787676564654675786897988908987786785764654645675"));
        BigRat r2 = r1.div(r1);
        assertEquals("1", r2.toString());
    }
}
