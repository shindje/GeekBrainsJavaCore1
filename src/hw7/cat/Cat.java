package hw7.cat;

public class Cat {
    String name;
    final int APPETITE = 10;
    private boolean hungry = true;

    public Cat(String name) {
        this.name = name;
    }

    public void eat(Plate plate){
        if (hungry) {
            if (plate.decreaseFood(APPETITE)) {
                System.out.println("Cat " + name + " eat...");
                hungry = false;
            } else {
                System.out.println("Cat " + name + " CAN'T eat: not enough food!");
            }
        } else {
            System.out.println("Cat " + name + " уже поел");
        }
    }

    public String getHungry() {
        if (hungry) {
            return "голоден";
        } else {
            return "не голоден";
        }
    }

    public String getName() {
        return name;
    }

}
