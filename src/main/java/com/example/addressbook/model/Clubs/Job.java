package com.example.addressbook.model.Clubs;

public class Club {
    private int id;
    private String clubName;
    private String category;
    private String interests;

    public Club(String clubName, String category, String interests) {
        this.clubName = clubName;
        this.category = category;
        this.interests = interests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClubName() {return clubName;}

    public void setClubName(String clubName) {
        this.clubName = clubName;
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

}