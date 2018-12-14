package io.lerk.soultraps.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Taken from: https://stackoverflow.com/a/44940559/1979736
 */
public class StopWatch {

    private static final Logger log = LoggerFactory.getLogger(StopWatch.class);
    private final LogLevel logLevel;

    private long startTime = 0;
    private long stopTime = 0;

    public StopWatch(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop(String name) {
        stopTime = System.nanoTime(); //stop time first!

        if(startTime == 0) {
            throw new IllegalStateException("StopWatch#start() must be called before stop() can be used!");
        }

        String message = "'" + name + "' took " +
                getElapsedTime() + " nanoseconds (" +
                getElapsedTimeMillis() + " milliseconds, " +
                getElapsedTimeSecs() + " seconds)";

        if(logLevel.equals(LogLevel.INFO)) {
            log.info(message);
        } else {
            log.debug(message);
        }
    }

    public long getElapsedTime() {
        return stopTime - startTime;
    }

    public double getElapsedTimeMillis() {
        return ((double) getElapsedTime()) / 1000000;
    }

    public double getElapsedTimeSecs() {
        double elapsed;
        elapsed = getElapsedTimeMillis() / 1000;
        return elapsed;
    }

    public enum LogLevel {
        INFO, DEBUG;
    }
}
