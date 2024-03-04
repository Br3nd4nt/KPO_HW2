import java.util.ArrayList;

public class Order {
    private Menu menu;
    private ArrayList<Dish> dishes;
    private OrderStatus status;

    private Customer customer;

    public Order(Menu menu, Customer customer) {
        this.menu = menu;
        this.dishes = new ArrayList<>();
        this.status = OrderStatus.Accepted;
        this.customer = customer;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    public double process() {
        this.status = OrderStatus.InProgress;
        // Simulate the process of preparing the order
        try {
            int totalCookingTime = 0;
            for (Dish dish : this.dishes) {
                totalCookingTime += dish.getDifficulty();
            }
            Thread.sleep((int) (totalCookingTime * 1e3)); // 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.status = OrderStatus.Ready;
        return customer.payForOrder(this);
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Dish dish : dishes) {
            total += dish.getPrice();
        }
        return total;
    }
}