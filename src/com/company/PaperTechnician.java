package com.company;

public class PaperTechnician implements Runnable{

    private String name;
    private ThreadGroup threadGroup;
    private ServicePrinter printer;

    // Constructor to initialize Paper Technician
    public PaperTechnician(String name, ThreadGroup threadGroup, ServicePrinter printer) {
        super();
        this.name = name;
        this.threadGroup = threadGroup;
        this.printer = printer;
    }

    @Override
    public void run() {

        int refilledTimes = 0;
        int attemptToRefill = 0;

        // Maximum times attempt to refill is 3
        for (int i = 0; i < 3; i++) {

            message(Utilities.PAPER_TECHNICIAN + "Attempt to refill the papers");
            attemptToRefill ++;
            printer.refillPaper();
            message(Utilities.PAPER_TECHNICIAN + " Printer Status: " + printer.toString());

            if(((LaserPrinter)printer).isPaperRefilled()) {
                refilledTimes ++;
            }

            try {
                Thread.sleep(Utilities.randomDuration());
            } catch (InterruptedException e) {
                message(Utilities.PAPER_TECHNICIAN + " Printer Status: " + printer.toString());
                e.printStackTrace();
            }
        }

        System.out.println("Paper Technician Finished, attempted to refill papers " + attemptToRefill + " times and " +
                refilledTimes + " times refilled the papers. Total paper packs refilled: " + ((LaserPrinter) printer).getPaperPacks());

    }

    private void message(String paperTechnicianMessage) {

        System.out.println(paperTechnicianMessage);
    }
}
