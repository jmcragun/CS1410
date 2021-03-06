package animation;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;
import java.util.Random;

/**
 * A fun little animation program. The base skeleton was written by Joe Zachary
 * 
 * @author Joshua Cragun
 *
 */
public class Animation
{
    /**
     * This is the method that you need to rewrite to create a custom animation. This method is called repeatedly as the
     * animation proceeds. It needs to draw on g how the animation should look after time milliseconds have passed.
     * 
     * @param g Graphics object on which to draw
     * @param time Number of milliseconds that have passed since animation started
     */
    public static void paintFrame (Graphics g, int time, int height, int width)
    {
        Random randGen = new Random();
        fillSpace(g, 0, 0, Color.BLACK);
        alienPortal(g, 200, 100, time, Color.MAGENTA);
        alienShip(g, 230, 200, time, Color.GRAY, Color.GREEN);
        humanShip(g, 1250, 650, time, Color.RED, Color.CYAN);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
        drawStar(g, (randGen.nextInt(3000) - 1000) + time / 10, randGen.nextInt(875), Color.WHITE);
    }

    /**
     * Draws a single frame of an animation of a house. The frame that is drawn corresponds to the point in the
     * animation time milliseconds after the animation starts.
     * 
     * During the animation, a house moves from left to right for two seconds, after which it moves down. The house
     * starts out invisible and grows larger as time goes on. The position of the house at the beginning of the
     * animation is given with initialX and initialY.
     */
    public static void drawHouse1 (Graphics g, int time, int initialX, int initialY)
    {
        if (time < 2000)
        {
            drawHouse(g, initialX + time / 10, initialY, time / 2000., Color.BLUE);
        }
        else
        {
            int intermediateX = initialX + 2000 / 10;
            int intermediateY = initialY;
            drawHouse(g, intermediateX, intermediateY + (time - 2000) / 10, time / 2000., Color.BLUE);
        }
    }

    /**
     * Draws a single frame of an animation of a house. The frame that is drawn corresponds to the point in the
     * animation time milliseconds after the animation starts.
     * 
     * During the animation, a house follows a sine wave for three seconds, after which it moves up and down. The
     * position of the house at the beginning of the animation is given with initialX and initialY.
     */
    public static void drawHouse2 (Graphics g, int time, int initialX, int initialY)
    {
        if (time < 3000)
        {
            drawHouse(g, initialX + time / 10, initialY + (int) (50 * Math.sin(time * Math.PI / 1000)), 1, Color.RED);
        }
        else
        {
            int intermediateX = initialX + 3000 / 10;
            int intermediateY = initialY;
            drawHouse(g, intermediateX, intermediateY + (int) (50 * Math.sin(time * Math.PI / 1000)), 1, Color.RED);
        }
    }

    /**
     * Draws a house on g whose upper-left corner is at coordinate (x,y) using the provided scaling factor and color.
     * The larger the scaling factor, the larger the house.
     */
    public static void drawHouse (Graphics g, int x, int y, double scale, Color color)
    {
        g.setColor(color);
        g.drawRect(x, y, (int) (50 * scale), (int) (30 * scale));
        g.drawLine(x, y, x + (int) (25 * scale), y - (int) (15 * scale));
        g.drawLine(x + (int) (25 * scale), y - (int) (15 * scale), x + (int) (50 * scale), y);
    }

    /**
     * Draws a single point along a spirograph. To get a better look at what each input means, look at
     * https://en.wikipedia.org/wiki/File:Resonance_Cascade.svg R is the radius of our large circle, which dicates the
     * range of the spirograph. xOrigin and yOrigin tell as where the center of the circle is located, i.e. the origin
     * of the circle with radius R. r is the radius of the sub-circle contained in R. p is a point along the radius of
     * r. t is the independent variable of the parametric equation. (In this case, time) NOTE: This method will not be
     * used due to its computational intensity
     * 
     * @param g
     * @param xOrigin
     * @param yOrigin
     * @param r
     * @param R
     * @param p
     * @param t
     * @param color
     */
    public static void drawSpirograph (Graphics g, int xOrigin, int yOrigin, double r, double R, double p, int t,
            Color color)
    {
        double k = p / r;
        double l = r / R;
        for (int i = t; i >= 0; i--)
        {
            int xFinal = xOrigin + (int) (R * ((1 - k) * Math.cos((double) (i * Math.PI / 1000))
                    + (l * k) * Math.cos(((1 - k) / k) * (double) (i * Math.PI / 1000))));
            int yFinal = yOrigin + (int) (R * ((1 - k) * Math.sin((double) (i * Math.PI / 1000))
                    - (l * k) * Math.sin(((1 - k) / k) * (double) (i * Math.PI / 1000))));
            g.setColor(color);
            g.fillOval(xFinal, yFinal, 5, 5);
        }

    }

