public class Customer implements Runnable {
    private final TicketPool ticketPool;

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            if (!ticketPool.removeTicket()) {
                System.out.println("No tickets available for purchase.");
                break;
            }
            try {
                Thread.sleep(1500); // Wait for 1.5 seconds before trying to buy another ticket
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
