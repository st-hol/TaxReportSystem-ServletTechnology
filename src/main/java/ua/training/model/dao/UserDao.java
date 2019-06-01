package ua.training.model.dao;


import ua.training.model.dao.impl.JdbcUserDao;
import ua.training.model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {

    List<User> findAllInspectors();

    List<User> findAssignedByInspector(final long idInspector);

    void assignInspector(User client, User inspector);

    User.ROLE getRoleByEmailPassword(final String email, final String password);

    boolean userIsExist(final String email, final String password);

    boolean emailIsAlreadyTaken(final String email);

}
