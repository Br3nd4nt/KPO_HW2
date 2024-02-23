public enum OrderStatus {
    Accepted("Accepted"),
    InProgress("In progress"),
    Ready("Ready");

    private String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}