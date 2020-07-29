/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.daos.DVDLibraryException;
import com.sg.dvdlibrary.daos.DVDLibraryInMemDao;
import com.sg.dvdlibrary.dtos.DVD;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author codedchai
 */
public class DVDLibraryServiceTest {
    
    public DVDLibraryServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    //TESTS
    @Test
    public void testAddDVDGoldenPath() {
        //create service based on in memory dao
        DVDLibraryService toTest = new DVDLibraryService(new DVDLibraryInMemDao());
        //create new DVD and set the parameters
        DVD toAdd = new DVD();
        toAdd.setTitle("Title");
        toAdd.setReleaseDate(LocalDate.parse("02/15/2000", 
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        toAdd.setRating("PG-13");
        toAdd.setDirectorName("Director Name");
        toAdd.setStudio("Studio");
        toAdd.setUserRating("Good!");
        //now need to try and add the DVD
        try {
            DVD returned = toTest.addDVD(toAdd);
            //now to check that what we added matches
            assertEquals("Title", returned.getTitle());
            assertEquals("02/15/2000", returned.getReleaseDate());
            assertEquals("PG-13", returned.getRating());
            assertEquals("Director Name", returned.getDirectorName());
            assertEquals("Studio", returned.getStudio());
            assertEquals("Good!", returned.getUserRating());
            
            //need to check that it was saved somewhere
            List<DVD> DVDs = toTest.getDVDByTitle("Title");
            DVD toValidate = DVDs.get(0);
            assertEquals("Title", toValidate.getTitle());
            assertEquals("02/15/2000", toValidate.getReleaseDate());
            assertEquals("PG-13", toValidate.getRating());
            assertEquals("Director Name", toValidate.getDirectorName());
            assertEquals("Studio", toValidate.getStudio());
            assertEquals("Good!", toValidate.getUserRating());
            
        } catch (DVDLibraryException ex) {
            fail();
        } catch (BlankInputException ex) {
            fail();
        } catch (DuplicateDVDException ex) {
            fail();
        }
        
    }
    
    @Test
    public void testBlankInput() {
        DVDLibraryService toTest = new DVDLibraryService(new DVDLibraryInMemDao());
        //create new DVD and set the parameters
        DVD toAdd = new DVD();
        //blank title check
        toAdd.setTitle("");
        toAdd.setReleaseDate(LocalDate.parse("02/15/2000", 
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        toAdd.setRating("PG-13");
        toAdd.setDirectorName("Director Name");
        toAdd.setStudio("Studio");
        toAdd.setUserRating("Good!");
        try {
            DVD returned = toTest.addDVD(toAdd);
            fail();
        } catch (DVDLibraryException ex) {
            fail();
        } catch (BlankInputException ex) {
            
        } catch (DuplicateDVDException ex) {
            fail();
        }
        //blank release date
        toAdd.setTitle("Title");
        toAdd.setReleaseDate(LocalDate.parse("", 
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        toAdd.setRating("PG-13");
        toAdd.setDirectorName("Director Name");
        toAdd.setStudio("Studio");
        toAdd.setUserRating("Good!");
       
        try {
            DVD returned = toTest.addDVD(toAdd);
            fail();
        } catch (DVDLibraryException ex) {
            fail();
        } catch (BlankInputException ex) {
            
        } catch (DuplicateDVDException ex) {
            fail();
        }
        //blank MPAA rating
        toAdd.setTitle("Title");
        toAdd.setReleaseDate(LocalDate.parse("02/15/2000", 
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        toAdd.setRating("");
        toAdd.setDirectorName("Director Name");
        toAdd.setStudio("Studio");
        toAdd.setUserRating("Good!");
        try {
            DVD returned = toTest.addDVD(toAdd);
            fail();
        } catch (DVDLibraryException ex) {
            fail();
        } catch (BlankInputException ex) {
            
        } catch (DuplicateDVDException ex) {
            fail();
        }
        //blank director name
        toAdd.setTitle("Title");
        toAdd.setReleaseDate(LocalDate.parse("02/15/2000", 
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        toAdd.setRating("PG-13");
        toAdd.setDirectorName("");
        toAdd.setStudio("Studio");
        toAdd.setUserRating("Good!");
        try {
            DVD returned = toTest.addDVD(toAdd);
            fail();
        } catch (DVDLibraryException ex) {
            fail();
        } catch (BlankInputException ex) {
            
        } catch (DuplicateDVDException ex) {
            fail();
        }
        //blank studio
        toAdd.setTitle("Title");
        toAdd.setReleaseDate(LocalDate.parse("02/15/2000", 
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        toAdd.setRating("PG-13");
        toAdd.setDirectorName("Director Name");
        toAdd.setStudio("");
        toAdd.setUserRating("Good!");
        try {
            DVD returned = toTest.addDVD(toAdd);
            fail();
        } catch (DVDLibraryException ex) {
            fail();
        } catch (BlankInputException ex) {
            
        } catch (DuplicateDVDException ex) {
            fail();
        }
        //blank user rating
        toAdd.setTitle("Title");
        toAdd.setReleaseDate(LocalDate.parse("02/15/2000", 
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        toAdd.setRating("PG-13");
        toAdd.setDirectorName("Director Name");
        toAdd.setStudio("Studio");
        toAdd.setUserRating("");
        try {
            DVD returned = toTest.addDVD(toAdd);
            fail();
        } catch (DVDLibraryException ex) {
            fail();
        } catch (BlankInputException ex) {
            
        } catch (DuplicateDVDException ex) {
            fail();
        }
    }
    
    @Test
    public void testAddDVDDuplicate() {
        //create service based on in memory dao
        DVDLibraryService toTest = new DVDLibraryService(new DVDLibraryInMemDao());
        //create new DVD and set the parameters
        DVD toAdd = new DVD();
        toAdd.setTitle("Title");
        toAdd.setReleaseDate(LocalDate.parse("02/15/2000", 
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        toAdd.setRating("PG-13");
        toAdd.setDirectorName("Director Name");
        toAdd.setStudio("Studio");
        toAdd.setUserRating("Good!");
        
        DVD toAdd2 = new DVD();
        toAdd2.setTitle("Title");
        toAdd2.setReleaseDate(LocalDate.parse("02/15/2000", 
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        toAdd2.setRating("PG-13");
        toAdd2.setDirectorName("Director Name");
        toAdd2.setStudio("Studio");
        toAdd2.setUserRating("Okay...");
        //now need to try and add the DVD
        try {
            DVD returned = toTest.addDVD(toAdd);
            DVD returned2 = toTest.addDVD(toAdd2);
            fail();
            
        } catch (DVDLibraryException ex) {
            fail();
        } catch (BlankInputException ex) {
            fail();
        } catch (DuplicateDVDException ex) {
        }
        
    }
    
    @Test
    public void testGetAllDVDs() {
    }

    @Test
    public void testGetValidDVDs() {
    }

    @Test
    public void testGetDVDByTitle() {
    }

    @Test
    public void testEditDVD() {
    }

    @Test
    public void testDeleteDVD() {
    }
    
}
