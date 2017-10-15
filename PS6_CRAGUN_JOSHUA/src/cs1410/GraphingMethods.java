package cs1410;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Methods in support of PS6.
 * 
 * @author Joshua Cragun and Joe Zachary
 */
public class GraphingMethods
{
    /**
     * Constant used to request a max operation
     */
    public final static int MAX = 0;

    /**
     * Constant used to request a min operation
     */
    public final static int MIN = 1;

    /**
     * Constant used to request a sum operation
     */
    public final static int SUM = 2;

    /**
     * Constant used to request an average operation
     */
    public final static int AVG = 3;

    /**
     * The dataSource must consist of one or more lines. If there is not at least one line, the method throws an
     * IllegalArgumentException whose message explains what is wrong.
     * 
     * Each line must consist of some text (a key), followed by a tab character, followed by a double literal (a value),
     * followed by a newline.
     * 
     * If any lines are encountered that don't meet this criteria, the method throws an IllegalArgumentException whose
     * message explains what is wrong.
     * 
     * Otherwise, the map returned by the method (here called categoryMap) must have all of these properties:
     * 
     * (1) The set of keys contained by categoryMap must be the same as the set of keys that occur in the Scanner
     * 
     * (2) The list valueMap.get(key) must contain exactly the same numbers that appear as values on lines in the
     * Scanner that begin with key. The values must occur in the list in the same order as they appear in the Scanner.
     * 
     * For example, if the Scanner contains
     * 
     * <pre>
     * Utah        10
     * Nevada       3
     * Utah         2
     * California  14
     * Arizona     21
     * Utah         2
     * California   7
     * California   6
     * Nevada      11
     * California   1
     * </pre>
     * 
     * (where the spaces in each line are intended to be a single tab), then this map should be returned:
     * 
     * <pre>
     *  Arizona    {21}
     *  California {14, 7, 6, 1} 
     *  Nevada     {3, 11}
     *  Utah       {10, 2, 2}
     * </pre>
     */
    public static TreeMap<String, ArrayList<Double>> readTable (Scanner dataSource)
    {
        // If there is not at least one line, the method throws an IllegalArgumentException whose message explains what
        // is wrong.
        if (!dataSource.hasNextLine())
        {
            throw new IllegalArgumentException("ERROR: Data source must have at least one line");
        }
        TreeMap<String, ArrayList<Double>> map = new TreeMap<>();
        while (dataSource.hasNextLine())
        {
            // Read the file line by line
            String dataLine = dataSource.nextLine();
            // If map doesn't have the key read in the line, add it
            if (map.get(getKey(dataLine)) == null)
            {
                ArrayList<Double> values = new ArrayList<Double>();
                values.add(getValue(dataLine));
                map.put(getKey(dataLine), values);
            }
            // Otherwise update the map
            else
            {
                ArrayList<Double> values = map.get(getKey(dataLine));
                values.add(getValue(dataLine));
                map.put(getKey(dataLine), values);
            }
        }
        return map;
    }

    /**
     * This helper method finds the first instance of a tab character inside a string and returns its index. If the
     * search fails it throws an IllegalArgumentException, because from the way this method will be used it implies that
     * the source file was formatted incorrectly. The method also performs preliminary formatting tests.
     * 
     * @param dataLine
     * @return
     */
    public static int getTabCharIndex (String dataLine)
    {
        int i = 0;
        // Checks to make sure input has size greater than zero, starts and ends with non-tab character,
        // and actually contains a tab character
        if (dataLine.equals("") || dataLine.charAt(0) == '\t' || !dataLine.contains("\t")
                || dataLine.charAt(dataLine.length() - 1) == '\t')
        {
            throw new IllegalArgumentException("ERROR: Source file could not be read\n       (Bad formatting)");
        }
        // Search for \t character
        while (i < dataLine.length())
        {
            if (dataLine.charAt(i) == '\t')
            {
                break;
            }
            i++;
        }
        return i;
    }

    /**
     * Finds the key that will be added to the returned TreeMap in readTable
     * 
     * @param dataLine
     * @return
     */
    public static String getKey (String dataLine)
    {
        // The key will be all the text from the beginning until the first tab occurrence, so it's grabbed and returned
        String result = dataLine.substring(0, getTabCharIndex(dataLine));
        return result;
    }

