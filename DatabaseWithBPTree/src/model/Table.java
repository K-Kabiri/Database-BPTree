package model;

import dataStructure.BPTree;
import dataStructure.DictionaryPair;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Table {

    // ---------------- field -----------------
    private String tableTitle;

    private String keyColumnName;

    private DataType keyDataType;
    private int numberOfColumn;
    private int rowIndex = 1;
    private boolean hasSpecificKey = false;
    private ArrayList<Record> records;
    private Map<String, BPTree> mapBPTrees;

    // ------------ constructor --------------

    public Table(String tableTitle, int numberOfColumn, boolean hasSpecificKey, DataType keyDataType) {
        this.tableTitle = tableTitle;
        this.keyDataType = keyDataType;
        this.numberOfColumn = numberOfColumn+1;
        this.hasSpecificKey = hasSpecificKey;
        this.records = new ArrayList<>();
        this.mapBPTrees = new HashMap<>();
        this.createBPTreeWithIndex();
    }

    // ----------- getter & setter -------------

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }

    public Map<String, BPTree> getMapBPTrees() {
        return mapBPTrees;
    }

    public void setMapBPTrees(HashMap<String, BPTree> mapBPTrees) {
        this.mapBPTrees = mapBPTrees;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public String getTableTitle() {
        return tableTitle;
    }

    public String getKeyColumnName() {
        return keyColumnName;
    }

    public DataType getKeyDataType() {
        return keyDataType;
    }

    public void setKeyDataType(DataType keyDataType) {
        this.keyDataType = keyDataType;
    }

    public void setKeyColumnName(String keyColumnName) {
        this.keyColumnName = keyColumnName;
    }

    public int getNumberOfColumn() {
        return numberOfColumn;
    }

    public void setNumberOfColumn(int numberOfColumn) {
        this.numberOfColumn = numberOfColumn;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }

    public boolean isHasSpecificKey() {
        return hasSpecificKey;
    }

    public void setHasSpecificKey(boolean hasSpecificKey) {
        this.hasSpecificKey = hasSpecificKey;
    }

    // -------------- methods ----------------
    private void createBPTreeWithIndex() {
        BPTree<Integer> bpTreeByIndex = new BPTree<>(5, null, null, Integer::compareTo, new Comparator<DictionaryPair<Integer>>() {
            @Override
            public int compare(DictionaryPair<Integer> o1, DictionaryPair<Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        this.mapBPTrees.put("Index", bpTreeByIndex);
    }

    public void creatBPTreeWithKey() {
        if (keyDataType == DataType.Integer) {
            BPTree<Integer> bpTreeByIndex = new BPTree<>(5, null, null, Integer::compareTo, new Comparator<DictionaryPair<Integer>>() {
                @Override
                public int compare(DictionaryPair<Integer> o1, DictionaryPair<Integer> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTreeByIndex);
        } else if (keyDataType == DataType.Double) {

            BPTree<Double> bpTreeByIndex = new BPTree<>(5, null, null, Double::compareTo, new Comparator<DictionaryPair<Double>>() {
                @Override
                public int compare(DictionaryPair<Double> o1, DictionaryPair<Double> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTreeByIndex);
        } else if (keyDataType == DataType.Character) {

            BPTree<Character> bpTreeByIndex = new BPTree<>(5, null, null, Character::compareTo, new Comparator<DictionaryPair<Character>>() {
                @Override
                public int compare(DictionaryPair<Character> o1, DictionaryPair<Character> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTreeByIndex);
        } else if (keyDataType == DataType.Boolean) {

            BPTree<Boolean> bpTreeByIndex = new BPTree<>(5, null, null, Boolean::compareTo, new Comparator<DictionaryPair<Boolean>>() {
                @Override
                public int compare(DictionaryPair<Boolean> o1, DictionaryPair<Boolean> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTreeByIndex);
        } else if (keyDataType == DataType.String) {
            BPTree<String> bpTreeByIndex = new BPTree<>(5, null, null, String::compareTo, new Comparator<DictionaryPair<String>>() {
                @Override
                public int compare(DictionaryPair<String> o1, DictionaryPair<String> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTreeByIndex);
        }
    }


    public void insertRecord(Record record) {
        this.rowIndex++;
        if (hasSpecificKey)
            mapBPTrees.get(keyColumnName).insert(record.getColumns().get(1).getValue(), record);

        mapBPTrees.get("Index").insert(record.getColumns().get(0).getValue(), record);
        this.records.add(record);
    }
//
//    public Record deleteRecord(E key) {
//        return null;
//    }
//
//
//    public ArrayList<Record> searchRecord(E key) {
//        return null;
//    }


    public ArrayList<Record> searchRecord(String colName) {
        return null;
    }
}
