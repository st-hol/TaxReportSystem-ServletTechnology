package ua.training.model.dao.mapper;


import ua.training.model.entity.Complaint;
import ua.training.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ComplaintMapper implements ObjectMapper<Complaint>{

    private static final String ID_COMPLAINT = "id_complaint";
    private static final String ID_PERSON = "id_person";
    private static final String CONTENT = "content";
    private static final String COMPLETION_TIME = "completion_time";


    @Override
    public Complaint extractFromResultSet(ResultSet rs) throws SQLException {
        UserService userService = UserService.getInstance();

        Complaint complaint = new Complaint();

        complaint.setId(rs.getInt(ID_COMPLAINT));
        complaint.setUser(userService.getUserById(rs.getLong(ID_PERSON)));
        complaint.setContent(rs.getString(CONTENT));
        complaint.setCompletionTime(rs.getTimestamp(COMPLETION_TIME));

        return complaint;
    }

    @Override
    public Complaint makeUnique(Map<Long, Complaint> existing, Complaint entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
