public class Administrator extends User{
    public Administrator(String username, String passwordHash) {
        super(username, passwordHash);
    }

    public void addDish(Menu menu, Dish dish) {
        menu.addDish(dish);
    }

    public void removeDish(Menu menu, Dish dish) {
        menu.removeDish(dish);
    }

    public void changeDish(){}
}
