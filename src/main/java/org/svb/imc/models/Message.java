package org.svb.imc.models;

import java.util.UUID;

import org.svb.imc.util.Utils;

public class Message {

    private String content;
    private String user;
    private String timestamp;

    private UUID incident;

    private MessageType type;

    public Message(String content, UUID incident, MessageType type, String user) {
        this.content = content;
        this.incident = incident;
        this.type = type;
        this.user = user;
        this.timestamp = Utils.getTimestamp();
    }

    public enum MessageType {
        Message, ActioItem, Question, Status, Note;
    }

    public String getContent() {
        return this.content;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public String getTimestamp() {
        return this.timestamp;
    }

    public String getIncident() {
        return this.incident.toString();
    }

    public MessageType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Message [content=" + this.content + ", incident="
                + this.incident.toString() + ", type=" + this.type + ", user="
                + this.user + ", timestamp=" + this.timestamp + "]";
    }
}
