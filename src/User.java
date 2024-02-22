public class User {
    protected String username;
    protected String passwordHash;

    public User(String username, String passwordHash) {
        this.passwordHash = passwordHash;
        this.username = username;
    }
}
