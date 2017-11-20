package cs1410;

import java.util.Arrays;

public class MultiDimensionalArrays
{
    public static void main (String[] args)
    {
        // A rectangular two-dimensional array
        int[][] a = new int[5][6];

        // Fill in the array with nested loops
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a[i].length; j++)
            {
                a[i][j] = i + j;
            }
        }

        // Extract values from the array
        int[] azero = a[0];
        System.out.println(Arrays.toString(azero));
        int n = a[3][1];
        System.out.println(n);

        // A "ragged" two-dimensional array
        int[][] b = new int[5][];
        for (int i = 0; i < b.length; i++)
        {
            b[i] = new int[i + 1];
        }

        // Fill in the array with nested loops
        for (int i = 0; i < b.length; i++)
        {
            for (int j = 0; j < b[i].length; j++)
            {
                b[i][j] = i + j;
            }
        }

        // Extract values from the array
        int[] bzero = b[0];
        System.out.println(Arrays.toString(bzero));
        int m = b[3][1];
        System.out.println(m);
    }
}
