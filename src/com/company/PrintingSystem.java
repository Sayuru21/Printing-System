package com.company;

public class PrintingSystem {

    public static void main(String[] args) {

        LaserPrinter printer = new LaserPrinter("lp-CG.24",50, 60, 00);

        ThreadGroup studentGroup = new ThreadGroup("Students");
        ThreadGroup technicianGroup = new ThreadGroup("Technicians");

        Student student1 = new Student("Praneeth", studentGroup, printer);
        Student student2 = new Student("Sadun", studentGroup, printer);
        Student student3 = new Student("Anuki", studentGroup, printer);
        Student student4 = new Student("Nethmi", studentGroup, printer);

        Thread studentThread1 = new Thread(studentGroup, student1, "Praneeth");
        Thread studentThread2 = new Thread(studentGroup, student2, "Sadun");
        Thread studentThread3 = new Thread(studentGroup, student3, "Anuki");
        Thread studentThread4 = new Thread(studentGroup, student4, "Nethmi");

        PaperTechnician paperTechnician = new PaperTechnician("Paper Technician", technicianGroup, printer);
        TonerTechnician tonerTechnician = new TonerTechnician("Toner Technician", technicianGroup, printer);

        Thread paperTechnicianThread = new Thread(technicianGroup, paperTechnician, "Paper Technician");
        Thread tonerTechnitianThread = new Thread(technicianGroup, tonerTechnician, "Toner Technician");

        studentThread1.start();
        studentThread2.start();
        studentThread3.start();
        studentThread4.start();

        paperTechnicianThread.start();
        tonerTechnitianThread.start();

        try {
            studentThread1.join();
            message(Utilities.PRINTING_SYSTEM + studentThread1.getName() + " completed the process");

            studentThread2.join();
            message(Utilities.PRINTING_SYSTEM + studentThread2.getName() + " completed the process");

            studentThread3.join();
            message(Utilities.PRINTING_SYSTEM + studentThread3.getName() + " completed the process");

            studentThread4.join();
            message(Utilities.PRINTING_SYSTEM + studentThread4.getName() + " completed the process");

            paperTechnicianThread.join();
            message(Utilities.PRINTING_SYSTEM + paperTechnicianThread.getName() + " completed the process");

            tonerTechnitianThread.join();
            message(Utilities.PRINTING_SYSTEM + tonerTechnitianThread.getName() + " completed the process");

            message(Utilities.PRINTING_SYSTEM + "Printing task completed. " + printer);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void message(String printerSystemMessage) {

        System.out.println(printerSystemMessage);
    }
}
