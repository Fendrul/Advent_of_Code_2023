package be.strykers.jour3;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondSolver {

    private static final Logger LOGGER = Logger.getLogger(SecondSolver.class.getName());

    public static void main(String[] args) {
        Pattern findNumberPattern = Pattern.compile("\\d+");
        Pattern findSpecialCharPattern = Pattern.compile("[^a-zA-Z0-9.]");

        Map<Pair<Integer, Integer>, LinkedNumber> numbers = new HashMap<>();
        Map<Pair<Integer, Integer>, Character> specialCharacters = new HashMap<>();

        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour3/test")) {
            int posY = 0;

            while (fileReader.hasNextLine()) {
                String line = fileReader.readLine();

                Matcher findNumberMatcher = findNumberPattern.matcher(line);
                Matcher findSpecialCharMatcher = findSpecialCharPattern.matcher(line);

                // parsing des nombres
                while (findNumberMatcher.find()) {
                    int index = findNumberMatcher.start();

                    int[] digits = findNumberMatcher.group().chars().map(Character::getNumericValue).toArray();

                    LinkedNumber current = new LinkedNumber(digits[0], index, posY);

                    numbers.put(new Pair<>(index, posY), current);

                    for (int i = 1; i < digits.length; i++) {
                        current = current.addNext(digits[i]);
                        numbers.put(new Pair<>(current.getPosX(), current.getPosY()), current);
                    }
                }

                // parsing des caractères spéciaux
                while (findSpecialCharMatcher.find()) {
                    int index = findSpecialCharMatcher.start();

                    specialCharacters.put(new Pair<>(index, posY), line.charAt(index));
                }

                posY++;
            }

            numbers.forEach((key, value) -> System.out.println(key.getValue0() + " " + key.getValue1() + " " + value.getValue()));

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
