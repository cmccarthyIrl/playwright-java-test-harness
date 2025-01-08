package com.cmccarthyirl.common;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogManager {

    private final Logger logger;

    public LogManager(Class<?> aClass) {
        try {
            logger = LoggerFactory.getLogger(aClass);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void info(String string) {
        ExtentTest currentTest = ExtentTests.getTest();
        if (currentTest != null) {
            ExtentTests.getTest().log(Status.INFO, string);
        }
        logger.info(string);
    }

    public void debug(String string) {
        ExtentTest currentTest = ExtentTests.getTest();
        if (currentTest != null) {
            ExtentTests.getTest().log(Status.INFO, string);
        }
        logger.debug(string);
    }

    public void warn(String string) {
        ExtentTest currentTest = ExtentTests.getTest();
        if (currentTest != null) {
            ExtentTests.getTest().log(Status.INFO, string);
        }
        logger.warn(string);
    }

    public void error(String string) {
        ExtentTest currentTest = ExtentTests.getTest();
        if (currentTest != null) {
            ExtentTests.getTest().log(Status.INFO, string);
        }
        logger.error(string);
    }

//    @Override
//    protected String getForegroundColorCode(ILoggingEvent iLoggingEvent) {
//        Level level = iLoggingEvent.getLevel();
//        return switch (level.toInt()) {
//            case Level.ERROR_INT -> ANSIConstants.BOLD + ANSIConstants.RED_FG;
//            case Level.WARN_INT -> ANSIConstants.YELLOW_FG;
//            case Level.INFO_INT -> ANSIConstants.GREEN_FG;
//            case Level.DEBUG_INT -> ANSIConstants.CYAN_FG;
//            default -> ANSIConstants.DEFAULT_FG;
//        };
//    }
}
