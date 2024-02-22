import java.util.ArrayList;

public class Authentication {
    private ArrayList<User> users;

    public Authentication() {
        this.users = new ArrayList<>();
    }

    public void register(User user) {
        users.add(user);
    }

    public User login(String username, String passwordHash) {
        for (User user : users) {
            if (user.username.equals(username) && user.passwordHash.equals(passwordHash)) {
                return user;
            }
        }
        return null;
    }
}
