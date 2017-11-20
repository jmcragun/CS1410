package lightsout;

import java.util.Random;

/**
 * This class provides an abstract version of the game which the GUI depends on
 */
public class LightsOutModel
{
    /** The number of rows and columns in a traditional Lights Out game. */
    private final static int ROWS = 5;
    private final static int COLS = 5;
    /** First key to test if a board configuration is solvable */
    private final static int[][] KEY1 = {
            { 0, 1, 1, 1, 0 },
            { 1, 0, 1, 0, 1 },
            { 1, 1, 0, 1, 1 },
            { 1, 0, 1, 0, 1 },
            { 0, 1, 1, 1, 0 } };
    /** Second key to test if a board configuration is solvable */
    private final static int[][] KEY2 = {
            { 1, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 1 },
            { 0, 0, 0, 0, 0 },
            { 1, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 1 } };
    /** The number used to indicate if a light is on */
    public static final int ON = 1;
    /** The board */
    private int[][] board;
    /** Number of wins */
    private int wins;
    /** Says if the game is over or not */
    private boolean gameOver;
    /** Says if the game is currently in custom mode */
    private boolean customMode;

    /**
     * Creates a traditional Lights Out board. All lights should be off.
     * 
     */
    public LightsOutModel ()
    {
        board = new int[ROWS][COLS];
        wins = 0;
        gameOver = false;
        customMode = false;
    }

    /**
     * Sets up the board to play a new game, whether or not the current game is complete.
     */
    public void newGame ()
    {
        gameOver = false;
        customMode = false;
        board = new int[ROWS][COLS];
        this.generateConfig();
    }

    /**
     * Switches between normal and custom mode. When exiting it makes sure that the board configuration is solvable
     * before returning to normal mode. If it is not, it remains in custom mode. Returns the now current state of
     * customMode.
     * 
     * @return
     */
    public boolean switchModes ()
    {
        if (this.customMode)
        {
            if (!this.verifyBoard())
            {
                return true;
            }
            this.customMode = false;
            this.gameOver = false;
            return false;
        }
        else
        {
            customMode = true;
            return true;
        }
    }

    /**
     * Makes a move on the board, lighting up all lights adjacent but non-diagonal to the selected space in addition to
     * the button that was pushed. Returns 1 if the move won the game and 2 if the game is already won.
     * 
     * @param row
     * @param col
     */
    public int pressButton (int row, int col)
    {
        if ((row < 0 || row >= ROWS) || (col < 0 || col >= COLS))
        {
            throw new IllegalArgumentException();
        }
        else if (this.isCustomMode())
        {
            // Switches the button pressed from on to off and vice versa
            this.board[row][col] ^= ON;
            return 0;
        }
        else if (!this.isGameOver() && !this.customMode)
        {
            // Switches the button pressed from on to off and vice versa
            this.board[row][col] ^= ON;
            // Tries to do the same with each adjacent light
            try
            {
                this.board[row - 1][col] ^= ON;
            }
            catch (Exception e)
            {
            }
            try
            {
                this.board[row + 1][col] ^= ON;
            }
            catch (Exception e)
            {
            }
            try
            {
                this.board[row][col - 1] ^= ON;
            }
            catch (Exception e)
            {
            }
            try
            {
                this.board[row][col + 1] ^= ON;
            }
            catch (Exception e)
            {
            }
            if (this.didWin())
            {
                this.gameOver = true;
                wins++;
                return 1;
            }
            return 0;
        }
        return 2;
    }

    /**
     * Similar to pressButton, however this method makes moves regardless of whether or not the game is over, and
     * doesn't check if a move won the game. This method is used when generating a new board.
     */
    private void autoMove (int row, int col)
    {
        // Switches the button pressed from on to off and vice versa
        this.board[row][col] ^= ON;
        // Tries to do the same with each adjacent light
        try
        {
            this.board[row - 1][col] ^= ON;
        }
        catch (Exception e)
        {
        }
        try
        {
            this.board[row + 1][col] ^= ON;
        }
        catch (Exception e)
        {
        }
        try
        {
            this.board[row][col - 1] ^= ON;
        }
        catch (Exception e)
        {
        }
        try
        {
            this.board[row][col + 1] ^= ON;
        }
        catch (Exception e)
        {
        }
    }

    /**
     * Checks if the game is over by iterating through each light on the board. Returns true if all lights are off,
     * false if any of them are on.
     * 
     * @return
     */
    private boolean didWin ()
    {
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                if (this.board[i][j] == ON)
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * A light's out board configuration is solvable if and only if it's configuration is orthogonal to KEY1 and KEY2 in
     * modulo 2. This method checks to make sure that is the case, returning true if it passes, and false if it does
     * not.
     * 
     * @return
     */
    public boolean verifyBoard ()
    {
        int sum1 = 0;
        int sum2 = 0;
        // Take the dot product of the board and key vectors
        // Do this by multiplying corresponding array elements and adding them up
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                sum1 += this.board[i][j] * KEY1[i][j];
                sum2 += this.board[i][j] * KEY2[i][j];
            }
        }
        // If the dot product is zero or even for both, then the board is orthogonal to both, and therefore is solvable
        if (sum1 % 2 == 0 && sum2 % 2 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Modifies the board's on/off configuration, creating a new, random board.
     * 
     * @return
     */
    private void generateConfig ()
    {
        Random rand = new Random();
        int moves = rand.nextInt(500);
        int row;
        int col;
        for (int i = 0; i < moves; i++)
        {
            row = rand.nextInt(5);
            col = rand.nextInt(5);
            this.autoMove(row, col);
        }
    }

    /**
     * Tells whether or not a given light is on or off
     * 
     * @param row
     * @param col
     * @return
     */
    public boolean isActivated (int row, int col)
    {
        if ((row < 0 || row >= ROWS) || (col < 0 || col >= COLS))
        {
            throw new IllegalArgumentException();
        }
        else if (this.board[row][col] == ON)
        {
            return true;
        }
        else
            return false;
    }

    /**
     * Returns the number of times the user has won a game
     * 
     * @return
     */
    public int getWins ()
    {
        return this.wins;
    }

    /**
     * Returns true if the game is over or false if it is not
     * 
     * @return
     */
    public boolean isGameOver ()
    {
        return gameOver;
    }

    /**
     * Returns true if the game is in custom mode or false if it is not
     * 
     * @return
     */
    public boolean isCustomMode ()
    {
        return customMode;
    }
}
