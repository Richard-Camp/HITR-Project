package com.example.addressbook.model.Jobs;

public class Job {
    private int id;
    private String jobName;
    private String category;
    private Integer salary;
    private String degree;
    private String companyWebsite;
    private String company;
    private String description;

    public Job(String jobName, String category, Integer salary, String degree, String company, String companyWebsite, String description) {
        this.jobName = jobName;
        this.category = category;
        this.salary = salary;
        this.degree = degree;
        this.companyWebsite = companyWebsite;
        this.company = company;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {return jobName;}

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCategory() {return category;}

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDegree(){
        return degree;
    }

    public void setDegree(String degree){
        this.degree = degree;
    }

    public String getCompanyWebsite(){
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite){
        this.companyWebsite = companyWebsite;
    }

    public String getCompany(){
        return company;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}