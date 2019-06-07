package ua.training.controller.command.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.Complaint;
import ua.training.model.entity.User;
import ua.training.model.service.ComplaintService;

import static ua.training.controller.command.TextConstants.Parameters.*;
import static ua.training.controller.command.TextConstants.Routes.*;

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
public class SubmitComplaint implements Command {

    private static final Logger logger = LogManager.getLogger(SubmitComplaint.class);

    private ComplaintService complaintService;

    public SubmitComplaint() {
        this.complaintService = ComplaintService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //to prevent user coming back to cached pages after logout
        CommandUtility.disallowBackToCached(request, response);

        final User currentSessionUser = CommandUtility.getCurrentSessionUser(request);
        final User inspector = currentSessionUser.getAssignedInspector();
        final String content = request.getParameter(CONTENT);

        Complaint complaint = accomplishNewComplaint(inspector, content);
        complaintService.makeComplaintAction(complaint);
        logger.info("Complaint was charged on inspector #" + inspector.getId());

        return TO_SUBMIT_COMPLAINT;
    }

    private Complaint accomplishNewComplaint(User inspector, String content) {
        final Complaint complaint = new Complaint();
        complaint.setUser(inspector);
        complaint.setCompletionTime(Timestamp.from(Instant.now()));
        complaint.setContent(content);

        return complaint;
    }
}

















