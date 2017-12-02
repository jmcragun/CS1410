package asteroids.game;

import javax.swing.*;
import static asteroids.game.Constants.*;
import java.awt.*;

/**
 * Defines the top-level appearance of an Asteroids game.
 */
@SuppressWarnings("serial")
public class Display extends JFrame
{
    /** The area where the action takes place */
    private Screen screen;

    /** Display player's score */
    private JLabel score;

    /** Display game level */
    private JLabel level;

    private Graphics g;

    private int x;

    private int y;

    private JPanel glass;

    private boolean glassB;

    private Controller controller;

    /**
     * Lays out the game and creates the controller
     */
    public Display (Controller controller)
    {
        // Title at the top
        setTitle(TITLE);

        // Default behavior on closing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.controller = controller;
        // The main playing area and the controller
        screen = new Screen(controller);

        // This panel contains the screen to prevent the screen from being
        // resized
        JPanel screenPanel = new JPanel();
        screenPanel.setLayout(new GridBagLayout());
        screenPanel.add(screen);

        // This panel contains buttons and labels
        JPanel controls = new JPanel();

        // The button that starts the game
        JButton startGame = new JButton(START_LABEL);
        controls.add(startGame);

        // Organize everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(screenPanel, "Center");
        mainPanel.add(controls, "North");
        setContentPane(mainPanel);
        pack();

        // JPanel that contains player score and current level
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BorderLayout());
        statsPanel.setOpaque(false);

        // JPanel that contains player score
        JPanel scorePanel = new JPanel();
        scorePanel.setOpaque(false);
        score = new JLabel("0");
        score.setFont(new Font("SansSerif", Font.PLAIN, 35));
        score.setForeground(Color.WHITE);
        scorePanel.add(score);

        // Adds to statsPanel
        statsPanel.add(scorePanel, "West");

        // JPanel that contains current level
        JPanel levelPanel = new JPanel();
        levelPanel.setOpaque(false);
        level = new JLabel("1");
        level.setFont(new Font("SansSerif", Font.PLAIN, 35));
        level.setForeground(Color.WHITE);
        levelPanel.add(level);

        // Adds to statsPanel
        statsPanel.add(levelPanel, "East");

        // JPanel that displays player's lives
        JPanel livesPanel = new JPanel();
        livesPanel.setOpaque(false);

        glassB = false;
        // Glass pane set over screenPanel that contains statsPanel
        glass = (JPanel) this.getGlassPane();
        glass.setOpaque(false);
        glass.setVisible(glassB);
        glass.setLayout(new GridBagLayout());
        this.setSize(SIZE, SIZE);
        this.setVisible(true);

        // Constraints for the statsPanel
        GridBagConstraints statsCon = new GridBagConstraints();
        statsCon.ipadx = 550;
        statsCon.ipady = 550;
        statsCon.anchor = GridBagConstraints.PAGE_START;

        // Add statsPanel to glass pane

        glass.add(statsPanel, statsCon);

        // Constraints for the livesPanel
        GridBagConstraints livesCon = new GridBagConstraints();
        // livesCon.ipadx = 550;
        livesCon.ipady = 530;

        // Add livesPanel to glass
        glass.add(livesPanel, livesCon);

        // Connect the controller to the start button
        startGame.addActionListener(controller);
    }

    /**
     * Draws the lives at a certain location
     * 
     * @param g
     * @param x
     * @param y
     */
    public void drawLives (Graphics g, int x, int y)
    {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 4, 10);
    }

    /**
     * Switches the glass pane to visible
     */
    public void glassSwitch ()
    {
        glassB = true;
        glass.setVisible(glassB);
    }

    /**
     * Called when it is time to update the screen display. This is what drives the animation.
     */
    public void refresh ()
    {
        updateScore();
        updateLevel();
        screen.repaint();
    }

    /**
     * Sets the large legend
     */
    public void setLegend (String s)
    {
        screen.setLegend(s);
    }

    public void updateScore ()
    {
        score.setText("" + controller.getScore());
    }
    
    public void updateLevel() {
        level.setText("" + controller.getLevel());
    }
}
