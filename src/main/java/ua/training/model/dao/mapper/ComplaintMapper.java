package ua.training.model.dao.mapper;


import ua.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static ua.training.model.entity.User.ROLE.getRoleById;

public class ComplaintMapper implements ObjectMapper<User>{

    //new User.Role(rs.getInt("rol_id")
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id_student"));

        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));


        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(getRoleById(
                rs.getInt("id_role")));


        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> existing, User entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
