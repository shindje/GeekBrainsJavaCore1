package hw6;

public class Main {

    public static void main(String[] args) {
        Cat cat1 = new Cat("Мурзик");
        cat1.jump(1.5);
        cat1.swim(1);
        cat1.run(500);

        Cat cat2 = new Cat("Васька", 600, 0, 3);
        cat2.jump(3);
        cat2.swim(1);
        cat2.run(500);

        Dog dog1 = new Dog("Жучка");
        dog1.jump(1.5);
        dog1.swim(1);
        dog1.run(500);

        Dog dog2 = new Dog("Бобик", 300, 5, 2);
        dog2.jump(1.5);
        dog2.swim(1);
        dog2.run(500);
    }
}
