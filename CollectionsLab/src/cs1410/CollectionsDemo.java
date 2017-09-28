package cs1410;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class CollectionsDemo
{
    /**
     * For experimenting with the collection classes
     */
    public static void main (String[] args) throws IOException
    {
        // Opens a file in this project's part of the workspace
        try (InputStream book = CollectionsDemo.class.getResourceAsStream("/books/PrideAndPrejudice.txt");
                Scanner input = new Scanner(book))
        {
            // Display the characters in the input
            //TreeSet<Character> allChars = countChars(input);
            //System.out.println(allChars);
            //System.out.println(allChars.size());
            
            // Display the words in the input
            //TreeSet<String> allWords = countWords(input);
            //System.out.println(allWords);
            //System.out.println(allWords.size());
            
            // For each word, display the number of times it occurs
            TreeMap<String,Integer> wordCounts = countWordOccurrences(input);
            //System.out.println(wordCounts);
            
            // For each count, display the words that occur that many times
            TreeMap<Integer,TreeSet<String>> occurrences = orderWordOccurrences(wordCounts);
            //System.out.println(occurrences);
            
            // Give a more readable output
            displayResults(occurrences);
        }
    }

    /**
     * Returns the number of distinct characters in the input. Also prints out a set containing all the characters.
     */
    public static TreeSet<Character> countChars (Scanner input)
    {
        // Create an empty set of chars
        TreeSet<Character> allChars = new TreeSet<>();
        
        while (input.hasNextLine())
        {
            String line = input.nextLine();
            for (int i = 0; i < line.length(); i++)
            {
                // Add each character on each line to the allChars set
                allChars.add(Character.toLowerCase(line.charAt(i)));
            }
            
            // Don't forget the terminating newline
            allChars.add('\n');
        }
        return allChars;
    }
    
    /**
     * Returns the number of distinct words (actually, tokens) in the input. Also prints out a set containing all the
     * words.
     */
    public static TreeSet<String> countWords (Scanner input)
    {
        // TODO: Modify the scanner so that it treats all non-letters as delimiters
        input.useDelimiter("[^a-zA-Z]+");
        // Create an empty set of strings
        TreeSet<String> allWords = new TreeSet<>();
        
        while (input.hasNext())
        {
            // Add each word to the set
            String word = input.next().toLowerCase();
            allWords.add(word);
        }
        return allWords;
    }
    
    /**
     * Returns a TreeMap that maps each word from the input to the number of times
     * it occurs in the input.
     */
    public static TreeMap<String,Integer> countWordOccurrences (Scanner input)
    {
        // Treat all non-letters as token delimiters
        input.useDelimiter("[^a-zA-Z]+");
        
        // Create an empty map from strings to ints
        TreeMap<String,Integer> wordCounts = new TreeMap<>();
        
        while (input.hasNext())
        {
            // Get the next word
            String word = input.next().toLowerCase();
            
            // Update the count for the word
            if (wordCounts.get(word) == null)
            {
                // TODO: Use the put method to record the first occurrence of word
                wordCounts.put(word, 1);
            }
            else
            {
                // TODO: Use the put method to record a subsequent occurrence of word
                int i = wordCounts.get(word);
                i++;
                wordCounts.put(word, i);
            }
        }
        return wordCounts;
    }
    
    /**
     * Returns a map that "reverses" the map in wordCounts. If word w maps to count c in wordCounts,
     * then c will map to a set containing w (and possibly other words) in the returned map. 
     */
    public static TreeMap<Integer,TreeSet<String>> orderWordOccurrences (TreeMap<String,Integer> wordCounts)
    {
        // Create an empty map
        TreeMap<Integer,TreeSet<String>> countMap = new TreeMap<>();
        
        // For each key (i.e. for each word)
        for (String word: wordCounts.keySet())
        {
            // Get the word's count from wordCounts
            int count = wordCounts.get(word);  // TODO: Replace 0 with an expression
            
            // If count is not already in the countMap, make it map to the empty set
            if (countMap.get(count) == null)
            {
                // TODO: Map count to the empty set of strings
                countMap.put(count, new TreeSet<String>());
            }
            
            // TODO: Add word to the set of words that occur count times
            TreeSet<String> treeSet = countMap.get(count);
            treeSet.add(word);
            countMap.put(count, treeSet);
        }
        return countMap;
    }
    
    /**
     * Displays the parameter in a more readable fashion
     */
    public static void displayResults (TreeMap<Integer,TreeSet<String>> occurrences)
    {
        for (int count: occurrences.descendingKeySet())
        {
            System.out.print(count);
            for (String word: occurrences.get(count))
            {
                System.out.println("\t" + word);
            }
            System.out.println();
        }
    }
}
