import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Authentication auth = new Authentication();
        Menu menu = new Menu();
        double total = 0;
        auth.register(new Customer("visitor1", "password1"));
        auth.register(new Administrator("admin1", "password1"));

        Administrator admin = (Administrator) auth.login("admin1", "password1");
        admin.addDish(menu, new Dish("dish 1", 1, 1));

        User visitor = auth.login("visitor1", "password1");
        if (visitor instanceof Customer customer) {
            // Create an order
            ArrayList<Dish> allItems = menu.getDishes();
            ArrayList<Dish> orderDishes = new ArrayList<>();
            orderDishes.add(allItems.get(0));
            orderDishes.add(allItems.get(0));
            orderDishes.add(allItems.get(0));
            Order order = customer.createOrder(menu, orderDishes);
            // Add dishes to the order
//            order.addDish(new Dish("Pizza", 10.0, 20));
//            order.addDish(new Dish("Burger", 8.0, 15));
            // Process the order
            total += order.process();
            System.out.println("total = " + total);
        }
    }
}