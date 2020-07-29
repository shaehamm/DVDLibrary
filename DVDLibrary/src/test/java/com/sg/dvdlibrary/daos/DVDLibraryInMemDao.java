/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.daos;

import com.sg.dvdlibrary.dtos.DVD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author codedchai
 */
public class DVDLibraryInMemDao implements DVDLibraryDaoInterface {
    
    List<DVD> allDVDs = new ArrayList<>();

    @Override
    public List<DVD> getAllDVDs() {
       
        return allDVDs;
    }

    @Override
    public DVD addDVD(DVD toAdd) throws DVDLibraryException {
        //add the DVD that's not in the list to the list of all DVDs
        allDVDs.add(toAdd);
        return toAdd;
    }

    @Override
    public void editDVD(DVD toEdit, DVD newDVD) throws DVDLibraryException {
        int indexToEdit = 0;

        for (int i = 0; i < allDVDs.size(); i++) {
            if (toEdit.equalsDVD(allDVDs.get(i))) {
                indexToEdit = i;
            }
        }
        allDVDs.set(indexToEdit, newDVD);
    }

    @Override
    public void deleteDVD(DVD toRemove) throws DVDLibraryException {
        int indexToRemove = 0;

        for (int i = 0; i < allDVDs.size(); i++) {
            if (toRemove.equalsDVD(allDVDs.get(i))) {
                indexToRemove = i;
            }
        }

        allDVDs.remove(indexToRemove);
    }

    @Override
    public List<DVD> getValidDVDs(String infoOnDVD) {
        List<DVD> matchingDVDs = new ArrayList<>();

        //goes through all the DVDs in the collection 
        for (DVD toCheck : allDVDs) {
            //compares the users input to each DVD info
            if (matchingInfo(infoOnDVD, toCheck)) {
                matchingDVDs.add(toCheck);
            }
        }
        return matchingDVDs;
    }

    private boolean matchingInfo(String infoOnDVD, DVD toCheck) {
        boolean matching = false;
        if (toCheck.getTitle().equalsIgnoreCase(infoOnDVD)
                || toCheck.getReleaseDate().equals(infoOnDVD)
                || toCheck.getRating().equalsIgnoreCase(infoOnDVD)
                || toCheck.getDirectorName().equalsIgnoreCase(infoOnDVD)
                || toCheck.getStudio().equalsIgnoreCase(infoOnDVD)) {
            matching = true;
        }
        return matching;
    }

    @Override
    public List<DVD> getDVDByTitle(String userTitle) {
        List<DVD> matchingTitles = new ArrayList<>();

        for (DVD toCheck : allDVDs) {
            if (toCheck.getTitle().contains(userTitle)) {
                matchingTitles.add(toCheck);
            }
        }
        return matchingTitles;
    }

}
