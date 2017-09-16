package cs1410;

import java.lang.Math;
import java.lang.StringBuilder;
import java.util.Scanner;
import java.math.BigInteger;

/**
 * A collection of methods for the second assignment of CS 1410.
 * 
 * @author Joshua Cragun
 */
public class MethodLibrary
{
    /**
     * You can use this main method for experimenting with your methods if you like, but it is not part of the
     * assignment.
     */
    public static void main (String[] args)
    {
        System.out.println("Computer science is fascinating");
    }

    /**
     * Returns the nth root of x, where n is positive. For example, nthRoot(27.0, 3) is 3.0 (the cube root of 27), and
     * nthRoot(64.0, 6) is 2.0. NOTE: A small amount of roundoff error is acceptable.
     * 
     * The number x is required to have a real-valued nth root, and n is required to be positive. If this requirement is
     * violated, the behavior of the method is undefined (it does not matter what it does).
     */
    public static double nthRoot (double x, int n)
    {
        //The exponent function is the Math java package is used with x and 1 over input n, read as a double
        //This is functionally the same as the nth root, and thus produces a valid result.
        return Math.pow(x, 1.0 / (double) n);
    }

    /**
     * Reports whether or not c is a vowel ('a', 'e', 'i', 'o', 'u' or the upper-case version). For example,
     * isVowel('a') and isVowel('U') are true; isVowel('x') and isVowel('H') are false.
     */
    public static boolean isVowel (char c)
    {
        //First make c to lower case in order to lessen the workload 
        c = Character.toLowerCase(c);
        //Our boolean "result" is assigned the result of the expression on the left,
        //Which is true if c is a vowel, and false if it isn't.
        boolean result = c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        return result;
    }

    /**
     * Reports whether or not number is a multiple of both factor1 and factor2. For example, isMultipleOf(15, 3, 5) is
     * true and isMutipleOf(27, 3, 4) is false.
     * 
     * Both factor1 and factor2 are required to be non-zero. If this requirement is violated, the behavior of the method
     * is undefined (it does not matter what it does).
     */
    public static boolean isMultipleOf (int number, int factor1, int factor2)
    {
        //Our boolean "result" is assigned the result of the left expression,
        //Which is only true if the remainder of (number / factor1) and (number / factor2) is equal to zero.
        boolean result = (number % factor1 == 0) && (number % factor2 == 0);
        return result;
    }

    /**
     * Returns the string that results from capitalizing the first character of s, which is required to have at least
     * one character. For example, capitalize("hello") is "Hello" and capitalize("Jack") is "Jack".
     * 
     * The string s is required to be non-empty. If this requirement is violated, the behavior of the method is
     * undefined (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: The Character.toUpperCase() method will be helpful. The method s.substring() [where s is a
     * String] will also be helpful. Learn more about them before starting!
     */
    public static String capitalize (String s)
    {
        //Create an upper case version of the front letter.
        //If upper case already or not a letter it is left unchanged.
        char firstLetter = Character.toUpperCase(s.charAt(0));
        //Append the upper case letter to the front of the input string, minus the original front letter.
        s = firstLetter + s.substring(1);
        return s;
    }

    /**
     * Returns a new string that (1) begins with all the characters of original that come after the first occurrence of
     * target and (2) ends with all the characters of target that come before the first occurrence of pattern. For
     * example, flip("abcdefg", 'd') is "efgabc", flip("ababad", 'b') is "abada", and flip("x", 'x') is "".
     * 
     * The string original is required to contain the character target. If this requirement is violated, the behavior of
     * the method is undefined (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: The methods s.indexOf() and s.substring() [where s is a String] will be helpful.
     */
    public static String flip (String original, char pattern)
    {
        //Create StringBuilder object in order to create less new objects when modifying the input string.
        StringBuilder sb = new StringBuilder(original);
        //Find the index of the input character called "pattern". Call this number "destroyerInt"
        int destroyerInt = original.indexOf(pattern);
        //Delete the unlucky character input from "original" using "destroyerInt"
        sb.deleteCharAt(destroyerInt);
        //StringBuilders are overrated. Convert to string.
        original = sb.toString();
        //Create two new strings by splitting the string "original" and assigning one half to each.
        String newString1 = original.substring(destroyerInt);
        String newString2 = original.substring(0, destroyerInt);
        //Concatenate the front side to the back, flipping the order 
        String newString = newString1.concat(newString2);
        return newString;
    }

    /**
     * Returns a new string that is just like s except all of the lower-case vowels ('a', 'e', 'i', 'o', 'u') have been
     * capitalized. For example, capitalizeVowels("hello") is "hEllO", capitalizeVowels("String") is "StrIng", and
     * capitalizeVowels("nth") is "nth".
     * 
     * IMPLEMENTATION HINT: The method s.replace() [where s is a String] will be helpful.
     */
    public static String capitalizeVowels (String s)
    {
        //Systematically replace all vowels with their capitalized counterparts
        //If there are no vowels the string is unchanged
        s = s.replace('a', 'A');
        s = s.replace('e', 'E');
        s = s.replace('i', 'I');
        s = s.replace('o', 'O');
        s = s.replace('u', 'U');
        return s;
    }

