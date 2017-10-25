package cs1410;

import java.util.Scanner;

/**
 * Represents a variety of information about a geocache. A geocache has a title, an owner, a difficulty rating, a
 * terrain rating, a GC code, a latitude, and a longitude.
 */
public class Cache
{
    // TODO: Put representation here
    private String gcCode;
    private String title;
    private String owner;
    private double difficulty;
    private double terrain;
    private double latitude;
    private double longitude;

    /**
     * Creates a Cache from a string that consists of these seven cache attributes: the GC code, the title, the owner,
     * the difficulty rating, the terrain rating, the latitude, and the longitude, in that order, separated by single
     * TAB ('\t') characters.
     * 
     * If any of the following problems are present, throws an IllegalArgumentException:
     * <ul>
     * <li>Fewer than seven attributes</li> (done)
     * <li>More than seven attributes</li> (done)
     * <li>A GC code that is anything other than "GC" followed by one or more upper-case letters and/or digits</li>
     * <li>A difficulty or terrain rating that parses to anything other than the doubles 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5,
     * or 5.</li>
     * <li>A title, owner, latitude, or longitude that consists only of white space</li> (done)
     */
    public Cache (String attributes)
    {
        // TODO: Implement
        try
        {
            Scanner scnr = new Scanner(attributes);
            gcCode = scnr.next();
            title = scnr.next();
            owner = scnr.next();
            difficulty = Double.parseDouble(scnr.next());
            terrain = Double.parseDouble(scnr.next());
            latitude = Double.parseDouble(scnr.next());
            longitude = Double.parseDouble(scnr.next());
            if (scnr.hasNext())
            {
                scnr.close();
                throw new IllegalArgumentException();
            }
            scnr.close();
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException();
        }
        if (!verifyGcCode(gcCode)) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Private helper method that makes sure the code is "GC" followed by one or more upper-case letters and/or digits.
     * 
     * @param code
     * @return
     */
    private static boolean verifyGcCode (String code)
    {
        String front = code.substring(0, 2);
        if (!front.equals("GC"))
        {
            return false;
        }
        if (!code.matches("[A-Z]|[0-9]"))
        {
            return false;
        }
        return true;
    }

    /**
     * Converts this cache to a string
     */
    public String toString ()
    {
        return getTitle() + " by " + getOwner();
    }

    /**
     * Returns the owner of this cache
     */
    public String getOwner ()
    {
        // TODO: Implement
        return "";
    }

    /**
     * Returns the title of this cache
     */
    public String getTitle ()
    {
        // TODO: Implement
        return "";
    }

    /**
     * Returns the difficulty rating of this cache
     */
    public double getDifficulty ()
    {
        // TODO: Implement
        return 1.0;
    }

    /**
     * Returns the terrain rating of this cache
     */
    public double getTerrain ()
    {
        // TODO: Implement
        return 1.0;
    }

    /**
     * Returns the GC code of this cache
     */
    public String getGcCode ()
    {
        // TODO: Implement
        return "";
    }

    /**
     * Returns the latitude of this cache
     */
    public String getLatitude ()
    {
        // TODO: Implement
        return "";
    }

    /**
     * Returns the longitude of this cache
     */
    public String getLongitude ()
    {
        // TODO: Implement
        return "";
    }
}
