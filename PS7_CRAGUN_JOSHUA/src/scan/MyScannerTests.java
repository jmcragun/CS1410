package scan;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyScannerTests
{
    @Test
    public void test ()
    {
        MyScanner myScnr = new MyScanner("This is a test");
        Scanner scnr = new Scanner("This is a test");
        assertEquals(myScnr.next(), scnr.next());
        scnr.close();
    }

    @Test
    public void test2 ()
    {
        MyScanner myScnr = new MyScanner("This 1 is a test");
        Scanner scnr = new Scanner("This 1 is a test");
        myScnr.next();
        scnr.next();
        assertEquals(myScnr.nextInt(), scnr.nextInt());
        scnr.close();
    }

    @Test(expected = NoSuchElementException.class)
    public void test3 ()
    {
        MyScanner myScnr = new MyScanner("");
        myScnr.next();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void test4 ()
    {
        MyScanner myScnr = new MyScanner(" \t\n");
        myScnr.next();
    }
    
    @Test(expected = InputMismatchException.class)
    public void test5 ()
    {
        MyScanner myScnr = new MyScanner("apple banana");
        myScnr.nextInt();
    }
    
    @Test
    public void test6 ()
    {
        MyScanner myScnr = new MyScanner("a b c");
        myScnr.next();
        assertTrue(myScnr.hasNext());
        myScnr.next();
        myScnr.next();
        assertFalse(myScnr.hasNext());
    }
    
    @Test
    public void test7 ()
    {
        MyScanner myScnr = new MyScanner("1 b c");
        assertTrue(myScnr.hasNextInt());
        myScnr.next();
        assertFalse(myScnr.hasNextInt());
    }
}
