package cs1410;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
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
        File file = null;
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
        while (file == null)
        {
            file = Dialogs.showOpenFileDialog("Please select a readable file");
            // close if exited or cancelled
            if (file == null)
            {
                return;
            }
            // reading the file may throw an exception, so a try block is used
            try
            {
                input = new Scanner(file);
            }
            // if the file isn't found then show a message saying the file wasn't found and reset the file to
            // reinitialize the loop
            catch (FileNotFoundException e)
            {
                Dialogs.showMessageDialog("File not found. Please try again.");
                file = null;
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
                    "ERROR: Invalid Arguments. Terminating.");
            input.close();
            return;
        }
        // Show the result in a user dialog, and close the scanner, and then return
        Dialogs.showMessageDialog("\"" + result + "\"");
        input.close();
        return;
    }
}
