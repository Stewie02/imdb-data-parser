package com.nhlstenden;

import com.nhlstenden.entities.interfaces.Writable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class writes the objects to the CSV files
 */
public class Writer {

    public void writeWritableList(List<Writable> writableList, String fileToWriteTo) {
        if (writableList.size() == 0) return;

        createFile(fileToWriteTo);
        try {
            FileWriter writer = new FileWriter(Constants.writeFolder + fileToWriteTo);
            writer.write(writableList.get(0).getHeader() + '\n');

            for (Writable w : writableList) {
                writer.write(w.toCSV() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("There was a problem writing the file: " + fileToWriteTo);
            e.printStackTrace();
        }

    }

    private void createFile(String outputFile) {
        try {
            File myObj = new File(Constants.writeFolder + outputFile);
            // Check if the file already exists. Otherwise create the file!
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
