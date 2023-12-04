package be.strykers.jour3;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import be.strykers.utils.LinkedChars;
import be.strykers.utils.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Solver {
    private final static Logger LOGGER = Logger.getLogger(Solver.class.getName());

    public static void main(String[] args) {

        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour3/input")) {

            List<Integer> numbers = new ArrayList<>();
            Character[][] map = initMap(fileReader);
            int width = map[0].length;

            numbers.add(searchThroughLine(map, width));

            while (fileReader.hasNextLine()) {
                advanceMap(map, fileReader.readLine());
                numbers.add(searchThroughLine(map, width));
            }

            System.out.println(numbers.stream().reduce(0, Integer::sum));

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private static Integer searchThroughLine(Character[][] map, int width) {
        List<Integer> numbers = new ArrayList<>();
        int sumRatio = 0;
        for (int posChar = 0; posChar < width; posChar++) {

            if (isAsterisk(map[1][posChar])) {
                List<Integer> foundNumbers = numberSearch(map, posChar);

                if (foundNumbers.size() == 2) {
                    sumRatio += foundNumbers.get(0) * foundNumbers.get(1);
                }
            }
        }

        return sumRatio;
    }

    private static List<Integer> numberSearch(Character[][] map, int posChar) {
        List<Integer> numbers = new ArrayList<>();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (posChar + x < 0 || posChar + x >= map[0].length) continue;

                if (isNumber(map[1 + y][posChar + x])) {
                    numbers.add(findAndReplaceNumber(map[1 + y], posChar + x));
                }
            }
        }

        return numbers;
    }

    private static int findAndReplaceNumber(Character[] characters, int x) {
        LinkedChars linkedChars = new LinkedChars();
        int initialX = x;

        linkedChars.add(characters[x]);

        characters[x] = '.';

        x--;
        while (x >= 0 && isNumber(characters[x])) {
            linkedChars.addFirst(characters[x]);
            characters[x] = '.';
            x--;
        }

        x = initialX;
        x++;
        while (x < characters.length && isNumber(characters[x])) {
            linkedChars.addLast(characters[x]);
            characters[x] = '.';
            x++;
        }

        return Integer.parseInt(linkedChars.toString());
    }

    private static Character[][] initMap(FileReader fileReader) {
        String firlstLine = fileReader.readLine();

        int width = firlstLine.length();

        Character[][] newMap = new Character[3][width];

        newMap[0] = Mapper.stringToCharArray(firlstLine);

        for (int i = 1; i < 3; i++) {
            newMap[i] = Mapper.stringToCharArray(fileReader.readLine());
        }


        return newMap;
    }

    private static void advanceMap(Character[][] map, String s) {
        map[0] = map[1];
        map[1] = map[2];
        map[2] = Mapper.stringToCharArray(s);
    }

    private static String repeatString(String s, int width) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < width; i++) {
            stringBuilder.append(s);
        }

        return stringBuilder.toString();
    }

    private static boolean isAsterisk(Character character) {
        int ascii = (int) character;

        return ascii == 42;
    }

    private static boolean isNumber(Character character) {
        int ascii = (int) character;

        return ascii >= 48 && ascii <= 57;
    }
}
