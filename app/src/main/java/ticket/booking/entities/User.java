package ticket.booking.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * User entity class
 */
public class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;
    private List<Ticket> tickets;

    // Default constructor for Jackson
    public User() {
        this.tickets = new ArrayList<>();
    }

    // Constructor for creating a new user
    public User(String userId, String username, String password, String email, String phone) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.name = username; // Default name to username
        this.tickets = new ArrayList<>();
    }

    // Constructor with name
    public User(String userId, String username, String password, String email, String phone, String name) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.tickets = new ArrayList<>();
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name != null ? name : username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        if (this.tickets == null) {
            this.tickets = new ArrayList<>();
        }
        this.tickets.add(ticket);
    }

    public void removeTicket(String ticketId) {
        if (this.tickets != null) {
            this.tickets.removeIf(ticket -> ticket.getTicketId().equals(ticketId));
        }
    }
}