package be.strykers.jour5;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import be.strykers.utils.Logger.LoggerBuilder;
import org.javatuples.Pair;

import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {

    public static void main(String[] args) throws URISyntaxException {
        LoggerBuilder.setConfig("src/main/java/be/strykers/jour5/log.txt");
        Logger LOGGER = LoggerBuilder.getLogger(Solver.class);

        long startTime = System.currentTimeMillis();

        Pattern findNumberPattern = Pattern.compile("\\d+");

        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour5/input")) {
            String firstLine = fileReader.readLine();
            Matcher findNumberMatcher = findNumberPattern.matcher(firstLine);
            Set<Pair<Long, Long>> seedsRanged = new HashSet<>();
            List<Long> seeds = new ArrayList<>();

            firstLine = firstLine.substring(firstLine.indexOf(":") + 2);
            Arrays.stream(firstLine.split(" "))
                    .map(Long::parseLong)
                    .forEach(seeds::add);

            for (int i = 0; i < seeds.size() / 2; i++) {
                long firstValue = seeds.get(i * 2);
                long secondValue = firstValue + seeds.get(i * 2 + 1);

                seedsRanged.add(new Pair<>(firstValue, secondValue));
            }

            MapperManager mapperManager = new MapperManager(new HashSet<>(seeds), seedsRanged);
            MapperBuilder mapperBuilder = new MapperBuilder(mapperManager);

            while (fileReader.hasNextLine()) {
                String line = fileReader.readLine();
                findNumberMatcher = findNumberPattern.matcher(line);

                if (findNumberMatcher.find()) {
                    String[] lineParts = line.split(" ");

                    long source = Long.parseLong(lineParts[0]);
                    long destination = Long.parseLong(lineParts[1]);
                    long range = Long.parseLong(lineParts[2]);

                    mapperBuilder.addMapComponent(source, destination, range);

                } else {
                    if (!mapperBuilder.isEmpty()) {
                        mapperBuilder.build();
                    }
                }
            }
            System.out.println("la valeur la plus basse est : " + mapperManager.getLowestValue());
            System.out.println("La valeur la plus basse avec les portées de seed est : " + mapperManager.getLowestValueFromRange());

            System.out.println("\nTemps d'exécution : " + (System.currentTimeMillis() - startTime) + "ms");

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
