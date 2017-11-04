package c4;

import static org.junit.Assert.*;
import org.junit.Test;

public class C4BoardTests
{
    @Test
    public void testConstructor ()
    {
        C4Board testBoard = new C4Board(5, 5);
        testBoard.moveTo(3);
        testBoard.moveTo(1);
        assertEquals(1, testBoard.getToMove());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2 ()
    {
        new C4Board(3, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveTo ()
    {
        C4Board testBoard = new C4Board(5, 5);
        testBoard.moveTo(5);
    }

    @Test
    public void testWinAndReset ()
    {
        C4Board testBoard = new C4Board(5, 5);
        testBoard.moveTo(3);
        testBoard.moveTo(2);
        testBoard.moveTo(3);
        testBoard.moveTo(2);
        testBoard.moveTo(3);
        testBoard.moveTo(2);
        testBoard.moveTo(3);
        assertEquals(1, testBoard.getOccupant(2, 3));
        assertEquals(2, testBoard.getOccupant(2, 2));
        assertEquals(1, testBoard.getWinsForP1());
        testBoard.newGame();
        assertEquals(2, testBoard.getToMove());
    }
}
