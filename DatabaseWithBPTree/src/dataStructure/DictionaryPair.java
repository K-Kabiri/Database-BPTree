package dataStructure;

import model.Record;

public class DictionaryPair<E> {
    private E key;
    private Record<E> value;

    public DictionaryPair(E key, Record<E> value) {
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

    public Record<E> getValue() {
        return value;
    }

    public void setValue(Record<E> value) {
        this.value = value;
    }
}