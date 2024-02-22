import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<Order> orders;

    public Customer(String username, String password) {
        super(username, password);
        this.orders = new ArrayList<Order>();
    }

    public Order createOrder(Menu menu) {
        Order order = new Order(menu);
        orders.add(order);
        return order;
    }
}
