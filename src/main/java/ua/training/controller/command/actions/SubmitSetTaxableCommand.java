package ua.training.controller.command.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.TaxableItem;
import ua.training.model.service.TaxableItemService;

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
public class SubmitSetTaxableCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SubmitSetTaxableCommand.class);


    private TaxableItemService taxableItemService;

    public SubmitSetTaxableCommand(TaxableItemService taxableItemService) {
        this.taxableItemService = taxableItemService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CommandUtility.defineTaxableItemsAttribute(request);
        CommandUtility.defineUsersAssignedToInspectorAttribute(request);


        final long idItem = Long.parseLong(request.getParameter("idItem"));
        final long idPerson = Long.parseLong(request.getParameter("idPerson"));
        //todo verif
        final int quantity = Integer.parseInt(request.getParameter("quantity"));

        TaxableItem taxableItem = new TaxableItem();
        taxableItem.setId(idItem);
        taxableItem.setIdPerson(idPerson);
        taxableItem.setQuantity(quantity);

        taxableItemService.setTaxableItemsPerPerson(taxableItem);
        logger.info("Taxable Item id#" + idItem + " was updated to person id#" + idPerson);

        return "/WEB-INF/inspector/set-taxable.jsp";
    }

}
