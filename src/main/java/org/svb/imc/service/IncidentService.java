package org.svb.imc.service;

import java.util.ArrayList;
import java.util.List;

import org.svb.imc.models.Incident;


public class IncidentService {

    private final List<Incident> incidents = new ArrayList<Incident>();

    public List<Incident> getIncidents() {
        return incidents;
    }
    
    public void addIncident(String title, String description, String user) {
        Incident incident = new Incident(title, description, user);
        this.incidents.add(incident);
    }
}
