/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.views;

import com.sg.dvdlibrary.dtos.DVD;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author codedchai
 */
public class DVDLibraryView {

    UserIO io = new UserIO();

    public int getMainMenuChoice() {
        //print the menu choices to the user
        io.print("\n_____Main Menu_____\n");
        io.print("1. List all DVDs\n");
        io.print("2. List a specific DVD\n");
        io.print("3. Search for a DVD by title\n");
        io.print("4. Add a DVD to the collection\n");
        io.print("5. Edit a DVD in the collection\n");
        io.print("6. Delete a DVD from the collection\n");
        io.print("7. Exit the application\n");
        //returns valid integer between 1 and 7 from the user 
        return io.readInt("Please choose from one of these options: ", 1, 7);

    }

    public void listAllDVDs(List<DVD> allDVDs, String prompt) {
        io.print("Here are all of the DVDs " + prompt + " (Title: Release date)\n");
        for (DVD toPrint : allDVDs) {
            io.print(toPrint.getTitle() + ": " + toPrint.getReleaseDate() + "\n");
        }
    } 

    public void noDVDsMessage() {
        io.print("There are no DVDs in the collection.\n");
    }

    public void confirmationMessage(DVD addedDVD, String action) {
        io.print("The DVD \"" + addedDVD.getTitle() + "\" has been sucessfully "
                + action + ".\n");
    }

    public DVD createDVD() {
        DVD toReturn = new DVD();
        //get all the necessary inputs from the user
        String title = io.readString("Enter the DVD's title: ");
        LocalDate releaseDate = io.readDate("Enter the DVD's release date (MM/dd/yyyy): ");
        String rating = io.readString("Enter the DVD's MPAA rating: ");
        String directorName = io.readString("Enter the director's name for the DVD: ");
        String studio = io.readString("Enter the studio for the DVD: ");
        String userRating = io.readString("Enter your rating and any thoughts you'd "
                + "like to remember: ");
        //make a DVD object with the elements given by the user
        toReturn.setTitle(title);
        toReturn.setReleaseDate(releaseDate);
        toReturn.setRating(rating);
        toReturn.setDirectorName(directorName);
        toReturn.setStudio(studio);
        toReturn.setUserRating(userRating);
        //return completed object
        return toReturn;
    }

    public String getDVDInfo() {
        return io.readString("Please enter valid information about the movie "
                + "(excluding user rating): ");
    }

    public void noMatch() {
        io.print("The info you have entered does not match anything on file.\n");
    }

    public void displayDVD(DVD toDisplay) {
        io.print("Title: " + toDisplay.getTitle() + "\n");
        io.print("Release Date: " + toDisplay.getReleaseDate() + "\n");
        io.print("MPAA Rating: " + toDisplay.getRating() + "\n");
        io.print("Director's Name: " + toDisplay.getDirectorName() + "\n");
        io.print("Studio: " + toDisplay.getStudio() + "\n");
        io.print("User Rating/Notes: " + toDisplay.getUserRating() + "\n");
    }

    public DVD specifyDVD(List<DVD> matchingDVDs) {
        io.print("Your input matches multiple DVDs on file, which DVD would you"
        + " like? (Title, Release Date, Studio)\n");
        for (int i = 0; i < matchingDVDs.size(); i++) {
            io.print((i + 1) + ": " + matchingDVDs.get(i).getTitle() + ", " +
                    matchingDVDs.get(i).getReleaseDate() + ", " + 
                            matchingDVDs.get(i).getStudio() + "\n");
        }
        int userInput = io.readInt("Enter the corresponding number: ", 1, 
                matchingDVDs.size());
        return matchingDVDs.get(userInput - 1);
    }

    public String getDVDTitle() {
        return io.readString("Please enter part or all of a DVD's title: ");
    }

    public void displayErrorMessage() {
        io.print("An error has occured. Please enter your input again.\n");
    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }

    public DVD editDVD(DVD specifiedDVD) {
        DVD edited = new DVD();
        io.print("Please enter the new information or press \"enter\" to keep "
        + "it the same.\n");
        edited.setTitle(io.editString("Title (" + specifiedDVD.getTitle() 
                + "): ", specifiedDVD.getTitle()));
        edited.setReleaseDate(io.editDVD("Release Date (" + 
                specifiedDVD.getReleaseDate() +"): ", specifiedDVD.getReleaseDate()));
        edited.setRating(io.editString("MPAA Rating (" + specifiedDVD.getRating()
                + "): ", specifiedDVD.getRating()));
        edited.setDirectorName(io.editString("Director's Name (" + 
                specifiedDVD.getDirectorName() +"): ", specifiedDVD.getDirectorName()));
        edited.setStudio(io.editString("Studio (" + specifiedDVD.getStudio()
                + "): ", specifiedDVD.getStudio()));
        edited.setUserRating(io.editString("User Rating/Notes (" + 
                specifiedDVD.getUserRating() + "): ", specifiedDVD.getUserRating()));
        
        return edited;
    }

}
