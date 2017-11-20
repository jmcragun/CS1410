package lightsout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LightsOutView extends JFrame implements ActionListener
{
    // Some formatting constants
    private final static int WIDTH = 600;
    private final static int HEIGHT = 600;
    public final static int ROWS = 5;
    public final static int COLS = 5;
    public final static Color BACKGROUND_COLOR = Color.BLUE;
    public final static Color ON = Color.YELLOW;
    public final static Color OFF = Color.GRAY;
    public final static int BORDER = 5;
    public final static int FONT_SIZE = 20;

    /** The "smarts" of the game **/
    private LightsOutModel model;

    /** The number of games that the player has won **/
    private JLabel wins;

    /** The portion of the GUI that contains the playing surface **/
    private Board board;

    private JButton newGame;

    private JButton customMode;

    public LightsOutView ()
    {
        // Set the title that appears at the top of the window
        setTitle("CS 1410 Lights Out");

        // Cause the application to end when the windows is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Give the window its initial dimensions
        setSize(WIDTH, HEIGHT);

        // The root panel contains everything
        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        setContentPane(root);

        // The center portion contains the playing board
        model = new LightsOutModel();
        board = new Board(model, this);
        root.add(board, "Center");

        // The top portion contains the score
        JPanel score = new JPanel();
        score.setLayout(new BorderLayout());
        root.add(score, "North");
        JPanel p = new JPanel();
        p.setBackground(BACKGROUND_COLOR);
        wins = new JLabel("Wins: 0");
        wins.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        wins.setForeground(Color.BLACK);
        wins.setBorder(new EmptyBorder(0, BORDER, 0, BORDER));
        p.add(wins);
        score.add(p, "West");

        // The bottom portion contains the New Game button and the custom game button
        JPanel buttons = new JPanel();
        buttons.setLayout(new BorderLayout());
        root.add(buttons, "South");

        newGame = new JButton("New Game");
        newGame.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        newGame.setForeground(Color.BLACK);
        newGame.setBackground(BACKGROUND_COLOR);
        newGame.addActionListener(this);
        buttons.add(newGame, "West");

        // The bottom portion contains the Custom Mode button
        customMode = new JButton("Custom Mode");
        newGame.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        newGame.setForeground(Color.BLACK);
        newGame.setBackground(BACKGROUND_COLOR);
        newGame.addActionListener(this);
        buttons.add(customMode, "East");
    }

    public void setWins (int n)
    {
        wins.setText("Wins: " + n);
        return;
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == this.newGame)
        {
            model.newGame();
            board.refresh();
        }
        else
        {
            model.switchModes();
            board.refresh();
        }
    }

}

@SuppressWarnings("serial")
class Board extends JPanel implements MouseListener
{
    /** The "smarts" of the game */
    private LightsOutModel model;

    /** The top level GUI for the game */
    private LightsOutView display;

    /**
     * Creates the board portion of the GUI.
     */
    public Board (LightsOutModel model, LightsOutView display)
    {
        // Record the model and the top-level display
        this.model = model;
        this.display = display;

        // Set the background color and the layout
        // setBackground(LightsOutView.BACKGROUND_COLOR);
        setLayout(new GridLayout(LightsOutView.ROWS, LightsOutView.COLS));

        // Create and lay out the grid of squares that make up the game.
        for (int i = LightsOutView.ROWS - 1; i >= 0; i--)
        {
            for (int j = 0; j < LightsOutView.COLS; j++)
            {
                Square s = new Square(i, j);
                s.addMouseListener(this);
                add(s);
            }
        }
    }

    /**
     * Refreshes the display. This should be called whenever something changes in the model.
     */
    public void refresh ()
    {
        // Iterate through all of the squares that make up the grid
        Component[] squares = getComponents();
        for (Component c : squares)
        {
            Square s = (Square) c;

            // Set the color of the squares appropriately
            boolean status = model.isActivated(s.getRow(), s.getCol());
            if (status)
            {
                s.setColor(LightsOutView.ON);
            }
            else
            {
                s.setColor(LightsOutView.OFF);
            }
        }
        display.setWins(model.getWins());
        repaint();
    }

    @Override
    public void mouseClicked (MouseEvent e)
    {
    }

    @Override
    public void mousePressed (MouseEvent e)
    {
        Square s = (Square) e.getSource();
        int result = model.pressButton(s.getRow(), s.getCol());
        refresh();
        if (result == 1)
        {
            JOptionPane.showMessageDialog(this, "You solved the puzzle, well done!");
        }
    }

    @Override
    public void mouseReleased (MouseEvent e)
    {
    }

    @Override
    public void mouseEntered (MouseEvent e)
    {
    }

    @Override
    public void mouseExited (MouseEvent e)
    {
    }
}

/**
 * A single square on the board where a move can be made
 */
@SuppressWarnings("serial")
class Square extends JPanel
{
    /** The row within the board of this Square. Rows are numbered from zero; row zero is at the bottom of the board. */
    private int row;

    /** The column within the board of this Square. Columns are numbered from zero; column zero is at the left */
    private int col;

    /** The current Color of this Square */
    private Color color;

    /**
     * Creates a square and records its row and column
     */
    public Square (int row, int col)
    {
        this.row = row;
        this.col = col;
        this.color = LightsOutView.OFF;
    }

    /**
     * Returns the row of this Square
     */
    public int getRow ()
    {
        return row;
    }

    /**
     * Returns the column of this Square
     */
    public int getCol ()
    {
        return col;
    }

    /**
     * Sets the color of this square
     */
    public void setColor (Color color)
    {
        this.color = color;
    }

    /**
     * Paints this Square onto g
     */
    @Override
    public void paintComponent (Graphics g)
    {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(LightsOutView.BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(color);
        g.fillRect(LightsOutView.BORDER, LightsOutView.BORDER, getWidth() - 2 * LightsOutView.BORDER,
                getHeight() - 2 * LightsOutView.BORDER);
    }
}
