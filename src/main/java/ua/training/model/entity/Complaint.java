package ua.training.model.entity;

import java.sql.Timestamp;
import java.util.Date;


public class Complaint {
    private long id;
    private User user;
    private String content;
    private Timestamp completionTime;


    public Complaint() {
    }

    public Complaint(long id, User user, String content, Timestamp completionTime) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.completionTime = completionTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Timestamp completionTime) {
        this.completionTime = completionTime;
    }
}
