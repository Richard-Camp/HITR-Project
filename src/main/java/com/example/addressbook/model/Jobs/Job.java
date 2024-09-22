package com.example.addressbook.model.Jobs;

public class Job {
    private int id;
    private String jobName;
    private String category;
    private String interests;
    private String degree;

    public Job(String jobName, String category, String interests, String degree) {
        this.jobName = jobName;
        this.category = category;
        this.interests = interests;
        this.degree = degree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {return jobName;}

    public void setJobName(String clubName) {
        this.jobName = clubName;
    }

    public String getCategory() {return category;}

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getDegree(){return degree;}

    public void setDegree(String degree){this.degree = degree;}

}