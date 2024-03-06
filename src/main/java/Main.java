import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    private static final Authentication auth = new Authentication();
    private static final Menu menu = new Menu();
    private static User currentUser = null;
    private static Boolean isAdmin = false;
    private static double income = 0;


    private static void enterOptions() {
        System.out.println("Choose option:");
        System.out.println("1) Login");
        System.out.println("2) Register admin");
        System.out.println("3) Register user");
        System.out.println("4) Exit");
        String input = sc.nextLine();
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
                enterOptions();

        }
    }

    private static void login() {
        System.out.print("Input login: ");
        String login = sc.nextLine();
        System.out.print("Input your password: ");
        String password = sc.nextLine();
        User user = auth.login(login, password);
        System.out.println();
        if (user == null) {
            System.out.println("ERROR: incorrect login or password");
            System.out.println();
            enterOptions();
        } else {
            System.out.println("Logged in successfully!");
            System.out.println();
            isAdmin = user instanceof Administrator;
            currentUser = user;
        }

    }

    private static void registerAdmin() {
        System.out.print("Input login: ");
        String login = sc.nextLine();
        System.out.print("Input your password: ");
        String password = sc.nextLine();
        currentUser = auth.register(login, password, true);
        isAdmin = true;
        System.out.println();
        System.out.println("Registered successfully!");
        System.out.println();
    }

    private static void registerUser() {
        System.out.print("Input login: ");
        String login = sc.nextLine();
        System.out.print("Input your password: ");
        String password = sc.nextLine();
        currentUser = auth.register(login, password, false);
        isAdmin = false;
        System.out.println();
        System.out.println("Registered successfully!");
        System.out.println();
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

    }

    private static void adminRoutine() {
        System.out.println("Choose action:");
        System.out.println("1) Get all dishes in menu");
        System.out.println("2) Add dish in menu");
        System.out.println("3) Delete dish from menu");
        System.out.println("4) Change dish in menu");
        System.out.println("5) Get statistics");
        System.out.println("6) Log out");
        String input = sc.nextLine();

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
                getStats();
                break;
            case "6":
                currentUser = null;
                isAdmin = false;
                enterOptions();
                break;
        }
    }

    private static void addDish() {
        double price;
        int diff;
        System.out.println();
        System.out.print("Input name of the dish: ");
        String name = sc.nextLine();
        System.out.print("Input price of the dish: ");
        try {
            price = Double.parseDouble(sc.nextLine());

        } catch (NumberFormatException ignored) {return;}
        System.out.print("Input difficulty of the dish: ");
        try {
            diff = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException ignored) {return;}
        ((Administrator)currentUser).addDish(menu, new Dish(name, price, diff));
        System.out.println();
        System.out.println("Dish added successfully!");
        System.out.println();
    }

    private static void deleteDish() {
        System.out.println();
        System.out.print("Input name of dish: ");
        String name = sc.nextLine();
        ArrayList<Dish> dishes = menu.getDishes();
        boolean flag = false;
        for (Dish dish : dishes) {
            if (Objects.equals(dish.getName(), name)) {
                ((Administrator) currentUser).removeDish(menu, dish);
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
    }

    private static void changeDish() {
        ArrayList<Dish> dishes = menu.getDishes();
        System.out.println();
        System.out.print("Input name of dish: ");
        String name = sc.nextLine();
        Dish dish = null;
        for (Dish value : dishes) {
            if (value.getName().equals(name)) {
                //found
                dish = value;
                break;
            }
        }
        System.out.println();
        if (dish == null)  {
            System.out.println("Dish not found.");

        } else {
            double newPrice;
            int newDiff;
            System.out.print("New name of dish(currently: " + dish.getName() + "): ");
            String newName = sc.nextLine();
            System.out.print("New price of dish(currently: " + dish.getPrice() + "): ");
            try{
                newPrice = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException ignored) {return;}

            System.out.print("New difficulty of dish(currently: " + dish.getDifficulty() + "): ");
            try{
                newDiff = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ignored) {return;}
            ((Administrator)currentUser).changeDish(menu, dish, newName, newPrice, newDiff);


        }
        System.out.println();
    }

    private static void getStats() {
        System.out.println();
        System.out.println("Your total income: " + income);
        System.out.println();
    }

    private static void customerRoutine() {
        System.out.println("Choose action: ");
        System.out.println("1) See all dishes in menu");
        System.out.println("2) Create new order");
        System.out.println("3) See all your orders");
        System.out.println("4) Add dishes in your order in progress");
        System.out.println("5) Cancel your order");
        System.out.println("6) Pay for your order");
        System.out.println("7) Log out");
        String input = sc.nextLine();
        switch (input) {
            case "1":
                printDishes();
                break;
            case "2":
                createOrder();
                break;
            case "3":
                seeOrders();
                break;
            case "4":
                addToOrder();
                break;
            case "5":
                cancelOrder();
                break;
            case "6":
                payForOrder();
                break;
            case "7":
                currentUser = null;
                isAdmin = false; // just in case
                enterOptions();
                break;
        }
    }

    private static void createOrder() {
        System.out.println();
        ArrayList<Dish> dishes = menu.getDishes();

        if (dishes.isEmpty()) {
            System.out.print("Sorry, the menu is currently empty.");
        } else {
            ArrayList<Dish> dishesInOrder = new ArrayList<>();
            System.out.println("For every item in the menu choose the quantity you want in your order:");
            for (Dish dish : dishes) {
                System.out.print(dish.getName() + " (price: " + dish.getPrice() + ") - ");

                int quantity;
                try{
                    quantity = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException ignored) {return;}
                for (int i = 0; i < quantity; i++) {
                    dishesInOrder.add(dish); // no need to create copy because we won't be changing it
                }
            }
            ((Customer)currentUser).createOrder(dishesInOrder);
        }

        System.out.println();
    }

    private static void seeOrders() {
        System.out.println();
        ArrayList<Order> orders = ((Customer)currentUser).getOrders();

        if (orders.isEmpty()) {
            System.out.println("You don't have any orders");
        } else {
            System.out.println("your orders: ");
            for (Order order : orders) {
                System.out.println(order);
            }
        }
        System.out.println();
    }

    private static void addToOrder() {
        System.out.println();
        List<Order> orders = ((Customer)currentUser).getOrders().stream()
                .filter(order -> order.getStatus() != OrderStatus.Ready).toList();

        if (orders.isEmpty()) {
            System.out.println("You don't have any orders in progress");
        } else {
            System.out.println("Choose order to change: ");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ") " + orders.get(i));
            }
            int numberOfOrder, quantity;
            try {
                numberOfOrder = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ignored) {return;}
            if (numberOfOrder > orders.size() || numberOfOrder < 1) {
                System.out.println("Wrong number of order");
            } else {
                ArrayList<Dish> dishesToAdd = new ArrayList<>();
                System.out.println("For every item in the menu choose the quantity you want add:");
                for (Dish dish : menu.getDishes()) {
                    System.out.print(dish.getName() + " (price: " + dish.getPrice() + ") - ");
                    try{
                        quantity = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException ignored) {return;}
                    for (int i = 0; i < quantity; i++) {
                        dishesToAdd.add(dish);
                    }
                }
                ((Customer)currentUser).addToOrder(orders.get(numberOfOrder - 1), dishesToAdd);
            }
        }

        System.out.println();
    }

    private static void cancelOrder() {
        System.out.println();
        List<Order> orders = ((Customer)currentUser).getOrders().stream()
                .filter(order -> order.getStatus() != OrderStatus.Ready).toList();

        if (orders.isEmpty()) {
            System.out.println("You don't have any orders in progress");
        } else {
            System.out.println("Choose order to cancel: ");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ") " + orders.get(i));
            }
            int numberOfOrder;
            try{
                numberOfOrder = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ignored) {return;}
            if (numberOfOrder > orders.size() || numberOfOrder < 1) {
                System.out.println("Wrong number of order");
            } else {

                ((Customer)currentUser).cancelOrder(orders.get(numberOfOrder));
                System.out.println("Order is cancelled");
            }
        }

        System.out.println();
    }

    private static void payForOrder() {
        System.out.println();
        List<Order> orders = ((Customer)currentUser).getOrders().stream()
                .filter(order -> order.getStatus() == OrderStatus.Ready && !order.getPaidStatus()).toList();

        if (orders.isEmpty()) {
            System.out.println("You don't have any unpaid ready orders");
        } else {
            System.out.println("Choose order to pay for: ");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ") " + orders.get(i));
            }
            int numberOfOrder;
            try{
                numberOfOrder = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ignored) {return;}
            if (numberOfOrder > orders.size() || numberOfOrder < 1) {
                System.out.println("Wrong number of order");
            } else {

                income += orders.get(numberOfOrder - 1).getPrice();
                ((Customer)currentUser).payForOrder(orders.get(numberOfOrder - 1));
                System.out.println("Order is paid!");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        enterOptions();
        if (currentUser == null) {
            //something went wrong
            System.exit(0);
        }

        while (currentUser != null) {
            if (isAdmin) {
                adminRoutine();
            } else {
                customerRoutine();
            }
        }
    }
}