package ua.training.controller.command.directions;



import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.training.controller.command.TextConstants.EDIT_REPORT;

/**
 * This class is responsible for forwarding
 * to check report page.
 *
 * @author Stanislav Holovachuk
 */

public class EditReport implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //to prevent user coming back to cached pages after logout
        CommandUtility.disallowBackToCached(request, response);

        CommandUtility.populateReportToEditAttribute(request);
        return EDIT_REPORT;
    }
}
