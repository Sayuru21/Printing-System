package com.company;

public class Student implements Runnable{

    private String name;
    private ThreadGroup threadGroup;
    private Printer printer;

    public Student(String name, ThreadGroup threadGroup, Printer printer) {
        super();
        this.name = name;
        this.threadGroup = threadGroup;
        this.printer = printer;
    }

    @Override
    public void run() {
        Document []documents = new Document[5];

        documents[0] = new Document(Thread.currentThread().getName(), "lecture_1", 28);
        documents[1] = new Document(Thread.currentThread().getName(), "lecture_2", 18);
        documents[2] = new Document(Thread.currentThread().getName(), "lecture_3", 17);
        documents[3] = new Document(Thread.currentThread().getName(), "lecture_4", 21);
        documents[4] = new Document(Thread.currentThread().getName(), "lecture_5", 16);

        int totalDocumentPages = 0;

        for (Document document: documents) {

            message(Utilities.STUDENT + "[ " + document.getUserID() + " ] requesting to print : " +
                    document.getDocumentName());
            printer.printDocument(document);
            totalDocumentPages += document.getNumberOfPages();

            try {
                Thread.sleep(Utilities.randomDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Joe Bloggs Finished Printing: 5 Documents, 95 pages
        System.out.println(Thread.currentThread().getName()+ " Finished Printing: " +
                documents.length + " Documents, " + totalDocumentPages + " pages");
    }

    private void message(String studentMessage) {

        System.out.println(studentMessage);
    }


}
