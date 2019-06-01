package ua.training.model.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Report {

    private long id;

    private String companyName;
    private String taxpayerCode;
    private Timestamp completionTime;
    private long totalAmountOfProperty;
    private User person;
    private boolean isAccepted;
    private boolean shouldBeChanged;
    private String inspectorComment;

    public Report() {
    }

    public Report(long id, String companyName, String taxpayerCode, Timestamp completionTime, long totalAmountOfProperty, User person, boolean isAccepted, boolean shouldBeChanged, String inspectorComment) {
        this.id = id;
        this.companyName = companyName;
        this.taxpayerCode = taxpayerCode;
        this.completionTime = completionTime;
        this.totalAmountOfProperty = totalAmountOfProperty;
        this.person = person;
        this.isAccepted = isAccepted;
        this.shouldBeChanged = shouldBeChanged;
        this.inspectorComment = inspectorComment;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public Timestamp getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Timestamp completionTime) {
        this.completionTime = completionTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxpayerCode() {
        return taxpayerCode;
    }

    public void setTaxpayerCode(String taxpayerCode) {
        this.taxpayerCode = taxpayerCode;
    }


    public long getTotalAmountOfProperty() {
        return totalAmountOfProperty;
    }

    public void setTotalAmountOfProperty(long totalAmountOfProperty) {
        this.totalAmountOfProperty = totalAmountOfProperty;
    }

    public void setAcceptedFromInt(int iIsAccepted) {
        boolean bIsAccepted = (iIsAccepted != 0);
        isAccepted = bIsAccepted;
    }

    public void setShouldChangeFromInt(int iShouldChange) {
        boolean bShouldChange = (iShouldChange != 0);
        shouldBeChanged = bShouldChange;
    }

    public boolean getIsAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean getShouldBeChanged() {
        return shouldBeChanged;
    }

    public void setShouldBeChanged(boolean shouldBeChanged) {
        this.shouldBeChanged = shouldBeChanged;
    }

    public String getInspectorComment() {
        return inspectorComment;
    }

    public void setInspectorComment(String inspectorComment) {
        this.inspectorComment = inspectorComment;
    }
}
