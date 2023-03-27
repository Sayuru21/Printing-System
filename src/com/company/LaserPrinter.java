package com.company;

public class LaserPrinter implements ServicePrinter{

    private String name;
    private int currentPaperLevel;
    private int currentTonerLevel;
    private int numberOfDocumentsPrinted;
    private boolean paperRefilled = false;
    private boolean tonerReplaced = false;
    private int paperPacks = 0;

    public LaserPrinter(String name, int currentPaperLevel, int currentTonerLevel, int numberOfDocumentsPrinted) {
        super();
        this.name = name;
        this.currentPaperLevel = currentPaperLevel;
        this.currentTonerLevel = currentTonerLevel;
        this.numberOfDocumentsPrinted = numberOfDocumentsPrinted;
    }

    public boolean isPaperRefilled() {

        return paperRefilled;
    }

    public boolean isTonerReplaced() {

        return tonerReplaced;
    }

    @Override
    public synchronized void printDocument(Document document) {

        message(Utilities.PRINTER + " Printer Status: " + this);

        while (!(document.getNumberOfPages() <= currentPaperLevel && document.getNumberOfPages() <= currentTonerLevel)){

            try {

                if(document.getNumberOfPages() > currentPaperLevel){
                    message(Utilities.PRINTER + "Insufficient Papers. Wait until refilling.......");
                } else if (document.getNumberOfPages() > currentTonerLevel) {
                    message(Utilities.PRINTER + "Insufficient Toner. Wait until toner replacing.......");
                }
                else{
                    message(Utilities.PRINTER + "Paper and Toner insufficient. Wait until they are " +
                            "available.......");
                }

                wait();
            }
            catch (InterruptedException e) {
                message(Utilities.PRINTER + " Printer Status: " + this);
                e.printStackTrace();
            }
        }

        this.currentPaperLevel -= document.getNumberOfPages();
        this.currentTonerLevel -= document.getNumberOfPages();
        this.numberOfDocumentsPrinted++;
        message(Utilities.PRINTER + "Printed Document: " + document);
        message(Utilities.PRINTER + "Printer Status: " + this);
        notifyAll();
    }


    @Override
    public synchronized void replaceTonerCartridge() {

        boolean tried = false;
        this.tonerReplaced = false;

        while (this.currentTonerLevel > (Minimum_Toner_Level - 1)) {

            if(tried) {
                message(Utilities.PRINTER + "No need of replacing the toner cartridge");
                break;
            }
            try {
                message(Utilities.PRINTER + "Printer Status: " + this);
                message(Utilities.PRINTER + "Please wait to recheck the toner because it has not yet reached the minimal level.");
                wait(5000);
            } catch (InterruptedException e) {
                message(Utilities.PRINTER + "Printer Status: " + this);
                e.printStackTrace();
            }

            tried = true;
        }

        if(this.currentTonerLevel < Minimum_Toner_Level) {
            this.currentTonerLevel = PagesPerTonerCartridge;
            this.tonerReplaced = true;
            message(Utilities.PRINTER + "Replace the toner cartridge");
            message(Utilities.PRINTER + "Printer Status: " + this);
        }
        notifyAll();
    }

    @Override
    public synchronized void refillPaper() {

        boolean tried = false;
        this.paperRefilled = false;

        while(!(this.currentPaperLevel <= (Full_Paper_Tray - 50))) {

            if(tried) {
                message(Utilities.PRINTER + "No need of paper refilling");
                break;
            }
            try {
                message(Utilities.PRINTER + "Printer Status: " + this);
                message(Utilities.PRINTER + "Waiting to check.......");
                wait(5000);
            }
            catch (InterruptedException e) {
                message(Utilities.PRINTER + "Printer Status: " + this);
                e.printStackTrace();
            }
            tried = true;
        }

        if(this.currentPaperLevel <= (Full_Paper_Tray - 50)) {

            int freePaperSpace = Full_Paper_Tray - this.currentPaperLevel;
            int freePaperPacks = freePaperSpace / SheetsPerPack;

            if(freePaperPacks > 1){
                this.currentPaperLevel += (SheetsPerPack * freePaperPacks);
                paperPacks += freePaperPacks;
            }
            else if(freePaperPacks == 1){
                this.currentPaperLevel += SheetsPerPack;
                paperPacks += 1;
            }

            this.paperRefilled = true;
            message(Utilities.PRINTER + "Refilled the Papers");
            message(Utilities.PRINTER + "Printer Status: " + this);
        }
        notifyAll();
    }

    @Override
    public String toString() {
//        [ PrinterID: lp-CG.24, Paper Level: 35, Toner Level: 310, Documents Printed: 4 ]
        return "[ PrinterID: " + name  +
                ", Paper Level: " + currentPaperLevel +
                ", Toner Level: " + currentTonerLevel +
                ", Document Printed: " + numberOfDocumentsPrinted;
    }

    public int getPaperPacks() {
        return paperPacks;
    }

    private void message(String printerMessage) {

        System.out.println(printerMessage);
    }

}
