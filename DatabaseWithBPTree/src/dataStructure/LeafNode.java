package dataStructure;

public class LeafNode<E> extends Node{
    private int maxNumPairs;
    private int minNumPairs;
    private int numPairs;
    private LeafNode<E> leftSibling;
    private LeafNode<E> rightSibling;
    private DictionaryPair<E>[] dictionary;

    public LeafNode(int m, DictionaryPair<E> dp) {
        this.maxNumPairs = m - 1;
        this.minNumPairs = (int)(Math.ceil(m/2) - 1);
        this.dictionary = new DictionaryPair[m];
        this.numPairs = 0;
       // this.insert(dp);
    }


    public LeafNode(int m, DictionaryPair<E>[] dps, InternalNode parent) {
        this.maxNumPairs = m - 1;
        this.minNumPairs = (int)(Math.ceil(m/2) - 1);
        this.dictionary = dps;
       // this.numPairs = BPlusTree.linearNullSearch(dps);
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
}
