public class User {
    protected String username;
    protected int passwordHash;

    public User(String username, String password) {
        this.passwordHash = password.hashCode();
        this.username = username;
    }
}
