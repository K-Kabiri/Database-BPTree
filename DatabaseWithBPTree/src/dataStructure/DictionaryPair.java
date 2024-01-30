package dataStructure;

import model.Record;

public class DictionaryPair<E> {
    // -------------- field ----------------

    private E key;
    private Record value;
    // ------------ constructor --------------

    public DictionaryPair(E key, Record value) {
        this.key = key;
        this.value = value;
    }

    // ----------- getter & setter -------------
    public E getKey() {
        return key;
    }

    public void setKey(E key) {
        this.key = key;
    }

    public Record getValue() {
        return value;
    }

    public void setValue(Record value) {
        this.value = value;
    }
}