package com.example.addressbook.model.Jobs;

import java.util.List;

public class JobManager {
    private SqliteJobDAO jobDAO;
    public JobManager(SqliteJobDAO jobDAO) {this.jobDAO = jobDAO;}

    public List<Job> searchJobs(String query) {
        return jobDAO.getAllJobs()
                .stream()
                .filter(user -> isJobMatched(user, query))
                .toList();
    }
    private boolean isJobMatched(Job job, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = job.getJobName()
                + " " + job.getCategory()
                + " " + job.getDegree()
                + " " + job.getCompany();
        return searchString.toLowerCase().contains(query);
    }
    public List<Job> getJobs(String query){
        return jobDAO.queryJobs(query);
    }
}
