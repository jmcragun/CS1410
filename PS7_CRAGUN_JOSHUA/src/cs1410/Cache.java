package cs1410;

import java.util.Scanner;

/**
 * Represents a variety of information about a geocache. A geocache has a title, an owner, a difficulty rating, a
 * terrain rating, a GC code, a latitude, and a longitude.
 */
public class Cache
{
    // The attributes of a cache are given by these instance variables
    // Stores geocache code
    private String gcCode;
    // Stores cache title
    private String title;
    // Stores cache owner
    private String owner;
    // Stores cache difficulty rating
    private double difficulty;
    // Stores terrain difficulty rating
    private double terrain;
    // Stores latitude and longitude coordinates
    private String latitude;
    private String longitude;

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
     * (done)
     * <li>A difficulty or terrain rating that parses to anything other than the doubles 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5,
     * or 5.</li> (done)
     * <li>A title, owner, latitude, or longitude that consists only of white space</li> (done)
     */
    public Cache (String attributes)
    {
        // replace each tab with a newline in order to user a scanner
        String s = attributes.replace('\t', '\n');
        try
        {
            Scanner scnr = new Scanner(s);
            gcCode = scnr.nextLine();
            // feed private field into a scanner to verify it isn't just whitespace
            Scanner codeCheck = new Scanner(gcCode);
            codeCheck.next();
            codeCheck.close();
            title = scnr.nextLine();
            // feed private field into a scanner to verify it isn't just whitespace
            Scanner titleCheck = new Scanner(title);
            titleCheck.next();
            titleCheck.close();
            owner = scnr.nextLine();
            // feed private field into a scanner to verify it isn't just whitespace
            Scanner ownerCheck = new Scanner(owner);
            ownerCheck.next();
            ownerCheck.close();
            difficulty = Double.parseDouble(scnr.nextLine());
            terrain = Double.parseDouble(scnr.nextLine());
            latitude = scnr.nextLine();
            // feed private field into a scanner to verify it isn't just whitespace
            Scanner latCheck = new Scanner(latitude);
            latCheck.next();
            latCheck.close();
            longitude = scnr.nextLine();
            // feed private field into a scanner to verify it isn't just whitespace
            Scanner longCheck = new Scanner(longitude);
            longCheck.next();
            longCheck.close();
            if (scnr.hasNextLine())
            {
                scnr.close();
                throw new Exception();
            }
            scnr.close();
        }
        // If something goes wrong in the assigning process then we know that either A.) There are fewer or more than 7
        // attributes, B.) an attribute was just whitespace, or C.) difficulty and terrain attributes were not numbers.
        // Either way the in put was poorly formatted, so an IllegalArgumentException is thrown.
        catch (Exception e)
        {
            throw new IllegalArgumentException("Error: Bad inputs");
        }
        // Make sure the geocache code is valid and that the doubles follow the pattern described.
        if (!verifyGcCode(gcCode))
        {
            throw new IllegalArgumentException("Error: Geocache code is poorly formatted");
        }
        if (!verifyRating(difficulty) || !verifyRating(terrain))
        {
            throw new IllegalArgumentException("Error: Difficulty or terrain rating is poorly formatted");
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
        // Make sure the first to chars are G and C
        String front = code.substring(0, 2);
        if (!front.equals("GC"))
        {
            return false;
        }
        // Make sure the code is only A-Z or 0-9.
        if (!code.matches("^[A-Z0-9]+$"))
        {
            return false;
        }
        return true;
    }

    /**
     * Private helper method that makes sure a given rating is 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, or 5.
     * 
     * @param rating
     * @return
     */
    private static boolean verifyRating (double rating)
    {
        // return false if the double isn't evenly divisible by .5 or is less than 1 or greater than 5. 
        if (rating % .5 != 0 || (rating < 1 || rating > 5))
        {
            return false;
        }
        else
        {
            return true;
        }
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
        return this.owner;
    }

    /**
     * Returns the title of this cache
     */
    public String getTitle ()
    {
        return this.title;
    }

    /**
     * Returns the difficulty rating of this cache
     */
    public double getDifficulty ()
    {
        return this.difficulty;
    }

    /**
     * Returns the terrain rating of this cache
     */
    public double getTerrain ()
    {
        return this.terrain;
    }

    /**
     * Returns the GC code of this cache
     */
    public String getGcCode ()
    {
        return this.gcCode;
    }

    /**
     * Returns the latitude of this cache
     */
    public String getLatitude ()
    {
        return this.latitude;
    }

    /**
     * Returns the longitude of this cache
     */
    public String getLongitude ()
    {
        return this.longitude;
    }
}