    /**
     * Makes the background a color of your choosing, for lack of better methods.
     * 
     * @param g
     * @param x
     * @param y
     * @param color
     */
    public static void fillSpace (Graphics g, int x, int y, Color color)
    {
        g.setColor(color);
        g.fillRect(x, y, 1920, 1080);
    }

    /**
     * Paints the alien's portal and animates it
     * 
     * @param g
     * @param x
     * @param y
     * @param timer
     * @param color
     */
    public static void alienPortal (Graphics g, int x, int y, int timer, Color color)
    {
        g.setColor(color);
        int maxX = 75;
        int maxY = 310;
        if (timer >= 1000 && timer <= 2500)
        {
            int growFactorX = timer / 20 - 50;
            int growFactorY = 2 * (timer / 10 - 95);
            g.fillOval(x, y, growFactorX, growFactorY);
        }
        else if (timer > 2500 && timer <= 3000)
        {
            g.fillOval(x, y, maxX, maxY);
        }
        else if (timer > 3000 && timer <= 3100)
        {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, maxX, maxY);
        }
        else if (timer > 3100 && timer <= 3200)
        {
            g.setColor(color);
            g.fillOval(x, y, maxX, maxY);
        }
        else if (timer > 2200 && timer <= 3300)
        {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, maxX, maxY);
        }
    }

    /**
     * Creates a single star in the location and color specified
     * 
     * @param g
     * @param x
     * @param y
     * @param color
     */
    public static void drawStar (Graphics g, int x, int y, Color color)
    {
        g.setColor(color);
        g.fillRect(x - 5, y - 1, 10, 3);
        g.fillRect(x - 1, y - 5, 3, 12);

    }

    /**
     * Creates an alien flying saucer and dictates its behavior
     * 
     * @param g
     * @param x
     * @param y
     * @param timer
     * @param colorShip
     * @param colorLaser
     */
    public static void alienShip (Graphics g, int x, int y, int timer, Color colorShip, Color colorLaser)
    {
        g.setColor(colorShip);
        int xMaxBody = 500;
        int yMaxBody = 75;
        int xMaxCockpit = 60;
        int yMaxCockpit = 30;
        if (timer > 2500 && timer < 3500)
        {
            g.fillOval(x, y, (int) ((1.0 / 2.0) * (double) timer) - 1250, (int) ((3.0 / 40.0) * (double) timer) - 187);
            g.fillOval(x + 220, y - 15, (int) ((3.0 / 50.0) * (double) timer) - 150,
                    (int) ((3.0 / 100.0) * (double) timer) - 75);
        }
        if (timer >= 3500 && timer < 5000)
        {
            g.fillOval(x + (timer / 10) - 350, y, xMaxBody, yMaxBody);
            g.fillOval(x + 220 + (timer / 10) - 350, y - 15, xMaxCockpit, yMaxCockpit);
        }
        if (timer > 5000 && timer <= 6000)
        {
            int xSource = x + 370;
            int ySource = y + 40;
            g.fillOval(x + 150, y, xMaxBody, yMaxBody);
            g.fillOval(xSource, y - 15, xMaxCockpit, yMaxCockpit);
            g.setColor(colorLaser);
            g.drawLine(xSource + (timer / 2) - 2500, ySource + (int) (23.0 / 35.0 * (timer / 2) - 1642),
                    xSource + 35 + (timer / 2) - 2500, ySource + 23 + (int) (23.0 / 35.0 * (timer / 2) - 1642));

        }
        if (timer > 6000 && timer <= 7000)
        {
            int xSource = x + 370;
            int ySource = y + 40;
            g.fillOval(x + 150, y, xMaxBody, yMaxBody);
            g.fillOval(xSource, y - 15, xMaxCockpit, yMaxCockpit);
            g.setColor(colorLaser);
            g.drawLine(xSource + (timer / 2) - 3000, ySource + (int) (23.0 / 35.0 * (timer / 2) - 1970),
                    xSource + 35 + (timer / 2) - 3000, ySource + 23 + (int) (23.0 / 35.0 * (timer / 2) - 1970));
        }
        if (timer > 7000 && timer <= 8000)
        {
            int xSource = x + 370;
            int ySource = y + 40;
            g.fillOval(x + 150, y, xMaxBody, yMaxBody);
            g.fillOval(xSource, y - 15, xMaxCockpit, yMaxCockpit);
            g.setColor(colorLaser);
            g.drawLine(xSource + (timer / 2) - 3500, ySource + (int) (23.0 / 35.0 * (timer / 2) - 2298),
                    xSource + 35 + (timer / 2) - 3500, ySource + 23 + (int) (23.0 / 35.0 * (timer / 2) - 2298));
        }
        if (timer > 8000 && timer <= 9000)
        {
            int xSource = x + 370;
            int ySource = y + 40;
            g.fillOval(x + 150, y, xMaxBody, yMaxBody);
            g.fillOval(xSource, y - 15, xMaxCockpit, yMaxCockpit);
            g.setColor(colorLaser);
            g.drawLine(xSource + (timer / 2) - 4000, ySource + (int) (23.0 / 35.0 * (timer / 2) - 2626),
                    xSource + 35 + (timer / 2) - 4000, ySource + 23 + (int) (23.0 / 35.0 * (timer / 2) - 2626));
        }
        if (timer > 9000 && timer <= 9750)
        {
            int xSource = x + 370;
            g.fillOval(x + 150, y, xMaxBody, yMaxBody);
            g.fillOval(xSource, y - 15, xMaxCockpit, yMaxCockpit);
        }
        if (timer > 9750)
        {
            int xSource = x + 370;
            g.fillOval(x + 150 + ((timer / 3) - 3250), y, xMaxBody, yMaxBody);
            g.fillOval(xSource + ((timer / 3) - 3250), y - 15, xMaxCockpit, yMaxCockpit);
        }
    }

    /**
     * Creates a human space ship in the animation and dictates its behavior
     * 
     * @param g
     * @param x
     * @param y
     * @param timer
     * @param colorShip
     * @param colorLaser
     */
    public static void humanShip (Graphics g, int x, int y, int timer, Color colorShip, Color colorLaser)
    {
        // creating the spaceship and have it oscillate up and down
        if (timer >= 0 && timer <= 10000)
        {
            g.setColor(Color.ORANGE);
            g.fillOval(x + 340, y + (int) (50 * Math.sin(timer * Math.PI / 1000)), 70, 40);
            g.fillOval(x + 340, y + 40 + (int) (50 * Math.sin(timer * Math.PI / 1000)), 70, 40);
            g.setColor(colorShip);
            g.fillRect(x, y + (int) (50 * Math.sin(timer * Math.PI / 1000)), 350, 100);
            g.fillRect(x - 150, y + (int) (50 * Math.sin(timer * Math.PI / 1000)), 150, 40);
            g.fillRect(x - 200, y + (int) (50 * Math.sin(timer * Math.PI / 1000)), 50, 50);
            g.setColor(Color.BLUE);
            g.fillRect(x - 200, y + 10 + (int) (50 * Math.sin(timer * Math.PI / 1000)), 20, 10);
        }
        // explosions on the ship
        if (timer > 6000 && timer < 6250)
        {
            g.setColor(Color.YELLOW);
            g.fillOval(1150, 613 + (int) (50 * Math.sin(timer * Math.PI / 1000)), (int) ((2.0 / 5.0) * timer - 2400),
                    (int) ((2.0 / 5.0) * timer - 2400));
        }
        if (timer > 7000 && timer < 7250)
        {
            g.setColor(Color.YELLOW);
            g.fillOval(1150, 613 + (int) (50 * Math.sin(timer * Math.PI / 1000)), (int) ((2.0 / 5.0) * timer - 2800),
                    (int) ((2.0 / 5.0) * timer - 2800));
        }
        if (timer > 8000 && timer < 8250)
        {
            g.setColor(Color.YELLOW);
            g.fillOval(1150, 613 + (int) (50 * Math.sin(timer * Math.PI / 1000)), (int) ((2.0 / 5.0) * timer - 3200),
                    (int) ((2.0 / 5.0) * timer - 3200));
        }
        if (timer > 9000 && timer < 9250)
        {
            g.setColor(Color.YELLOW);
            g.fillOval(1150, 613 + (int) (50 * Math.sin(timer * Math.PI / 1000)), (int) ((2.0 / 5.0) * timer - 3600),
                    (int) ((2.0 / 5.0) * timer - 3600));
        }
        // the human ship will now die
        // rip
        if (timer >= 9250 && timer < 9750)
        {
            g.setColor(Color.YELLOW);
            g.fillOval(1125, 625 + (int) (50 * Math.sin(timer * Math.PI / 1000)), (int) ((1.0 / 10.0) * timer - 925),
                    (int) ((1.0 / 10.0) * timer - 925));
        }
        if (timer >= 9250 && timer < 9750)
        {
            g.setColor(Color.ORANGE);
            g.fillOval(1175, 613 + (int) (50 * Math.sin(timer * Math.PI / 1000)), (int) ((1.0 / 10.0) * timer - 925),
                    (int) ((1.0 / 10.0) * timer - 925));
        }
        if (timer >= 9450 && timer < 9750)
        {
            g.setColor(Color.YELLOW);
            g.fillOval(1300, 700 + (int) (50 * Math.sin(timer * Math.PI / 1000)), (int) ((1.0 / 6.0) * timer - 1575),
                    (int) ((1.0 / 6.0) * timer - 1575));
        }
        if (timer >= 9450 && timer < 9750)
        {
            g.setColor(Color.ORANGE);
            g.fillOval(1350, 675 + (int) (50 * Math.sin(timer * Math.PI / 1000)), (int) ((1.0 / 6.0) * timer - 1575),
                    (int) ((1.0 / 6.0) * timer - 1575));
        }
        if (timer >= 9650 && timer < 9750)
        {
            g.setColor(Color.YELLOW);
            g.fillOval(1500, 650 + (int) (50 * Math.sin(timer * Math.PI / 1000)), (int) ((1.0 / 2.0) * timer - 4825),
                    (int) ((1.0 / 2.0) * timer - 4825));
        }
        if (timer >= 9750 && timer < 10000)
        {
            g.setColor(Color.YELLOW);
            if ((timer >= 9800 && timer <= 9850) || (timer >= 9900))
            {
                g.setColor(Color.ORANGE);
            }
            g.fillOval(1125, 625 + (int) (50 * Math.sin(timer * Math.PI / 1000)), 50, 50);
            g.setColor(Color.ORANGE);
            if ((timer >= 9800 && timer <= 9850) || (timer >= 9900))
            {
                g.setColor(Color.YELLOW);
            }
            g.fillOval(1175, 613 + (int) (50 * Math.sin(timer * Math.PI / 1000)), 50, 50);
            g.setColor(Color.YELLOW);
            if ((timer >= 9800 && timer <= 9850) || (timer >= 9900))
            {
                g.setColor(Color.ORANGE);
            }
            g.fillOval(1300, 700 + (int) (50 * Math.sin(timer * Math.PI / 1000)), 50, 50);
            g.setColor(Color.ORANGE);
            if ((timer >= 9800 && timer <= 9850) || (timer >= 9900))
            {
                g.setColor(Color.YELLOW);
            }
            g.fillOval(1350, 675 + (int) (50 * Math.sin(timer * Math.PI / 1000)), 50, 50);
            g.setColor(Color.YELLOW);
            if ((timer >= 9800 && timer <= 9850) || (timer >= 9900))
            {
                g.setColor(Color.ORANGE);
            }
            g.fillOval(1500, 650 + (int) (50 * Math.sin(timer * Math.PI / 1000)), 50, 50);
            g.setColor(Color.ORANGE);
            if ((timer >= 9800 && timer <= 9850) || (timer >= 9900))
            {
                g.setColor(Color.YELLOW);
            }
            g.fillOval(950, 613 + (int) (50 * Math.sin(timer * Math.PI / 1000)), (int) ((14.0 / 5.0) * timer - 27300),
                    (int) ((4.0 / 3.0) * timer - 13000));
        }
    }
}