    /**
     * Gets the value tied to a key on one line of data from the source file, and tests formatting once again
     * 
     * @param dataLine
     * @return
     */
    public static double getValue (String dataLine)
    {
        // Grab all text before the key, but it might have poor formating
        // So helper methods are used to make sure the value is a number and doesn't have any extra whitespace
        String resultCandidate = dataLine.substring(getTabCharIndex(dataLine) + 1);
        if (isNumeric(resultCandidate) && !(resultCandidate.contains("\t") || resultCandidate.contains(" ")))
        {
            return Double.parseDouble(resultCandidate);
        }
        // If it passes, return it, otherwise throw an exception
        else
        {
            throw new IllegalArgumentException("ERROR: Source file could not be read\n       (Bad formatting)");
        }
    }

    /**
     * Helper method determines if a string is a number or not
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric (String str)
    {
        // Return true if input can be parsed as a double, otherwise return false
        try
        {
            double d = Double.parseDouble(str);
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    /**
     * If categoryMap is of size zero, throws an IllegalArgumentException whose message explains what is wrong.
     * 
     * Else if any of the values in the category map is an empty set, throws an IllegalArgumentException whose message
     * explains what is wrong.
     * 
     * Else if any of the numbers in the categoryMap is not positive, throws an IllegalAgumentException whose message
     * explains what is wrong.
     * 
     * Else if operation is anything other than SUM, AVG, MAX, or MIN, throws an IllegalArgumentException whose message
     * explains what is wrong.
     *
     * Else, returns a TreeMap<String, Double> (here called summaryMap) such that:
     * 
     * (1) The sets of keys contained by categoryMap and summaryMap are the same
     * 
     * (2) For all keys, summaryMap.get(key) is the result of combining the numbers contained in the set
     * categoryMap.get(key) using the specified operation. If the operation is MAX, "combining" means finding the
     * largest of the numbers. If the operation is MIN, "combining" means finding the smallest of the numbers. If the
     * operation is SUM, combining means summing the numbers. If the operation is AVG, combining means averaging the
     * numbers.
     * 
     * For example, suppose the categoryMap maps like this:
     * 
     * <pre>
     *  Arizona    {21
     *  California {14, 7, 6, 1} 
     *  Nevada     {3, 11}
     *  Utah       {10, 2, 2}
     * </pre>
     * 
     * and the operation is SUM. The map that is returned must map like this:
     * 
     * <pre>
     *  Arizona    21
     *  California 28 
     *  Nevada     14
     *  Utah       14
     * </pre>
     */
    public static TreeMap<String, Double> prepareGraph (TreeMap<String, ArrayList<Double>> categoryMap, int operation)
    {
        TreeMap<String, Double> summaryMap = new TreeMap<>();
        // Make sure inputs meet requirements
        validatePrepareGraphInput(categoryMap, operation);
        // Perform the indicated operation based on the value of operation
        switch (operation)
        {
            case 0:
                summaryMap = maxSummary(categoryMap);
                break;
            case 1:
                summaryMap = minSummary(categoryMap);
                break;
            case 2:
                summaryMap = sumSummary(categoryMap);
                break;
            case 3:
                summaryMap = avgSummary(categoryMap);
                break;
        }
        return summaryMap;
    }

    /**
     * Helper method that makes sure all the input given to prepareGraphInput is valid
     * 
     * @param categoryMap
     * @param operation
     */
    public static void validatePrepareGraphInput (TreeMap<String, ArrayList<Double>> categoryMap, int operation)
    {
        // If categoryMap is of size zero, throws an IllegalArgumentException whose message explains what is wrong.
        if (categoryMap.size() == 0)
        {
            throw new IllegalArgumentException("ERROR: No information in categoryMap");
        }
        // Else if operation is anything other than SUM, AVG, MAX, or MIN, throws an IllegalArgumentException whose
        // message explains what is wrong.
        if (!(operation == 0 || operation == 1 || operation == 2 || operation == 3))
        {
            throw new IllegalArgumentException("ERROR: Invalid operation. Please enter a number between 0 and 3");
        }
        for (String key : categoryMap.keySet())
        {
            ArrayList<Double> values = categoryMap.get(key);
            // Else if any of the values in the category map is an empty set, throws an IllegalArgumentException whose
            // message explains what is wrong.
            if (values.isEmpty())
            {
                throw new IllegalArgumentException("ERROR: Empty value set");
            }
            // Else if any of the numbers in the categoryMap is not positive, throws an IllegalAgumentException whose
            // message explains what is wrong.
            for (double value : values)
            {
                if (value < 0)
                {
                    throw new IllegalArgumentException("ERROR: Negative values not accepted");
                }
            }
        }
    }

