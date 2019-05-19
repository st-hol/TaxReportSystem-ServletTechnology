package ua.training.model.service;


import ua.training.model.dao.ComplaintDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Complaint;


/**
 * This class realize logic
 * for manipulation with db.
 *
 * @author Stanislav Holovachuk
 */
public class ComplaintService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * Makes user's complaint.
     *
     * @param complaint Complaint.
     */
    public void makeComplaintAction(Complaint complaint)  {
        try (ComplaintDao complaintDao  = daoFactory.createComplaintDao()) {
            complaintDao.create(complaint);
        }
    }

}


//todo Optional