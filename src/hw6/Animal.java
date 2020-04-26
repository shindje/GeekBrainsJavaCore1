package hw6;

public class Animal {

    private String name;
    private static int count;
    double maxRun;
    double maxSwim;
    double maxJump;

    String getName() {
        return name;
    }

    Animal (String name) {
        this.name = name;
        count++;
        System.out.printf(getClass().getSimpleName() + " %s created. Animals: %s.", getName(), count);
    }

    void run(double length) {
        System.out.printf(" Result: %s\n", doAction("run", length, maxRun));
    }

    void swim(double length) {
        System.out.printf(" Result: %s\n", doAction("swim", length, maxSwim));
    }

    void jump(double heigth) {
        System.out.printf(" Result: %s\n", doAction("jump", heigth, maxJump));
    }

    boolean doAction (String action, double param, double maxParam) {
        if (param > maxParam) {
            System.out.printf(getClass().getSimpleName() + " %s CAN'T %s %.1f м.", getName(), action, param);
            return false;
        } else {
            System.out.printf(getClass().getSimpleName() + " %s %s %.1f м.", getName(), action, param);
            return true;
        }
    }
}
