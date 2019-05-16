package ua.training.model.dao.mapper;


import ua.training.model.entity.Complaint;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static ua.training.model.entity.User.ROLE.getRoleById;

public class ComplaintMapper implements ObjectMapper<Complaint>{

    @Override
    public Complaint extractFromResultSet(ResultSet rs) throws SQLException {
        UserService userService = new UserService();

        Complaint complaint = new Complaint();

        complaint.setId(rs.getInt("id_complaint"));
        complaint.setUser(userService.getStudentById(rs.getLong("id_person")));
        complaint.setContent(rs.getString("content"));
        complaint.setCompletionTime(rs.getTimestamp("completion_time"));


        return complaint;
    }

    @Override
    public Complaint makeUnique(Map<Long, Complaint> existing, Complaint entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
