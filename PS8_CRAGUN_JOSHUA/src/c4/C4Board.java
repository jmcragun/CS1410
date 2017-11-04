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

    /** Number of turns taken in a game */
    private int turns;

    /** Number of P1's wins */
    private int p1Wins;

    /** Number of P2's wins */
    private int p2Wins;

    /** Number of ties */
    private int ties;

    /** Says if the game is over or not */
    private boolean gameOver;

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
            gameRows = rows;
            gameCols = cols;
            turns = 1;
            p1Wins = 0;
            p2Wins = 0;
            gameOver = false;
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
        board = new int[gameRows][gameCols];
        gameOver = false;
        if (turns % 2 == 1)
        {
            turns = 1;
        }
        else if (turns % 2 == 0)
        {
            turns = 0;
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
        if (!(col < gameCols) || col < 0)
        {
            throw new IllegalArgumentException();
        }
        int row = 0;
        for (row = 0; row < gameRows; row++)
        {
            if (this.getOccupant(row, col) == 0)
            {
                if (turns % 2 == 1)
                {
                    this.board[row][col] = P1;
                    turns++;
                    if (FourInARow.fourInRow(board))
                    {
                        p1Wins++;
                        gameOver = true;
                        return P1;
                    }
                }
                else
                {
                    this.board[row][col] = P2;
                    turns++;
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
        if (this.isTie(board))
        {
            ties++;
            return TIE;
        }
        return 0;
    }

    /**
     * Reports the occupant of the board square at the specified row and column. If the row or column doesn't exist,
     * throws an IllegalArgumentException. If the square is unoccupied, returns 0. Otherwise, returns P1 (if the square
     * is occupied by player 1) or P2 (if the square is occupied by player 2).
     */
    public int getOccupant (int row, int col)
    {
        if (this.board[row][col] == P1)
        {
            return P1;
        }
        else if (this.board[row][col] == P2)
        {
            return P2;
        }
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
        else if (turns % 2 == 1)
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
