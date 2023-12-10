package be.strykers.jour10.PipeHandlers;

import be.strykers.jour10.pipes.Pipe;
import be.strykers.jour10.pipes.PipeType;

public class AnimalPipeHandler extends PipeHandler {
    @Override
    public void handle(Pipe pipe, int y, int x, Pipe[][] pipes) {
        // Checking down
        if (isInsideMap(y + 1, x, pipes)) {
            Pipe neighbour = pipes[y + 1][x];

            if (PipeType.findAnyFrom(neighbour.getPipeType(), PipeType.HORIZONTAL, PipeType.RIGHT_UP, PipeType.LEFT_UP)) {
                pipe.addNeighbour(neighbour);
            }
        }

        // Checking up
        if (isInsideMap(y - 1, x, pipes)) {
            Pipe neighbour = pipes[y - 1][x];

            if (PipeType.findAnyFrom(neighbour.getPipeType(), PipeType.HORIZONTAL, PipeType.RIGHT_DOWN, PipeType.LEFT_DOWN)) {
                pipe.addNeighbour(neighbour);
            }
        }

        // Checking right
        if (isInsideMap(y, x + 1, pipes)) {
            Pipe neighbour = pipes[y][x + 1];

            if (PipeType.findAnyFrom(neighbour.getPipeType(), PipeType.VERTICAL, PipeType.LEFT_DOWN, PipeType.LEFT_UP)) {
                pipe.addNeighbour(neighbour);
            }
        }

        // Checking left
        if (isInsideMap(y, x - 1, pipes)) {
            Pipe neighbour = pipes[y][x - 1];

            if (PipeType.findAnyFrom(neighbour.getPipeType(), PipeType.VERTICAL, PipeType.RIGHT_DOWN, PipeType.RIGHT_UP)) {
                pipe.addNeighbour(neighbour);
            }
        }
    }
}
