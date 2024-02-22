import java.util.ArrayList;

public class Order {
    private Menu menu;
    private ArrayList<Dish> dishes;
    private String status;

    public Order(Menu menu) {
        this.menu = menu;
        this.dishes = new ArrayList<>();
        this.status = "Accepted";
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    public void process() {
        this.status = "In progress";
        // Simulate the process of preparing the order
        try {
            Thread.sleep(10000); // 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.status = "Готов";
    }
}