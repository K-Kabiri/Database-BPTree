package dataStructure;

public class InternalNode extends Node{
    private int maxDegree;
    private int minDegree;
    private int degree;
    private InternalNode leftSibling;
    private InternalNode rightSibling;
    private Integer[] keys;
    private Node[] childPointers;

    public InternalNode(int m, Integer[] keys) {
        this.maxDegree = m;
        this.minDegree = (int)Math.ceil(m/2.0);
        this.degree = 0;
        this.keys = keys;
        this.childPointers = new Node[this.maxDegree+1];
    }

    public InternalNode(int m, Integer[] keys, Node[] pointers) {
        this.maxDegree = m;
        this.minDegree = (int)Math.ceil(m/2.0);
        //this.degree = BPlusTree.linearNullSearch(pointers);
        this.keys = keys;
        this.childPointers = pointers;
    }

    // ----------- getter & setter -------------

    public int getMaxDegree() {
        return maxDegree;
    }

    public void setMaxDegree(int maxDegree) {
        this.maxDegree = maxDegree;
    }

    public int getMinDegree() {
        return minDegree;
    }

    public void setMinDegree(int minDegree) {
        this.minDegree = minDegree;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public InternalNode getLeftSibling() {
        return leftSibling;
    }

    public void setLeftSibling(InternalNode leftSibling) {
        this.leftSibling = leftSibling;
    }

    public InternalNode getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(InternalNode rightSibling) {
        this.rightSibling = rightSibling;
    }

    public Integer[] getKeys() {
        return keys;
    }

    public void setKeys(Integer[] keys) {
        this.keys = keys;
    }

    public Node[] getChildPointers() {
        return childPointers;
    }

    public void setChildPointers(Node[] childPointers) {
        this.childPointers = childPointers;
    }
}
