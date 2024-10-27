package com.example.addressbook.model.Events;

public class Event {
    private int id;
    private String eventName;
    private String companyName;
    private String eventDate;
    private String category;
    private String companyWebsite;

    public Event(String eventName, String companyName, String eventDate,String category, String companyWebsite) {
        this.eventName = eventName;
        this.companyName = companyName;
        this.eventDate = eventDate;
        this.category = category;
        this.companyWebsite = companyWebsite;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {return eventName;}

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCategory() {return category;}

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public String getCompanyWebsite(){
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite){
        this.companyWebsite = companyWebsite;
    }

    public String getEventDate(){
        return eventDate;
    }

    public void setEventDate(String eventDate){
        this.eventDate = eventDate;
    }
}
