package com.example.addressbook.model.Jobs;

import java.util.List;

public class JobManager {
    private SqliteJobDAO jobDAO;
    public JobManager(SqliteJobDAO jobDAO) {this.jobDAO = jobDAO;}

    public List<Job> searchClubs(String query) {
        return jobDAO.getAllClubs()
                .stream()
                .filter(user -> isClubMatched(user, query))
                .toList();
    }
    private boolean isClubMatched(Job job, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = job.getJobName()
                + " " + job.getCategory()
                + " " + job.getInterests()
                + " " + job.getDegree();
        return searchString.toLowerCase().contains(query);
    }
}
