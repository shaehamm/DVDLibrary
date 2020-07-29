/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.daos;

import com.sg.dvdlibrary.dtos.DVD;
import java.util.List;

/**
 *
 * @author codedchai
 */
public interface DVDLibraryDaoInterface {

    DVD addDVD(DVD toAdd) throws DVDLibraryException;

    void deleteDVD(DVD toRemove) throws DVDLibraryException;

    void editDVD(DVD toEdit, DVD newDVD) throws DVDLibraryException;

    List<DVD> getAllDVDs();

    List<DVD> getDVDByTitle(String userTitle);

    List<DVD> getValidDVDs(String infoOnDVD);
    
}
