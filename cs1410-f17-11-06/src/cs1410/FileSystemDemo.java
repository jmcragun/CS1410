package cs1410;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Collection of methods for examining the file system.
 */
public class FileSystemDemo
{
    /**
     * Runs the methods below.
     */
    public static void main (String[] args) throws URISyntaxException
    {
        // This refers to the "demo" folder in the project
        File demo = new File(System.getProperty("user.dir") + "/demo");

        //System.out.println("countFiles: " + count(demo));
        //System.out.println("largestFile: " + largestFileSize(demo));
        //System.out.println("atLeast : " + atLeast(demo, 10000));
        System.out.println("atLeast : " + atLeast(demo, 15640001));
    }

    /**
     * If fd is a file, returns 1. Otherwise, returns the number of files/directories that are contained, directly or
     * indirectly, inside of directory fd.
     */
    public static int count (File fd)
    {
        if (fd.isFile())
        {
            return 1;
        }
        else
        {
            // Here is a recursive accumulation loop
            int total = 0;

            if (fd.listFiles() != null)
            {
                for (File f : fd.listFiles())
                {
                    total = total + count(f);
                }
            }

            return total + 1;
        }
    }

    /**
     * If fd is a file, returns its size. Otherwise, returns the size of the largest file/directory that is contained,
     * directly or indirectly, inside of directory fd.
     */
    public static long largestFileSize (File fd)
    {
        if (fd.isFile())
        {
            return fd.length();
        }
        else
        {
            // Here is a recursive optimization loop
            long largestSoFar = fd.length();

            if (fd.listFiles() != null)
            {
                for (File f : fd.listFiles())
                {
                    largestSoFar = Math.max(largestSoFar, largestFileSize(f));
                }
            }

            return largestSoFar;
        }
    }

    /**
     * If fd is a file, reports whether its size is at least b bytes. Otherwise, reports whether 
     * directory fd contains,
     * either directly or indirectly, a file/directory whose size is at least b bytes.
     */
    public static boolean atLeast (File fd, long bytes)
    {
        if (fd.isFile())
        {
            return fd.length() >= bytes;
        }
        else
        {
            if (fd.length() >= bytes)
            {
                return true;
            }
            
            if (fd.listFiles() != null)
            {
                for (File f : fd.listFiles())
                {
                    if (atLeast(f, bytes))
                    {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
