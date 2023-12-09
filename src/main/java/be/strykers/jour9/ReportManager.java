package be.strykers.jour9;

import be.strykers.utils.Logger.LoggerBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class ReportManager {
    private static final Logger LOGGER = LoggerBuilder.getLogger();
    Set<Report> reports = new HashSet<>();

    public void addReport(Report report) {
        if (report == null) throw new IllegalArgumentException("report cannot be null");

        reports.add(report);
    }

    public long findFirstPart() {
        long sum = 0;
        int counter = 0;


        for (Report report : reports) {
            LOGGER.fine("Starting report " + counter++);
            sum += report.getNextValuePredicted();
        }

        return sum;
    }
}
