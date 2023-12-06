package be.strykers.utils.Logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return String.format("%s : %s\n",
                record.getLoggerName(),
                record.getMessage());

    }
}
