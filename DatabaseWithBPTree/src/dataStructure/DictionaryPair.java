package dataStructure;

import database.model.Record;

import java.util.Comparator;

public class DictionaryPair<E> implements Comparable<DictionaryPair<E>>{
    // -------------- field ----------------
    private Comparator<E> comparator;
    private E key;
    private Record value;
    // ------------ constructor --------------

    public DictionaryPair(E key, Record value , Comparator<E> comparator) {
        this.comparator = comparator;
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

    @Override
    public int compareTo(DictionaryPair<E> o) {
        return comparator.compare(o.getKey() , this.getKey());
    }
}