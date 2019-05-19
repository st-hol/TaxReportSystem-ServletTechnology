package ua.training.model.dao.mapper;


import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static ua.training.model.entity.User.ROLE.getRoleById;

public class UserMapper implements ObjectMapper<User>{

    //new User.Role(rs.getInt("rol_id")
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        UserService userService = new UserService();

        user.setId(rs.getInt("id_person"));

        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));


        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(getRoleById(
                rs.getInt("id_role")));

        if (rs.getInt("id_person") == rs.getLong("id_inspector")){
            user.setAssignedInspector(user);
        }else {
            user.setAssignedInspector(userService.getUserById(rs.getLong("id_inspector")));
        }

        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> existing, User entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
