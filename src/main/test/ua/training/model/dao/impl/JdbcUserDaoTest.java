package ua.training.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class JdbcUserDaoTest {

    private static long id = -1;
    private static String first_name = "'test'";
    private static String last_name =  "'test'";
    private static String email = "'test'";
    private static String password = "'1'";
    private static int id_role = 1;
    private static long id_inspector = 1;


    @BeforeClass
    public static void setUp() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tax_report_system", "root", "PASSWORD");
            Statement stmt = con.createStatement();
            stmt.execute("INSERT IGNORE INTO persons " +
                    "SET id_person = " + id +
                    ", first_name = " + first_name +
                    ", last_name = " + last_name +
                    ", email = " + email +
                    ", password = " + password +
                    ", id_role = " + id_role +
                    ", id_inspector = " + id_inspector + ";");
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void getRoleByEmailPassword() {

        UserService UserService = new UserService();
        User User = UserService.getUserById(id);
        User.ROLE role = UserService.getRoleByEmailAndPass(User.getEmail(), User.getPassword());

        assertEquals(User.getRole().getRoleID(), role.getRoleID());
    }

    @Test
    public void userIsExist() {
        UserService UserService = new UserService();
        User User = UserService.getUserById(id);
        boolean isExistingUser = UserService.isExistingUser(User.getEmail(), User.getPassword());

        assertTrue(isExistingUser);
    }

    @Test
    public void emailAlreadyTaken() {
        final UserService UserService = new UserService();
        final User User = UserService.getUserById(id);

        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao UserDao = daoFactory.createUserDao();
        boolean isAlreadyTakenEmail = UserDao.emailIsAlreadyTaken(User.getEmail());

        assertTrue(isAlreadyTakenEmail);
    }


    @Test
    public void whenUserExistThenReturnUserById() {

        final UserService userService = new UserService();
        final User result = userService.getUserById(id);

        final User expected = new User(id, "test", "test", "test", "1", User.ROLE.INSPECTOR, new User(id_inspector));
        assertEquals(result, expected);
    }

}