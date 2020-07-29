/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

/**
 *
 * @author codedchai
 */
public class DuplicateDVDException extends Exception{
    
    public DuplicateDVDException(String message) {
        super(message);
    }
    
    public DuplicateDVDException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
