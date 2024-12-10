import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    private final List<Integer> tickets;
    private final int maxTickets;

    public TicketPool(int maxTickets) {
        this.maxTickets = maxTickets;
        this.tickets = new LinkedList<>();
    }

    // Add tickets to the pool (by vendors)
    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            if (tickets.size() < maxTickets) {
                tickets.add(1); // Add a ticket
                System.out.println("Vendor added a ticket. Total tickets: " + tickets.size());
            }
        }
    }

    // Remove tickets from the pool (by customers)
    public synchronized boolean removeTicket() {
        if (!tickets.isEmpty()) {
            tickets.remove(0);
            System.out.println("Customer bought a ticket. Tickets left: " + tickets.size());
            return true;
        }
        return false;
    }

    public int getRemainingTickets() {
        return tickets.size();
    }
}
