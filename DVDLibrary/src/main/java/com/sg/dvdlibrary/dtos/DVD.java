/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dtos;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author codedchai
 */
public class DVD {

    //fields
    private String title;
    private LocalDate releaseDate;
    private String rating;
    private String directorName;
    private String studio;
    private String userRating;

    private int id;

    //getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public boolean equalsDVD(DVD that) {
        boolean toReturn = false;
        if (this.title.equals(that.title)
                && this.releaseDate.equals(that.releaseDate)
                && this.directorName.equals(that.directorName)) {
            toReturn = true;
        }
        return toReturn;
    }

}
