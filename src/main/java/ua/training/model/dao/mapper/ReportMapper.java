package ua.training.model.dao.mapper;


import ua.training.model.entity.Report;
import ua.training.model.entity.User;
import ua.training.model.service.ReportService;
import ua.training.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static ua.training.model.entity.User.ROLE.getRoleById;

public class ReportMapper implements ObjectMapper<Report>{


    @Override
    public Report extractFromResultSet(ResultSet rs) throws SQLException {
        Report report = new Report();

        UserService userService = new UserService();

        report.setId(rs.getInt("id_report"));
        report.setPerson(userService.getStudentById(rs.getLong("id_person")));
        report.setCompanyName(rs.getString("company_name"));
        report.setTaxpayerCode(rs.getString("taxpayer_code"));
        report.setCompletionTime(rs.getTimestamp("completion_time"));
        report.setTotalAmountOfProperty(rs.getLong("total_amount_of_property"));
        report.setAcceptedFromInt(rs.getInt("is_accepted"));
        report.setShouldChangeFromInt(rs.getInt("should_be_changed"));
        report.setInspectorComment(rs.getString("inspector_comment"));


        return report;
    }

    @Override
    public Report makeUnique(Map<Long, Report> existing, Report entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
