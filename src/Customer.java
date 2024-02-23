import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<Order> orders;

    public Customer(String username, String password) {
        super(username, password);
        this.orders = new ArrayList<Order>();
    }

    public Order createOrder(Menu menu, ArrayList<Dish> dishes) {
        Order order = new Order(menu, this);
        for (Dish dish : dishes) {
            order.addDish(dish);
        }
        orders.add(order);
        return order;
    }

    public double payForOrder(Order order) {
        if (orders.contains(order) && order.getStatus() == OrderStatus.Ready) {
            orders.remove(order);
            return order.getTotalPrice();
        }
        return 0;
    }
}
