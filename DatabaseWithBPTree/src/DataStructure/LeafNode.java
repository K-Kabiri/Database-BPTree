package DataStructure;

public class LeafNode extends Node{
    int maxNumPairs;
    int minNumPairs;
    int numPairs;
    LeafNode leftSibling;
    LeafNode rightSibling;
    DictionaryPair[] dictionary;

    public LeafNode(int m, DictionaryPair dp) {
        this.maxNumPairs = m - 1;
        this.minNumPairs = (int)(Math.ceil(m/2) - 1);
        this.dictionary = new DictionaryPair[m];
        this.numPairs = 0;
       // this.insert(dp);
    }


    public LeafNode(int m, DictionaryPair[] dps, InternalNode parent) {
        this.maxNumPairs = m - 1;
        this.minNumPairs = (int)(Math.ceil(m/2) - 1);
        this.dictionary = dps;
       // this.numPairs = BPlusTree.linearNullSearch(dps);
        this.parent = parent;
    }
}
