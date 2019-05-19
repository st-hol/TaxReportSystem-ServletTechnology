package ua.training.controller.command.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.Complaint;
import ua.training.model.entity.User;
import ua.training.model.service.ComplaintService;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * This class is responsible for applying for admission
 * from user-role personal cabinet.
 *
 * @author Stanislav Holovachuk
 */
public class SubmitComplaintCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SubmitComplaintCommand.class);

    UserService userService;
    ComplaintService complaintService;

    public SubmitComplaintCommand(ComplaintService complaintService, UserService userService) {
        this.complaintService = complaintService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Complaint complaint = new Complaint();

        //todo validation
        //long idChargedInspector = Long.parseLong(request.getParameter("idChargedInspector"));

        final User currentSessionUser = CommandUtility.getCurrentSessionUser(request);
        final User inspector = currentSessionUser.getAssignedInspector();
        final String content = request.getParameter("content");

        complaint.setUser(inspector);
        complaint.setCompletionTime(Timestamp.from(Instant.now()));
        complaint.setContent(content);

        complaintService.makeComplaintAction(complaint);
        logger.info("Complaint was charged on inspector " + inspector.getId());

        return "/WEB-INF/client/submit-complaint.jsp";
    }

}
