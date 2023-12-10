package be.strykers.jour10.SearchTiles;

import be.strykers.jour10.pipes.Pipe;

import java.util.Set;

public interface SearchTiles {
    Set<Pipe> search(Pipe[][] pipeMap, Pipe startPipe, Set<Pipe> pipePerimeter);
}
