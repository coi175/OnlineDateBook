package model;

import java.sql.Time;
import java.sql.Date;

public class Task {
    private Long id;
    private String title;
    private String description;
    private Date data;
    private Long userId;
    private Time time;

    private String state;

    public Task(){};

    public Task(String title, String description, Date data, Long userId, Time time, String state) {
        this.title = title;
        this.description = description;
        this.data = data;
        this.userId = userId;
        this.time = time;
        this.state = state;
    }
    public Task(Long id, String title, String description, Date data, Long userId, Time time, String state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.data = data;
        this.userId = userId;
        this.time = time;
        this.state = state;
    }
    public Task(Long id, String title, String description, Time time) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Task) && (id != null)
                ? id.equals(((Task) other).id)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Task[id=%d,title=%s,time=%s]",
                id, title, time);
    }
}
