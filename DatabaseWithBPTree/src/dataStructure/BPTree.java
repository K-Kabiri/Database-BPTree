package dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import model.Record;

public class BPTree<E extends Comparable<E>> {

    // -------------- field ----------------
    private int max;
    private InternalNode<E> root;
    private LeafNode<E> firstLeaf;
    private final Comparator<E> EComparator = new Comparator<E>() {
        @Override
        public int compare(E o1, E o2) {
            return o1.compareTo(o2);
        }
    };
    private final Comparator<DictionaryPair<E>> dictionaryPairComparator = new Comparator<DictionaryPair<E>>() {
        @Override
        public int compare(DictionaryPair<E> o1, DictionaryPair<E> o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            return o1.getKey().compareTo(o2.getKey());
        }
    };

    // ------------ constructor --------------
    public BPTree(int max, InternalNode<E> root, LeafNode<E> firstLeaf) {
        this.max = max;
        this.root = root;
        this.firstLeaf = firstLeaf;
    }

    // ----------- getter & setter -------------
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public InternalNode<E> getRoot() {
        return root;
    }

    public void setRoot(InternalNode<E> root) {
        this.root = root;
    }

    public LeafNode<E> getFirstLeaf() {
        return firstLeaf;
    }

    public void setFirstLeaf(LeafNode<E> firstLeaf) {
        this.firstLeaf = firstLeaf;
    }


    public Comparator<E> getComparator() {
        return EComparator;
    }

    // -------------- Methods ----------------

    private boolean isEmpty() {
        return firstLeaf == null;
    }

    /*
    This func finds leaf node.
    It starts from root and search for this key until find the key which greater than this key.
    If this node is leaf, just return it .
    other case it is internal and the next func should be called.
     */
    private LeafNode<E> findLeafNode(E key) {
        // initialize keys and index variable
        E[] keys = this.root.getKeys();
        int i;
        // find next node on path to appropriate leaf node
        for (i = 0; i < this.root.getDegree() - 1; i++) {
            if (EComparator.compare(key, keys[i]) < 0) {
                break;
            }
        }
		/* return node if it is a LeafNode object,
		otherwise repeat the search function a level down
		 */
        Node<E> child = this.root.getChildPointers()[i];
        if (child instanceof LeafNode) {
            return (LeafNode<E>) child;
        } else {
            return findLeafNode((InternalNode<E>) child, key);
        }
    }

    /*
    This func start searching with given internal node and given key.
     */
    private LeafNode<E> findLeafNode(InternalNode<E> node, E key) {
        // initialize keys and index variable
        E[] keys = node.getKeys();
        int i;
        // find next node on path to appropriate leaf node
        for (i = 0; i < node.getDegree() - 1; i++) {
            if (EComparator.compare(key, keys[i]) < 0) {
                break;
            }
        }
		/* return node if it is a LeafNode object,
		otherwise repeat the search function a level down
		 */
        Node<E> childNode = node.getChildPointers()[i];
        if (childNode instanceof LeafNode) {
            return (LeafNode<E>) childNode;
        } else {
            return findLeafNode((InternalNode<E>) node.getChildPointers()[i], key);
        }
    }

    /*
    This method find the midpoint of the max degree m of the B+ tree
     */
    private int getMidpoint() {
        return (int) Math.ceil((this.max + 1) / 2.0) - 1;
    }

    /*
    sorting method used upon lists of DictionaryPairs (that may contain interspersed null values)
     */
    private void sortDictionary(DictionaryPair<E>[] dictionary) {
        Arrays.sort(dictionary, dictionaryPairComparator);
    }

    /*
     This method splits a single dictionary into two dictionaries where all dictionaries are of equal length.
      ( used when splitting a node within the B+ tree )
     */
    private DictionaryPair<E>[] splitDictionary(LeafNode<E> ln, int split) {

        DictionaryPair<E>[] dictionary = ln.getDictionary();

        // initialize two dictionaries that each hold half of the original dictionary values
        @SuppressWarnings("unchecked")
        DictionaryPair<E>[] halfDict = new DictionaryPair[this.max];

        // copy half of the values into halfDict
        for (int i = split; i < dictionary.length; i++) {
            halfDict[i - split] = dictionary[i];
            ln.delete(i);
        }

        return halfDict;
    }

