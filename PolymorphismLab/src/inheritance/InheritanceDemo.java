package inheritance;

public class InheritanceDemo
{
    /**
     * Demonstrates some aspects of interfaces
     */
    public static void main (String[] args)
    {
        // Create four objects
        Animal animal1 = new Animal(3);
        Dog dog1 = new Dog("Collie");
        Cat cat1 = new Cat("Sheba");
        Snake snake1 = new Snake();

        // Exercise 1. Can you predict which of the method calls below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // method call does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        System.out.println(animal1.getLegCount());
        System.out.println(dog1.getLegCount());
        System.out.println(cat1.getLegCount());
        System.out.println(snake1.getLegCount());

        // Exercise 2. Can you predict which of the method calls below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // method call does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        // System.out.println(animal1.getBreed());
        System.out.println(dog1.getBreed());
        // System.out.println(cat1.getBreed());
        // System.out.println(snake1.getBreed());

        // Exercise 3. Can you predict which of the method calls below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // method call does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        // System.out.println(animal1.getBreed());
        Animal animal2 = dog1;
        // System.out.println(animal2.getBreed());
        Animal animal3 = cat1;
        // System.out.println(animal3.getBreed());
        Animal animal4 = snake1;
        // System.out.println(animal4.getBreed());

        // Exercise 4. Can you predict which of the method calls below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // method call does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        System.out.println(animal1.speak());
        System.out.println(dog1.speak());
        System.out.println(cat1.speak());
        System.out.println(snake1.speak());

        // Exercise 5. Can you predict which of the method calls below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // method call does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        System.out.println(animal1.speak());
        Animal animal5 = dog1;
        System.out.println(animal5.speak());
        Animal animal6 = cat1;
        System.out.println(animal6.speak());
        Animal animal7 = snake1;
        System.out.println(animal7.speak());

        // Exercise 6. Can you predict which of the method calls below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // method call does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        System.out.println(animal1.shout());
        System.out.println(dog1.shout());
        System.out.println(cat1.shout());
        System.out.println(snake1.shout());

        // Exercise 7. Can you predict which of the method calls below will fail to compile?
        // Uncomment each method call in turn to see if your prediction was right. If a
        // method call does not compile, comment it back out. When you finish, predict what
        // the program will print, then run it to see if you are right.

        System.out.println(animal1.shout());
        Animal animal8 = dog1;
        System.out.println(animal8.shout());
        Animal animal9 = cat1;
        System.out.println(animal9.shout());
        Animal animal10 = snake1;
        // System.out.println(animal10.shout());
    }
}
