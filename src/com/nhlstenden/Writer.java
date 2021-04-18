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

    /**
     * This function writes a list of writable to a file
     * @param writableList List of writable
     * @param fileToWriteTo The name of the file to write to
     */
    public void writeWritableList(List<Writable> writableList, String fileToWriteTo) {
        // If the list is empty we aren't going to write anything, so just return immediately
        if (writableList.size() == 0) return;

        // Create the file
        createFile(fileToWriteTo);
        try {
            FileWriter writer = new FileWriter(Constants.writeFolder + fileToWriteTo);
            // Write the header
            writer.write(writableList.get(0).getHeader() + '\n');

            // Write for every writable the CSV string to the file
            for (Writable w : writableList) {
                writer.write(w.toCSV() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("There was a problem writing the file: " + fileToWriteTo);
            e.printStackTrace();
        }

    }

    /**
     * Creates a file with the name in the parameters
     * @param outputFile Name for the new file
     */
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
