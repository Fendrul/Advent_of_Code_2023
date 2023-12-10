package be.strykers.jour10.pipes;

import java.util.List;

public interface Pipe {

    PipeType getPipeType();

    List<Pipe> getNeighbours();

    void addNeighbour(Pipe pipe);

    int getX();

    int getY();
}
