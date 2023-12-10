package be.strykers.jour10.SearchTiles;

import be.strykers.jour10.pipes.Pipe;

import java.util.HashSet;
import java.util.Set;

public class SearchDown implements SearchTiles {
    @Override
    public Set<Pipe> search(Pipe[][] pipeMap, Pipe startPipe, Set<Pipe> pipePerimeter) {
        Set<Pipe> containedTiles = new HashSet<>();
        int x = startPipe.getX();
        int y = startPipe.getY() + 1;

        Pipe currentPipe = pipeMap[y][x];

        while (!pipePerimeter.contains(currentPipe)) {
            containedTiles.add(currentPipe);
            y++;
            currentPipe = pipeMap[y][x];
        }

        return containedTiles;
    }
}
