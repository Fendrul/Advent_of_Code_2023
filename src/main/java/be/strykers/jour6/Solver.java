package be.strykers.jour6;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import be.strykers.utils.Logger.LoggerBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {

    public static void main(String[] args) {
        LoggerBuilder.setConfig("src/main/java/be/strykers/jour6/log.txt", Level.OFF);
        Logger LOGGER = LoggerBuilder.getLogger(Solver.class);

        long startTime = System.currentTimeMillis();

        RaceManager raceManager = new RaceManager();
        RaceManager raceManagerForUnifiedResult = new RaceManager();
        Pattern findNumberPattern = Pattern.compile("\\d+");

        List<Integer> times = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        long unifiedTime = 0;
        long unifiedDistance = 0;
        try (FileReader fileHandler = new BufferedReader("src/main/java/be/strykers/jour6/input")) {
            String line = fileHandler.readLine();

            //remove the line from the ":" and concatenate all numbers
            unifiedTime = Long.parseLong(line.substring(line.indexOf(":") + 1).replaceAll("\\s", ""));

            Matcher findNumberMatcher = findNumberPattern.matcher(line);

            while (findNumberMatcher.find()) {
                times.add(Integer.parseInt(findNumberMatcher.group()));
            }

            line = fileHandler.readLine();

            unifiedDistance = Long.parseLong(line.substring(line.indexOf(":") + 1).replaceAll("\\s", ""));

            findNumberMatcher = findNumberPattern.matcher(line);
            while (findNumberMatcher.find()) {
                distances.add(Integer.parseInt(findNumberMatcher.group()));
            }

            raceManagerForUnifiedResult.addRace(
                    new Race(unifiedTime, unifiedDistance)
            );

            LOGGER.fine("""
                                        
                    The unified time is %s
                    The unified distance is %s
                    """.formatted(unifiedTime, unifiedDistance));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int raceCount = 0; raceCount < times.size(); raceCount++) {
            Race race = new Race(
                    times.get(raceCount),
                    distances.get(raceCount)
            );

            raceManager.addRace(race);
        }

        System.out.println("First part : " + raceManager.getFirstPartResult());
        System.out.println("Second part : " + raceManagerForUnifiedResult.getSecondPartResult());

        System.out.println("Total execution time : " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
