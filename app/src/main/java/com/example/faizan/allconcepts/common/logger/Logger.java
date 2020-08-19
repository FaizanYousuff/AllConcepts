package com.example.faizan.allconcepts.common.logger;

import java.util.Calendar;

public abstract class Logger {

    private boolean enable = true;

    public static final int ERROR = 0;
    public static final int WARN = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int DIAGNOSE = 4;
    public static final int TRACE = 5;
    private static int level = INFO;
    private final String BUILD_VERSION = "1.0";

    private static Logger logger = null;
    private String[] prefixes = {"SEVERE", "WARN", "DEBUG", "INFO", "DIAGNOSE", "TRACE"};
    private final static String MONTHS[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private String name = "DEFAULT";

    protected Logger() {
    }

    protected Logger(String name) {
        this.name = name;
    }

    public static void init(String logger) {
        if ("console".equalsIgnoreCase(logger)) {
            Logger.logger = new ConsoleLogger();
        } else if ("file".equalsIgnoreCase(logger)) {
            FileLogger fileLogger = new FileLogger(logger);
            fileLogger.init();
            Logger.logger = fileLogger;
        }
    }

    public static void init(String logger, String fileName) {
        if ("console".equalsIgnoreCase(logger)) {
            Logger.logger = new ConsoleLogger();
        } else if ("file".equalsIgnoreCase(logger)) {
            FileLogger fileLogger = new FileLogger(logger);
            fileLogger.initWithFile(fileName);
            Logger.logger = fileLogger;
        }
    }

    /**
     * Use this method to get the logger when it is required to preserve the
     * class and method names in the logs when the library is obfuscated.
     *
     * @param name Fully qualified name of the class
     * @return
     */
    public static Logger getNewLogger(String name) {
        if (logger == null) {
            init("Console");
        }
        return logger.newLogger(name);
    }

    /**
     * Use this method to get logger when the library would not be obfuscated.
     *
     * @param obj Object specific logger
     * @return
     */
    public static Logger getNewLogger(Object obj) {
        String name = obj.getClass().getName();
        return logger.newLogger(name);
    }

    /**
     * <ul>
     * <li>When log level is set to ERROR, only error conditions that are errors
     * are logged</li>
     * <li>
     * When log level is set to WARN, the following are logged
     * <ul>
     * <li>ERROR</li>
     * <li>WARN</li>
     * </ul>
     * </li>
     * <li>
     * When log level is set to DEBUG, the following are logged
     * <ul>
     * <li>ERROR</li>
     * <li>WARN</li>
     * <li>DEBUG</li>
     * </ul>
     * </li>
     * <li>When log level is set to INFO, everything is logged</li>
     * </ul>
     *
     * @param level Set Level for the entire logging system
     */
    public static void setLevel(int level) {
        Logger.level = level;
    }

    private void log(int level, String message) {
        boolean log = false;
        String logMesg;
        if (level == DIAGNOSE)
            log = true;
        if (level == TRACE)
            log = true;
        if (Logger.level == ERROR) {
            if (level == ERROR)
                log = true;
        } else if (Logger.level == WARN) {
            if (level == ERROR || level == WARN)
                log = true;
        } else if (Logger.level == DEBUG) {
            if (level == ERROR || level == WARN || level == DEBUG)
                log = true;
        } else if (Logger.level == INFO) {
            log = true;
        }
        logMesg = "[" + now() + "] " + "[" + Thread.currentThread().getName() + "] " + "[" + BUILD_VERSION + "] " + " [" + prefixes[level] + "] " + name + "."
                + message;
        /*if (log && enable) {
            writeLog(logMesg);
        }*/

        writeLog(logMesg);
    }

    public abstract void init();

    public static void closeLog() {
        logger.close();

    }

    public abstract void close();

    protected abstract void writeLog(String mesg);

    protected abstract Logger newLogger(String name);

    public void warn(String message) {
        log(WARN, message);
    }

    public void severe(String message) {
        log(ERROR, message);
    }

    public void info(String message) {
        log(INFO, message);
    }

    public void debug(String message) {
        log(DEBUG, message);
    }

    public void diagnose(String message) {
        log(DIAGNOSE, message);
    }

    public void trace(String message) {
        log(TRACE, message);
    }

    protected String now() {

        StringBuilder f = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        String mon = MONTHS[cal.get(Calendar.MONTH)];
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        int hr = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);

        f.append(mon);
        f.append("-");
        f.append(day);
        f.append("-");
        f.append(year);
        f.append(" ");
        f.append(hr);
        f.append(":");
        f.append(min);
        f.append(":");
        f.append(sec);
        return f.toString();
    }
}
