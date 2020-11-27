package com.urise.webapp;

import java.io.File;
import java.util.Objects;

public class MainAllFiles {

    public static void main(String[] args) {

        File file = new File("./src");
        listFilesForFolder(file);

        file = new File("./test");
        listFilesForFolder(file);
    }

    public static void listFilesForFolder(final File folder) {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles(), "IO error")) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

}
