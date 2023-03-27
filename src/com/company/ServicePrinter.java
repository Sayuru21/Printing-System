package com.company;

/** **************************************************************
 * File:      ServicePrinter.java (INTERFACE)	
 * Author:    P. Howells	
 * Contents:  6SENG002W CWK  
 *            This provides the interface for the technicians  
 *            to use & service the printer. 
 * Date:      22/10/20
 * Version:   1.0	
 ************************************************************** */

public interface ServicePrinter extends Printer
{

    // Printer static constants
    int Full_Paper_Tray = 250 ;
    int SheetsPerPack = 50  ;
    int Full_Toner_Level = 500 ;
    int Minimum_Toner_Level = 10  ;
    int PagesPerTonerCartridge = 500 ;

    // Technicians methods
    void replaceTonerCartridge( ) ;
    void refillPaper( ) ;

}

