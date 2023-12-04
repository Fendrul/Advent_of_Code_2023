package be.strykers.jour4;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;

import java.util.Arrays;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solver {
    private final static Logger LOGGER = Logger.getLogger(Solver.class.getName());

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

//        int sum = 0;
        CardManager cardManager = new CardManager();

        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour4/input")) {
            Pattern findNumberPattern = Pattern.compile("\\d+");

            while (fileReader.hasNextLine()) {

                String line = fileReader.readLine();
                Matcher findNumberMatcher = findNumberPattern.matcher(line);

                int gameID;
                if (findNumberMatcher.find()) {
                    gameID = Integer.parseInt(findNumberMatcher.group());
                } else {
                    throw new RuntimeException("Game ID not found");
                }

                line = line.substring(findNumberMatcher.end() + 1);

                String[] lineParts = line.split("\\|");

                Set<Integer> firstSet = Arrays.stream(lineParts[0].split(" "))
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());
                Set<Integer> secondSet = Arrays.stream(lineParts[1].split(" "))
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());

                Card newCard = new Card(gameID, firstSet, secondSet);
                cardManager.addCard(newCard);
            }

            System.out.println("Solution de la première partie : " + cardManager.getFirstPartScore());
            System.out.println("Solution de la seconde partie : " + cardManager.getSecondPartScore());
            System.out.println("\nTemps d'exécution : " + (System.currentTimeMillis() - startTime) + "ms");

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
