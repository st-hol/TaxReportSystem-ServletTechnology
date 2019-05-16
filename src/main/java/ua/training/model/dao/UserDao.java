package ua.training.model.dao;


import ua.training.model.dao.impl.JdbcUserDao;
import ua.training.model.entity.User;

public interface UserDao extends GenericDao<User> {


   User.ROLE getRoleByEmailPassword(final String email, final String password);

   boolean userIsExist(final String email, final String password);

   boolean emailIsAlreadyTaken(final String email);

   JdbcUserDao.PaginationResult findByPagination(int offset, int noOfRecords);

}
