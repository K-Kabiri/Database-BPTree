package model;

import java.util.ArrayList;

public interface ITable<E> {
    E insertRecord(Record<E> record);
    Record<E> deleteRecord(E key);
    ArrayList<Record<E>> searchRecord(E key);
    ArrayList<Record<E>> searchRecord(String colName);

}
