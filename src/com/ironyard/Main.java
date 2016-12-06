package com.ironyard;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import java.util.HashMap;

public class Main {

    public static HashMap<String, User> userHashMap = new HashMap<>();

    public static void main(String[] args) {

        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = userHashMap.get(name);

                    //does the user exist in the hashmap?
                    HashMap m = new HashMap<>();

                    //if it doesn't, go to login
                    if(user == null){
                        return new ModelAndView(m, "login.html");
                    }
                    //if it does, go to home
                    else {
                        return new ModelAndView(user, "home.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/login",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    User user = userHashMap.get(name);
                    //if it's not in the HM
                    if(user == null){
                        user = new User(name);
                        userHashMap.put(name, user);
                    }

                    Session session = request.session();
                    session.attribute("userName", name);

                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/create-game",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = userHashMap.get(name);

                    if(user == null){
                        throw new Exception("User is not logged in");
                    }

                    String gameName = request.queryParams("gameName");
                    String gameGenre = request.queryParams("gameGenre");
                    String gamePlatform = request.queryParams("gamePlatform");
                    int gameYear = Integer.parseInt(request.queryParams("gameYear"));

                    //using the constructor
                    Game game = new Game(gameName, gameGenre, gamePlatform, gameYear);

                    user.gamesArrayList.add(game);

                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/logout",
                ((request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                })
        );

    }//end main()
}//end class Main
