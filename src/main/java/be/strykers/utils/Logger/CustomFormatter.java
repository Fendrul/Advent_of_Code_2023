package be.strykers.utils.Logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * The CustomFormatter class is a custom log record formatter that formats
 * the log message by adding a newline character at the end.
 * It extends the java.util.logging.Formatter class.
 */
public class CustomFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return String.format("%s\n",
                record.getMessage());

    }
}
