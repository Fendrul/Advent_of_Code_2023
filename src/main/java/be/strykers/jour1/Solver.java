package be.strykers.jour1;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import be.strykers.utils.FileReader.Number;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {
    private static final Logger LOGGER = Logger.getLogger(Solver.class.getName());
    private final static String FILE_PATH = "src/main/java/be/strykers/jour1/puzzleInput";

    public static void main(String[] args) {
        int sum = 0;
        Pattern firstDigitFoundPattern = Pattern.compile("[0-9]");
        Pattern lastDigitFoundPattern = Pattern.compile("(\\d)(?!.*\\d)");

        try (FileReader reader = new BufferedReader(FILE_PATH)) {

            while (!reader.hasNextLine()) {
                String line = reader.readLine();

                List<Pair<Integer, Integer>> occurences = new ArrayList<>();

                Matcher firstDigitMatcher = firstDigitFoundPattern.matcher(line);
                Matcher lastDigitMatcher = lastDigitFoundPattern.matcher(line);

                Pair<Integer, Integer> firstNumberOccurence = findFirstOccurence(line);
                if (firstNumberOccurence.getValue1() != null) occurences.add(firstNumberOccurence);
                Pair<Integer, Integer> lastNumberOccurence = findLastOccurence(line);
                if (lastNumberOccurence.getValue1() != null) occurences.add(lastNumberOccurence);

                if (firstDigitMatcher.find()) {
                    occurences.add(new Pair<>(firstDigitMatcher.start(), Integer.parseInt(firstDigitMatcher.group())));
                }

                if (lastDigitMatcher.find()) {
                    occurences.add(new Pair<>(lastDigitMatcher.start(), Integer.parseInt(lastDigitMatcher.group())));
                }

                if (!occurences.isEmpty()) {
                    Pair<Integer, Integer> fistOccurence = Collections.min(occurences, Comparator.comparing(Pair::getValue0));
                    Pair<Integer, Integer> lastOccurence = Collections.max(occurences, Comparator.comparing(Pair::getValue0));

                    sum += (fistOccurence.getValue1() * 10) + lastOccurence.getValue1();
                }

            }

            System.out.println("\nLa somme totale est " + sum);

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }

    }

    public static Pair<Integer, Integer> findFirstOccurence(String line) {
        Pair<Integer, Integer> firstOccurence = new Pair<>(line.length(), null);

        for (Number value : Number.values()) {
            int index = line.indexOf(value.getName());
            if (index != -1 && index < firstOccurence.getValue0()) {
                firstOccurence = new Pair<>(index, value.getValue());
            }
        }

        return firstOccurence;
    }

    public static Pair<Integer, Integer> findLastOccurence(String line) {
        Pair<Integer, Integer> lastOccurence = new Pair<>(-1, null);

        for (Number value : Number.values()) {
            int index = line.lastIndexOf(value.getName());
            if (index != -1 && index > lastOccurence.getValue0()) {
                lastOccurence = new Pair<>(index, value.getValue());
            }
        }

        return lastOccurence;
    }
}