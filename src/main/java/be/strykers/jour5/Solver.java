package be.strykers.jour5;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import org.javatuples.Pair;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {
    private static final Logger LOGGER = Logger.getLogger(Solver.class.getName());

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Pattern findNumberPattern = Pattern.compile("\\d+");

        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour5/input")) {
            String firstLine = fileReader.readLine();
            Matcher findNumberMatcher = findNumberPattern.matcher(firstLine);
            Set<Pair<Long, Long>> seeds = new HashSet<>();
            List<Long> seedsTemp = new ArrayList<>();

            firstLine = firstLine.substring(firstLine.indexOf(":") + 2);
            Arrays.stream(firstLine.split(" "))
                    .map(Long::parseLong)
                    .forEach(seedsTemp::add);

            for (int i = 0; i < seedsTemp.size() / 2; i++) {
                long firstValue = seedsTemp.get(i * 2);
                long secondValue = firstValue + seedsTemp.get(i * 2 + 1);

                seeds.add(new Pair<>(firstValue, secondValue));
            }

            MapperManager mapperManager = new MapperManager(seeds);
            MapperBuilder mapperBuilder = new MapperBuilder(mapperManager);

            while (fileReader.hasNextLine()) {
                String line = fileReader.readLine();
                findNumberMatcher = findNumberPattern.matcher(line);

                if (findNumberMatcher.find()) {
                    String[] lineParts = line.split(" ");

                    long source = Long.parseLong(lineParts[0]);
                    long destination = Long.parseLong(lineParts[1]);
                    long range = Long.parseLong(lineParts[2]);

                    mapperBuilder.addMapEntity(source, destination, range);

                } else {
                    if (!mapperBuilder.isEmpty()) {
                        mapperBuilder.build();
                    }
                }
            }
            System.out.println("la valeur la plus basse est : " + mapperManager.getLowestValue());
            System.out.println("La valeur la plus basse avec les portées de seed est :" + mapperManager.getLowestValueFromRange());

            System.out.println("\nTemps d'exécution : " + (System.currentTimeMillis() - startTime) + "ms");

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
