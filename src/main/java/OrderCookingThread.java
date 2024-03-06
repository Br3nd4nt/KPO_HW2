public class OrderCookingThread extends Thread{
    private final Order order;
    private int cookingTime;
    private int addedTime;

    public OrderCookingThread(Order order, int cookingTime) {
        this.order = order;
        this.cookingTime = cookingTime;
    }

    @Override
    public void run() {
        try {
            sleep((cookingTime + addedTime) * 1000L);
            cookingTime = 0;
            addedTime = 0;
            order.setStatus(OrderStatus.Ready);
        } catch (InterruptedException ignored) {}
    }

    public Order getOrder() {
        return order;
    }

    public void addTime(int time) {
        if (time <= 0) {
            return;
        }
        addedTime = time;
        order.setStatus(OrderStatus.InProgress);
    }
}
