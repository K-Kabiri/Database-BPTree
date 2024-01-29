package model;

import java.util.ArrayList;

public class Record<E> {
    private E key;
    private ArrayList<Cell> columns;

    public Record(E key) {
        this.key = key;
        this.columns = new ArrayList<>();
    }

    // ----------- getter & setter -------------

    public E getKey() {
        return key;
    }

    public void setKey(E key) {
        this.key = key;
    }

    public ArrayList<Cell> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Cell> columns) {
        this.columns = columns;
    }
}
