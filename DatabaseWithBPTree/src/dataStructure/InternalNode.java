package dataStructure;

public class InternalNode<E> extends Node<E> {
    // -------------- field ----------------
    private int maxDegree;
    private int minDegree;
    private int degree;
    private InternalNode<E> leftSibling;
    private InternalNode<E> rightSibling;
    private E[] keys;
    private Node<E>[] childPointers;

    // ------------ constructor --------------

    public InternalNode(int m, E[] keys) {
        this.maxDegree = m;
        this.minDegree = (int) Math.ceil(m / 2.0);
        this.degree = 0;
        this.keys = keys;
        this.childPointers = new Node[this.maxDegree + 1];
    }

    public InternalNode(int m, E[] keys, Node<E>[] pointers) {
        this.maxDegree = m;
        this.minDegree = (int) Math.ceil(m / 2.0);
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

    public InternalNode<E> getLeftSibling() {
        return leftSibling;
    }

    public void setLeftSibling(InternalNode<E> leftSibling) {
        this.leftSibling = leftSibling;
    }

    public InternalNode<E> getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(InternalNode<E> rightSibling) {
        this.rightSibling = rightSibling;
    }

    public E[] getKeys() {
        return keys;
    }

    public void setKeys(E[] keys) {
        this.keys = keys;
    }

    public Node<E>[] getChildPointers() {
        return childPointers;
    }

    public void setChildPointers(Node<E>[] childPointers) {
        this.childPointers = childPointers;
    }

    // -------------- Methods -----------------

    /*
    - This method appends pointer to the end of the childPointers.
    (The pointer can point to an InternalNode object or a LeafNode object)
     */
    public void appendChildPointer(Node<E> pointer) {
        this.childPointers[degree] = pointer;
        this.degree++;
    }

    /*
    - This method find index of given node pointer ( in case can not be find return -1 )
     */
    public int findIndexOfPointer(Node<E> pointer) {
        for (int i = 0; i < childPointers.length; i++) {
            if (childPointers[i] == pointer) {
                return i;
            }
        }
        return -1;
    }

    /*
    - This method inserts the pointer at the specified index within the childPointers.
     ( Some pointers may be shifted to the right of the index )
     */
    public void insertChildPointer(Node<E> pointer, int index) {
        for (int i = degree - 1; i >= index; i--) {
            childPointers[i + 1] = childPointers[i];
        }
        this.childPointers[index] = pointer;
        this.degree++;
    }

    /*
    - This method uses in delete operation ( when its current degree of children falls below the allowed minimum )
     */
    public boolean isDeficient() {
        return this.degree < this.minDegree;
    }

    /*
    - This method uses in delete operation ( when its current degree is above the specified minimum)
    ( if the InternalNode has not enough dictionary pairs in order to give one away )
     */
    public boolean isLendable() {
        return this.degree > this.minDegree;
    }

    /*
    - This method uses in delete operation .
    ( if the internalNode can be merged with if it has the minimum degree of children )
     */
    public boolean isMergeable() {
        return this.degree == this.minDegree;
    }

    /*
    - This method uses in insert operation .
    ( if the InternalNode is considered overfull )
     */
    public boolean isOverfull() {
        return this.degree == maxDegree + 1;
    }

    /*
    - This method sets keys[index] to null .
     */
    public void removeKey(int index) {
        this.keys[index] = null;
    }

    /*
    - This method sets childPointers[index] to null .
     */
    public void removePointer(int index) {
        this.childPointers[index] = null;
        this.degree--;
    }

    /*
     - This method removes pointer from the childPointers.
     */
    public void removePointer(Node<E> pointer) {
        for (int i = 0; i < childPointers.length; i++) {
            if (childPointers[i] == pointer) {
                this.childPointers[i] = null;
            }
        }
        this.degree--;
    }
}