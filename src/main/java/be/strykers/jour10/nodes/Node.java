package be.strykers.jour10.nodes;

import be.strykers.jour10.pipes.Pipe;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Node implements Comparable<Node> {
    private final Pipe pipe;
    private final Node parent;
    private int gScore;
    private int fScore;

    public Node(Pipe pipe, Node parent, int gScore, int fScore) {
        this.parent = parent;
        this.pipe = pipe;
        this.gScore = gScore;
        this.fScore = fScore;
    }

    @Override
    public int compareTo(@NotNull Node other) {
        return Integer.compare(this.fScore, other.fScore);
    }

    public List<Node> getPathList() {
        List<Node> pathList = new ArrayList<>();
        Node currentNode = this;

        while (currentNode != null) {
            pathList.add(currentNode);
            currentNode = currentNode.parent;
        }

        return pathList;
    }

    public boolean hasParent() {
        return parent != null;
    }
}
