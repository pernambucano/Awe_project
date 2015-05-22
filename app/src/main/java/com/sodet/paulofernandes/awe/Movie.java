package com.sodet.paulofernandes.awe;


import java.util.ArrayList;

public class Movie {
    private String title;
    private String thumbnailUrl;
    private String backdropUrl;
    private String sinopse;
    private double rating;


    private String genre;

    private ArrayList<Session> sessions;

    public Movie()
    {

    }



    public Movie(String title, String thumbnailUrl, double rating, String genre,ArrayList<Session> sessions, String backdropUrl, String sinopse)
    {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.rating = rating;
        this.genre = genre;
        this.backdropUrl = backdropUrl;
        this.sinopse = sinopse;
        this.sessions = sessions;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }



    public String getBackdropUrl() {
        return backdropUrl;
    }

    public void setBackdropUrl(String backdropUrl) {
        this.backdropUrl = backdropUrl;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }


    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
