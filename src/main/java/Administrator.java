public class Administrator extends User{
    public Administrator(String username, String passwordHash) {
        super(username, passwordHash);
    }

    public Administrator(String username, int hash) {
        super(username, hash);
    }

    public void addDish(Menu menu, Dish dish) {
        menu.addDish(dish);
    }

    public void removeDish(Menu menu, Dish dish) {
        menu.removeDish(dish);
    }

    public void changeDish(Menu menu, Dish dish, String newName, double newPrice, int newDifficulty){
        dish.setName(newName);
        dish.setPrice(newPrice);
        dish.setDifficulty(newDifficulty);
    }
}
