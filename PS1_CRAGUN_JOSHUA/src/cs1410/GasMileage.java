package cs1410;

import cs1410lib.Dialogs;

/** 
 * The purpose of this program is to ask the user for input on the type of car they have,
 * how many miles they've driven, the price of gasoline per gallon, and how many gallons are in the car's tank
 * through a series of popup dialog boxes.
 * 
 * The program will then compute the total cost of the gas that would be
 * required to fill the tank, the number of miles per gallon that were attained since the most recent fill-up,
 * and the gasoline cost per mile since the previous fill-up.
 * 
 * Finally, the program will print that information along with a summary of the car model in the console
 * and in a single dialog box.
 * 
 * @author Joshua Cragun
 *
 */
public class GasMileage
{
    /**
     * The main method will be used to achieve the purpose of this class. 
     * Please email any feedback to jmcragun@gmail.com if you are interested.
     * @param args
     */
    public static void main (String[] args)
    {
        //Fetches car model
        String carModel = Dialogs.showInputDialog("Please input the model of the vehicle you own:");
        //Fetches miles since last fill-up and converts it into an integer
        String milesDrivenInput = Dialogs.showInputDialog("Please input how many miles you have driven since you last filled your gas tank:");
        int milesDriven = Integer.parseInt(milesDrivenInput);
        //Fetches the cost of gasoline per gallon, removes the dollar sign if input, and 
        //converts it into a double
        String gasRateInput = Dialogs.showInputDialog("Please input you local price of gasoline per gallon:");
        if (gasRateInput.charAt(0) == '$')
        {
            gasRateInput = gasRateInput.substring(1);
        }
        double gasRate = Double.parseDouble(gasRateInput);
        //Fetches the tank's size in gallons and converts it into a double
        String tankSizeInput = Dialogs.showInputDialog("Please input how many gallons are in your car's tank:");
        double tankSize = Double.parseDouble(tankSizeInput);
        
        //We will now begin the calculations
        //Tank fillup cost and formatting
        double tankFillupCost = gasRate * tankSize;
        String formattedTankFillupCost = '$' + String.format("%.2f", tankFillupCost);
        //Miles per gallon since last fillup and formatting
        double milesPerGallon = milesDriven / tankSize;
        String formattedMilesPerGallon = String.format("%.2f", milesPerGallon);
        //Gas cost per mile since last fillup
        double gasCostPerMile = tankFillupCost / milesDriven;
        String formattedGasCostPerMile = '$' + String.format("%.2f", gasCostPerMile);
        
        //And now we shall create the dialog popup and print the outputs to the console
        Dialogs.showMessageDialog(carModel + "\nCost to fill tank: " + formattedTankFillupCost + "\nMiles per gallon since last fill-up: " + formattedMilesPerGallon + "\nGas cost per mile since last fill-up: " + formattedGasCostPerMile);
        System.out.println(carModel + "\nCost to fill tank: " + formattedTankFillupCost + "\nMiles per gallon since last fill-up: " + formattedMilesPerGallon + "\nGas cost per mile since last fill-up: " + formattedGasCostPerMile);
    }
}
