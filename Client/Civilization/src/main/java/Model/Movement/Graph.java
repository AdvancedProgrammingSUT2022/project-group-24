package Model.Movement;

import Model.TileRelated.Tile.Tile;
import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    @Expose
    private Set<Node> nodes = new HashSet<>();
    public Graph(Graph graph) {
        this.nodes = graph.nodes;
    }
    public Graph(){}
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }
    public Node getNode(Tile tile) {
        for (Node node: nodes) {
            if(node.getTile().equals(tile))return node;
        }
        return null;
    }
    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
