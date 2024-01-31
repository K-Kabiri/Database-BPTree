package dataStructure;

import java.util.Arrays;
import java.util.Comparator;

public class LeafNode<E> extends Node<E> {
    // -------------- field ----------------
    private int maxNumPairs;
    private int minNumPairs;
    private int numPairs;
    private LeafNode<E> leftSibling;
    private LeafNode<E> rightSibling;
    private DictionaryPair<E>[] dictionary;
    BPTree<E> bpTree = new BPTree<>();

    private Comparator<E> EComparator;
    private Comparator<DictionaryPair<E>> dictionaryPairComparator;

    // ------------ constructor --------------

    public LeafNode(int m, DictionaryPair<E> dp, Comparator<E> EComparator, Comparator<DictionaryPair<E>> comparator) {
        this.maxNumPairs = m - 1;
        this.minNumPairs = (int) (Math.ceil(m / 2.0) - 1);
        this.dictionary = new DictionaryPair[m];
        this.numPairs = 0;
        this.EComparator = EComparator;
        this.dictionaryPairComparator = comparator;
        this.insert(dp);
    }

    protected int compare(E o1, E o2) {
        return EComparator.compare(o1, o2);
    }

    protected int compare(DictionaryPair<E> d1, DictionaryPair<E> d2) {
        return EComparator.compare(d1.getKey(), d2.getKey());
    }

    public LeafNode(int m, DictionaryPair<E>[] dps, InternalNode<E> parent) {
        this.maxNumPairs = m - 1;
        this.minNumPairs = (int) (Math.ceil(m / 2.0) - 1);
        this.dictionary = dps;
        this.numPairs = bpTree.linearNullSearch(dps);
        this.setParent(parent);
    }

    // ----------- getter & setter -------------

    public int getMaxNumPairs() {
        return maxNumPairs;
    }

    public void setMaxNumPairs(int maxNumPairs) {
        this.maxNumPairs = maxNumPairs;
    }

    public int getMinNumPairs() {
        return minNumPairs;
    }

    public void setMinNumPairs(int minNumPairs) {
        this.minNumPairs = minNumPairs;
    }

    public int getNumPairs() {
        return numPairs;
    }

    public void setNumPairs(int numPairs) {
        this.numPairs = numPairs;
    }

    public LeafNode<E> getLeftSibling() {
        return leftSibling;
    }

    public void setLeftSibling(LeafNode<E> leftSibling) {
        this.leftSibling = leftSibling;
    }

    public LeafNode<E> getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(LeafNode<E> rightSibling) {
        this.rightSibling = rightSibling;
    }

    public DictionaryPair<E>[] getDictionary() {
        return dictionary;
    }

    public void setDictionary(DictionaryPair<E>[] dictionary) {
        this.dictionary = dictionary;
    }

    // -------------- Methods -----------------

    /*
      this method sets the dictionary pair at that index within the dictionary to null.
       ( Delete dictionary pair from leaf )
     */
    public void delete(int index) {
        this.dictionary[index] = null;
        numPairs--;
    }

    /*
      This method attempts to insert a dictionary pair within the dictionary of the LeafNode object.
      ( when numPairs == maxNumPairs returns false )
     */
    public boolean insert(DictionaryPair<E> dp) {
        if (this.isFull()) {
            return false;
        } else {
            this.dictionary[numPairs] = dp;
            numPairs++;
            Arrays.sort(this.dictionary, 0, numPairs, dictionaryPairComparator);
            return true;
        }
    }

    /*
      This method uses in delete operation (the numPairs within the LeafNode object is below minNumPairs)
     */
    public boolean isDeficient() {
        return numPairs < minNumPairs;
    }

    /*
      This method uses in insert operation (the numPairs within the LeafNode is equal to the maximum number of pairs)
     */
    public boolean isFull() {
        return numPairs == maxNumPairs;
    }

    /*
     This method uses in delete operation.
     (The LeafNode object can lend a dictionary pair if its numPairs is greater than the minimum number of pairs it can hold)
     */
    public boolean isLendable() {
        return numPairs > minNumPairs;
    }

    /*
      This method uses in delete operation.
     (when the number of pairs within the LeafNode object is equal to the minimum number of pairs it can hold)
     */
    public boolean isMergeable() {
        return numPairs == minNumPairs;
    }

}