    /*
    When we insert new data into the B+ tree and internalNode overfull case , this method is called to split the overfull node
     ( This method calls the sub-methods of splitKeys() and splitChildPointers() )
    */
    private void splitInternalNode(InternalNode<E> internalNode) {
        // get parent
        InternalNode<E> parent = internalNode.getParent();

        // split keys and pointers internalNode half
        int midpoint = getMidpoint();
        E newParentKey = internalNode.getKeys()[midpoint];
        E[] halfKeys = splitKeys(internalNode.getKeys(), midpoint);
        Node<E>[] halfPointers = splitChildPointers(internalNode, midpoint);

        // change degree of internalNode
        internalNode.setDegree(linearNullSearch(internalNode.getChildPointers()));

        // create new sibling internal node and add half of keys and pointers
        InternalNode<E> sibling = new InternalNode<E>(this.max, halfKeys, halfPointers);
        for (Node<E> pointer : halfPointers) {
            if (pointer != null) {
                pointer.setParent(sibling);
            }
        }

        // make internal nodes siblings of one another
        sibling.setRightSibling(internalNode.getRightSibling());
        if (sibling.getRightSibling() != null) {
            sibling.getRightSibling().setLeftSibling(sibling);
        }
        internalNode.setRightSibling(sibling);
        sibling.setLeftSibling(internalNode);

        if (parent == null) {

            // create new root node and add midpoint key and pointers
            @SuppressWarnings("unchecked")
            E[] keys = (E[]) new Object[this.max];
            keys[0] = newParentKey;
            InternalNode<E> newRoot = new InternalNode<E>(this.max, keys);
            newRoot.appendChildPointer(internalNode);
            newRoot.appendChildPointer(sibling);
            this.root = newRoot;

            // add pointers from children to parent
            internalNode.setParent(newRoot);
            sibling.setParent(newRoot);

        } else {

            // add key to parent
            parent.getKeys()[parent.getDegree() - 1] = newParentKey;
            Arrays.sort(parent.getKeys(), 0, parent.getDegree(), EComparator);

            // set up pointer to new sibling
            int pointerIndex = parent.findIndexOfPointer(internalNode) + 1;
            parent.insertChildPointer(sibling, pointerIndex);
            sibling.setParent(parent);
        }
    }

    /*
     This method is removing half of the keys and returning them in a separate array.
     ( use in split Internal node )
     */
    private E[] splitKeys(E[] keys, int split) {
        @SuppressWarnings("unchecked")
        E[] halfKeys = (E[]) new Object[this.max];
        // remove split-indexed value from keys
        keys[split] = null;

        // copy half of the values into halfKeys while updating original keys
        for (int i = split + 1; i < keys.length; i++) {
            halfKeys[i - split - 1] = keys[i];
            keys[i] = null;
        }

        return halfKeys;
    }

    /*
     This method is removing all pointers within the childPointers after the specified split
      ( returns the removed pointers in a list of their own to be used when constructing a new sibling )
     */
    private Node<E>[] splitChildPointers(InternalNode<E> internalNode, int split) {

        Node<E>[] pointers = internalNode.getChildPointers();
        @SuppressWarnings("unchecked")
        Node<E>[] halfPointers = new Node[this.max + 1];

        // copy half of the values into halfPointers while updating original keys
        for (int i = split + 1; i < pointers.length; i++) {
            halfPointers[i - split - 1] = pointers[i];
            internalNode.removePointer(i);
        }

        return halfPointers;
    }

    /*
     linear search on a list of Node[] pointers
     ( returns the index of the first null entry found otherwise returns -1 )
     */
    public int linearNullSearch(Node<E>[] pointers) {
        for (int i = 0; i < pointers.length; i++) {
            if (pointers[i] == null) {
                return i;
            }
        }
        return -1;
    }

    //********************************************
    /*
       This method performs a standard binary search on a sorted
       DictionaryPair[].
       (returns the index of the dictionary pair with target key if found. Otherwise, returns a negative value)
     */
    private int binarySearch(DictionaryPair<E>[] dps, int numPairs, E key) {

        return Arrays.binarySearch(dps, 0, numPairs, new DictionaryPair<E>(key, new Record<>(key)), dictionaryPairComparator);
    }


