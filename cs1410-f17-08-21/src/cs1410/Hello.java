package cs1410;

import java.util.Scanner;
import cs1410lib.Dialogs;

public class Hello
{
    /**
     * This version uses popup dialogs to interact with the user.
     */
    public static void main (String[] args)
    {
        
    }

    /**
     * This version uses the console to interact with the user. To use this one, rename it to main and the other one to
     * mainx.
     */
    public static void mainx (String[] args)
    {
        System.out.print("Enter degrees F: ");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        double tempF = Double.parseDouble(input);
        double tempC = (tempF - 32) * 5.0 / 9.0;
        System.out.println("Degrees C: " + tempC);
    }
    /**
     * Reverses string
     * @param s
     * @return
     */
    public static String f (String s)
    {
        String result = "";
        int i = 0;
        while (i < s.length())
        {
            result = s.charAt(i) + result;
            i++;
        }
        return result;
    }

}
