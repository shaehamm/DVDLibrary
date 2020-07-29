/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.daos;

/**
 *
 * @author codedchai
 */
public class DVDLibraryException extends Exception {
    //allows us to hide details from the rest of our classes
    //how most all of out exception classes will look

    public DVDLibraryException(String message) {
        super(message);
    }

    public DVDLibraryException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
