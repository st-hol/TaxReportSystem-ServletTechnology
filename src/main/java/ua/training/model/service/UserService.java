package ua.training.model.service;


import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.JdbcUserDao;
import ua.training.model.entity.User;
import ua.training.model.exception.AlreadyExistingDBRecordException;


import java.util.*;


/**
 * This class realize logic
 * for manipulation with db.
 *
 * @author Stanislav Holovachuk
 */
public class UserService {

    private DaoFactory daoFactory = DaoFactory.getInstance();


    /**
     * Registers user's account if such not exist yet.
     *
     * @param user User.
     */
    public void registerUserAccount(User user) throws AlreadyExistingDBRecordException {
        try (UserDao userDao = daoFactory.createUserDao()) {

            if ( userDao.emailIsAlreadyTaken(user.getEmail()) ){
                throw new AlreadyExistingDBRecordException("Failed registering already existing user email "+
                        user.getEmail());
            }

            userDao.create(user);
        }
    }


    /**
     * obtains student by email.
     *
     * @param email String.
     */
    public User getStudentByEmail(String email) {

        List<User> users = getAllUsers();

        return users.stream()
                .filter(student -> email.equals(student.getEmail()))
                .findAny()
                .orElse(null);

    }

    /**
     * checks if such user exist in db.
     * @param email String.
     */
    public boolean isExistingUser(String email, String password){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.userIsExist(email, password);
        }
    }


    /**
     * obtain role by email and password.
     * @param email String.
     * @param password String.
     */
    public User.ROLE getRoleByEmailAndPass(String email, String password){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.getRoleByEmailPassword(email, password);
        }
    }


    /**
     * obtains student by id.
     *
     * @param id long.
     */
    public User getStudentById(long id){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findById(id);
        }
    }

    /**
     * obtains List of all students.
     */
    public List<User> getAllUsers() {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }


    /**
     * obtains List of certain quantity of enrolled students.
     */
    public JdbcUserDao.PaginationResult getAllEnrolledStudentsByPagination(int lowerBound, int upperBound) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByPagination(lowerBound, upperBound);
        }
    }


}


//todo Optional