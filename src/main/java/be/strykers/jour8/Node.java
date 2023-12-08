package be.strykers.jour8;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    private final String nodeName;
    private Node left;
    private Node right;

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }
}
