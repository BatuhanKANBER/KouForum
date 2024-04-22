package com.kouforum.backend.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "shareds")
public class Shared {
    @Id
    @GeneratedValue
    private long id;

    @Size(min = 1, max = 500)
    @Column(length = 500)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestemp(java.sql.Timestamp timestamp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTimestemp'");
    }
}
