package loops;

import java.util.Scanner;

/**
 * A collection of methods for the fourth assignment of CS1410. In particular these methods will be utilizing loops to
 * achieve their purpose, which will be explained above or inside each method.
 * 
 * @author Joshua Cragun
 *
 */
public class Loops
{
    public static void main (String[] args)
    {
        // The primary function of main is to test the output of the following methods.
        System.out.println();
    }

    /**
     * Takes two string inputs and returns true if the second string is present as a token in the first string
     * 
     * @param s
     * @param t (Must have only one token. May behave strangely otherwise)
     * @return
     */
    public static boolean containsToken (String s, String t)
    {
        Scanner scnr = new Scanner(s);
        // cycles through each token of s and compares it to t.
        // If there's a match then true is immediately returned.
        // If no match is ever found, false is returned.
        while (scnr.hasNext())
        {
            if (t.equals(scnr.next()))
            {
                scnr.close();
                return true;
            }
        }
        scnr.close();
        return false;
    }

    /**
     * Receives a scanner and a string as inputs. The scanner's input may have multiple lines inside, the other should
     * only have a single token. The method will search through the first input's string to see if any of the lines
     * entered contain the specified token. If there's a match, the line containing it is returned. Otherwise, an empty
     * string is returned.
     * 
     * @param scn
     * @param t (Must have only one token. May behave strangely otherwise)
     * @return
     */
    public static String findLineWithToken (Scanner scn, String t)
    {
        String result = "";
        // continues until there are no more tokens left in the string input to the scanner
        while (scn.hasNext())
        {
            // A placeholder string is used to avoid calling nextLine() twice, which would consequentially skip lines.
            String thisLine = scn.nextLine();
            if (containsToken(thisLine, t))
            {
                result = thisLine;
                break;
            }
        }
        return result;
    }

    /**
     * takes a String s as a parameter and returns a boolean. It returns true if s reads the same forwards and
     * backwards, and returns false otherwise.
     * 
     * @param s
     * @return
     */
    public static boolean isPalindrome (String s)
    {
        // cycles for the duration of the string's first half, not including the middle character, if odd.
        // it saves time and if it's odd the middle character need not be considered
        for (int i = 0; i < s.length() / 2; i++)
        {
            // if at any point the index's character doesn't match the mirrored index (which is the length of s minus one, minus the first index)
            // then false is immediately returned
            if (s.charAt(i) != s.charAt(s.length() - (1 + i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * takes a Scanner scn as its parameter and returns a String. It returns the longest token from scn that is a
     * palindrome (if one exists) or the empty string (otherwise).
     * 
     * @param scn
     * @return
     */
    public static String findLongestPalindrome (Scanner scn)
    {
        String result = "";
        while (scn.hasNext())
        {
            // an intermediate string is used to avoid calling scn.next() multiple times, avoiding possible runtime
            // errors
            String competitor = scn.next();
            // the competitor token is tested to be a palindrome and check to see if its length is greater than result's current length
            // if it is, competitor is stored into result
            if (isPalindrome(competitor) && (competitor.length() > result.length()))
            {
                result = competitor;
            }
        }
        return result;
    }

    /**
     * takes a Scanner scn as its parameter and returns an int. It finds the line in scn with the most whitespace
     * characters and returns the number of whitespace characters it contains (if scn contains at least one line) or -1
     * (otherwise)
     * 
     * @param scn
     * @return
     */
    public static int findMostWhitespace (Scanner scn)
    {
        int result = 0;
        // iterates until the scanner is empty
        while (scn.hasNext())
        {
            // at the beginning of each iteration the next line is taken and stored in currentLine, so the .charAt(i) method can be later called without complication
            // with more ease
            String currentLine = scn.nextLine();
            // At the start of each iteration the int variable competitor is re-initialized to be 0
            int competitor = 0;
            for (int i = 0; i < currentLine.length(); i++)
            {
                // for each character in each line, competitor is incremented if said character is whitespace
                if (Character.isWhitespace(currentLine.charAt(i)))
                {
                    competitor++;
                }
            }
            // if competitor is greater than result, the value is stored in result
            if (competitor > result)
            {
                result = competitor;
            }
        }
        // if the content of scn is exhausted and result still equals 0, then return -1
        if (result == 0)
        {
            return -1;
        }
        return result;
    }
}
