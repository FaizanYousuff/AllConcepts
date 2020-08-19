package com.example.faizan.allconcepts.designPatterns;

public class Main {

    public static void main(String[] args) {

        // Singleton Design pattern
        //  This pattern make sure only one instance of class is created

        // create default conmtructor as private and add getInstance 

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.println("######### SINGLETON design pattern #############");
        System.out.println("logger1 obj hashcode "+ logger1.hashCode());
        System.out.println("logger2 obj hashcode "+ logger2.hashCode());

        System.out.println("######################");


    }
}
