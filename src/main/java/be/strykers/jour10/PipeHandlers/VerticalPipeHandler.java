package be.strykers.jour10.PipeHandlers;

import be.strykers.jour10.pipes.Pipe;

public class VerticalPipeHandler extends PipeHandler {
    @Override
    public void handle(Pipe pipe, int y, int x, Pipe[][] pipes) {
        if (isInsideMap(y, x + 1, pipes)) {
            pipe.addNeighbour(pipes[y][x + 1]);
        }
        if (isInsideMap(y, x - 1, pipes)) {
            pipe.addNeighbour(pipes[y][x - 1]);
        }
    }
}
