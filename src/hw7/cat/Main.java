package hw7.cat;

public class Main {

    public static void main(String[] args) {
        Cat[] cats = new Cat[10];
        Plate plate = new Plate(45);
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat("cat_" + i);
            cats[i].eat(plate);
        }
        plate.info();

        for (int i = 0; i < cats.length; i++) {
            System.out.println("Cat " + cats[i].getName() + " " + cats[i].getHungry());
        }

        plate.addFood(30);
        System.out.println("Добавили 30 еды");

        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(plate);
        }
        plate.info();

        for (int i = 0; i < cats.length; i++) {
            System.out.println("Cat " + cats[i].getName() + " " + cats[i].getHungry());
        }
    }

}
