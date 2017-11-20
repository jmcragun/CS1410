package lightsout;

import static org.junit.Assert.*;
import org.junit.Test;

public class LightsOutModelTests
{
    @Test
    public void testConstructor ()
    {
        LightsOutModel lom = new LightsOutModel();
        lom.pressButton(0, 0);
        lom.pressButton(2, 2);
        lom.pressButton(3, 3);
        assertTrue(lom.isActivated(0, 0));
        assertTrue(lom.isActivated(0, 1));
        assertTrue(lom.isActivated(1, 0));
        assertFalse(lom.isGameOver());
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (j % 5 == 0)
                {
                    System.out.println("");
                }
                if (lom.isActivated(i, j))
                {
                    System.out.print("1 ");
                }
                else
                {
                    System.out.print("0 ");
                }
            }
        }
        assertTrue(lom.verifyBoard());
        lom.pressButton(0, 0);
        lom.pressButton(3, 3);
        lom.pressButton(2, 2);
        System.out.println("");
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (j % 5 == 0)
                {
                    System.out.println("");
                }
                if (lom.isActivated(i, j))
                {
                    System.out.print("1 ");
                }
                else
                {
                    System.out.print("0 ");
                }
            }
        }
        assertTrue(lom.isGameOver());
    }
}
