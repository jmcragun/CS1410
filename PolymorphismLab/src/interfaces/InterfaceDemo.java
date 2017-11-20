package interfaces;

import java.util.ArrayList;

public class InterfaceDemo
{
    /**
     * Demonstrates some aspects of interfaces
     */
    public static void main (String[] args)
    {
        // Create six objects
        A a1 = new A("hello", "when the shark bites");
        A a2 = new A("how do?", "oh let the sun beat down upon my face");
        B b1 = new B("bonjour");
        B b2 = new B("salut");
        C c1 = new C("the itsy bitsy spider");
        C c2 = new C("happy birthday to you");

        // Exercise 1. Can you predict which of the method calls below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // method call does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        // System.out.println(a1.speak());
        // System.out.println(a1.sing());
        // System.out.println(b1.speak());
        // System.out.println(b1.sing());
        // System.out.println(c1.speak());
        // System.out.println(c2.speak());

        // Exercise 2. Can you predict which of the assignments below will fail to compile?
        // Uncomment each assignment in turn to see if your prediction was right. If an
        // assignment does not compile, comment it back out.

        // Speaker sp1 = a1;
        // Speaker sp2 = b1;
        // Speaker sp3 = c1;
        // Singer sn1 = a1;
        // Singer sn2 = b1;
        // Singer sn3 = c1;

        // Exercise 3. Can you predict which of the method calls below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // method call does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        // System.out.println(sp1.speak());
        // System.out.println(sp1.sing());
        // System.out.println(sp2.speak());
        // System.out.println(sp2.sing());
        // System.out.println(sn1.speak());
        // System.out.println(sn1.sing());
        // System.out.println(sn3.speak());
        // System.out.println(sn3.sing());

        // Exercise 4. Can you predict which of the calls to add below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // call to add does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        ArrayList<Speaker> speakers = new ArrayList<>();
        speakers.add(a1);
        speakers.add(a2);
        speakers.add(b1);
        speakers.add(b2);
        // speakers.add(c1);
        // speakers.add(c2);
        allSpeak(speakers);

        // Exercise 5. Can you predict which of the calls to add below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // call to add does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        ArrayList<Singer> singers = new ArrayList<>();
        singers.add(a1);
        singers.add(a2);
        // singers.add(b1);
        // singers.add(b2);
        singers.add(c1);
        singers.add(c2);
        allSing(singers);

        // Exercise 6. Can you predict which of the calls to add below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // call to add does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        ArrayList<B> bSingers = new ArrayList<>();
        // bSingers.add(a1);
        // bSingers.add(a2);
        bSingers.add(b1);
        bSingers.add(b2);
        // bSingers.add(c1);
        // bSingers.add(c2);
        allSingB(bSingers);
    }

    /**
     * Prints out the result of having each object in the list speak
     */
    private static void allSpeak (ArrayList<Speaker> speakers)
    {
        System.out.println("Speaking:");
        for (Speaker s : speakers)
        {
            System.out.println(" " + s.speak());
        }
        System.out.println();
    }

    /**
     * Prints out the result of having each object in the list sing
     */
    private static void allSing (ArrayList<Singer> singers)
    {
        System.out.println("Singing:");
        for (Singer s : singers)
        {
            System.out.println(" " + s.sing());
        }
        System.out.println();
    }

    /**
     * Prints out the result of having each object in the list sing
     */
    private static void allSingB (ArrayList<B> singers)
    {
        System.out.println("Singing with B:");
        for (B s : singers)
        {
            System.out.println(" " + s.sing());
        }
        System.out.println();
    }
}
