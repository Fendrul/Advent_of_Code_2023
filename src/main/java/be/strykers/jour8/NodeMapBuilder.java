package be.strykers.jour8;

import org.javatuples.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NodeMapBuilder {
    Map<String, Pair<String, String>> stringNodesMap;

    public NodeMapBuilder() {
        stringNodesMap = new HashMap<>();
    }

    public void add(String nodeCore, String nodeLeft, String nodeRight) {
        stringNodesMap.put(nodeCore, new Pair<>(nodeLeft, nodeRight));
    }

    public NodesMapManager build(DirectionMap direction, String startingNode, String terminatingNode, String endingLetterForStartingNodes, String endingLetterForTerminatingNodes) {
        Map<String, Node> nodesMap = new HashMap<>();
        Set<Node> nodesSet = new HashSet<>();
        Set<Node> multipleStartingNodes = new HashSet<>();
        Set<Node> multipleTerminatingNodes = new HashSet<>();

        for (Map.Entry<String, Pair<String, String>> entry : stringNodesMap.entrySet()) {
            String nodeCore = entry.getKey();

            Node node = new Node(nodeCore);
            nodesMap.put(nodeCore, node);
        }

        for (Map.Entry<String, Pair<String, String>> entry : stringNodesMap.entrySet()) {
            String nodeCore = entry.getKey();
            Pair<String, String> nodePair = entry.getValue();

            Node node = nodesMap.get(nodeCore);
            if (nodePair.getValue0() != null)
                node.setLeft(nodesMap.get(nodePair.getValue0()));
            if (nodePair.getValue1() != null)
                node.setRight(nodesMap.get(nodePair.getValue1()));

            nodesSet.add(node);

            if (nodeCore.endsWith(endingLetterForStartingNodes))
                multipleStartingNodes.add(node);

            if (nodeCore.endsWith(endingLetterForTerminatingNodes))
                multipleTerminatingNodes.add(node);
        }

        Node startingNodeObject = nodesMap.get(startingNode);
        Node terminatingNodeObject = nodesMap.get(terminatingNode);

        return new NodesMapManager(direction, startingNodeObject, terminatingNodeObject, multipleStartingNodes, multipleTerminatingNodes);
    }
}
