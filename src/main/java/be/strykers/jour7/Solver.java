package be.strykers.jour7;

import be.strykers.utils.FileReader.BufferedReader;
import be.strykers.utils.FileReader.FileReader;
import be.strykers.utils.Logger.LoggerBuilder;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Solver {

    public static void main(String[] args) throws Exception {
        LoggerBuilder.setConfig(Solver.class, "src/main/java/be/strykers/jour7/logs.txt", Level.FINE);
        Logger LOGGER = LoggerBuilder.getLogger();

        long startTime = System.currentTimeMillis();

        HandsManager handsManager = new HandsManager();

        /**
         * PARSING
         */

        try (FileReader fileReader = new BufferedReader("src/main/java/be/strykers/jour7/input")) {
            String line;
            int bid;
            while (fileReader.hasNextLine()) {
                line = fileReader.readLine();

                String[] lineParts = line.split(" ");
                char[] cards = lineParts[0].toCharArray();
                bid = Integer.parseInt(lineParts[1]);

                LOGGER.finest("Cards and bid " + Arrays.toString(cards) + " " + bid);

                Hand hand = new Hand(cards, bid);
                handsManager.addHand(hand);
            }

            /**
             * SOLUTION
             */

            System.out.println("solution to first part : " + handsManager.getFirstPartSolution());

            System.out.println("Total execution time : " + (System.currentTimeMillis() - startTime) + "ms");

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
