package com.example.faizan.allconcepts.designPatterns;

public class Logger {

    private static  Logger instance;

    public static Logger getInstance(){

        if(instance == null){
            instance  = new Logger();
        }
        return instance;
    }

    private Logger() {
    }
}
