import java.util.ArrayList;

public class Authentication {
    private final ArrayList<User> users;

    public Authentication() {
        this.users = new ArrayList<>();
    }

    public void register(User user) {
        users.add(user);
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username) && user.passwordHash == password.hashCode()) {
                return user;
            }
        }
        return null;
    }
}
