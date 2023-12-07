package be.strykers.utils.Logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerBuilder {
    private static Logger logger;

    private LoggerBuilder() {
    }

    public static void setConfig(Class<?> clazz, String logPath) {
        setConfig(clazz, logPath, Level.ALL);
    }

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

    public static Logger getLogger() {
        if (logger == null) throw new RuntimeException("You must set the log config first.");

        return logger;
    }
}