    // ------------------------------- Insert ----------------------------------
    /*
    - This method inserts a dictionary pair into the B+ tree.
     */
    public void insert(E key, Record<E> value) {

        // root is null
        if (isEmpty()) {
            /* create leaf node as first node in B plus tree
              & set as first leaf node (can be used later for in-order leaf traversal)
             */
            this.firstLeaf = new LeafNode<E>(this.max, new DictionaryPair<E>(key, value));
        }

        // root is not null
        else {
            // find leaf node to insert into
            LeafNode<E> leafNode = (this.root == null) ? this.firstLeaf : findLeafNode(key);

            // when insert into leaf node fails (this can happen in the case node becomes overfull)
            if (!leafNode.insert(new DictionaryPair<E>(key, value))) {

                // sort all the dictionary pairs with the included pair to be inserted
                leafNode.getDictionary()[leafNode.getNumPairs()] = new DictionaryPair<E>(key, value);
                leafNode.setNumPairs(leafNode.getNumPairs() + 1);
                sortDictionary(leafNode.getDictionary());

                // split the sorted pairs into two halves
                int midpoint = getMidpoint();
                DictionaryPair<E>[] halfDict = splitDictionary(leafNode, midpoint);

                // goes here when there is 1 node in tree
                if (leafNode.getParent() == null) {
                    // create internal node to serve as parent ( use dictionary midpoint key )
                    @SuppressWarnings("unchecked")
                    E[] parent_keys = (E[]) new Object[this.max];
                    parent_keys[0] = halfDict[0].getKey();
                    InternalNode<E> parent = new InternalNode<E>(this.max, parent_keys);
                    leafNode.setParent(parent);
                    parent.appendChildPointer(leafNode);
                }

                // goes here when parent exists
                else {
                    // add new key to parent for proper indexing
                    E newParentKey = halfDict[0].getKey();
                    leafNode.getParent().getKeys()[leafNode.getParent().getDegree() - 1] = newParentKey;
                    Arrays.sort(leafNode.getParent().getKeys(), 0, leafNode.getParent().getDegree(), EComparator);
                }

                // create new LeafNode which holds the other half
                LeafNode<E> newLeafNode = new LeafNode<E>(this.max, halfDict, leafNode.getParent());

                // update child pointers of parent node
                int pointerIndex = leafNode.getParent().findIndexOfPointer(leafNode) + 1;
                leafNode.getParent().insertChildPointer(newLeafNode, pointerIndex);

                // make leaf nodes siblings of one another
                newLeafNode.setRightSibling(leafNode.getRightSibling());
                if (newLeafNode.getRightSibling() != null) {
                    newLeafNode.getRightSibling().setLeftSibling(newLeafNode);
                }
                leafNode.setRightSibling(newLeafNode);
                newLeafNode.setLeftSibling(leafNode);

                // if root is null , set the root of B+ tree to be the parent
                if (this.root == null) {
                    this.root = leafNode.getParent();
                }

                // If parent is overfull, repeat the process up the tree until no deficiencies are found
                else {
                    InternalNode<E> internalNode = leafNode.getParent();
                    while (internalNode != null) {
                        if (internalNode.isOverfull()) {
                            splitInternalNode(internalNode);
                        } else {
                            break;
                        }
                        internalNode = internalNode.getParent();
                    }
                }
            }
        }
    }
    // ------------------------------- Search ----------------------------------

    /*
       This method returns the value associated with the key within a dictionary pair that exists inside the B+ tree.
       ( if B+ tree is empty or the key doesn't exist in it return null )
     */

    public Record<E> search(E key) {
        // If B+ tree is completely empty return null
        if (isEmpty()) {
            return null;
        }
        // find leaf node that holds the dictionary key
        LeafNode<E> ln = (this.root == null) ? this.firstLeaf : findLeafNode(key);

        // perform binary search to find index of key within dictionary
        DictionaryPair<E>[] dps = ln.getDictionary();
        int index = binarySearch(dps, ln.getNumPairs(), key);

        // if index negative, the key doesn't exist in B+ tree
        if (index < 0) {
            return null;
        } else {
            return dps[index].getValue();
        }
    }

    /*
      This method traverses the list of the B+ tree and records
      all values whose associated keys are within the range specified by
      lowerBound and upperBound.
     */
    public ArrayList<Record<E>> search(E lowerBound, E upperBound) {

        // Instantiate Double array to hold values
        ArrayList<Record<E>> values = new ArrayList<Record<E>>();

        // Iterate through the list of leaves
        LeafNode<E> currNode = this.firstLeaf;

        while (currNode != null) {
            // Iterate through the dictionary of each node
            DictionaryPair<E>[] dps = currNode.getDictionary();

            for (DictionaryPair<E> dp : dps) {
				/* Stop searching the dictionary once a null value is encountered
				   as this the indicates the end of non-null values */
                if (dp == null) {
                    break;
                }
                // include value if its key fits within the provided range
                if (lowerBound.compareTo(dp.getKey()) <= 0 && dp.getKey().compareTo(upperBound) <= 0) {
                    values.add(dp.getValue());
                }
            }
			/*
			   update the current node to be the right sibling
			   (leaf traversal is from left to right)
			   */
            currNode = currNode.getRightSibling();
        }
        return values;
    }
}