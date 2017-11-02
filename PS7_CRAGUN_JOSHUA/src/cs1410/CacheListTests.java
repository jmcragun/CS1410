package cs1410;

import static org.junit.Assert.*;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;

public class CacheListTests
{
    @Test
    public void test () throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTitleConstraint("Tooth");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCRQWK", selected.get(0).getGcCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test2 () throws IOException
    {
        new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRrWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
    }
    
    @Test(expected = IOException.class)
    public void test3 () throws IOException
    {
        File file = new File("C:\\Program Files (x86)\\a");
        new CacheList(new Scanner(file));
    }
    @Test
    public void test4 () throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setOwnerConstraint("geo");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCRQWK", selected.get(0).getGcCode());
    }
}
