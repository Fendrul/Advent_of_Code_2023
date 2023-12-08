package be.strykers.jour8;

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
        LoggerBuilder.setConfig(Solver.class, "src/main/java/be/strykers/jour8/log.txt", Level.OFF);
        Logger LOGGER = LoggerBuilder.getLogger();

        long currentTimeMillis = System.currentTimeMillis();

        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour8/input")) {
            String line = fileReader.readLine();

            NodesMapManager nodesMapManager;
            DirectionMap direction = new DirectionMap(line);

            fileReader.readLine();
            List<String> nodesString = new ArrayList<>();
            while (fileReader.hasNextLine()) {
                line = fileReader.readLine();
                nodesString.add(line);
            }

            NodeMapBuilder nodeMapBuilder = new NodeMapBuilder();
            Pattern charactersPattern = Pattern.compile("[a-zA-Z0-9]+");
            for (String nodeLine : nodesString) {
                Matcher charatchersMatcher = charactersPattern.matcher(nodeLine);
                String nodeCore = null;
                String nodeLeft = null;
                String nodeRight = null;

                if (charatchersMatcher.find())
                    nodeCore = charatchersMatcher.group();

                if (charatchersMatcher.find())
                    nodeLeft = charatchersMatcher.group();

                if (charatchersMatcher.find())
                    nodeRight = charatchersMatcher.group();

                if (nodeCore == null || nodeLeft == null || nodeRight == null)
                    throw new RuntimeException("Parsing error, one of the node elements is null");

                nodeMapBuilder.add(nodeCore, nodeLeft, nodeRight);
            }

            nodesMapManager = nodeMapBuilder.build(direction, "AAA", "ZZZ", "A", "Z");

            System.out.println("Solution for first part : " + nodesMapManager.getFirstPartSolution());
            System.out.println("Solution for second part : " + nodesMapManager.getSecondPartSolution());

            System.out.println("Time elapsed : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
