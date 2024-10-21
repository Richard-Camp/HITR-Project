package com.example.addressbook.model.Clubs;

import java.util.List;

public class ClubManager {
    private SqliteClubDAO clubDAO;
    public ClubManager(SqliteClubDAO clubDAO) {this.clubDAO = clubDAO;}

    public List<Club> searchClubs(String query) {
        return clubDAO.getAllClubs()
                .stream()
                .filter(club -> isClubMatched(club, query))
                .toList();
    }

    private boolean isClubMatched(Club club, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = club.getClubName()
                + " " + club.getCategory()
                + " " + club.getInterests();
        return searchString.toLowerCase().contains(query);
    }
}
