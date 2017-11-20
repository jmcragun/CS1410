package lightsout;

import javax.swing.SwingUtilities;

/**
 * This is a version of the lights out game for the CS1410 class at the University of Utah
 * @author Joshua Cragun
 *
 */

public class LightsOut
{
    public static void main (String args[])
    {
        SwingUtilities.invokeLater( () -> new LightsOutView());
    }
}
