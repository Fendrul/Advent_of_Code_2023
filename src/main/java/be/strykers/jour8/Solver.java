package be.strykers.jour8;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import be.strykers.utils.Logger.LoggerBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Solver {

    public static void main(String[] args) {
        LoggerBuilder.setConfig(Solver.class, "src/main/java/be/strykers/jour8/log.txt");
        Logger LOGGER = LoggerBuilder.getLogger();

        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour8/test")) {
            String line = fileReader.readLine();

            Direction direction = new Direction(line);

            fileReader.readLine();
            List<String> nodes = new ArrayList<>();
            while (fileReader.hasNextLine()) {
                line = fileReader.readLine();
                nodes.add(line);
            }

            System.out.println(direction);
            System.out.println(nodes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
