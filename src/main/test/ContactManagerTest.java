import com.example.addressbook.model.User.MockUserDAO;
import com.example.addressbook.model.User.User;
import com.example.addressbook.model.User.UserManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ContactManagerTest {
    private UserManager userManager;
    private User[] users = {
            new User("johndoe", "password123", "johndoe@example.com"),
            new User("janedoe", "password456", "janedoe@example.com"),
            new User("jaydoe", "password789", "jaydoe@example.com"),
            new User("johnsmith", "password101", "johnsmith@example.com"),
            new User("janesmith", "password202", "janesmith@example.com"),
            new User("alicegray", "password303", "aliceg@gmail.com"),
            new User("shanegrey", "password404", "shaneg@gmail.com")
    };

    @BeforeEach
    public void setUp() {
        userManager = new UserManager(new MockUserDAO()); // Assuming a mock DAO is available
    }

    @Test
    public void testAddUser() {
        userManager.addUser(users[0]);
        List<User> usersList = userManager.getAllUsers();
        assertEquals(1, usersList.size());
        assertEquals(users[0].getUserName(), usersList.get(0).getUserName());
    }

    @Test
    public void testSearchUserByUsername() {
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> foundUsers = userManager.searchUsers("johndoe");
        assertEquals(1, foundUsers.size());
        assertEquals(users[0].getUserName(), foundUsers.get(0).getUserName());
    }

    @Test
    public void testSearchUserByEmail() {
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> foundUsers = userManager.searchUsers("example.com");
        assertEquals(5, foundUsers.size());
    }

    @Test
    public void testSearchNoResults() {
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> foundUsers = userManager.searchUsers("nonexistentuser");
        assertEquals(0, foundUsers.size());
    }

    @Test
    public void testSearchEmptyQuery() {
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> foundUsers = userManager.searchUsers("");
        assertEquals(users.length, foundUsers.size());
    }

    @Test
    public void testSearchNullQuery() {
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> foundUsers = userManager.searchUsers(null);
        assertEquals(users.length, foundUsers.size());
    }

    @Test
    public void testSearchByPartialUsername() {
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> foundUsers = userManager.searchUsers("doe");
        assertEquals(3, foundUsers.size());
    }



}
