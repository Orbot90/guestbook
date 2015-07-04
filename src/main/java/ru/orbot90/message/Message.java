package ru.orbot90.message;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by orbot on 04.07.15.
 */
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "message_text")
    private String message;
    @Column(name = "message_date")
    private Date date;
    @Column(name = "message_postername")
    private String name;

    public Message(String name, Date date, String message) {
        this.date = date;
        this.message = message;
        this.name = name;
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
}
