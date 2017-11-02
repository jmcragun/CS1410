package scan;

import java.util.NoSuchElementException;
import java.util.InputMismatchException;

/**
 * My personal attempt at recreating certain aspects of the Scanner object. This class should: Be called MyScanner and
 * be in a package called scan Provide a constructor that takes a String as a parameter Provide four methods: 1.)
 * hasNext(), which takes no parameters and returns a boolean 2.) next(), which takes no parameters and returns a String
 * 3.) hasNextInt(), which takes no parameters and returns a boolean 4.) nextInt(), which takes no parameters and
 * returns an int
 * 
 * @author Joshua Cragun
 *
 */
public class MyScanner
{
    // MyScanner will rely on the following instance variables
    // tokens will store a String that has been decomposed into an array of strings
    private String[] tokens;
    private int used;
    private String temp;
    private int tempInt;

    /**
     * This is the MyScanner constructor. Decomposes input into tokens and initializes used to 0.
     * 
     * @param input
     */
    public MyScanner (String input)
    {
        tokens = input.trim().split("\\s+");
        used = 0;
    }
    
    /**
     * Returns true if this scanner has another token in its input.
     * 
     * @return
     */
    public boolean hasNext ()
    {
        if (used < tokens.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Finds and returns the next complete token from this scanner.
     * 
     * @return
     */
    public String next ()
    {
        try
        {
            temp = tokens[used];
            used++;
            if (temp.equals(""))
            {
                throw new ArrayIndexOutOfBoundsException();
            }
            return temp;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new NoSuchElementException();
        }
    }
    
    /**
     * Returns true if the next token in this scanner's input can be interpreted as an int value using the nextInt()
     * method.
     * 
     * @return
     */
    public boolean hasNextInt ()
    {
        try
        {
            this.nextInt();
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Scans the next token of the input as an int. Returns int if token passes the scan, otherwise throws and
     * exception.
     * 
     * @return
     */
    public int nextInt ()
    {
        try
        {
            tempInt = Integer.parseInt(tokens[used]);
            used++;
            return tempInt;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new NoSuchElementException();
        }
        catch (NumberFormatException e)
        {
            throw new InputMismatchException();
        }
    }
}
