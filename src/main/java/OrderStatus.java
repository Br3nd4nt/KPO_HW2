public enum OrderStatus {
    InProgress("In progress"),
    Ready("Ready");

    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}