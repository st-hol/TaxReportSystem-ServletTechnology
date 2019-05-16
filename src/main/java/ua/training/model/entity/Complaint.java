package ua.training.model.entity;

import java.time.LocalDate;
import java.sql.Timestamp;
import java.util.Date;


public class Complaint {
    private long id;
    private User user;
    private String content;
    private Date completion_time;

    public Complaint(long id, User user, String content, Date completion_time) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.completion_time = completion_time;
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

    public Date getCompletion_time() {
        return completion_time;
    }

    public void setCompletion_time(Date completion_time) {
        this.completion_time = completion_time;
    }
}
