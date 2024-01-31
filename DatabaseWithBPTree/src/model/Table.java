package model;

import dataStructure.BPTree;
import dataStructure.DictionaryPair;

import java.util.*;

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
        this.numberOfColumn = numberOfColumn + 1;
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

    // ----------------------- METHODS --------------------------

    // ------------------------ Insert ---------------------------
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

    // --------------------- Search --------------------------
    public void creatNewBPTree(String colName, DataType dataType) {
        if (dataType == DataType.Integer) {
            BPTree<Integer> bpTree = new BPTree<>(5, null, null, Integer::compareTo, new Comparator<DictionaryPair<Integer>>() {
                @Override
                public int compare(DictionaryPair<Integer> o1, DictionaryPair<Integer> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (Record record : this.records) {
                for (Cell cell : record.getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((Integer) cell.getValue(), record);
                }
            }

        } else if (dataType == DataType.Double) {

            BPTree<Double> bpTree = new BPTree<>(5, null, null, Double::compareTo, new Comparator<DictionaryPair<Double>>() {
                @Override
                public int compare(DictionaryPair<Double> o1, DictionaryPair<Double> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (Record record : this.records) {
                for (Cell cell : record.getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((Double) cell.getValue(), record);
                }
            }

        } else if (dataType == DataType.Character) {

            BPTree<Character> bpTree = new BPTree<>(5, null, null, Character::compareTo, new Comparator<DictionaryPair<Character>>() {
                @Override
                public int compare(DictionaryPair<Character> o1, DictionaryPair<Character> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (Record record : this.records) {
                for (Cell cell : record.getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((Character) cell.getValue(), record);
                }
            }

        } else if (dataType == DataType.Boolean) {

            BPTree<Boolean> bpTree = new BPTree<>(5, null, null, Boolean::compareTo, new Comparator<DictionaryPair<Boolean>>() {
                @Override
                public int compare(DictionaryPair<Boolean> o1, DictionaryPair<Boolean> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (Record record : this.records) {
                for (Cell cell : record.getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((Boolean) cell.getValue(), record);
                }
            }

        } else if (dataType == DataType.String) {
            BPTree<String> bpTree = new BPTree<>(5, null, null, String::compareTo, new Comparator<DictionaryPair<String>>() {
                @Override
                public int compare(DictionaryPair<String> o1, DictionaryPair<String> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (Record record : this.records) {
                for (Cell cell : record.getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((String) cell.getValue(), record);
                }
            }
        }
    }

    /*
    this method use for finding data type of col which you want to search by
     */
    private DataType findDataTypeOfCol(String colName) {
        for (Cell cell : records.get(0).getColumns()) {
            if (Objects.equals(cell.getColumnName(), colName))
                return cell.getDataType();
        }
        return null;
    }

    // use this method uses in searching by range & search in BP tree which name is colName
    public ArrayList<Record> searchByColName(String colName, String lowerBound, String upperBound) {
        /*
        if the BPTree based on col is existed in map we need to search in BPTree
        (we should consider type of data)
         */
        DataType colDataType = this.findDataTypeOfCol(colName);
        if (mapBPTrees.containsKey(colName)) {
            if (colDataType == DataType.Integer) {
                return mapBPTrees.get(colName).search(Integer.valueOf(lowerBound), Integer.valueOf(upperBound));
            }
            if (colDataType == DataType.Double) {
                return mapBPTrees.get(colName).search(Double.valueOf(lowerBound), Double.valueOf(upperBound));
            }
            if (colDataType == DataType.Character) {
                return mapBPTrees.get(colName).search(lowerBound, upperBound);
            }
            if (colDataType == DataType.String) {
                return mapBPTrees.get(colName).search(lowerBound, upperBound);
            }
            if (colDataType == DataType.Boolean) {
                return mapBPTrees.get(colName).search(Boolean.valueOf(lowerBound), Boolean.valueOf(upperBound));
            }
        }
        /*
        first create a new BPTree based on this new name and then call this func again
         */
        else {
            this.creatNewBPTree(colName, colDataType);
            return this.searchByColName(colName, lowerBound, upperBound);
        }
        return null;
    }

    // this method uses in searching by index
    public Record searchByIndex(int index) {
        return mapBPTrees.get("Index").search(index);
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
}
