package ua.training.controller.command.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.TaxableItem;
import ua.training.model.service.TaxableItemService;
import static ua.training.controller.command.TextConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is responsible for acception/rejection of report.
 * from user-role personal cabinet.
 *
 * @author Stanislav Holovachuk
 */
public class SubmitSetTaxable implements Command {

    private static final Logger logger = LogManager.getLogger(SubmitSetTaxable.class);
    private TaxableItemService taxableItemService;

    public SubmitSetTaxable() {
        this.taxableItemService = TaxableItemService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //to prevent user coming back to cached pages after logout
        CommandUtility.disallowBackToCached(request, response);

        CommandUtility.populateTaxableItemsAttribute(request);
        CommandUtility.populateUsersAssignedToInspectorAttribute(request);

        final long idItem = Long.parseLong(request.getParameter(ID_ITEM));
        final long idPerson = Long.parseLong(request.getParameter(ID_PERSON));
        final int quantity = Integer.parseInt(request.getParameter(QUANTITY));

        final TaxableItem taxableItem = new TaxableItem();
        taxableItem.setId(idItem);
        taxableItem.setIdPerson(idPerson);
        taxableItem.setQuantity(quantity);

        taxableItemService.setTaxableItemsPerPerson(taxableItem);
        logger.info("Taxable Item id#" + idItem + " was updated to person id#" + idPerson);

        return SET_TAXABLE_ITEMS;
    }

}



























//        String path = request.getServletContext().getContextPath();
//        return "redirect@" + path + "/app/set-taxable";


