package io.lerk.soultraps.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.lerk.soultraps.sys.Soultraps.DEBUG;

/**
 * Stop watch utility class.
 * <p>
 * Taken from: https://stackoverflow.com/a/44940559/1979736
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class StopWatch {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(StopWatch.class);

    /**
     * Log level to use.
     */
    private final LogLevel logLevel;

    /**
     * Start time.
     */
    private long startTime = 0;

    /**
     * Stop time.
     */
    private long stopTime = 0;

    /**
     * Constructor.
     *
     * @param logLevel the log level
     */
    public StopWatch(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * Starts the stopwatch.
     */
    public void start() {
        startTime = System.nanoTime();
    }

    /**
     * Stops the stopwatch and prints out the stats.
     *
     * @param name the name of the stopwatch
     */
    public void stop(String name) {
        stopTime = System.nanoTime(); //stop time first!

        if (DEBUG) {
            if (startTime == 0) {
                throw new IllegalStateException("StopWatch#start() must be called before stop() can be used!");
            }

            String message = "'" + name + "' took " +
                    getElapsedTime() + " nanoseconds (" +
                    getElapsedTimeMillis() + " milliseconds, " +
                    getElapsedTimeSecs() + " seconds)";

            if (logLevel.equals(LogLevel.INFO)) {
                log.info(message);
            } else {
                log.debug(message);
            }
        }
    }

    /**
     * Gets currently elapsed time in nanoseconds.
     *
     * @return currently elapsed time
     */
    public long getElapsedTime() {
        return stopTime - startTime;
    }

    /**
     * Gets currently elapsed time in milliseconds.
     *
     * @return currently elapsed time
     */
    public double getElapsedTimeMillis() {
        return ((double) getElapsedTime()) / 1000000;
    }

    /**
     * Gets currently elapsed time in seconds.
     *
     * @return currently elapsed time
     */
    public double getElapsedTimeSecs() {
        double elapsed;
        elapsed = getElapsedTimeMillis() / 1000;
        return elapsed;
    }

    /**
     * Log levels.
     */
    public enum LogLevel {
        /**
         * Info
         */
        INFO,

        /**
         * Debug
         */
        DEBUG;
    }
}
