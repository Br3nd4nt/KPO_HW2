public class OrderCookingThread extends Thread{
    private final Order order;
    private int cookingTime;
    private int addedTime;


    public OrderCookingThread(Order order, int cookingTime) {
        this.order = order;
        order.cookingTime = cookingTime;
        order.addedTime = 0;
    }

    @Override
    public void run() {
        try {
            sleep((order.cookingTime + order.addedTime) * 1000L);
            order.cookingTime = 0;
            if (order.addedTime != 0) {
                order.cookingTime = order.addedTime;
                order.addedTime = 0;
                run();
            } else {
                order.setStatus(OrderStatus.Ready);
            }
        } catch (InterruptedException ignored) {}
    }

    public Order getOrder() {
        return order;
    }

    public void addTime(int time) {
        if (time <= 0) {
            return;
        }
        order.addedTime = time;
    }
}
