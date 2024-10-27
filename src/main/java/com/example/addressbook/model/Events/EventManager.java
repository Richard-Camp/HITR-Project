package com.example.addressbook.model.Events;

import com.example.addressbook.model.Internships.Internship;
import com.example.addressbook.model.Internships.SqliteInternshipDAO;

import java.util.List;

public class EventManager {
    private SqliteEventDAO eventDAO;
    public EventManager(SqliteEventDAO eventDAO) {this.eventDAO = eventDAO;}

    public List<Event> searchEvents(String query) {
        return eventDAO.getAllEvents()
                .stream()
                .filter(event -> isEventMatched(event, query))
                .toList();
    }

    private boolean isEventMatched(Event event, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = event.getEventName()
                + " " + event.getCategory();
        return searchString.toLowerCase().contains(query);
    }

    public List<Event> getEvents(String query){
        return eventDAO.queryEvents(query);
    }
}
