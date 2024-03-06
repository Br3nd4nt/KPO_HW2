import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Authentication {
    private final ArrayList<User> users;
    private final String JSON_path = "src/jsons/auth.json";
    private final String salt = "the quick brown fox jumps over the lazy dog";

    public Authentication() {
        this.users = new ArrayList<>();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            File file = new File(JSON_path);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                stringBuilder.append(data);
            }
            myReader.close();
            String jsonString = stringBuilder.toString().trim();
            JsonArray array = Json.parse(jsonString).asArray();
            for (JsonValue value : array) {
                JsonObject object = value.asObject();
                String login = object.getString("login", "");
                int hash = object.getInt("hash", 0);
                boolean isAdmin = object.getBoolean("isAdmin", false);
                if (login.isEmpty() || hash == 0) {
                    continue;
                }
                if (isAdmin) {
                    this.users.add(new Administrator(login, hash));
                } else {
                    this.users.add(new Customer(login, hash));
                }
            }

        } catch (IOException ignored) {}
    }

    public User register(String login, String password, boolean isAdmin) {
        User newUser;
        if (isAdmin) {
            newUser = new Administrator(login, password + salt);
        } else {
            newUser = new Customer(login, password + salt);
        }
        users.add(newUser);
        save();
        return newUser;
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username) && user.passwordHash == (password + salt).hashCode()) {
                return user;
            }
        }
        return null;
    }

    private void save() {
        JsonArray JSONUsers = Json.array();
        for (User user : users) {
            JsonObject object = new JsonObject();
            object.add("login", user.username);
            object.add("hash", user.passwordHash);
            object.add("isAdmin", user instanceof Administrator);
            JSONUsers.add(object);
        }
        try {
            FileWriter writer = new FileWriter(JSON_path);
            writer.write(JSONUsers.toString());
            writer.close();
        } catch (IOException ignored) {

        }
    }
}
