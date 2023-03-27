package com.company;

public class TonerTechnician implements Runnable{

    private String name;
    private ThreadGroup threadGroup;
    private ServicePrinter printer;

    // Constructor to initialize Toner Technician
    public TonerTechnician(String name, ThreadGroup threadGroup, ServicePrinter printer) {
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

            message(Utilities.TONER_TECHNICIAN + " Attempt to replace the toner cartridge");
            attemptToRefill ++;
            printer.replaceTonerCartridge();
            message(Utilities.TONER_TECHNICIAN + " Printer Status: " + printer.toString());

            if(((LaserPrinter)printer).isTonerReplaced()) {
                refilledTimes ++;
            }

            try {
                Thread.sleep(Utilities.randomDuration());
            } catch (InterruptedException e) {
                message(Utilities.TONER_TECHNICIAN + " Printer Status: " + printer.toString());
                e.printStackTrace();
            }
        }
        System.out.println("Toner Technician Finished, attempted to refill toners " + attemptToRefill + " times and " +
                refilledTimes + " times refilled the toner");
    }

    private void message(String tonerTechnicianMessage) {

        System.out.println(tonerTechnicianMessage);
    }
}
