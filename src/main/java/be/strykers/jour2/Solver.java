package be.strykers.jour2;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import be.strykers.utils.enums.Colors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {
    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;
    private final static Logger LOGGER = Logger.getLogger(Solver.class.getName());

    public static void main(String[] args) {
        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour2/input")) {
            int sumSetPower = 0;

            Pattern findNumberPattern = Pattern.compile("\\d+");

            while (fileReader.hasNextLine()) {
                String line = fileReader.readLine();
                Matcher findNumberMatcher = findNumberPattern.matcher(line);
                Map<Colors, Integer> colorCount = initColorCount();
//                Map<Colors, Integer> colorsMax = initColorsMax();
//                boolean isValidGame = true;

                LOGGER.log(Level.FINE, line);

//                int gameID;
//                if (findNumberMatcher.find()) gameID = Integer.parseInt(findNumberMatcher.group());
//                else throw new Exception("No game ID found");

                line = line.replaceAll("^.*:", "");
                String[] lineParts = line.split(";|,");


                for (String linePart : lineParts) {
                    int playValue;

                    if (findNumberMatcher.find()) playValue = Integer.parseInt(findNumberMatcher.group());
                    else throw new Exception("No play value found");

                    for (Colors currentColor : Colors.values()) {
                        if (linePart.contains(currentColor.getName())) {
                            if (playValue > colorCount.get(currentColor)) {
                                colorCount.put(currentColor, playValue);
                            }
                        }
                    }
                }

                AtomicInteger setPower = new AtomicInteger(1);
                colorCount.forEach((key, value) -> {
                    setPower.updateAndGet(v -> v * value);
                });

                sumSetPower += setPower.get();
            }

            System.out.println(sumSetPower);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<Colors, Integer> initColorsMax() {
        Map<Colors, Integer> newColorsMax = new HashMap<>();

        newColorsMax.put(Colors.red, MAX_RED);
        newColorsMax.put(Colors.green, MAX_GREEN);
        newColorsMax.put(Colors.blue, MAX_BLUE);

        return newColorsMax;
    }

    public static Map<Colors, Integer> initColorCount() {
        Map<Colors, Integer> colorCount = new HashMap<>();
        for (Colors color : Colors.values()) {
            colorCount.put(color, 0);
        }
        return colorCount;
    }
}
