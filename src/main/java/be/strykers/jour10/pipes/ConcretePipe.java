package be.strykers.jour10.pipes;

import be.strykers.utils.Logger.LoggerBuilder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Getter
public class ConcretePipe implements Pipe {
    private static final Logger LOGGER = LoggerBuilder.getLogger();

    private final PipeType pipeType;
    private final List<Pipe> neighbours;
    int x;
    int y;

    public ConcretePipe(PipeType pipeType, int x, int y) {
        this.pipeType = pipeType;
        this.neighbours = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    @Override
    public void addNeighbour(Pipe pipe) {
        neighbours.add(pipe);

        if (neighbours.size() > 2)
            throw new RuntimeException("Pipe has more than 2 neighbours");
    }
}
