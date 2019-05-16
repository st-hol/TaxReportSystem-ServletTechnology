package ua.training.model.dao;


import ua.training.model.dao.impl.JdbcDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;





    public abstract UserDao createUserDao();





    public static DaoFactory getInstance() {

        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JdbcDaoFactory();
                }
            }
        }

        return daoFactory;
    }

}
