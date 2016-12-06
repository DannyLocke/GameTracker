package com.ironyard;

/**
 * Created by dlocke on 12/6/16.
 */
public class Game {

    String name;
    String genre;
    String platform;
    int releaseYear;

    //constructor
    public Game(String name, String genre, String platform, int releaseYear) {
        this.name = name;
        this.genre = genre;
        this.platform = platform;
        this.releaseYear = releaseYear;
    }
}
