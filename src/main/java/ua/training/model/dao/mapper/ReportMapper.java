package ua.training.model.dao.mapper;


import ua.training.model.entity.Report;
import ua.training.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ReportMapper implements ObjectMapper<Report>{

    private static final String ID_REPORT = "id_report";
    private static final String ID_PERSON = "id_person";
    private static final String COMPANY_NAME = "company_name";
    private static final String TAXPAYER_CODE = "taxpayer_code";
    private static final String COMPLETION_TIME = "completion_time";
    private static final String TOTAL_AMOUNT_OF_PROPERTY = "total_amount_of_property";
    private static final String IS_ACCEPTED = "is_accepted";
    private static final String SHOULD_BE_CHANGED = "should_be_changed";
    private static final String INSPECTOR_COMMENT = "inspector_comment";

    @Override
    public Report extractFromResultSet(ResultSet rs) throws SQLException {
        Report report = new Report();

        UserService userService = UserService.getInstance();

        report.setId(rs.getInt(ID_REPORT));
        report.setPerson(userService.getUserById(rs.getLong(ID_PERSON)));
        report.setCompanyName(rs.getString(COMPANY_NAME));
        report.setTaxpayerCode(rs.getString(TAXPAYER_CODE));
        report.setCompletionTime(rs.getTimestamp(COMPLETION_TIME));
        report.setTotalAmountOfProperty(rs.getLong(TOTAL_AMOUNT_OF_PROPERTY));
        report.setAcceptedFromInt(rs.getInt(IS_ACCEPTED));
        report.setShouldChangeFromInt(rs.getInt(SHOULD_BE_CHANGED));
        report.setInspectorComment(rs.getString(INSPECTOR_COMMENT));


        return report;
    }

    @Override
    public Report makeUnique(Map<Long, Report> existing, Report entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
