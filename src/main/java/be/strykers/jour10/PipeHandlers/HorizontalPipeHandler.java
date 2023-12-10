package be.strykers.jour10.PipeHandlers;

import be.strykers.jour10.pipes.Pipe;

public class HorizontalPipeHandler extends PipeHandler {
    @Override
    public void handle(Pipe pipe, int y, int x, Pipe[][] pipes) {
        if (isInsideMap(y + 1, x, pipes)) {
            pipe.addNeighbour(pipes[y + 1][x]);
        }
        if (isInsideMap(y - 1, x, pipes)) {
            pipe.addNeighbour(pipes[y - 1][x]);
        }
    }
}
