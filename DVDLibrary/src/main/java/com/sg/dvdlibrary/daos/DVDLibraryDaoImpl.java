/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.daos;

import com.sg.dvdlibrary.dtos.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author codedchai
 */
public class DVDLibraryDaoImpl implements DVDLibraryDaoInterface {

    String path = "dvds.txt";

    @Override
    public List<DVD> getAllDVDs() {
        //initialize an array list to hold all the DVDs
        List<DVD> allDVDs = new ArrayList<>();
        try {
            //read in the DVDs with scanner
            Scanner fileScanner = new Scanner(new BufferedReader(new FileReader("dvds.txt")));

            //loops while the file still has lines (assuming no header)
            while (fileScanner.hasNextLine()) {
                String row = fileScanner.nextLine();
                //converts the read in line to a DVD object
                DVD toAdd = lineToDVD(row);
                //adds the DVD object to the arraylist of DVDs
                allDVDs.add(toAdd);
            }
        } catch (FileNotFoundException ex) {
        }
        return allDVDs;
    }

    @Override
    public DVD addDVD(DVD toAdd) throws DVDLibraryException {
        //need to get all DVDs to update the full list of DVDs
        List<DVD> allDVDs = getAllDVDs();
        //add the DVD that's not in the list to the list of all DVDs
        allDVDs.add(toAdd);
        //update the file by writing the new list to the file
        writeToFile(allDVDs);
        return toAdd;
    }

    @Override
    public void editDVD(DVD toEdit, DVD newDVD) throws DVDLibraryException {
        List<DVD> allDVDs = getAllDVDs();
        int indexToEdit = 0;
        
        for (int i = 0; i < allDVDs.size(); i++) {
            if (toEdit.equalsDVD(allDVDs.get(i))) {
                indexToEdit = i;
            }
        }
        allDVDs.set(indexToEdit, newDVD);
        writeToFile(allDVDs);
    }

    @Override
    public void deleteDVD(DVD toRemove) throws DVDLibraryException {
        List<DVD> allDVDs = getAllDVDs();
        int indexToRemove = 0;
        
        for (int i = 0; i < allDVDs.size(); i++) {
            if (toRemove.equalsDVD(allDVDs.get(i))) {
                indexToRemove = i;
            }
        }
        
        allDVDs.remove(indexToRemove);
        writeToFile(allDVDs);
    }

    private DVD lineToDVD(String row) {
        DVD toReturn = new DVD();
        //split the row at the delimiter
        String[] elements = row.split("::");
        //call the correct setters on specific elements
        toReturn.setTitle(elements[0]);
        toReturn.setReleaseDate(LocalDate.parse(elements[1]));
        toReturn.setRating(elements[2]);
        toReturn.setDirectorName(elements[3]);
        toReturn.setStudio(elements[4]);
        toReturn.setUserRating(elements[5]);
        //returns fully made DVD object
        return toReturn;
    }

    private void writeToFile(List<DVD> allDVDs) throws DVDLibraryException {
        try {
            //create a printwriter
            PrintWriter writer = new PrintWriter(new FileWriter(path));
            //repeat for all the rows in the array list
            for (int i = 0; i < allDVDs.size(); i++) {
                //convert the arraylist row to a string so it can be written
                //to the file
                String toWrite = convertDVDToString(allDVDs.get(i));
                //write the string to the file
                writer.println(toWrite);
            }
            writer.flush();
            writer.close();

        } catch (IOException ex) {
            throw new DVDLibraryException("Could not open file " + path, ex);
        }
    }

    private String convertDVDToString(DVD toConvert) {
        //create a string from the arraylist row, with delimiter "::"
        String toReturn = toConvert.getTitle() + "::"
                + toConvert.getReleaseDate() + "::"
                + toConvert.getRating() + "::"
                + toConvert.getDirectorName() + "::"
                + toConvert.getStudio() + "::"
                + toConvert.getUserRating();
        return toReturn;
    }
 
    @Override
    public List<DVD> getValidDVDs(String infoOnDVD) {
        List<DVD> allDVDs = getAllDVDs();
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
        List<DVD> allDVDs = getAllDVDs();
        List<DVD> matchingTitles = new ArrayList<>();

        for (DVD toCheck : allDVDs) {
            if (toCheck.getTitle().contains(userTitle)) {
                matchingTitles.add(toCheck);
            }
        }
        return matchingTitles;
    }
}
