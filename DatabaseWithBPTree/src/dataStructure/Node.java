package dataStructure;

public class Node<E> {
    private InternalNode<E> parent;

    public Node() {}

    // ----------- getter & setter -------------

    public InternalNode<E> getParent() {
        return parent;
    }

    public void setParent(InternalNode<E> parent) {
        this.parent = parent;
    }
}
