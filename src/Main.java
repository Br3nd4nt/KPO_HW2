import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    private static final Authentication auth = new Authentication();
    private static Menu menu = new Menu();

    private static User currentUser = null;
    private static Boolean isAdmin = false;

    private static void printOptions() {
        System.out.println("Choose option:");
        System.out.println("1) Login");
        System.out.println("2) Register admin");
        System.out.println("3) Register user");
        System.out.println("4) Exit");
        String input = sc.next();
        switch (input) {
            case "1":
                login();
                break;
            case "2":
                registerAdmin();
                break;
            case "3":
                registerUser();
                break;
            case "4":
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                printOptions();

        }
    }

    private static void login() {
        System.out.print("Input login: ");
        String login = sc.next();
        System.out.print("Input your password: ");
        String password = sc.next();
        User user = auth.login(login, password);
        if (user == null) {
            System.out.println();
            System.out.println("ERROR: incorrect login or password");
            System.out.println();
            printOptions();
        } else {
            System.out.println();
            System.out.println("Logged in successfully!");
            System.out.println();
            isAdmin = user instanceof Administrator;
            currentUser = user;
        }

    }
    private static void registerAdmin() {
        System.out.print("Input login: ");
        String login = sc.next();
        System.out.print("Input your password: ");
        String password = sc.next();
        Administrator newAdmin = new Administrator(login, password);
        auth.register(newAdmin);
        currentUser = newAdmin;
        isAdmin = true;
        System.out.println();
        System.out.println("Registered successfully!");
        System.out.println();
    }

    private static void registerUser() {
        System.out.print("Input login: ");
        String login = sc.next();
        System.out.print("Input your password: ");
        String password = sc.next();
        Customer newCustomer = new Customer(login, password);
        auth.register(newCustomer);
        currentUser = newCustomer;
        isAdmin = false;
        System.out.println();
        System.out.println("Registered successfully!");
        System.out.println();
    }

    private static void adminRoutine() {
        System.out.println("Choose action:");
        System.out.println("1) Get all dishes in menu");
        System.out.println("2) Add dish in menu");
        System.out.println("3) Delete dish from menu");
        System.out.println("4) Change dish in menu");
        System.out.println("5) Log out");
        String input = sc.next();
        switch (input){
            case "1":
                printDishes();
                break;
            case "2":
                addDish();
                break;
            case "3":
                deleteDish();
                break;
            case "4":
                changeDish();
                break;
            case "5":
                currentUser = null;
                isAdmin = false;
                printOptions();
                break;
            default:
                adminRoutine();
        }
    }

    private static void printDishes() {
        System.out.println();
        ArrayList<Dish> dishes = menu.getDishes();
        if (dishes.isEmpty()) {
            System.out.println("Menu is empty.");
        } else {
            System.out.println("Dishes in menu:");
            System.out.println();
            for (Dish d : dishes) {
                System.out.println(d.toString());
            }
        }
        System.out.println();
        if (isAdmin) {
            adminRoutine();
        } else {
            customerRoutine();
        }
    }

    private static void addDish() {
        System.out.println();
        System.out.print("Input name of the dish: ");
        String name = sc.next();
        System.out.print("Input price of the dish: ");
        double price = sc.nextDouble();
        System.out.print("Input difficulty of the dish: ");
        int diff = sc.nextInt();
        menu.addDish(new Dish(name, price, diff));
        System.out.println();
        System.out.println("Dish added successfully!");
        System.out.println();
        adminRoutine();
    }

    private static void deleteDish() {
        System.out.println();
        System.out.print("Input name of dish: ");
        String name = sc.next();
        ArrayList<Dish> dishes = menu.getDishes();
        boolean flag = false;
        for (int i = 0; i < dishes.size(); i++) {
            if (Objects.equals(dishes.get(i).getName(), name)) {
                dishes.remove(dishes.get(i));
                flag = true;
                break;
            }
        }
        System.out.println();
        if (flag) {
            System.out.print("Dish deleted successfully!");
        } else {
            System.out.print("Dish not found.");
        }
        System.out.println();
        adminRoutine();
    }

    private static void changeDish() {
        ArrayList<Dish> dishes = menu.getDishes();
        System.out.println();
        System.out.print("Input name of dish: ");
        String name = sc.next();
        Dish dish = null;
        for (int i = 0; i < dishes.size(); i++) {
            if (dishes.get(i).getName().equals(name)) {
                //found
                dish = dishes.get(i);
                break;
            }
        }
        System.out.println();
        if (dish == null)  {
            System.out.println("Dish not found.");

        } else {
            System.out.print("New name of dish(currently: " + dish.getName() + "): ");
            String newName = sc.next();
            System.out.print("New price of dish(currently: " + dish.getPrice() + "): ");
            double newPrice = sc.nextDouble();
            System.out.print("New difficulty of dish(currently: " + dish.getDifficulty() + "): ");
            int newDiff = sc.nextInt();
            dish.setName(newName);
            dish.setPrice(newPrice);
            dish.setDifficulty(newDiff);

        }
        System.out.println();
        adminRoutine();
    }
    public static void main(String[] args) {
        printOptions();
        if (currentUser == null) {
            //something went wrong
            System.exit(0);
        }

        while (currentUser != null) {
            if (isAdmin) {
                adminRoutine();
            }
        }
//        Authentication auth = new Authentication();
//
//        double total = 0;
//        auth.register(new Customer("visitor1", "password1"));
//        auth.register(new Administrator("admin1", "password1"));
//
//        Administrator admin = (Administrator) auth.login("admin1", "password1");
//        admin.addDish(menu, new Dish("dish 1", 1, 1));
//
//        User visitor = auth.login("visitor1", "password1");
//        if (visitor instanceof Customer customer) {
//            // Create an order
//            ArrayList<Dish> allItems = menu.getDishes();
//            ArrayList<Dish> orderDishes = new ArrayList<>();
//            orderDishes.add(allItems.get(0));
//            orderDishes.add(allItems.get(0));
//            orderDishes.add(allItems.get(0));
//            Order order = customer.createOrder(menu, orderDishes);
//            // Add dishes to the order
////            order.addDish(new Dish("Pizza", 10.0, 20));
////            order.addDish(new Dish("Burger", 8.0, 15));
//            // Process the order
//            total += order.process();
//            System.out.println("total = " + total);
//        }
    }
}