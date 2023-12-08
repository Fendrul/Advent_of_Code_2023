package be.strykers.jour8;

import be.strykers.utils.Cyclerable.Cyclerator;
import be.strykers.utils.Logger.LoggerBuilder;
import be.strykers.utils.MathSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class NodesMapManager {
    private final Logger LOGGER = LoggerBuilder.getLogger();
    private final DirectionMap directionMap;
    private final Node startingNode;
    private final Set<Node> multipleStartingNodes;
    private final Set<Node> multipleTerminatingNodes;
    private final Node terminatingNode;

    public NodesMapManager(DirectionMap directionMap, Node startingNode, Node terminatingNode, Set<Node> multipleStartingNodes, Set<Node> multipleTerminatingNodes) {
        this.directionMap = directionMap;
        this.startingNode = startingNode;
        this.terminatingNode = terminatingNode;
        this.multipleStartingNodes = multipleStartingNodes;
        this.multipleTerminatingNodes = multipleTerminatingNodes;
    }

    public int getFirstPartSolution() {
        int count = 0;
        Cyclerator<Character> directionCyclerator = directionMap.getCyclerator();

        Node currentNode = startingNode;

        while (currentNode != terminatingNode) {
            Character direction = directionCyclerator.cycle();

            if (direction == 'L') {
                currentNode = currentNode.getLeft();
            } else if (direction == 'R') {
                currentNode = currentNode.getRight();
            }

            count++;
        }

        return count;
    }

    public long getSecondPartSolution() {
        List<Long> nodesDistance = new ArrayList<>();

        for (Node node : multipleStartingNodes) {
            nodesDistance.add(getPathToNextEndingNode(node));
        }

        return MathSolver.PPCM(nodesDistance.toArray(new Long[0]));
    }

    private Long getPathToNextEndingNode(Node startingNode) {
        long count = 0;
        Cyclerator<Character> directionCyclerator = directionMap.getCyclerator();
        long directionLength = directionMap.getSize();

        Node currentNode = startingNode;

        Character direction;
        while (count + 1 % directionLength != 0 && !multipleTerminatingNodes.contains(currentNode)) {
            direction = directionCyclerator.cycle();

            if (direction == 'L') {
                currentNode = currentNode.getLeft();
            } else if (direction == 'R') {
                currentNode = currentNode.getRight();
            }

            LOGGER.finest("Going to node " + currentNode.getNodeName() + " (" + currentNode.getLeft().getNodeName() + ", " + currentNode.getRight().getNodeName() + "), direction " + direction);

            count++;
        }

        return count;
    }
}
