package be.strykers.jour10;

import be.strykers.jour10.pipes.PipeMapBuilder;
import be.strykers.jour10.pipes.PipeMapManager;
import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import be.strykers.utils.Logger.LoggerBuilder;
import org.javatuples.Pair;

import java.util.logging.Logger;

public class Solver {

    public static void main(String[] args) {
        LoggerBuilder.setConfig(Solver.class, "src/main/java/be/strykers/jour10/log.txt");
        Logger LOGGER = LoggerBuilder.getLogger();

        long startTime = System.currentTimeMillis();

        PipeMapManager pipeMapManager;

        try (FileReader filereader = new BufferedReader("src/main/java/be/strykers/jour10/test")) {
            PipeMapBuilder pipeMapBuilder = new PipeMapBuilder();

            while (filereader.hasNextLine()) {
                String line = filereader.readLine();
                pipeMapBuilder.addLine(line);
            }

            pipeMapManager = pipeMapBuilder.build();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Pair<Integer, Integer> solution = pipeMapManager.getSolutions();

        System.out.println("First solution : " + solution.getValue0());
        System.out.println("Second solution : " + solution.getValue1());

        System.out.println("\nTotal execution time : " + (System.currentTimeMillis() - startTime) + " ms");

    }
}
