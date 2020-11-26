package com.urise.webapp;

import java.io.File;

public class MainAllFiles {

    public static void main(String[] args) {
        try {
            File file = new File("./src");
            listFilesForFolder(file);

            file = new File("./test");
            listFilesForFolder(file);
        } catch (Exception e) {

        }
    }

    public static void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

}
