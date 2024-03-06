import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final ArrayList<Dish> dishes;
    private OrderStatus status;
    private final Customer customer;
    private final boolean isPaidFor = false;

    public Order(Customer customer) {
        this.dishes = new ArrayList<>();
        this.status = OrderStatus.InProgress;
        this.customer = customer;

    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }


    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getPrice() {
        double total = 0;
        for (Dish dish : dishes) {
            total += dish.getPrice();
        }
        return total;
    }

    public int getDifficulty() {
        int total = 0;
        for (Dish dish : dishes) {
            total += dish.getDifficulty();
        }
        return total;
    }

    public boolean getPaidStatus() {
        return this.isPaidFor;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Order (Status: " + this.status.getTitle() + "): \n");
        HashMap<String, Integer> counter = new HashMap<>();
        for (Dish dish : this.dishes) {
            counter.put(dish.getName(), counter.getOrDefault(dish.getName(), 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            result.append("\t").append(entry.getKey()).append(" --- x").append(entry.getValue()).append("\n");
        }
        result.append("Total price: ").append(this.getPrice());
        return result.toString();
    }
}