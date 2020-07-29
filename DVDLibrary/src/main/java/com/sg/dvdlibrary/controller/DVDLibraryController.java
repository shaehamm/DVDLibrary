/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.daos.DVDLibraryException;
import com.sg.dvdlibrary.dtos.DVD;
import com.sg.dvdlibrary.service.BlankInputException;
import com.sg.dvdlibrary.service.DVDLibraryService;
import com.sg.dvdlibrary.service.DuplicateDVDException;
import com.sg.dvdlibrary.views.DVDLibraryView;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author codedchai
 */
public class DVDLibraryController {

    DVDLibraryView view;
    DVDLibraryService service;

    //contructor
    public DVDLibraryController(DVDLibraryView view, DVDLibraryService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        boolean done = false;

        while (!done) {
            //display menu and get choice from the user
            int menuChoice = view.getMainMenuChoice();
            //based on user input run associated code
            try {
                switch (menuChoice) {
                    case 1:
                        listAllDVDs();
                        break;
                    case 2:
                        listOneDVD();
                        break;
                    case 3:
                        searchByTitle();
                        break;
                    case 4:
                        addDVD();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        removeDVD();
                        break;
                    case 7:
                        done = true;
                        break;
                    default:
                        view.displayErrorMessage();
                        break;
                }

            } catch (DVDLibraryException | BlankInputException |
                    DuplicateDVDException ex) {
                view.displayErrorMessage(ex.getMessage());
            } 
        }
    }

    //lists all DVDs currently in the collection (if any)
    private void listAllDVDs() {
        List<DVD> allDVDs = service.getAllDVDs();
        if (allDVDs.size() > 0) {
            view.listAllDVDs(allDVDs, "currently in the collection");
        } else {
            view.noDVDsMessage();
        }
    }

    //lists one DVD after getting a valid indicator
    private void listOneDVD() {
        String infoOnDVD = view.getDVDInfo();
        List<DVD> matchingDVDs = service.getValidDVDs(infoOnDVD);
        //check if the info entered matches anything on file
        if (matchingDVDs.size() == 1) {
            //if there's one match we can display the DVD to the user
            view.displayDVD(matchingDVDs.get(0));
        } else if (matchingDVDs.isEmpty()) {
            view.noMatch();
        } else {
            DVD specifiedDVD = view.specifyDVD(matchingDVDs);
            view.displayDVD(specifiedDVD);
        }
    }

    //shows all DVDs that match the user's input
    private void searchByTitle() {
        //get the DVD's title from the user
        String userTitle = view.getDVDTitle();
        //check if it matches any titles on file
        List<DVD> titleMatches = service.getDVDByTitle(userTitle);

        if (titleMatches.isEmpty()) {
            view.noMatch();
        } else {
            //if there's any matches we can display the DVDs to the user
            view.listAllDVDs(titleMatches, "that match the input \""
                    + userTitle + "\"");
        }
    }

    //adds a DVD to the collection
    private void addDVD() throws DVDLibraryException, BlankInputException, DuplicateDVDException {
        DVD toAdd = view.createDVD();
        DVD addedDVD = service.addDVD(toAdd);
        view.confirmationMessage(addedDVD, "added");
    }

    //edits a DVD already in the collection
    private void editDVD() throws DVDLibraryException, BlankInputException, DuplicateDVDException {
        //gets/find the DVD the user wants to edit
        String userInput = view.getDVDInfo();
        List<DVD> matchingDVDs = service.getValidDVDs(userInput);
        //checks if any DVDs match the input
        if (matchingDVDs.size() > 0) {
            DVD newDVD;
            //if there's multiple matches get the specific DVD that needs to 
            //be edited
            if (matchingDVDs.size() > 1) {
                DVD specifiedDVD = view.specifyDVD(matchingDVDs);
                //gets new info from the user
                newDVD = view.editDVD(specifiedDVD);
                service.editDVD(specifiedDVD, newDVD);
            } else {
                newDVD = view.editDVD(matchingDVDs.get(0));
                service.editDVD(matchingDVDs.get(0), newDVD);
            }
            view.confirmationMessage(newDVD, "edited");
        } else {
            view.noMatch();
        }
    }

    //removes a DVD already in the collection
    private void removeDVD() throws DVDLibraryException {
       String userInput = view.getDVDInfo();
        List<DVD> matchingDVDs = service.getValidDVDs(userInput);
        //checks if any DVDs match the input
        if (matchingDVDs.size() > 0) {
            DVD toDelete = new DVD();
            //if there's multiple matches get the specific DVD that needs to 
            //be deleted
            if (matchingDVDs.size() > 1) {
                toDelete = view.specifyDVD(matchingDVDs);
            } else {
                toDelete = matchingDVDs.get(0);
            }
            service.deleteDVD(toDelete);
            view.confirmationMessage(toDelete, "deleted");
        } else {
            view.noMatch();
        } 
    }

}
