package be.strykers.jour10.pipes;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AnimalPipe implements Pipe {
    private final List<Pipe> neighbours;
    @Setter
    private PipeType pipeType;
    private int x;
    private int y;

    public AnimalPipe(PipeType pipeType, int x, int y) {
        this.pipeType = pipeType;
        this.neighbours = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    @Override
    public void addNeighbour(Pipe pipe) {
        neighbours.add(pipe);

        if (neighbours.size() > 4)
            throw new RuntimeException("AnimalPipe has more than 4 neighbours");
    }
}
