package com.example.faizan.allconcepts.common.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileLogger extends Logger {

    static final String logFileName = "wallet.log";

    static File file = null;
    static FileOutputStream out = null;

    protected FileLogger(String name) {
        super(name);
    }

    protected Logger newLogger(String name) {
        return new FileLogger(name);
    }

    public void init() {
        try {
            file = new File(logFileName);
            if (file != null) {
                if (file.exists()) {
                    if (!file.delete()) {
                       /*check condition not deleted*/
                    }
                }
                if (!file.createNewFile()) {
                       /*check condition for no new file*/
                }
                out = new FileOutputStream(file);
            }
        } catch (IOException ioEx) {
            System.out.println("ERROR: Unable to open File I/O connection " + ioEx);
        }
    }

    public void initWithFile(String fileName) {
        /*file initialisation with file name*/
    }

    protected void writeLog(String mesg) {
        if (out != null) {
            try {
                out.write((mesg + "\n").getBytes());
                out.flush();
            } catch (IOException e) {
                System.out.println(" ERROR in writing the logs to file " + e);
            }

        } else {
            System.out.println("{NO Filelogger} " + mesg);
        }
    }

    public void close() {

        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            System.out.println("FileLogger, closing" + e);
        }
    }
}
