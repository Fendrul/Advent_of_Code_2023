package be.strykers.jour10.nodes;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Path {
    List<Node> nodes;

    public Path(Node node) {
        nodes = new ArrayList<>();
        nodes.add(node);
    }

    public Node getEndNode() {
        return nodes.get(nodes.size() - 1);
    }

    public void addNode(Node node) {
        nodes.add(node);
    }
}
