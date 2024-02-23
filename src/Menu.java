import java.util.ArrayList;

public class Menu {
    private ArrayList<Dish> dishes;

    public Menu() {
        this.dishes = new ArrayList<Dish>();
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }
}