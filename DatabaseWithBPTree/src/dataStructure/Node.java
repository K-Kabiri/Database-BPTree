package dataStructure;

public class Node<E> {
    // -------------- field ---------------
    private InternalNode<E> parent;

    // ------------ constructor --------------

    public Node() {
    }

    // ----------- getter & setter -------------

    public InternalNode<E> getParent() {
        return parent;
    }

    public void setParent(InternalNode<E> parent) {
        this.parent = parent;
    }
}
