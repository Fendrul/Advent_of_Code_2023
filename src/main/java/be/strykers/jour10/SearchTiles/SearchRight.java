package be.strykers.jour10.SearchTiles;

import be.strykers.jour10.pipes.Pipe;

import java.util.HashSet;
import java.util.Set;

public class SearchRight implements SearchTiles {
    @Override
    public Set<Pipe> search(Pipe[][] pipeMap, Pipe startPipe, Set<Pipe> pipePerimeter) {
        Set<Pipe> containedTiles = new HashSet<>();
        int x = startPipe.getX() + 1;
        int y = startPipe.getY();

        Pipe currentPipe = pipeMap[y][x];

        while (!pipePerimeter.contains(currentPipe)) {
            containedTiles.add(currentPipe);
            x++;
            currentPipe = pipeMap[y][x];
        }

        return containedTiles;
    }
}
