package com.example.faizan.allconcepts.common.logger;

public class ConsoleLogger extends Logger {

    protected ConsoleLogger() {
    }

    protected ConsoleLogger(String name) {
        super(name);
    }

    public void init() {
        /*ConsoleLogger::init*/
    }

    public void close() {
       /*ConsoleLogger::close*/
    }

    protected void writeLog(String mesg) {
       /* ConsoleLogger::print message*/
    }

    public Logger newLogger(String name) {
        return new ConsoleLogger(name);
    }
}
