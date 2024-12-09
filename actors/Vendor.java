package actors;

import Core.Logger;
import Core.TicketPool;

import java.util.UUID;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRate;

    public Vendor(TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ticketPool.addTicket(UUID.randomUUID().toString());
                Thread.sleep(releaseRate);
            }
        } catch (InterruptedException e) {
            Logger.logError("Vendor interrupted: " + e.getMessage());
        }
    }
}
