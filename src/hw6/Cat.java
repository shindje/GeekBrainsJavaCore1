package hw6;

public class Cat extends Animal {

    private static int count;

    Cat(String name) {
        super(name);
        count++;
        maxRun = 200;
        maxSwim = 0;
        maxJump = 2;
        System.out.printf(" Cats: %s.\n", count);
    }

    Cat(String name, double maxRun, double maxSwim, double maxJump) {
        this(name);
        this.maxRun = maxRun;
        this.maxSwim = maxSwim;
        this.maxJump = maxJump;
    }
}
