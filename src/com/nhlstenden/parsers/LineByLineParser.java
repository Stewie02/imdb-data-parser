package com.nhlstenden.parsers;

import com.nhlstenden.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This abstract class has the parse function which reads a file and for every line call the abstract parseLine method
 */
public abstract class LineByLineParser implements Parser {

    protected String fileName;

    public LineByLineParser(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Parses the whole file and calls for every line the parseLine function
     */
    public void parse() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(Constants.rawFilesFolder + fileName));
            String line = reader.readLine();
            while (line != null) {
                parseLine(line);
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the individual line
     * @param line The line to parse
     */
    protected abstract void parseLine(String line);

}
