import com.example.addressbook.model.User.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("John", "password123", "john.doe@example.com");
    }

    @Test
    public void testGetId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    public void testGetUserName() {
        assertEquals("John", user.getUserName());
    }

    @Test
    public void testSetUserName() {
        user.setUserName("Jane");
        assertEquals("Jane", user.getUserName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", user.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpassword456");
        assertEquals("newpassword456", user.getPassword());
    }
}
