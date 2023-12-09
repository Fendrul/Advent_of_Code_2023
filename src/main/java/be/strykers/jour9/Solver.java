package be.strykers.jour9;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import be.strykers.utils.Logger.LoggerBuilder;
import org.javatuples.Pair;

import java.util.Arrays;
import java.util.logging.Logger;

public class Solver {

    public static void main(String[] args) {
        LoggerBuilder.setConfig(Solver.class, "src/main/java/be/strykers/jour9/logs.txt");
        Logger LOGGER = LoggerBuilder.getLogger();

        long startTime = System.currentTimeMillis();

        ReportManager reportManager = new ReportManager();
        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour9/input")) {
            String line;

            while (fileReader.hasNextLine()) {
                line = fileReader.readLine();
                String[] lineparts = line.split(" ");

                reportManager.addReport(
                        Report.builder()
                                .numbers(
                                        Arrays.stream(lineparts).mapToInt(Integer::parseInt).toArray()
                                )
                                .build()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Pair<Long, Long> results = reportManager.findParts();

        System.out.println("Part 1: " + results.getValue1());
        System.out.println("Part 2: " + results.getValue0());

        System.out.println("Total execution time: " + (System.currentTimeMillis() - startTime) + "ms");

    }
}
