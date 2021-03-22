package com.nhlstenden;

import com.nhlstenden.entities.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class writes the objects to the CSV files
 */
public class Writer {

    /**
     * Writes all the movies to the CSV
     * @param map Map containing all the movies
     * @param outputFile The filename where everything will be stored
     */
    public void writeMovies(Map<String, Movie> map, String outputFile) {
        // First create the file

        createFile(outputFile);
        try {
            FileWriter writer = new FileWriter(outputFile);
            writer.write(Movie.getHeader() + '\n');

            // For every movie write the CSV
            for (Map.Entry<String, Movie> entry : map.entrySet()) {
                writer.write(entry.getValue().toCSV() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes all the genres and the genreMovie CSV file
     * @param map Map with all the genres
     * @param outputGenreFile Filename to output the genres
     * @param outputGenreMovieFile Filename to output the genreMovie
     */
    public void writeGenres(Map<String, Genre> map, String outputGenreFile, String outputGenreMovieFile) {
        // Create both files
        createFile(outputGenreFile);
        createFile(outputGenreMovieFile);

        try {
            FileWriter writerGenre = new FileWriter(outputGenreFile);
            FileWriter writerGenreMovie = new FileWriter(outputGenreMovieFile);

            // Write the headers
            writerGenre.write(Genre.getHeader() + '\n');
            writerGenreMovie.write("genreId,movieId");

            // Every Genre write it to the file
            for (Map.Entry<String, Genre> entry : map.entrySet()) {
                writerGenre.write(entry.getValue().toCSV() + '\n');

                // For every movie in here write the records to show the relation between the records
                for (Movie movie : entry.getValue().getMovies())
                    writerGenreMovie.write(entry.getValue().getId() + "," + movie.getId() + '\n');
            }
            writerGenre.close();
            writerGenreMovie.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes all the countries and the countryMovie CSV file
     * @param map Map with all the genres
     * @param outputCountryFile Filename to output the countries
     * @param outputCountryMovieFile Filename to output the countryMovie
     */
    public void writeCountries(Map<String, Country> map, String outputCountryFile, String outputCountryMovieFile) {
        // First create the files
        createFile(outputCountryFile);
        createFile(outputCountryMovieFile);

        try {
            FileWriter writerCountry = new FileWriter(outputCountryFile);
            FileWriter writerCountryMovie = new FileWriter(outputCountryMovieFile);

            // Write the headers!
            writerCountry.write(Country.getHeader() + '\n');
            writerCountryMovie.write("countryId,movieId\n");

            // For every country write the record to the file
            for (Map.Entry<String, Country> entry : map.entrySet()) {
                writerCountry.write(entry.getValue().toCSV() + '\n');

                 // For every movie write the record to show the relation between the records
                for (Movie movie : entry.getValue().getMovies())
                    writerCountryMovie.write(entry.getValue().getId() + ',' + movie.getId() + '\n');
            }

            // Close the writers
            writerCountry.close();
            writerCountryMovie.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write all the actors to the CSV file
     * @param actors The list with Actors
     * @param outputFileActor The filename to output the actors to
     * @param outputFileActorMovie The filename to output the actorMovie to
     */
    public void writeActors(List<Actor> actors, String outputFileActor, String outputFileActorMovie) {
        // First create the files
        createFile(outputFileActor);
        createFile(outputFileActorMovie);

        try {
            FileWriter writerActor = new FileWriter(outputFileActor);
            FileWriter writerActorMovie = new FileWriter(outputFileActorMovie);

            // Write the headers
            writerActor.write(Actor.getHeader() + '\n');
            writerActorMovie.write("actorId,movieId\n");

            // For every actors write the CSV
            for (Actor a : actors) {
                writerActor.write(a.toCSV() + '\n');

                // For every movie write the record to show the relation between movie and actor
                for (Movie movie : a.getMovies()) {
                    writerActorMovie.write(a.getId() + ',' + movie.getId() + '\n');
                }
            }
            // Close the files
            writerActor.close();
            writerActorMovie.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function creates a file with the given output name
     * @param outputFile name for the new file
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