    /**
     * Helper method that returns a TreeMap with each key key in the categoryMap, associated with the max of the key's
     * associated ArrayList
     * 
     * @param categoryMap
     * @return
     */
    public static TreeMap<String, Double> maxSummary (TreeMap<String, ArrayList<Double>> categoryMap)
    {
        TreeMap<String, Double> result = new TreeMap<String, Double>();
        double max;
        // For every key in categoryMap
        for (String key : categoryMap.keySet())
        {
            ArrayList<Double> values = categoryMap.get(key);
            max = values.get(0);
            // And for every value in categoryMap's ArrayList
            for (Double value : values)
            {
                // If the current value is greater than max, set max to that value
                if (value > max)
                {
                    max = value;
                }
            }
            // Put the key and the max for the key into the result
            result.put(key, max);
        }
        return result;
    }

    /**
     * Helper method that returns a TreeMap with each key key in the categoryMap, associated with the minimum of the
     * key's associated ArrayList
     * 
     * @param categoryMap
     * @return
     */
    public static TreeMap<String, Double> minSummary (TreeMap<String, ArrayList<Double>> categoryMap)
    {
        TreeMap<String, Double> result = new TreeMap<String, Double>();
        double min;
        // For every key in categoryMap
        for (String key : categoryMap.keySet())
        {
            ArrayList<Double> values = categoryMap.get(key);
            min = values.get(0);
            // And for every value in categoryMap's ArrayList
            for (Double value : values)
            {
                // If the current value is less than min, set min to that value
                if (value < min)
                {
                    min = value;
                }
            }
            // Put the key and the min for the key into the result
            result.put(key, min);
        }
        return result;
    }

    /**
     * Helper method that returns a TreeMap with each key key in the categoryMap, associated with the sum of all
     * elements in the key's associated ArrayList
     * 
     * @param categoryMap
     * @return
     */
    public static TreeMap<String, Double> sumSummary (TreeMap<String, ArrayList<Double>> categoryMap)
    {
        TreeMap<String, Double> result = new TreeMap<String, Double>();
        // For every key in categoryMap
        for (String key : categoryMap.keySet())
        {
            ArrayList<Double> values = categoryMap.get(key);
            double sum = 0;
            // Add every value associate to that key
            for (Double value : values)
            {
                sum += value;
            }
            // Associate the key and sum and put it into the result
            result.put(key, sum);
        }
        return result;
    }

    /**
     * Helper method that returns a TreeMap with each key in the categoryMap, associated with the mean value of the
     * key's associated ArrayList
     * 
     * @param categoryMap
     * @return
     */
    public static TreeMap<String, Double> avgSummary (TreeMap<String, ArrayList<Double>> categoryMap)
    {
        // This is exactly the same as sumSummary except we divide the sub by the size of the list at the end
        TreeMap<String, Double> result = new TreeMap<String, Double>();
        for (String key : categoryMap.keySet())
        {
            ArrayList<Double> values = categoryMap.get(key);
            double sum = 0;
            for (Double value : values)
            {
                sum += value;
            }
            result.put(key, (sum / values.size()));
        }
        return result;
    }

