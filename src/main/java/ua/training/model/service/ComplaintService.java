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

    private DaoFactory daoFactory;

    private static ComplaintService instance;

    private ComplaintService() {
        daoFactory = DaoFactory.getInstance();
    }

    public static ComplaintService getInstance() {
        if (instance == null) {
            synchronized (ComplaintService.class) {
                if (instance == null) {
                    instance = new ComplaintService();
                }
            }
        }
        return instance;
    }

    /**
     * Makes user's complaint.
     *
     * @param complaint Complaint.
     */
    public void makeComplaintAction(Complaint complaint) {
        ComplaintDao complaintDao = daoFactory.createComplaintDao();
        complaintDao.create(complaint);
    }

}
