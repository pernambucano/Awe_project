package com.sodet.paulofernandes.awe;


import java.util.ArrayList;

public class Movie {
    private String title, thumbnailUrl;
    private double rating;
    private ArrayList<String> genre;

    public Movie()
    {

    }

    public Movie(String title, String thumbnailUrl, double rating, ArrayList<String> genre)
    {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.rating = rating;
        this.genre = genre;

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

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

}
