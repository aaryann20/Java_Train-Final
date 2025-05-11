package ticket.booking.gui;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void appHasAGreeting() {
        App classUnderTest = new App();
        assertEquals("Train Booking System", classUnderTest.getGreeting());
    }
}