package com.nhlstenden;

public class Main {

    public static void main(String[] args) {

        System.out.println("We start parsing!");

        // Let's start the parser!!
        ImdbParser parser = new ImdbParser();
        parser.parse();
        parser.writeEverythingToTheFiles();

        System.out.println("We are finished!");

    }
}
