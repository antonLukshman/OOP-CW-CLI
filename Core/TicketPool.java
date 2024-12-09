package Core;

import java.util.*;

public class TicketPool {
    private final List<String> tickets = Collections.synchronizedList(new ArrayList<>());
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(String ticket) {
        if (tickets.size() < maxCapacity) {
            tickets.add(ticket);
            Logger.log("Ticket added: " + ticket);
        }
    }

    public synchronized String removeTicket() {
        if (!tickets.isEmpty()) {
            String ticket = tickets.remove(0);
            Logger.log("Ticket removed: " + ticket);
            return ticket;
        }
        return null;
    }

    public synchronized int getAvailableTickets() {
        return tickets.size();
    }
}
