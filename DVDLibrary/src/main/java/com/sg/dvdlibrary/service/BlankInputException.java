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
public class BlankInputException extends Exception {
    
    public BlankInputException(String message) {
        super(message);
    }
    
    public BlankInputException(String message, Throwable ex) {
        super(message, ex);
    }
}
