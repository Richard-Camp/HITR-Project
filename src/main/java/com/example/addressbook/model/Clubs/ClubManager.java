package com.example.addressbook.model.Clubs;

import java.util.List;

public class ClubManager {
    private SqliteClubDAO clubDAO;
    public ClubManager(SqliteClubDAO clubDAO) {this.clubDAO = clubDAO;}

    public List<Job> searchClubs(String query) {
        return clubDAO.getAllClubs()
                .stream()
                .filter(user -> isClubMatched(user, query))
                .toList();
    }
    private boolean isClubMatched(Job club, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = club.getClubName()
                + " " + club.getCategory()
                + " " + club.getInterests();
        return searchString.toLowerCase().contains(query);
    }
}
