/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.daos.DVDLibraryDaoInterface;
import com.sg.dvdlibrary.daos.DVDLibraryException;
import com.sg.dvdlibrary.dtos.DVD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author codedchai
 */
public class DVDLibraryService {
    
    DVDLibraryDaoInterface dao;
    
    public DVDLibraryService(DVDLibraryDaoInterface dao) {
        this.dao = dao;
    }

    public List<DVD> getAllDVDs() {
        return dao.getAllDVDs();
    }

    public List<DVD> getValidDVDs(String infoOnDVD) {
        return dao.getValidDVDs(infoOnDVD);
    }

    public List<DVD> getDVDByTitle(String userTitle) {
        return dao.getDVDByTitle(userTitle);
    }

    public DVD addDVD(DVD toAdd) throws BlankInputException, DVDLibraryException, DuplicateDVDException {
        List<DVD> allDVDs = getAllDVDs();
        //need to check that anything entered is not blank
        if (toAdd.getTitle().isBlank() || toAdd.getReleaseDate().equals(null) ||
                toAdd.getDirectorName().isBlank() || toAdd.getStudio().isBlank()
                || toAdd.getRating().isBlank() || toAdd.getUserRating().isBlank()) {
            throw new BlankInputException("DVD input cannot be blank.");
        }
        for (int i = 0; i < allDVDs.size(); i++) {
            if (toAdd.equalsDVD(allDVDs.get(i))) {
                throw new DuplicateDVDException("This DVD already exists.");
            }
        }
        return dao.addDVD(toAdd);
    }

    public void editDVD(DVD specifiedDVD, DVD newDVD) throws BlankInputException, DVDLibraryException, DuplicateDVDException {
        List<DVD> allDVDs = getAllDVDs();
        
        if (newDVD.getTitle().isBlank() || newDVD.getReleaseDate().isEqual(null) ||
                newDVD.getDirectorName().isBlank() || newDVD.getStudio().isBlank()
                || newDVD.getRating().isBlank() || newDVD.getUserRating().isBlank()) {
            throw new BlankInputException("Input cannot be blank.");
        }
        for (int i = 0; i < allDVDs.size(); i++) {
            if (newDVD.equalsDVD(allDVDs.get(i))) {
                throw new DuplicateDVDException("This DVD already exists.");
            }
        }
       
        dao.editDVD(specifiedDVD, newDVD);
    }

    public void deleteDVD(DVD toDelete) throws DVDLibraryException {
        dao.deleteDVD(toDelete);
    }
    
}
