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
        String input = Dialogs.showInputDialog("Enter degrees F:");
        double tempF = Double.parseDouble(input);
        double tempC = (tempF - 32) * 5.0 / 9.0;
        Dialogs.showMessageDialog("Degrees C: " + tempC);
    }

    /**
     * This version uses the console to interact with the user.
     * To use this one, rename it to main and the other one to mainx.
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

}
