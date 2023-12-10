package be.strykers.jour10.PipeHandlers;

import be.strykers.jour10.pipes.Pipe;

public abstract class PipeHandler {

    public abstract void handle(Pipe pipe, int y, int x, Pipe[][] pipes);

    boolean isInsideMap(int i, int x, Pipe[][] pipes) {
        return i >= 0 && i < pipes.length && x >= 0 && x < pipes[i].length;
    }
}
