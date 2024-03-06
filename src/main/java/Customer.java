import java.util.ArrayList;

public class Customer extends User{
    private final ArrayList<Order> orders;
    private final ArrayList<OrderCookingThread> cookingThreads;

    public Customer(String username, String password) {
        super(username, password);
        this.orders = new ArrayList<>();
        this.cookingThreads = new ArrayList<>();
    }

    public Customer(String username, int hash) {
        super(username, hash);
        this.orders = new ArrayList<>();
        this.cookingThreads = new ArrayList<>();
    }

    public void createOrder(ArrayList<Dish> dishes) {
        Order order = new Order(this);
        for (Dish dish : dishes) {
            order.addDish(dish);
        }
        orders.add(order);
        OrderCookingThread cookingThread = new OrderCookingThread(order, order.getDifficulty());
        cookingThread.start();
        cookingThreads.add(cookingThread);

    }

    public void addToOrder(Order order, ArrayList<Dish> dishes) {
        for (var thread : cookingThreads) {
            if (thread.getOrder() == order) {
                int time = 0;
                for (Dish dish : dishes) {
                    time += dish.getDifficulty();
                    thread.getOrder().addDish(dish);
                }
                order.addedTime += time;
            }

        }
    }

    public void cancelOrder(Order order) {
        for (var thread : cookingThreads) {
            if (thread.getOrder() == order) {
                thread.interrupt();
                order = null;
                cookingThreads.remove(thread);
            }
        }
    }

    public void payForOrder(Order order) {
        if (orders.contains(order) && order.getStatus() == OrderStatus.Ready) {
            orders.remove(order);
            order.getPrice();
        }
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
