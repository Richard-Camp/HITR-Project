package com.example.addressbook.model.Internships;

public class Internship {
    private int id;
    private String internshipName;
    private String companyName;
    private String category;
    private String internshipWebsite;
    private String description;

    public Internship(String internshipName, String companyName,String category, String description, String internshipWebsite) {
        this.internshipName = internshipName;
        this.companyName = companyName;
        this.category = category;
        this.internshipWebsite = internshipWebsite;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInternshipName() {return internshipName;}

    public void setInternshipName(String internshipName) {
        this.internshipName = internshipName;
    }

    public String getCategory() {return category;}

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInternshipWebsite(){
        return internshipWebsite;
    }

    public void setInternshipWebsite(String internshipWebsite){
        this.internshipWebsite = internshipWebsite;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
}