    /**
     * Reports whether s1 and s2 end with the same n characters. For example, sameEnding("abcde" "xde", 2) is true and
     * sameEnding("abcde", "xde", 3) is false.
     * 
     * The value of n is required to be non-negative, and the two strings must each contain at least n characters. If
     * this requirement is violated, the behavior of the method is undefined (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: The methods s.length() and s.substring() [where s is a String] will be helpful.
     */
    public static boolean sameEnding (String s1, String s2, int n)
    {
        //Find the last n letters of both strings
        s1 = s1.substring(s1.length() - n, s1.length() - 1);
        s2 = s2.substring(s2.length() - n, s2.length() - 1);
        //Checks for equality. Stores it in boolean "result"
        boolean result = s1.equals(s2);
        return result;
    }

    /**
     * Returns the value of the largest of the five int literals, separated by white space, that make up the integerList
     * parameter. For example, largestOfFive(" 15 28 -99 62 44 ") is 62.
     * 
     * The string integerList is required to consist of exactly five int literals surrounded by white space. If this
     * requirement is violated, the behavior of the method is undefined (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: The class java.util.Scanner will be helpful. Use the one-argument constructor that takes a
     * String as a parameter and the nextInt() method.
     */
    public static int largestOfFive (String integerList)
    {
        Scanner scnr = new Scanner(integerList);
        //Takes the max of the first two ints in the list and stores it in "result"
        //Then compares "result" with the other three integers.
        //If any are larger, "result" is updated with that value.
        int result = Math.max(scnr.nextInt(), scnr.nextInt());
        result = Math.max(result, scnr.nextInt());
        result = Math.max(result, scnr.nextInt());
        result = Math.max(result, scnr.nextInt());
        scnr.close();
        return result;
    }

    /**
     * Reports whether or not date1 comes earlier in time than date2. For example, isEarlierThan("12-01-2015",
     * "02-15-2017") is true but isEarlierThan("10-11-2016", "10-11-2016") and isEarlierThan("09-09-1967", "02-15-1933")
     * is false.
     * 
     * The two parameters must be of the form MM-DD-YYYY where YYYY is a year, MM is the two-digit number of a month, DD
     * is a two-digit number of a day, and the entire date is valid. If this requirement is violated, the behavior of
     * the method is undefined (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: Turn this into a String comparison problem.
     */
    public static boolean isEarlierThan (String date1, String date2)
    {
        //For a smooth lexicographical comparison used by the compareTo method,
        //Convert both input strings into yyyyMMdd
        //First remove all "-" characters
        date1 = date1.replace("-", "");
        date2 = date2.replace("-", "");
        //Prepare two new strings using StringBuilders. These will eventually be our reformatted dates.
        //Initialize them as empty strings
        StringBuilder sb1 = new StringBuilder("");
        StringBuilder sb2 = new StringBuilder("");
        //Concatenate the year, then month, and then day to the interal string of sb1 and sb2
        sb1.append(date1.substring(4,8)).append(date1.substring(2, 4)).append(date1.substring(0, 2));
        sb2.append(date2.substring(4,8)).append(date1.substring(2, 4)).append(date1.substring(0, 2));
        //Let's make some strings, baby!
        String reformattedDate1 = sb1.toString();
        String reformattedDate2 = sb2.toString();
        //Compare the output of compareTo, to zero. Store the evaluation in boolean "result"
        boolean result = reformattedDate1.compareTo(reformattedDate2) < 0;
        return result;
    }

    /**
     * Returns the integer numeral that represents the sum of the integer numerals integer1 and integer2. For example,
     * addNumerals("125", "64") is "189". The method works for numerals of any length, including numerals that consist
     * of hundreds or thousands of digits and are far too big to parse as ints or longs.
     * 
     * The two parameters must both be valid integer numerals. That is, both must consist of one or more digits
     * optionally preceded by a + and - sign. If this requirement is violated, the behavior of the method is undefined
     * (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: The class java.math.BigInteger will be helpful. Use the one-argument constructor that takes
     * a String as a parameter, the add method(), and the toString() method.
     */
    public static String addNumerals (String integer1, String integer2)
    {
        //Make two BigIntegers, named bigInt1 and bigInt2 respectively. Initialize them with integer1 and 2
        BigInteger bigInt1 = new BigInteger(integer1);
        BigInteger bigInt2 = new BigInteger(integer2);
        //Add them and store the sum in bigInt1
        bigInt1.add(bigInt2);
        //Convert bigInt1 into a string and store it in "result"
        String result = bigInt1.toString();
        return result;
    }
}
