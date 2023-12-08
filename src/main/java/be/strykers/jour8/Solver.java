package be.strykers.jour8;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import be.strykers.utils.Logger.LoggerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {
    private static final String START_NODE_NAME = "AAA";
    private static final String END_NODE_NAME = "ZZZ";
    private static final String START_CHAR = "A";
    private static final String END_CHAR = "Z";
    private static final Pattern CHARACTERS_PATTERN = Pattern.compile("[a-zA-Z0-9]+");

    /**
     * The main method of the Solver class.
     * Sets up the logger configuration, reads the input file, and solves the puzzle.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        LoggerBuilder.setConfig(Solver.class, "src/main/java/be/strykers/jour8/log.txt", Level.OFF);
        long startTime = System.currentTimeMillis();

        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour8/input")) {

            solve(fileReader);

            System.out.println("Time elapsed : " + (System.currentTimeMillis() - startTime) + " ms");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Solves the puzzle based on the provided input.
     *
     * @param fileReader The FileReader object used to read the input file.
     * @throws IOException If an I/O error occurs.
     */
    private static void solve(FileReader fileReader) throws IOException {
        String line = fileReader.readLine();
        DirectionMap directionMap = new DirectionMap(line);
        fileReader.readLine(); // Skip empty line

        List<String> nodeLines = readAllLines(fileReader);

        NodesMapManager nodesMapManager = buildNodesMapManager(nodeLines, directionMap);

        System.out.println("Solution for first part : " + nodesMapManager.getFirstPartSolution());
        System.out.println("Solution for second part : " + nodesMapManager.getSecondPartSolution());
    }

    /**
     * Builds a NodesMapManager object based on a list of node lines and a DirectionMap object.
     *
     * @param nodeLines    The list of node lines.
     * @param directionMap The DirectionMap object used to build the NodesMapManager.
     * @return The NodesMapManager object.
     */
    private static NodesMapManager buildNodesMapManager(List<String> nodeLines, DirectionMap directionMap) {
        NodeMapBuilder nodeMapBuilder = new NodeMapBuilder();

        nodeLines.stream()
                .map(nodeLine -> parseNodeEntry(nodeLine, CHARACTERS_PATTERN))
                .filter(nodeParts -> nodeParts.stream().allMatch(Optional::isPresent))
                .forEach(nodeParts -> nodeMapBuilder.add(nodeParts.get(0).get(), nodeParts.get(1).get(), nodeParts.get(2).get()));

        return nodeMapBuilder.build(directionMap, START_NODE_NAME, END_NODE_NAME, START_CHAR, END_CHAR);
    }

    private static List<String> readAllLines(FileReader fileReader) throws IOException {
        List<String> lines = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            lines.add(fileReader.readLine());
        }

        return lines;
    }

    private static List<Optional<String>> parseNodeEntry(String nodeLine, Pattern pattern) {
        List<Optional<String>> nodeParts = new ArrayList<>();
        Matcher matcher = pattern.matcher(nodeLine);

        for (int i = 0; i < 3; i++) {
            nodeParts.add(findAndReturnGroup(matcher));
        }

        return nodeParts;
    }

    private static Optional<String> findAndReturnGroup(Matcher matcher) {
        return matcher.find() ? Optional.ofNullable(matcher.group()) : Optional.empty();
    }
}
