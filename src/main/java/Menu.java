import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Dish> dishes;
    private final String JSON_path = "src/jsons/menu.json";

    public Menu() {
        this.dishes = new ArrayList<>();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            File file = new File(JSON_path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                stringBuilder.append(data);
            }
            scanner.close();
            String jsonString = stringBuilder.toString().trim();
            JsonArray array = Json.parse(jsonString).asArray();
            for (JsonValue value : array) {
                JsonObject object = value.asObject();
                String name = object.getString("name", "");
                double price = object.getDouble("price", 0);
                int difficulty = object.getInt("difficulty", 0);
                if (name.isEmpty() || price == 0 || difficulty == 0) {
                    continue;
                }
                this.dishes.add(new Dish(name, price, difficulty));
            }
        } catch (IOException ignored) {}
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
        save();
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
        save();
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    private void save() {
        JsonArray JSONmenu = Json.array();
        for (Dish dish : dishes) {
            JsonObject object = new JsonObject();
            object.add("name", dish.getName());
            object.add("price", dish.getPrice());
            object.add("difficulty", dish.getDifficulty());
            JSONmenu.add(object);
        }
        try {
            FileWriter writer = new FileWriter(JSON_path);
            writer.write(JSONmenu.toString());
            writer.close();
        } catch (IOException ignored) {

        }
    }
}