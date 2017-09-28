package cs1410;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import cs1410lib.Dialogs;

/**
 * A class that serves to meet the qualifications described in PS5 for CS1410. The objective is to obtain input from the
 * user to generate text, that asks the user until they either cancel or comply. Specifically, it will ask the user or
 * an analysis level, an output length, and the address of an valid text file. In addition, it will check to make sure
 * that all these inputs are valid. It will then create a message dialog box with the output to specification.
 * 
 * @author Joshua Cragun
 *
 */
public class Generator
{
    public static void main (String[] args) throws IOException
    {
        // Initialize needed variables
        int level = -1;
        int length = -1;
        String address = "";
        Scanner input = null;
        // Use while loop to continually prompt the user for proper input
        while (level < 0)
        {
            // Parsing the input may throw exceptions, so a try block is used
            try
            {
                // Make a placeholder string for the level
                String levelS = Dialogs.showInputDialog(
                        "Please enter a level of analysis to be conducted (must be 0 or above).\n(Note: small values may increase processing time substantially)");
                // Exit the program if the exit button or cancel is pressed
                if (levelS == null)
                {
                    return;
                }
                level = Integer.parseInt(levelS);
                // For user friendliness, negative integers throw NumberFormatExceptions
                // I decided that it was clearer if the user was explicitly told they entered an invalid integer
                if (level < 0)
                {
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException e)
            {
                // If the user doesn't enter a desired integer, ask them to enter a valid integer and set level to -1,
                // forcing the
                // loop to restart.
                Dialogs.showMessageDialog("Please enter a valid integer number");
                level = -1;
            }
        }
        // Use while loop to continually prompt the user for proper input
        while (length < 0)
        {
            // Parsing the input may throw exceptions, so a try block is used
            try
            {
                // Make a placeholder string for the length
                String lengthS = Dialogs.showInputDialog(
                        "Please enter how long you would like your text output to be (measured in number of characters, 0 or above).\n(Note: Large values may increase processing time)");
                // Exit the program if the exit button or cancel is pressed
                if (lengthS == null)
                {
                    return;
                }
                length = Integer.parseInt(lengthS);
                // For user friendliness, negative integers throw NumberFormatExceptions
                // I decided that it was clearer if the user was explicitly told they entered an invalid integer
                if (length < 0)
                {
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException e)
            {
                // If the user doesn't enter an integer we want, ask them to enter a valid integer and set level to -1,
                // forcing the
                // loop to restart.
                Dialogs.showMessageDialog("Please enter a valid integer number");
                length = -1;
            }
        }
        // Use while loop to continually prompt the user for proper input
        while (address == "")
        {
            address = Dialogs.showInputDialog(
                    "Please enter a valid file address for source text.\n(Note: This is relative to the file location of Generator.java)");
            // Exit the program if the exit button or cancel is pressed
            if (address == null)
            {
                // It is possible that the scanner "input" was changed from "null", so if the user exits we should try
                // to close input.
                if (input != null)
                {
                    input.close();
                }
                return;
            }
            // Parsing the input may throw exceptions, so a try block is used
            try
            {
                InputStream book = Generator.class.getResourceAsStream(address);
                input = new Scanner(book);
            }
            // If the file address is invalid, a NullPointerExeption is thrown. If caught, give the user a message
            // something went wrong and then set address to an empty string, forcing the loop to restart
            catch (NullPointerException e)
            {
                Dialogs.showMessageDialog("There was a problem using the file you specified. Please try again.");
                address = "";
            }

        }
        // Inform user that all input was taken without issue
        Dialogs.showMessageDialog(
                "Thank you. Your output is being computed.\nThis may take anywhere between a few seconds and a few minutes.");
        String result = "";
        // The generateText method may throw exceptions, so a try block is used.
        try
        {
            result = PS5Library.generateText(input, level, length);
        }
        // An IllegalArgumentException may be thrown, however it will only be thrown if the length is shorter than the
        // level of analysis, so it would be an easy way to test if the inputs are valid by catching it.
        catch (IllegalArgumentException e)
        {
            Dialogs.showMessageDialog(
                    "ERROR: Invalid Arguments. Terminating.\n(File must have more charcters than the level of analysis)");
            input.close();
            return;
        }
        // Show the result in a user dialog, and close the scanner, and then return
        Dialogs.showMessageDialog("\"" + result + "\"");
        input.close();
        return;
    }
}
