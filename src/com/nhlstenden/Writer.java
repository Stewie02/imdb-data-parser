package com.nhlstenden;

import com.nhlstenden.entities.Movie;
import com.nhlstenden.entities.containers.Container;
import com.nhlstenden.entities.interfaces.Entity;
import com.nhlstenden.entities.interfaces.Writable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class writes the objects to the CSV files
 */
public class Writer {

    public void writeWritableList(List<Writable> entities, String fileToWriteTo) {
        if (entities.size() == 0) {
            System.out.println("0 entities");
            return;
        }
        createFile(fileToWriteTo);
        try {
            FileWriter writer = new FileWriter(Constants.writeFolder + fileToWriteTo);
            writer.write(entities.get(0).getHeader() + '\n');

            for (Writable e : entities) {
                writer.write(e.toCSV() + '\n');
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
