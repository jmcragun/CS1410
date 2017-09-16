package cs1410;
/**
 * 
 * @author Joshua Cragun
 *
 */
public class StopSigns2
{
    /**
     * Also draws stop signs
     * @param args
     */
    public static void main(String[] args)
    {
        emptySign();
        labeledSign();
        emptySign();
        labeledSign();
        emptySign();

    }
    public static void signTop()
    {
        //prints top of sign
        System.out.println("  ______  ");
        System.out.println(" /      \\ ");
        System.out.println("/        \\");
    }
    public static void signBottom()
    {
        //prints bottom of sign
        System.out.println("\\        /");
        System.out.println(" \\______/ ");
        System.out.println();
    }
    public static void signLabel(String label)
    {
        //prints label of sign
        System.out.println("|  " + label + "  |");
    }
    public static void emptySign()
    {
        //makes an empty stop sign
        signTop();
        signBottom();
    }
    public static void labeledSign()
    {
        //makes a labeled stop sign
        signTop();
        signLabel("STOP");
        signBottom();
    }
    /*
     *  Teacher: "Kids, what does the chicken give you?"
        Student: "Meat!"
        Teacher: "Very good! Now what does the pig give you?"
        Student: "Bacon!"
        Teacher: "Great! And what does the fat cow give you?"
        Student: "Homework!"
     */
}
