/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.daos.DVDLibraryDaoImpl;
import com.sg.dvdlibrary.service.DVDLibraryService;
import com.sg.dvdlibrary.views.DVDLibraryView;

/**
 *
 * @author codedchai
 */
public class App {
    
    public static void main(String[] args) {
        
        DVDLibraryView view = new DVDLibraryView();
        DVDLibraryDaoImpl dao = new DVDLibraryDaoImpl();
        
        DVDLibraryService service = new DVDLibraryService(dao);
        
        DVDLibraryController controller = new
            DVDLibraryController(view, service);
        
        controller.run();
    }
    
}
