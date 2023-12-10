package be.strykers.jour10.pipes;

import be.strykers.jour10.PipeHandlers.*;
import be.strykers.utils.Logger.LoggerBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PipeMapBuilder {
    Logger LOGGER = LoggerBuilder.getLogger();
    List<String> lines;

    public PipeMapBuilder() {
        lines = new ArrayList<>();
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public PipeMapManager build() {
        Pipe[][] pipes = new Pipe[lines.size()][lines.get(0).length()];

        AnimalPipe animal = null;

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);

            for (int x = 0; x < line.length(); x++) {
                PipeType pipeType = PipeType.getLetter(line.charAt(x));

                if (pipeType == PipeType.ANIMAL) {
                    animal = new AnimalPipe(pipeType, x, y);
                    pipes[y][x] = animal;
                } else {
                    pipes[y][x] = new ConcretePipe(pipeType, x, y);
                }
            }
        }

        Map<PipeType, PipeHandler> handlerByType = Map.of(
                PipeType.ANIMAL, new AnimalPipeHandler(),
                PipeType.HORIZONTAL, new HorizontalPipeHandler(),
                PipeType.VERTICAL, new VerticalPipeHandler(),
                PipeType.LEFT_DOWN, new LeftDownPipeHandler(),
                PipeType.LEFT_UP, new LeftUpPipeHandler(),
                PipeType.RIGHT_DOWN, new RightDownPipeHandler(),
                PipeType.RIGHT_UP, new RightUpPipeHandler()
        );

        for (int y = 0; y < pipes.length; y++) {
            for (int x = 0; x < pipes[y].length; x++) {
                Pipe pipe = pipes[y][x];

                if (pipe.getPipeType() == PipeType.GROUND) {
                    continue;
                }

                handlerByType.get(pipe.getPipeType()).handle(pipe, y, x, pipes);

            }
        }

        return new PipeMapManager(animal, pipes);
    }

    private boolean isInsideMap(int y, int x, Pipe[][] pipes) {
        return y >= 0 && y < pipes.length && x >= 0 && x < pipes[y].length;
    }
}
