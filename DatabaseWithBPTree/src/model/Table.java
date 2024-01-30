package model;

import dataStructure.BPTree;

import java.util.ArrayList;
import java.util.HashMap;

public class Table<E extends Comparable<E>> implements ITable<E> {

    // ---------------- field -----------------
    private String tableTitle;
    private static int rowIndex = 0;
    private ArrayList<Record<E>> records;
    private HashMap<String , BPTree<?>> bpTrees;

    // ------------ constructor --------------

    public Table(String tableTitle) {
        this.tableTitle = tableTitle;
        this.records = new ArrayList<>();
        this.bpTrees = new HashMap<>();
        this.createBPTreeWithIndex();
    }

    // ----------- getter & setter -------------

    public ArrayList<Record<E>> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Record<E>> records) {
        this.records = records;
    }

    public HashMap<String, BPTree<?>> getBpTrees() {
        return bpTrees;
    }

    public void setBpTrees(HashMap<String, BPTree<?>> bpTrees) {
        this.bpTrees = bpTrees;
    }

    public static int getRowIndex() {
        return rowIndex;
    }
    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }

    // -------------- methods ----------------
    private void createBPTreeWithIndex(){
        BPTree<Integer> bpTreeByIndex = new BPTree<>(5 ,  null , null);
        this.bpTrees.put("Index" , bpTreeByIndex);
    }

    @Override
    public E insertRecord(Record<E> record){
        return null;
    }

    @Override
     public Record<E> deleteRecord(E key){
        return null;
    }

    @Override
    public ArrayList<Record<E>> searchRecord(E key){
        return null;
    }

    @Override
    public ArrayList<Record<E>> searchRecord(String colName) {
        return null;
    }
}
