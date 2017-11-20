package cs1410;

import javax.swing.*;
import java.awt.*;

/**
 * Provides a TipCalculator application
 * 
 * @author Joe Zachary
 */
public class TipCalculator
{
    /**
     * Launches the tip calculator on the GUI event thread.
     */
    public static void main (String[] args)
    {
        SwingUtilities.invokeLater( () -> new TipCalculator());
        //SwingUtilities.invokeLater( () -> new TipCalculator());
    }

    /**
     * Creates and launches a TipCalculator GUI
     */
    public TipCalculator ()
    {
        // A JFrame is a top-level GUI window.
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(300, 400));
        frame.setTitle("Tip Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // A JPanel is a Component on which other Components can be placed.
        JPanel rootPanel = new JPanel();
        rootPanel.add(new JLabel("Check Amount: "));
        rootPanel.add(new JTextField(20));
        rootPanel.add(new JLabel("Tip %: "));
        rootPanel.add(new JTextField(20));
        rootPanel.add(new JButton("Calculate Tip"));
        rootPanel.add(new JLabel("Total: "));
        JTextField totalField = new JTextField(20);
        totalField.setEditable(false);
        rootPanel.add(totalField);

        // To display something in a JFrame, set its content pane
        frame.setContentPane(rootPanel);

        // Complete layout and make visible

        frame.pack();
        frame.setVisible(true);
    }
}
