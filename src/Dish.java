public class Dish {
    private String name;
    private double price;
    private int difficulty;

    public Dish(String name, double price, int difficulty) {
        this.name = name;
        this.price = price;
        this.difficulty = difficulty;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Name: " + this.name + "; Price: " + this.price + "; Difficulty: " + this.difficulty;
    }
}