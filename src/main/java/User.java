public class User {
    protected String username;
    protected int passwordHash;

    public User(String username, String password) {

        this.passwordHash = (password).hashCode();
        this.username = username;
    }

    public User(String username, int hash) {
        this.passwordHash = hash;
        this.username = username;
    }
}
