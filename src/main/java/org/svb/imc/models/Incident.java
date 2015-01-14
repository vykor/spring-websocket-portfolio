package org.svb.imc.models;

import java.util.UUID;

import org.svb.imc.util.Utils;

public class Incident {

    private UUID uuid;
    private String title;
    private String description;
    private String coordinator;
    private String management;
    private String ticket;
    private String conference;
    private String user;
    private IncidentStatus status;
    private String createTime;
    private String resolveTime;

    public Incident(String title, String description, String user) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.user = user;
        this.status = IncidentStatus.Active;
        this.createTime = Utils.getTimestamp();
    }

    public enum IncidentStatus {
        Active, Resolved, Cancelled, Deleted;
    }

    public String getUUID() {
        return this.uuid.toString();
    }

    public void setUUID(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCoordinator() {
        return this.coordinator;
    }

    public void setCoordinator(String name) {
        this.coordinator = name;
    }

    public String getManagement() {
        return this.management;
    }

    public void setManagement(String name) {
        this.management = name;
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getConference() {
        return this.conference;
    }

    public void setConference(String number) {
        this.conference = number;
    }

    public String getUser() {
        return this.user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public IncidentStatus getStatus() {
        return this.status;
    }

    public void setStatus(IncidentStatus status) {
        this.status = status;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public String getResolveTime() {
        return this.resolveTime;
    }

    public void setResolveTime(String time) {
        this.resolveTime = time;
    }

    @Override
    public String toString() {
        return "Incident [uuid=" + this.uuid + ", title=" + this.title
                + ", description=" + this.description + ", coordinator="
                + this.coordinator + ", management=" + this.management
                + ", ticket=" + this.ticket + ", conference=" + this.conference
                + ", user=" + this.user + ", status=" + this.status
                + ", createTime=" + this.createTime + "]";
    }

}
