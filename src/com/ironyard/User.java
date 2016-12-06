package com.ironyard;

import java.util.ArrayList;

/**
 * Created by dlocke on 12/6/16.
 */
public class User {

    String name;
    ArrayList<Game> gamesArrayList = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }
}