    /**
     * If colorMap is empty, throws an IllegalArgumentException.
     * 
     * If there is a key in colorMap that does not occur in summaryMap, throws an IllegalArgumentException whose message
     * explains what is wrong.
     * 
     * If any of the numbers in the summaryMap is non-positive, throws an IllegalArgumentException whose message
     * explains what is wrong.
     * 
     * Otherwise, displays on g the subset of the data contained in summaryMap that has a key that appears in colorMap
     * with either a pie chart (if usePieChart is true) or a bar graph (otherwise), using the colors in colorMap.
     * 
     * Let SUM be the sum of all the values in summaryMap whose keys also appear in colorMap, let KEY be a key in
     * colorMap, let VALUE be the value to which KEY maps in summaryMap, and let COLOR be the color to which KEY maps in
     * colorMap. The area of KEY's slice (in a pie chart) and the length of KEY's bar (in a bar graph) must be
     * proportional to VALUE/SUM. The slice/bar should be labeled with both KEY and VALUE, and it should be colored with
     * COLOR.
     * 
     * For example, suppose summaryMap has this mapping:
     * 
     * <pre>
     *  Arizona    21
     *  California 28 
     *  Nevada     14
     *  Utah       14
     * </pre>
     * 
     * and colorMap has this mapping:
     * 
     * <pre>
     *  California Color.GREEN
     *  Nevada     Color.BLUE
     *  Utah       Color.RED
     * </pre>
     * 
     * Since Arizona does not appear as a key in colorMap, Arizona's entry in summaryMap is ignored.
     * 
     * In a pie chart Utah and Nevada should each have a quarter of the pie and California should have half. In a bar
     * graph, California's line should be twice as long as Utah's and Nevada's. Utah's slice/bar should be red, Nevada's
     * blue, and California's green.
     * 
     * The method should display the pie chart or bar graph by drawing on the g parameter. The example code below draws
     * both a pie chart and a bar graph for the situation described above.
     */
    public static void drawGraph (Graphics g, TreeMap<String, Double> summaryMap, TreeMap<String, Color> colorMap,
            boolean usePieChart)
    {
        // This implementation ignores its parameters (except for usePieChart) and draws a pie chart or a bar graph
        // for the data described in the Javadoc.

        final int TOP = 10;        // Offset of graph from top edge
        final int LEFT = 10;       // Offset of graph from left edge
        final int DIAM = 300;      // Diameter of pie chart
        final int WIDTH = 10;      // Width of bar in bar chart

        validateDrawGraphArguments(summaryMap, colorMap);
        // The sum of each key's value in summaryMap shared between summaryMap and colorMap is useful, so it's
        // calculated here
        double sum = 0;
        for (String key : colorMap.keySet())
        {
            sum += summaryMap.get(key);
        }
        // Draw a pie chart if requested
        if (usePieChart)
        {
            int iterator = 1;
            int prevAngle = 0;
            int arcAngle = 0;
            // For every key in colorMap, create a corresponding arc
            for (String key : colorMap.keySet())
            {
                Color color = colorMap.get(key);
                // The arc angle will be the ration between the key's value and their sum, multiplied by 360
                arcAngle = (int) (360 * (summaryMap.get(key) / sum));
                g.setColor(color);
                g.fillArc(LEFT, TOP, DIAM, DIAM, prevAngle, arcAngle);
                // Add the arc angle to prevAngle so that the next arc doesn't overlap
                prevAngle += arcAngle;
                g.setColor(Color.BLACK);
                // Write the key and summary map's values at the coordinates given, and increment iterator so that the
                // strings don't overlap.
                g.drawString(key + " " + summaryMap.get(key), LEFT + DIAM + 4 * WIDTH, TOP + (iterator * WIDTH));
                iterator += 2;
            }
        }

        // Draw a bar chart if requested
        else
        {
            int iterator1 = 0;
            int iterator2 = 1;
            for (String key : colorMap.keySet())
            {
                Color color = colorMap.get(key);
                g.setColor(color);
                // Draw the bar graph rectangle at the complementary x position such that x pos + width = DIAM
                g.fillRect(DIAM - (int) (DIAM * summaryMap.get(key) / sum), TOP + (WIDTH * iterator1),
                        (int) (DIAM * summaryMap.get(key) / sum), 2 * WIDTH);
                // Increment iterator1 so that the next bar will be below the first
                iterator1 += 3;
                g.setColor(Color.BLACK);
                g.drawString(key + " " + summaryMap.get(key), LEFT + DIAM + 2 * WIDTH,
                        TOP + (iterator2 * WIDTH) + WIDTH / 2);
                // Write the key and summary map's values at the coordinates given, and increment iterator2 so that the
                // strings don't overlap
                iterator2 += 3;
            }
        }
    }

    /**
     * This helper method takes in the inputs given to drawGraph and validates them
     * 
     * @param summaryMap
     * @param colorMap
     */
    public static void validateDrawGraphArguments (TreeMap<String, Double> summaryMap, TreeMap<String, Color> colorMap)
    {
        // Makes sure the colorMap isn't empty
        if (colorMap.isEmpty())
        {
            throw new IllegalArgumentException("ERROR: colorMap is empty");
        }
        // For each key in colorMap, make sure summaryMap has that key too, and that none of the values associeted the
        // summary map's keys are below 0
        for (String key : colorMap.keySet())
        {
            if (!summaryMap.containsKey(key))
            {
                throw new IllegalArgumentException("ERROR: colorMap keys are not subset of summaryMap keys");
            }
            if (summaryMap.get(key) < 0)
            {
                throw new IllegalArgumentException("ERROR: Negative values in summaryMap");
            }
        }
    }
}
