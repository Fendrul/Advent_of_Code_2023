package be.strykers.utils.Logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The LoggerBuilder class is a utility class that helps configure and obtain a logger instance.
 * It provides methods to set the log configuration and get the logger instance.
 */
public class LoggerBuilder {
    private static Logger logger;

    private LoggerBuilder() {
    }

    /**
     * Sets the log configuration for the given class and log path.
     * The default log level is set to Level.ALL.
     * If you want to set a different log level, use the overloaded method setConfig(Class<?> clazz, String logPath, Level level).
     *
     * @param clazz   the class for which the log configuration is set
     * @param logPath the path of the log file
     */
    public static void setConfig(Class<?> clazz, String logPath) {
        setConfig(clazz, logPath, Level.ALL);
    }

    /**
     * Sets the log configuration for the given class and log path.
     *
     * @param clazz   the class for which the log configuration is set
     * @param logPath the path of the log file
     * @param level   the log level
     * @throws RuntimeException if an error occurs while setting the configuration
     */
    public static void setConfig(Class<?> clazz, String logPath, Level level) {

        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler(logPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileHandler.setFormatter(new CustomFormatter());
        fileHandler.setLevel(level);

        LoggerBuilder.logger = Logger.getLogger(clazz.getName());

        logger.setLevel(level);

        logger.addHandler(fileHandler);
    }

    /**
     * Returns the logger instance. If the logger instance is not set, a {@link RuntimeException} is thrown.
     *
     * @return the logger instance
     * @throws RuntimeException if the logger instance is not set
     */
    public static Logger getLogger() {
        if (logger == null) throw new RuntimeException("You must set the log config first.");

        return logger;
    }
}
