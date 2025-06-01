package ticket.booking.service;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing user bookings
 */
public class UserBookingService {
    private User currentUser;
    private List<Ticket> userTickets;

    public UserBookingService() {
        this.userTickets = new ArrayList<>();
    }

    /**
     * Sets the current logged-in user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        fetchBookings();
    }

    /**
     * Gets the current logged-in user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Fetches all bookings for the current user
     */
    public void fetchBookings() {
        // In a real application, this would fetch from a database
        // For now, we'll use a simple in-memory list
        userTickets = new ArrayList<>();

        // Add some mock data if needed
        if (currentUser != null) {
            System.out.println("User has " + userTickets.size() + " tickets.");
            for (Ticket ticket : userTickets) {
                System.out.println("Ticket ID: " + ticket.getTicketId() +
                        " belongs to User " + ticket.getUserId() +
                        " from " + ticket.getSource() +
                        " to " + ticket.getDestination() +
                        " on " + ticket.getDateOfTravel());
            }
        }
    }

    /**
     * Gets all tickets for the current user
     */
    public List<Ticket> getUserTickets() {
        return userTickets;
    }

    /**
     * Gets trains between source and destination
     */
    public List<Train> getTrains(String source, String destination) {
        List<Train> allTrains = DatabaseManager.loadTrains();
        List<Train> matchingTrains = new ArrayList<>();

        for (Train train : allTrains) {
            List<String> stations = train.getStations();
            if (stations != null) {
                int sourceIndex = -1;
                int destIndex = -1;

                // Find indices of source and destination stations
                for (int i = 0; i < stations.size(); i++) {
                    if (stations.get(i).equalsIgnoreCase(source)) {
                        sourceIndex = i;
                    }
                    if (stations.get(i).equalsIgnoreCase(destination)) {
                        destIndex = i;
                    }
                }

                // Add train if source comes before destination
                if (sourceIndex >= 0 && destIndex >= 0 && sourceIndex < destIndex) {
                    matchingTrains.add(train);
                }
            }
        }

        return matchingTrains;
    }

    /**
     * Books a seat on a train
     */
    public boolean bookTrainSeat(Train train, int row, int col) {
        try {
            // Update the seat status in the train object
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() &&
                    col >= 0 && col < seats.get(row).size()) {

                // Check if seat is available
                if (seats.get(row).get(col) != 0) {
                    return false; // Seat already booked
                }

                // Mark seat as booked
                seats.get(row).set(col, 1);
                train.setSeats(seats);

                // Create a new ticket
                String ticketId = "TN" + System.currentTimeMillis();
                Ticket ticket = new Ticket(
                        ticketId,
                        currentUser.getUserId(),
                        "Source", // Replace with actual source
                        "Destination", // Replace with actual destination
                        java.time.LocalDate.now().toString(),
                        train
                );

                // Add to user's tickets
                userTickets.add(ticket);

                // Update train in database
                List<Train> allTrains = DatabaseManager.loadTrains();
                for (int i = 0; i < allTrains.size(); i++) {
                    if (allTrains.get(i).getTrainId().equals(train.getTrainId())) {
                        allTrains.set(i, train);
                        break;
                    }
                }
                DatabaseManager.saveTrains(allTrains);

                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Cancels a booking
     */
    public boolean cancelBooking(String ticketId) {
        try {
            Ticket ticketToRemove = null;

            // Find the ticket
            for (Ticket ticket : userTickets) {
                if (ticket.getTicketId().equals(ticketId)) {
                    ticketToRemove = ticket;
                    break;
                }
            }

            if (ticketToRemove != null) {
                // Remove from user's tickets
                userTickets.remove(ticketToRemove);

                // Update seat status in train
                Train train = ticketToRemove.getTrain();

                // Find the seat (this is simplified - in a real app you'd store seat info in the ticket)
                // For now, we'll just find any seat that's booked
                List<List<Integer>> seats = train.getSeats();
                boolean seatFound = false;

                for (int i = 0; i < seats.size() && !seatFound; i++) {
                    for (int j = 0; j < seats.get(i).size() && !seatFound; j++) {
                        if (seats.get(i).get(j) == 1) {
                            // Mark seat as available
                            seats.get(i).set(j, 0);
                            seatFound = true;
                        }
                    }
                }

                train.setSeats(seats);

                // Update train in database
                List<Train> allTrains = DatabaseManager.loadTrains();
                for (int i = 0; i < allTrains.size(); i++) {
                    if (allTrains.get(i).getTrainId().equals(train.getTrainId())) {
                        allTrains.set(i, train);
                        break;
                    }
                }
                DatabaseManager.saveTrains(allTrains);

                return true;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}