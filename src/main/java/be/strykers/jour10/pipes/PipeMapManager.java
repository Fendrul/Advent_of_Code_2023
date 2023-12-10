package be.strykers.jour10.pipes;

import be.strykers.jour10.Direction;
import be.strykers.jour10.SearchTiles.*;
import be.strykers.jour10.nodes.Node;
import be.strykers.utils.Logger.LoggerBuilder;
import org.javatuples.Pair;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PipeMapManager {
    private static final Logger LOGGER = LoggerBuilder.getLogger();
    private final Pipe[][] pipeMap;
    private AnimalPipe animal;

    public PipeMapManager(AnimalPipe animal, Pipe[][] pipes) {
        this.animal = animal;
        this.pipeMap = pipes;
    }

    public Pair<Integer, Integer> getSolutions() {
        List<Node> loop = getLoops(animal);
        Set<Pipe> pipeSet = loop.stream().map(Node::getPipe).collect(Collectors.toSet());
        Pair<Integer, Integer> result;

        int tilesInsideLoop = getTilesInsideLoop(pipeSet);


        Node lastNode = loop.stream().max(Comparator.comparing(Node::getGScore)).orElseThrow(RuntimeException::new);

        result = Pair.with(lastNode.getGScore(), tilesInsideLoop);

        return result;
    }

    private int getTilesInsideLoop(Set<Pipe> pipePerimeter) {
        Set<Pipe> containedTiles = new HashSet<>();
        Set<Pipe> visitedPipes = new HashSet<>();
        Direction direction;
        Map<Direction, SearchTiles> searchTilesMap = Map.of(
                Direction.UP, new SearchUp(),
                Direction.DOWN, new SearchDown(),
                Direction.LEFT, new SearchLeft(),
                Direction.RIGHT, new SearchRight()
        );
        int count = 1;

        //search for the first pipe in the map
        Pipe currentPerimPipe = getFirstPipe(pipePerimeter);

        direction = Direction.RIGHT;
        while (!containedTiles.contains(currentPerimPipe)) {
            containedTiles.add(currentPerimPipe);
            SearchTiles searchTiles = null;
            int x = currentPerimPipe.getX();
            int y = currentPerimPipe.getY();

            LOGGER.fine("Searching for tile " + currentPerimPipe.getPipeType().getLetter());

            if (currentPerimPipe.getPipeType() == PipeType.ANIMAL && count > 1)
                changeAnimalType(direction, containedTiles);

            if (currentPerimPipe.getPipeType() == PipeType.HORIZONTAL || currentPerimPipe.getPipeType() == PipeType.VERTICAL) {
                searchTiles = searchTilesMap.get(direction);
            }

            if (searchTiles != null) {
                Set<Pipe> searchResult = searchTiles.search(pipeMap, currentPerimPipe, pipePerimeter);
                containedTiles.addAll(searchResult);
            }

            currentPerimPipe = switch (direction) {
                case UP -> pipeMap[y][x + 1];
                case DOWN -> pipeMap[y][x - 1];
                case LEFT -> pipeMap[y - 1][x];
                case RIGHT -> pipeMap[y + 1][x];
            };
            direction = rotateDirection(direction, currentPerimPipe.getPipeType());

            count++;
        }

        containedTiles.removeAll(pipePerimeter);

        return containedTiles.size();
    }

    private Direction rotateDirection(Direction direction, PipeType pipeType) {
        switch (pipeType) {
            case RIGHT_UP -> {
                if (direction == Direction.RIGHT) {
                    direction = Direction.UP;
                } else if (direction == Direction.DOWN) {
                    direction = Direction.LEFT;
                } else throw new RuntimeException("Wrong direction");
            }

            case RIGHT_DOWN -> {
                if (direction == Direction.DOWN) {
                    direction = Direction.RIGHT;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.UP;
                } else throw new RuntimeException("Wrong direction");
            }

            case LEFT_UP -> {
                if (direction == Direction.UP) {
                    direction = Direction.LEFT;
                } else if (direction == Direction.RIGHT) {
                    direction = Direction.DOWN;
                } else throw new RuntimeException("Wrong direction");
            }

            case LEFT_DOWN -> {
                if (direction == Direction.LEFT) {
                    direction = Direction.DOWN;
                } else if (direction == Direction.UP) {
                    direction = Direction.RIGHT;
                } else throw new RuntimeException("Wrong direction");
            }
        }

        return direction;
    }

    private Pipe getFirstPipe(Set<Pipe> pipePerimeter) {
        boolean found = false;
        int y = 0;
        Pipe currentPerimPipe = null;
        while (!found && y < pipeMap.length) {
            int x = 0;
            while (!found && x < pipeMap[0].length) {
                currentPerimPipe = pipeMap[y][x];

                if (pipePerimeter.contains(currentPerimPipe)) {
                    found = true;
                }
                x++;
            }
            y++;
        }

        return currentPerimPipe;
    }

    private void logMap() {
        logMap(-1, -1);
    }

    private void logMap(int x, int y) {
        StringBuilder sb = new StringBuilder();

        for (int yy = 0; yy < pipeMap.length; yy++) {
            for (int xx = 0; xx < pipeMap[0].length; xx++) {

                if (yy == y && xx == x) {
                    sb.append("X");
                } else {
                    sb.append(pipeMap[yy][xx].getPipeType().getLetter());
                }
            }

            sb.append("\n");
        }

        LOGGER.fine(sb.toString());
    }

    private boolean isInsideMap(int x, int y) {
        return y >= 0 && y < pipeMap.length && x >= 0 && x < pipeMap[0].length;
    }

    private List<Node> getLoops(Pipe start) {
        List<Pipe> visitedPipes = new ArrayList<>();
        List<Node> lastNodes = new ArrayList<>();

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(Node::getFScore));
        Node startNode = new Node(start, null, 0, 0);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            Pipe lastNodePipe = currentNode.getPipe();

            visitedPipes.add(lastNodePipe);

            for (Pipe neighbour : lastNodePipe.getNeighbours()) {
                if (!visitedPipes.contains(neighbour)) {
                    queue.add(new Node(neighbour, currentNode, currentNode.getGScore() + 1, currentNode.getGScore() + 1));
                }
            }

            lastNodes.add(currentNode);
        }

        List<Node> lastNodePath = lastNodes.get(lastNodes.size() - 1).getPathList();
        List<Node> secondLastNodePath = lastNodes.get(lastNodes.size() - 2).getPathList();

        //reverse the secondLastNodePath
        Collections.reverse(secondLastNodePath);

        List<Node> loop = combinePaths(lastNodePath, secondLastNodePath);
        loop.add(startNode);

        return loop;
    }

    private List<Node> combinePaths(List<Node> nodePath, List<Node> secondNodePath) {
        LinkedList<Node> loop = new LinkedList<>(nodePath);

        for (int i = 1; i < secondNodePath.size(); i++) {
            loop.add(secondNodePath.get(i));
        }

        return loop;
    }
}
