package cs1410;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * A library of methods for implementing the random text generation algorithm described in PS5, Fall 2017.
 * 
 * @author Joshua Cragun and Joe Zachary
 */
public class PS5Library
{
    /**
     * Demonstrates the use of the generateText method.
     */
    public static void main (String[] args) throws IOException
    {
        // You won't need to use this feature in PS5, but this shows how to include a resource (in this
        // case a text file) as part of a project. I created a package called "books", then put two
        // text files into the package. I was then able to open one of those files as shown below. When
        // I export the project, the resources go along with it.
        try (InputStream book = PS5Library.class.getResourceAsStream("/books/PrideAndPrejudice.txt");
                Scanner input = new Scanner(book))
        {
            System.out.println(generateText(input, 6, 200));
        }
    }

    /**
     * Uses all the text in the input to generate and return random text with the specified level and length, using the
     * algorithm described in PS5, CS 1410, Fall 2017.
     * 
     * @throws IllegalArgumentException if level < 0, or length < 0, or there are less than level+1 chars in the input.
     */
    public static String generateText (Scanner input, int level, int length)
    {
        // Validate the parameters
        if (level < 0 || length < 0)
        {
            throw new IllegalArgumentException();
        }

        // Grab all the text from the Scanner and make sure it is long enough.
        String text = scannerToString(input);
        if (level >= text.length())
        {
            throw new IllegalArgumentException();
        }

        // Create a random number generator to pass to the methods that make random choices
        Random rand = new Random();

        // Get the initial pattern.
        String pattern = chooseSubstring(text, level, rand);

        // Build up the final result one character at a time. We use a StringBuilder because
        // it is more efficient than using a String when doing long sequences of appends.
        StringBuilder result = new StringBuilder();
        while (result.length() < length)
        {
            try
            {
                // Pick at random a character that follows the pattern in the text and append it
                // to the result. If there is no such character (which can happen if the pattern
                // occurs only once, at the very end of text), the method we're calling will throw
                // a NoSuchElementException, which is caught below.
                char newChar = pickCharThatFollowsPattern(text, pattern, rand);
                result.append(newChar);

                // Update the pattern by removing its first character and adding on the new
                // character. The length of the pattern remains the same. If the pattern is
                // the empty string, though, it never changes.)
                if (pattern.length() > 0)
                {
                    pattern = pattern.substring(1) + newChar;
                }
            }
            catch (NoSuchElementException e)
            {
                // It is possible to get stuck if the pattern occurs only once in the text and
                // that occurrence is at the very end of the text. In this case, we pick a new
                // seed and keep going.
                pattern = chooseSubstring(text, level, rand);
            }
        }

        // Return the string we've accumulated.
        return result.toString();
    }
    /**
     * Takes a Scanner as its parameter and returns a String. 
     * The returned string consists of all the characters in the scanner in their original order, including the newlines.
     * The last line (assuming there are any lines) should always end with a newline, even if it didn't in the original text.
     * @param scn
     * @return
     */
    public static String scannerToString (Scanner scn)
    {
        StringBuilder strBldr = new StringBuilder();
        while (scn.hasNext())
        {
            strBldr.append(scn.nextLine() + '\n');
        }
        return strBldr.toString();
    }
    /**
     * Takes a String text, an int length, and a random number generator as its parameters. 
     * It should use the random number generator to return a randomly chosen substring of text that has the specified length. 
     * If length is either negative or greater than the length of text, the method should throw an IllegalArgumentException.  
     * @param text
     * @param length
     * @param rand
     * @return
     */
    public static String chooseSubstring (String text, int length, Random rand)
    {
        //Throws exception if length is negative or longer than the text
        if (length < 0 || length > text.length())
        {
            throw new IllegalArgumentException();
        }
        //Applying the formula given 
        int numSubstrings = text.length() + 1 - length;
        //Create a starting index between 0 and the number of substrings, which can serve as unique substring start indexes
        int randStartIndex = rand.nextInt(numSubstrings);
        String result = text.substring(randStartIndex, randStartIndex + length);
        return result;
    }
    /**
     *  Takes a String text and a String pattern as parameters, and returns an ArrayList<Character>. 
     *  The returned list should contain the character that follows each non-tail occurrence of the pattern in the text.
     *  (A non-tail occurrence of the pattern is one that is not at the very end of the text.)
     *  The length of the list must be the same as the number of non-tail occurrences of the pattern.
     *  The character stored at index n of the list must be the character that followed the nth non-tail occurrence of the pattern.
     * @param text
     * @param pattern
     * @return
     */
    public static ArrayList<Character> getCharsThatFollowPattern (String text, String pattern)
    {
        // Create object we will return
        ArrayList<Character> result = new ArrayList<Character>();
        // Create a list of indexes where pattern occurs
        ArrayList<Integer> occurrences = new ArrayList<Integer>();
        if (!text.contains(pattern))
        {
            // Immediately return if the text doesn't contain the pattern
            return result;
        }
        // Initialize index placeholder
        int lastIndex = 0;
        while (lastIndex != -1)
        {
            // If our list of indexes doesn't contain the pattern from our last index, add it
            // Alternatively, if we already have the closest index, ignore it
            if (!(occurrences.contains(text.indexOf(pattern, lastIndex))))
            {
                occurrences.add(text.indexOf(pattern, lastIndex));
            }
            // Increment last index and set itself to the next instance of the pattern
            lastIndex++;
            //If the last occurrence was found, lastIndex will be set to -1, ending the loop.
            lastIndex = text.indexOf(pattern, lastIndex);
        }
        // For every index in occurrences
        for (int index : occurrences)
        {
            // The character that follows the pattern is the pattern's first index plus its length
            int charPatternIndex = index + pattern.length();
            // Add the character at that index if and only if it is less than the length of the text
            // In order to avoid invalid index exceptions
            if (charPatternIndex < text.length())
            {
                result.add(text.charAt(charPatternIndex));
            }
        }
        return result;
    }
    /**
     * Takes a String text, a String pattern, and a random number generator as parameters. 
     * It should randomly choose a non-tail occurrence of the pattern in the text,
     * returning the character that immediately follows that occurrence of the pattern.
     * If there are no non-tail occurrences of the pattern in the text, the method should throw a NoSuchElementException.
     * @param text
     * @param pattern
     * @param rand
     * @return
     */
    public static char pickCharThatFollowsPattern (String text, String pattern, Random rand)
    {
        //Get list of characters we need
        ArrayList<Character> charList = getCharsThatFollowPattern(text, pattern);
        //It is possible that no characters exist that follow the pattern, in which case an exception is thrown
        if (charList.size() <= 0)
        {
            throw new NoSuchElementException();
        }
        //The char returned is taken from a random index of the list
        char result = charList.get(rand.nextInt(charList.size()));
        return result;
    }
}
