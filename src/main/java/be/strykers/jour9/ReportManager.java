package be.strykers.jour9;

import be.strykers.utils.Logger.LoggerBuilder;
import org.javatuples.Pair;

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

    public Pair<Long, Long> findParts() {
        Pair<Long, Long> sums = Pair.with(0L, 0L);
        int counter = 0;


        for (Report report : reports) {
            LOGGER.fine("Starting report " + counter++);
            Pair<Integer, Integer> sumsToAdd = report.getNextValuePredicted();

            sums = Pair.with(sums.getValue0() + sumsToAdd.getValue0(), sums.getValue1() + sumsToAdd.getValue1());
            LOGGER.fine("");
        }

        return sums;
    }
}
