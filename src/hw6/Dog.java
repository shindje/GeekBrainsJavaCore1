package hw6;

public class Dog extends Animal {

    private static int count;

    Dog(String name) {
        super(name);
        count++;
        maxRun = 500;
        maxSwim = 10;
        maxJump = 0.5;
        System.out.printf(" Dogs: %s.\n", count);
    }

    Dog(String name, double maxRun, double maxSwim, double maxJump) {
        this(name);
        this.maxRun = maxRun;
        this.maxSwim = maxSwim;
        this.maxJump = maxJump;
    }

}
