package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    // Console messages
    final static String PRINTER ="[ " + currentDateTime() + " ] [ PRINTER ]: ";
    final static String STUDENT = "[ " + currentDateTime() + " ] [ STUDENT ]: ";
    final static String PRINTING_SYSTEM = "[ " + currentDateTime() + " ] [ PRINTING SYSTEM ]: ";
    final static String PAPER_TECHNICIAN = "[ " + currentDateTime() + " ] [ PAPER TECHNICIAN ]: ";
    final static String TONER_TECHNICIAN = "[ " + currentDateTime() + " ] [ TONER TECHNICIAN ]: ";

    public static String currentDateTime(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
        Date date = new Date();
        return formatter.format(date);
    }

    // Random duration time for thread sleeping
    public static long randomDuration(){

        double randomTime;

        do {
             randomTime = Math.random();
        }
        while (randomTime == 0.0);

        return (long) (randomTime * 1000);
    }
}
