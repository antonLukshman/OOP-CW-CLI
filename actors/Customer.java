package actors;

import Core.Logger;
import Core.TicketPool;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRate;

    public Customer(TicketPool ticketPool, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ticketPool.removeTicket();
                Thread.sleep(retrievalRate);
            }
        } catch (InterruptedException e) {
            Logger.logError("Customer interrupted: " + e.getMessage());
        }
    }
}
