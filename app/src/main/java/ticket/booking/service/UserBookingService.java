package ticket.booking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for user authentication and booking operations
 */
public class UserBookingService {

    private static final String USER_FILE_PATH = "app/src/main/java/ticket/booking/localDb/users.json";

    private final ObjectMapper objectMapper;
    private List<User> userList;
    private User currentUser;
    private TrainService trainService;

    /**
     * Constructor for general operations (signup, etc.)
     */
    public UserBookingService() throws IOException {
        this.objectMapper = new ObjectMapper();
        loadUserListFromFile();
        this.trainService = new TrainService();
    }

    /**
     * Constructor for logged-in user operations
     */
    public UserBookingService(User user) throws IOException {
        this();
        this.currentUser = user;

        // Find and use the full user data from the list
        Optional<User> foundUser = userList.stream()
                .filter(u -> u.getName().equals(user.getName()) &&
                        UserServiceUtil.checkPassword(user.getPassword(), u.getHashedPassword()))
                .findFirst();

        if (foundUser.isPresent()) {
            this.currentUser = foundUser.get();
        }
    }

    /**
     * Load users from file
     */
    private void loadUserListFromFile() throws IOException {
        File usersFile = new File(USER_FILE_PATH);

        // Create directory and file if they don't exist
        if (!usersFile.exists()) {
            File dir = new File(usersFile.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            usersFile.createNewFile();

            // Initialize with empty array
            objectMapper.writeValue(usersFile, new ArrayList<User>());
        }

        userList = objectMapper.readValue(usersFile, new TypeReference<List<User>>() {});
    }

    /**
     * Save users to file
     */
    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USER_FILE_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    /**
     * Check login credentials
     */
    public Boolean loginUser() {
        if (currentUser == null) {
            return false;
        }

        Optional<User> foundUser = userList.stream()
                .filter(user -> user.getName().equals(currentUser.getName()) &&
                        UserServiceUtil.checkPassword(currentUser.getPassword(), user.getHashedPassword()))
                .findFirst();

        return foundUser.isPresent();
    }

    /**
     * Register a new user
     */
    public Boolean signUp(User newUser) {
        try {
            // Check if username already exists
            boolean usernameExists = userList.stream()
                    .anyMatch(user -> user.getName().equals(newUser.getName()));

            if (usernameExists) {
                return false;
            }

            userList.add(newUser);
            saveUserListToFile();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Get current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Print all tickets belonging to the current user
     */
    public void fetchBookings() {
        if (currentUser != null && currentUser.getTicketsBooked() != null) {
            currentUser.printTickets();
        }
    }

    /**
     * Get tickets belonging to current user
     */
    public List<Ticket> getUserTickets() {
        if (currentUser != null && currentUser.getTicketsBooked() != null) {
            return currentUser.getTicketsBooked();
        }
        return new ArrayList<>();
    }

    /**
     * Cancel a booking by ticket ID
     */
    public Boolean cancelBooking(String ticketId) {
        if (currentUser == null || ticketId == null || ticketId.isEmpty()) {
            return false;
        }

        List<Ticket> tickets = currentUser.getTicketsBooked();
        boolean removed = tickets.removeIf(ticket -> ticket.getTicketId().equals(ticketId));

        if (removed) {
            try {
                // Update the user in the list
                updateCurrentUserInList();
                saveUserListToFile();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    /**
     * Search for trains between source and destination
     */
    public List<Train> getTrains(String source, String destination) {
        try {
            return trainService.searchTrains(source, destination);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Get the seat map for a train
     */
    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }

    /**
     * Book a seat on a train
     */
    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try {
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.updateTrain(train);
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Add a ticket to the current user
     */
    public void addTicketToUser(Ticket ticket) throws IOException {
        if (currentUser == null) {
            throw new IllegalStateException("No user is logged in");
        }

        List<Ticket> tickets = currentUser.getTicketsBooked();
        if (tickets == null) {
            tickets = new ArrayList<>();
            currentUser.setTicketsBooked(tickets);
        }

        tickets.add(ticket);

        // Update user in list
        updateCurrentUserInList();
        saveUserListToFile();
    }

    /**
     * Update the current user in the user list
     */
    private void updateCurrentUserInList() {
        if (currentUser == null) {
            return;
        }

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserId().equals(currentUser.getUserId())) {
                userList.set(i, currentUser);
                break;
            }
        }
    }
}