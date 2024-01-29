package dataStructure;

public class Node {
    private InternalNode parent;

    public Node() {
    }
    // ----------- getter & setter -------------

    public InternalNode getParent() {
        return parent;
    }

    public void setParent(InternalNode parent) {
        this.parent = parent;
    }
}
