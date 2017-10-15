package cs1410;

import static org.junit.Assert.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import org.junit.Test;

public class MyTestCases
{
    @Test(expected = IllegalArgumentException.class)
    public void testReadTable ()
    {
        try (Scanner scn = new Scanner("\t\t"))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadTable1 ()
    {
        try (Scanner scn = new Scanner("Android50\t"))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadTable2 ()
    {
        try (Scanner scn = new Scanner("a\ta"))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadTable3 ()
    {
        try (Scanner scn = new Scanner("a\t 50"))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadTable4 ()
    {
        try (Scanner scn = new Scanner("a\t\t4"))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadTable5 ()
    {
        try (Scanner scn = new Scanner(""))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadTable6 ()
    {
        try (Scanner scn = new Scanner("a\t5\nb\t2\nc\t 6\nd\t3\n"))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrepareGraph ()
    {
        GraphingMethods.prepareGraph(new TreeMap<String, ArrayList<Double>>(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrepareGraph1 ()
    {
        TreeMap<String, ArrayList<Double>> test = new TreeMap<String, ArrayList<Double>>();
        ArrayList<Double> testArray1 = new ArrayList<Double>();
        testArray1.add(5.0);
        testArray1.add(1.0);
        test.put("a", testArray1);
        ArrayList<Double> testArray2 = new ArrayList<Double>();
        test.put("b", testArray2);
        TreeMap<String, Double> result = GraphingMethods.prepareGraph(test, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrepareGraph2 ()
    {
        TreeMap<String, ArrayList<Double>> test = new TreeMap<String, ArrayList<Double>>();
        ArrayList<Double> testArray1 = new ArrayList<Double>();
        testArray1.add(5.0);
        testArray1.add(1.0);
        test.put("a", testArray1);
        TreeMap<String, Double> result = GraphingMethods.prepareGraph(test, 60);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrepareGraph3 ()
    {
        TreeMap<String, ArrayList<Double>> test = new TreeMap<String, ArrayList<Double>>();
        ArrayList<Double> testArray1 = new ArrayList<Double>();
        testArray1.add(5.0);
        testArray1.add(-1.0);
        test.put("a", testArray1);
        TreeMap<String, Double> result = GraphingMethods.prepareGraph(test, 0);
    }

    @Test
    public void testPrepareGraph4 ()
    {
        TreeMap<String, ArrayList<Double>> testInput = new TreeMap<String, ArrayList<Double>>();
        ArrayList<Double> testArray1 = new ArrayList<Double>();
        testArray1.add(5.0);
        testArray1.add(1.0);
        testInput.put("a", testArray1);
        ArrayList<Double> testArray2 = new ArrayList<Double>();
        testArray2.add(3.0);
        testArray2.add(4.0);
        testArray2.add(1.0);
        testInput.put("b", testArray2);
        
        TreeMap<String, Double> expected = new TreeMap<String, Double>();
        expected.put("a", 6.0);
        expected.put("b", 8.0);
        
        TreeMap<String, Double> actual = GraphingMethods.prepareGraph(testInput, 2);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testPrepareGraph5 ()
    {
        TreeMap<String, ArrayList<Double>> testInput = new TreeMap<String, ArrayList<Double>>();
        ArrayList<Double> testArray1 = new ArrayList<Double>();
        testArray1.add(5.0);
        testArray1.add(1.0);
        testInput.put("a", testArray1);
        ArrayList<Double> testArray2 = new ArrayList<Double>();
        testArray2.add(3.0);
        testArray2.add(4.0);
        testArray2.add(1.0);
        testInput.put("b", testArray2);
        
        TreeMap<String, Double> expected = new TreeMap<String, Double>();
        expected.put("a", 5.0);
        expected.put("b", 4.0);
        
        TreeMap<String, Double> actual = GraphingMethods.prepareGraph(testInput, 0);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testPrepareGraph6 ()
    {
        TreeMap<String, ArrayList<Double>> testInput = new TreeMap<String, ArrayList<Double>>();
        ArrayList<Double> testArray1 = new ArrayList<Double>();
        testArray1.add(5.0);
        testArray1.add(1.0);
        testInput.put("a", testArray1);
        ArrayList<Double> testArray2 = new ArrayList<Double>();
        testArray2.add(3.0);
        testArray2.add(4.0);
        testArray2.add(1.0);
        testInput.put("b", testArray2);
        
        TreeMap<String, Double> expected = new TreeMap<String, Double>();
        expected.put("a", 1.0);
        expected.put("b", 1.0);
        
        TreeMap<String, Double> actual = GraphingMethods.prepareGraph(testInput, 1);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testPrepareGraph7 ()
    {
        TreeMap<String, ArrayList<Double>> testInput = new TreeMap<String, ArrayList<Double>>();
        ArrayList<Double> testArray1 = new ArrayList<Double>();
        testArray1.add(5.0);
        testArray1.add(1.0);
        testInput.put("a", testArray1);
        ArrayList<Double> testArray2 = new ArrayList<Double>();
        testArray2.add(3.0);
        testArray2.add(4.0);
        testArray2.add(1.0);
        testInput.put("b", testArray2);
        
        TreeMap<String, Double> expected = new TreeMap<String, Double>();
        expected.put("a", 3.0);
        expected.put("b", 2.6666666666666665);
        
        TreeMap<String, Double> actual = GraphingMethods.prepareGraph(testInput, 3);
        System.out.println(actual);
        assertEquals(expected, actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testDrawGraph()
    {
        TreeMap<String, Double> summaryMap = new TreeMap<String, Double>();
        summaryMap.put("a", 5.0);
        summaryMap.put("b", 2.0);
        summaryMap.put("c", 3.0);
        TreeMap<String, Color> colorMap = new TreeMap<String, Color>();
        colorMap.put("a", Color.GREEN);
        colorMap.put("b", Color.RED);
        colorMap.put("c", Color.BLUE);
        colorMap.put("d", Color.ORANGE);
        GraphingMethods.validateDrawGraphArguments(summaryMap, colorMap);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDrawGraph1()
    {
        TreeMap<String, Double> summaryMap = new TreeMap<String, Double>();
        summaryMap.put("a", 5.0);
        summaryMap.put("b", -12.0);
        summaryMap.put("c", 3.0);
        TreeMap<String, Color> colorMap = new TreeMap<String, Color>();
        colorMap.put("a", Color.GREEN);
        colorMap.put("b", Color.RED);
        colorMap.put("c", Color.BLUE);
        GraphingMethods.validateDrawGraphArguments(summaryMap, colorMap);
    }
}
