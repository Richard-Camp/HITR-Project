package com.example.addressbook.model.Internships;

import java.util.List;

public class InternshipManager {
    private SqliteInternshipDAO internshipDAO;
    public InternshipManager(SqliteInternshipDAO internshipDAO) {this.internshipDAO = internshipDAO;}

    public List<Internship> searchInternships(String query) {
        return internshipDAO.getAllInternships()
                .stream()
                .filter(internship -> isInternshipMatched(internship, query))
                .toList();
    }

    private boolean isInternshipMatched(Internship internship, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = internship.getInternshipName()
                + " " + internship.getCategory();
        return searchString.toLowerCase().contains(query);
    }

    public List<Internship> getInternships(String query){
        return internshipDAO.queryInternships(query);
    }
}
