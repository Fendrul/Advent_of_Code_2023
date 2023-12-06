package be.strykers.utils.Logger;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerBuilder {
    static Level level;
    private static FileHandler fileHandler;

    private LoggerBuilder() {
    }

    public static void setConfig(String logPath, Level level) {
        LoggerBuilder.level = level;
        setConfig(logPath);
    }

    public static void setConfig(String logPath) {
        try {
            fileHandler = new FileHandler(logPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileHandler.setFormatter(new CustomFormatter());
        fileHandler.setLevel(getLevel());
    }

    public static Logger getLogger(Class<?> clazz) {
        if (fileHandler == null) throw new RuntimeException("You must set the log file path first");

        Logger logger = Logger.getLogger(clazz.getName());

        logger.setLevel(getLevel());

        logger.addHandler(fileHandler);

        return logger;
    }

    private static Level getLevel() {
        return Objects.requireNonNullElse(level, Level.ALL);
    }

}
