package com.example.addressbook.model.Clubs;

public class Club {
    private int id;
    private String clubName;
    private String category;
    private String interests;
    private String clubImage;
    private String clubWebsite;

    public Club(String clubName, String category, String interests, String clubImage, String clubWebsite) {
        this.clubName = clubName;
        this.category = category;
        this.interests = interests;
        this.clubImage = clubImage;
        this.clubWebsite = clubWebsite;
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

    public String getClubImage(){
        return clubImage;
    }

    public void setClubImage(String clubImage){
        this.clubImage = clubImage;
    }

    public String getClubWebsite(){
        return clubWebsite;
    }

    public void setClubWebsite(String clubWebsite){
        this.clubWebsite = clubWebsite;
    }
}