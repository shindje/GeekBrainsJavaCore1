package hw7.cat;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void info(){
        System.out.println("Food : "+ food);
    }

    public int getFood() {
        return food;
    }

    public boolean decreaseFood(int amount) {
        if (food < amount) {
            return false;
        } else {
            food -= amount;
            return true;
        }
    }

    public void addFood(int amount) {
        food += amount;
    }
}
