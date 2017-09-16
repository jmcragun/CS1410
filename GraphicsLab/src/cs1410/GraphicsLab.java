package cs1410;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import cs1410lib.GraphicsWindow;

/**
 * Code in support of a CS 1410 lab.
 * 
 * @author Joe Zachary for CS 1410
 */
public class GraphicsLab
{
    /**
     * Starts an application containing two GraphicsWindows.
     */
    public static void main (String[] args)
    {
        new GraphicsWindow("Window 1", 400, 400, 0, 0, Color.WHITE, g -> draw1(g));
        new GraphicsWindow("Window 2", 400, 500, 500, 0, Color.WHITE, g -> draw2(g));
    }

    /**
     * Draws a picture on g.
     */
    public static void draw1 (Graphics g)
    {
        drawHouse(g, 40, 150);
        drawHouse(g, 50, 175);
        drawHouse(g, 60, 200);
    }

    /**
     * Draws a picture on g.
     */
    public static void draw2 (Graphics g)
    {
        int x = 65;
        int y = 50;
        g.setColor(Color.YELLOW);
        g.fillOval(x + 5, y - 12, 40, 75);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y + 50, 50, 300);
        g.setColor(Color.BLACK);
        Font font = new Font("Verdana", Font.BOLD, 18);
        g.setFont(font);
        g.drawString("Happy Birthday!", x, y + 380);
    }

    public static void drawHouse (Graphics g, int x, int y)
    {
        // Draw using red
        g.setColor(Color.RED);

        // Draw a rectangle
        g.drawRect(x, y, 300, 120);

        // Draws a door
        g.drawRect(x + 135, y + 60, 30, 60);
        // Draw first line of a triangle
        g.drawLine(x, y, x + 150, y - 90);

        // Draw second line of a triangle
        g.drawLine(x + 150, y - 90, x + 300, y);
    }
}
