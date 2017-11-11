package c4;

/**
 * Represents the state of a Connect Four board on which multiple games can be played. The board consists of a
 * rectangular grid of squares in which playing pieces can be placed. Squares are identified by their zero-based row and
 * column numbers, where rows are numbered starting with the bottom row, and columns are numbered by starting from the
 * leftmost column.
 * 
 * Multiple games can be played on a single board. Whenever a new game begins, the board is empty. There are two
 * players, identified by the constants P1 (Player 1) and P2 (Player 2). P1 moves first in the first game, P2 moves
 * first in the second game, and so on in alternating fashion.
 * 
 * A C4Board also keeps track of the outcomes of the games that have been played on it. It tracks the number of wins by
 * P1, the number of wins by P2, and the number of ties. It does not track games that were abandoned before being
 * completed.
 */
public class C4Board
{
    /** The number used to indicate Player 1 */
    public static final int P1 = 1;

    /** The number used to indicate Player 2 */
    public static final int P2 = 2;

    /** The number used to indicate a tie */
    public static final int TIE = 3;

    /** The number of rows in the game */
    private int gameRows;

    /** The number of columns in the game */
    private int gameCols;

    /** The board */
    private int[][] board;

    /** Number of P1's wins */
    private int p1Wins;

    /** Number of P2's wins */
    private int p2Wins;

    /** Number of ties */
    private int ties;

    /** Says if the game is over or not */
    private boolean gameOver;

    /** Says if P1 went first */
    private boolean p1First;

    /** Says if it's P1's turn */
    private boolean p1Turn;

    /**
     * Creates a C4Board with the specified number of rows and columns. There should be no pieces on the board and it
     * should be player 1's turn to move.
     * 
     * However, if either rows or cols is less than four, throws an IllegalArgumentException.
     */
    public C4Board (int rows, int cols)
    {
        if (rows < 4 || cols < 4)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            // Initializes default instance variables
            gameRows = rows;
            gameCols = cols;
            p1Wins = 0;
            p2Wins = 0;
            gameOver = false;
            p1First = true;
            p1Turn = true;
            board = new int[rows][cols];
        }
    }

    /**
     * Sets up the board to play a new game, whether or not the current game is complete. All of the pieces should be
     * removed from the board. The player who had the second move in the previous game should have the first move in the
     * new game.
     */
    public void newGame ()
    {
        // Empty the board, keep the size
        board = new int[gameRows][gameCols];
        // Switch who takes the first turn between each game
        gameOver = false;
        if (p1First == true)
        {
            p1First = false;
            p1Turn = false;
        }
        else if (p1First == false)
        {
            p1First = true;
            p1Turn = true;
        }
    }

    /**
     * Records a move, by the player whose turn it is to move, in the first open square in the specified column. Returns
     * P1 if the move resulted in a win for player 1, returns P2 if the move resulted in a win for player 2, returns TIE
     * if the move resulted in a tie, and returns 0 otherwise.
     * 
     * If the column is full, or if the game is over because a win or a tie has previously occurred, does nothing but
     * return zero.
     * 
     * If a non-existent column is specified, throws an IllegalArgumentException.
     */
    public int moveTo (int col)
    {
        // Check to make sure the column is valid
        if (!(col < gameCols) || col < 0)
        {
            throw new IllegalArgumentException();
        }
        // Find lowest available row in a column
        int row = 0;
        for (row = 0; row < gameRows; row++)
        {
            if (this.getOccupant(row, col) == 0)
            {
                if (p1Turn == true && gameOver == false)
                {
                    // Add P1's move
                    this.board[row][col] = P1;
                    p1Turn = false;
                    // Check if it was a winning move
                    if (FourInARow.fourInRow(board))
                    {
                        p1Wins++;
                        gameOver = true;
                        return P1;
                    }
                }
                else if (p1Turn == false && gameOver == false)
                {
                    // Add P2's move
                    this.board[row][col] = P2;
                    p1Turn = true;
                    // Check if it was a winning move
                    if (FourInARow.fourInRow(board))
                    {
                        p2Wins++;
                        gameOver = true;
                        return P2;
                    }
                }
                break;
            }
        }
        // After any possible move was made, check if a tie has occurred
        if (this.isTie(board))
        {
            ties++;
            return TIE;
        }
        // Otherwise return 0
        return 0;
    }

    /**
     * Reports the occupant of the board square at the specified row and column. If the row or column doesn't exist,
     * throws an IllegalArgumentException. If the square is unoccupied, returns 0. Otherwise, returns P1 (if the square
     * is occupied by player 1) or P2 (if the square is occupied by player 2).
     */
    public int getOccupant (int row, int col)
    {
        // Check that the arguments are valid
        if ((!(row < gameRows) || row < 0) || (!(col < gameCols) || col < 0))
        {
            throw new IllegalArgumentException();
        }
        // If the value in the board at the coordinates is 1, then return P1
        else if (this.board[row][col] == P1)
        {
            return P1;
        }
        // If the value in the board at the coordinates is 2, then return P2
        else if (this.board[row][col] == P2)
        {
            return P2;
        }
        // Otherwise, return 0
        else
        {
            return 0;
        }
    }

    /**
     * Reports whose turn it is to move. Returns P1 (if it is player 1's turn to move), P2 (if it is player 2's turn to
     * move, or 0 (if it is neither player's turn to move because the current game is over because of a win or tie).
     */
    public int getToMove ()
    {
        if (gameOver == true)
        {
            return 0;
        }
        else if (p1Turn == true)
        {
            return P1;
        }
        else
        {
            return P2;
        }
    }

    /**
     * Reports how many games have been won by player 1 since this board was created.
     */
    public int getWinsForP1 ()
    {
        return p1Wins;
    }

    /**
     * Reports how many games have been won by player 2 since this board was created.
     */
    public int getWinsForP2 ()
    {
        return p2Wins;
    }

    /**
     * Reports how many ties have occurred since this board was created.
     */
    public int getTies ()
    {
        return ties;
    }

    private boolean isTie (int A[][])
    {
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A[i].length; j++)
            {
                if (this.getOccupant(i, j) == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
