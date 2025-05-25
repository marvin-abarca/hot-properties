package edu.depaul.group_project.hot_properties.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //• Many-to-one: message sender (User)
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    //• Many-to-one: message receiver (User)
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Column(nullable = false)
    private String content;


    private LocalDateTime timeMessageSent;

    private String reply;

    // • Many-to-one: associated property (Property).
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public Message() {}
    public Message(Long id, String content, LocalDateTime timeMessageSent, String reply) {
        this.id = id;
        this.content = content;
        this.timeMessageSent = timeMessageSent;
        this.reply = reply;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LocalDateTime getTimeMessageSent() {
        return timeMessageSent;
    }

    public void setTimeMessageSent(LocalDateTime timeMessageSent) {
        this.timeMessageSent = timeMessageSent;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
