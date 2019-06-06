package ua.training.model.dao.mapper;


import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static ua.training.model.entity.User.ROLE.getRoleById;

public class UserMapper implements ObjectMapper<User>{

    private static final String ID_PERSON = "id_person";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String ID_ROLE = "id_role";
    private static final String ID_INSPECTOR = "id_inspector";



    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        UserService userService = UserService.getInstance();

        user.setId(rs.getInt(ID_PERSON));

        user.setFirstName(rs.getString(FIRST_NAME));
        user.setLastName(rs.getString(LAST_NAME));


        user.setEmail(rs.getString(EMAIL));
        user.setPassword(rs.getString(PASSWORD));
        user.setRole(getRoleById(
                rs.getInt(ID_ROLE)));

        if (rs.getInt(ID_PERSON) == rs.getLong(ID_INSPECTOR)){
            user.setAssignedInspector(user);
        }else {
            user.setAssignedInspector(userService.getUserById(rs.getLong(ID_INSPECTOR)));
        }

        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> existing, User entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
