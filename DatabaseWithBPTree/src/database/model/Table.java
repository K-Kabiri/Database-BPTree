package database.model;

import dataStructure.BPTree;
import dataStructure.DictionaryPair;
import exception.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    /*
       This method create a B+ tree with Index column as a key column
       and hold it in BPTrees map
     */
    private void createBPTreeWithIndex() {
        BPTree<Integer> bpTreeByIndex = new BPTree<>(5, null, null, Integer::compareTo, new Comparator<DictionaryPair<Integer>>() {
            @Override
            public int compare(DictionaryPair<Integer> o1, DictionaryPair<Integer> o2) {
                if (o1 == null && o2 == null) { return 0; }
                if (o1 == null) { return 1; }
                if (o2 == null) { return -1; }
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        this.mapBPTrees.put("Index", bpTreeByIndex);
    }

    /*
    This method create a B+ tree with input key as a key column
    and hold it in BPTrees map
     */
    public void creatBPTreeWithKey() {
        if (keyDataType == DataType.INTEGER) {
            BPTree<Integer> bpTree = new BPTree<>(5, null, null, Integer::compareTo, new Comparator<DictionaryPair<Integer>>() {
                @Override
                public int compare(DictionaryPair<Integer> o1, DictionaryPair<Integer> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTree);
        } else if (keyDataType == DataType.DOUBLE) {

            BPTree<Double> bpTree = new BPTree<>(5, null, null, Double::compareTo, new Comparator<DictionaryPair<Double>>() {
                @Override
                public int compare(DictionaryPair<Double> o1, DictionaryPair<Double> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTree);
        } else if (keyDataType == DataType.CHARACTER) {

            BPTree<Character> bpTree = new BPTree<>(5, null, null, Character::compareTo, new Comparator<DictionaryPair<Character>>() {
                @Override
                public int compare(DictionaryPair<Character> o1, DictionaryPair<Character> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTree);
        } else if (keyDataType == DataType.BOOLEAN) {

            BPTree<Boolean> bpTree = new BPTree<>(5, null, null, Boolean::compareTo, new Comparator<DictionaryPair<Boolean>>() {
                @Override
                public int compare(DictionaryPair<Boolean> o1, DictionaryPair<Boolean> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTree);
        } else if (keyDataType == DataType.STRING) {
            BPTree<String> bpTree = new BPTree<>(5, null, null, String::compareTo, new Comparator<DictionaryPair<String>>() {
                @Override
                public int compare(DictionaryPair<String> o1, DictionaryPair<String> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTree);
        } else if (keyDataType == DataType.DATE) {
            BPTree<LocalDate> bpTree = new BPTree<>(5, null, null, LocalDate::compareTo, new Comparator<DictionaryPair<LocalDate>>() {
                @Override
                public int compare(DictionaryPair<LocalDate> o1, DictionaryPair<LocalDate> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(keyColumnName, bpTree);
        }
    }

     /*
       This method insert a new record as value and its column name as key to the BPTrees map.
       also add this new record to the list of records.
     */

    public void insertRecord(Record record) {
        this.rowIndex++;
        if (hasSpecificKey)
            mapBPTrees.get(keyColumnName).insert(record.getColumns().get(1).getValue(), record);

        mapBPTrees.get("Index").insert(record.getColumns().get(0).getValue(), record);
        this.records.add(record);
    }

    // --------------------- Search --------------------------

    /*
   This method create a B+ tree with input colName as a key column
   and put it in BPTrees map
    */
    public void creatNewBPTree(String colName, DataType dataType) {
        if (dataType == DataType.INTEGER) {
            BPTree<Integer> bpTree = new BPTree<>(5, null, null, Integer::compareTo, new Comparator<DictionaryPair<Integer>>() {
                @Override
                public int compare(DictionaryPair<Integer> o1, DictionaryPair<Integer> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (int i = 1; i < records.size(); i++) {
                for (Cell cell : records.get(i).getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((Integer) cell.getValue(), records.get(i));
                }
            }

        } else if (dataType == DataType.DOUBLE) {

            BPTree<Double> bpTree = new BPTree<>(5, null, null, Double::compareTo, new Comparator<DictionaryPair<Double>>() {
                @Override
                public int compare(DictionaryPair<Double> o1, DictionaryPair<Double> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (int i = 1; i < records.size(); i++) {
                for (Cell cell : records.get(i).getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((Double) cell.getValue(), records.get(i));
                }
            }

        } else if (dataType == DataType.CHARACTER) {

            BPTree<Character> bpTree = new BPTree<>(5, null, null, Character::compareTo, new Comparator<DictionaryPair<Character>>() {
                @Override
                public int compare(DictionaryPair<Character> o1, DictionaryPair<Character> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (int i = 1; i < records.size(); i++) {
                for (Cell cell : records.get(i).getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((Character) cell.getValue(), records.get(i));
                }
            }

        } else if (dataType == DataType.BOOLEAN) {

            BPTree<Boolean> bpTree = new BPTree<>(5, null, null, Boolean::compareTo, new Comparator<DictionaryPair<Boolean>>() {
                @Override
                public int compare(DictionaryPair<Boolean> o1, DictionaryPair<Boolean> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (int i = 1; i < records.size(); i++) {
                for (Cell cell : records.get(i).getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((Boolean) cell.getValue(), records.get(i));
                }
            }

        } else if (dataType == DataType.STRING) {
            BPTree<String> bpTree = new BPTree<>(5, null, null, String::compareTo, new Comparator<DictionaryPair<String>>() {
                @Override
                public int compare(DictionaryPair<String> o1, DictionaryPair<String> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (int i = 1; i < records.size(); i++) {
                for (Cell cell : records.get(i).getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((String) cell.getValue(), records.get(i));
                }
            }
        } else if (dataType == DataType.DATE) {
            BPTree<LocalDate> bpTree = new BPTree<>(5, null, null, LocalDate::compareTo, new Comparator<DictionaryPair<LocalDate>>() {
                @Override
                public int compare(DictionaryPair<LocalDate> o1, DictionaryPair<LocalDate> o2) {
                    if (o1 == null && o2 == null) { return 0; }
                    if (o1 == null) { return 1; }
                    if (o2 == null) { return -1; }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            this.mapBPTrees.put(colName, bpTree);
            // for insert all existed records in new BPTree
            for (int i = 1; i < records.size(); i++) {
                for (Cell cell : records.get(i).getColumns()) {
                    if (Objects.equals(cell.getColumnName(), colName))
                        bpTree.insert((LocalDate) cell.getValue(), records.get(i));
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

    /*
    use this method uses in searching by range & search in BP tree which name is colName
     */
    public ArrayList<Record> searchByColName(String colName, String lowerBound, String upperBound) {
        /*
        if the BPTree based on col is existed in map we need to search in BPTree
        (we should consider type of data)
         */
        DataType colDataType = this.findDataTypeOfCol(colName);
        if (mapBPTrees.containsKey(colName)) {
            if (colDataType == DataType.INTEGER) {
                return mapBPTrees.get(colName).search(Integer.valueOf(lowerBound), Integer.valueOf(upperBound));
            }
            if (colDataType == DataType.DOUBLE) {
                return mapBPTrees.get(colName).search(Double.valueOf(lowerBound), Double.valueOf(upperBound));
            }
            if (colDataType == DataType.CHARACTER) {
                return mapBPTrees.get(colName).search(lowerBound, upperBound);
            }
            if (colDataType == DataType.STRING) {
                return mapBPTrees.get(colName).search(lowerBound, upperBound);
            }
            if (colDataType == DataType.BOOLEAN) {
                return mapBPTrees.get(colName).search(Boolean.valueOf(lowerBound), Boolean.valueOf(upperBound));
            }
            if (colDataType == DataType.DATE){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return mapBPTrees.get(colName).search(LocalDate.parse(lowerBound,formatter), LocalDate.parse(upperBound,formatter));
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

    /*
     This method uses in searching by index
     */
    public Record searchByIndex(int index) {
        return mapBPTrees.get("Index").search(index);
    }

    // --------------------- Delete --------------------------
    public Boolean deleteByIndex(int index) throws EmptyTree, NonExistentKey {
        // at first search this value by index
        Record result = this.searchByIndex(index);
        // then delete this record from all BPTree's
        ArrayList<Record> searchResult = new ArrayList<>();
        searchResult.add(result);
        deleteRecordFromAllBPTree(searchResult);
        return true;
    }
    public Boolean deleteByField(String colName , String value) throws EmptyTree, NonExistentKey {
        // at first search this value in BPTree's
        ArrayList<Record> searchResult = this.searchByColName(colName , value , value );
        // then delete all records from all BPTree's
        deleteRecordFromAllBPTree(searchResult);
        return true;
    }
    private Boolean deleteRecordFromAllBPTree(ArrayList<Record> resultedRecord ) throws EmptyTree, NonExistentKey {
        /*
        we should delete each record from all BPTree
        we need key value for delete func input so find this first
         */
        for (Record record : resultedRecord ) {
            // update index of record in table and update the next cell (index)
            for(int i = record.getIndexInTable() ; i < records.size() ; i++){
                records.get(i).setIndexInTable(records.get(i).getIndexInTable() - 1);
                records.get(i).getColumns().get(0).setValue((Integer)records.get(i).getColumns().get(0).getValue() - 1);
            }
            // update row index
            rowIndex -- ;
            // remove from records arraylist
            records.remove(record);

            Set<String> colNames = mapBPTrees.keySet();
            Object value = null;
            for(int i = 0 ; i < mapBPTrees.size() ; i++){
                String colName = colNames.toArray()[i].toString();
                for(Cell cell : record.getColumns()) {
                    if(Objects.equals(cell.getColumnName(), colName)){
                        value = cell.getValue();
                        break;
                    }
                }
                /* for index BPTree , at first we need to create new BPTree with new index
                & remove previous BPTree of indexes
                 (cuz all key after this record had been changed when indexes was updated)
                 */
                if(Objects.equals(colName, "Index")){
                    mapBPTrees.remove(colName);
                    createNewIndexBPTree();
                }
                // in other case just delete the value from BPTree
                else {
                    mapBPTrees.get(colName).delete(value);
                }
            }

        }
        return true;
    }
    private void createNewIndexBPTree(){
        BPTree<Integer> bpTreeByIndex = new BPTree<>(5, null, null, Integer::compareTo, new Comparator<DictionaryPair<Integer>>() {
            @Override
            public int compare(DictionaryPair<Integer> o1, DictionaryPair<Integer> o2) {
                if (o1 == null && o2 == null) { return 0; }
                if (o1 == null) { return 1; }
                if (o2 == null) { return -1; }
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        this.mapBPTrees.put("Index", bpTreeByIndex);
        // for insert all existed records in new BPTree
        for (int i = 1; i < records.size(); i++) {
            for (Cell cell : records.get(i).getColumns()) {
                if (Objects.equals(cell.getColumnName(), "Index"))
                    bpTreeByIndex.insert((Integer) cell.getValue(), records.get(i));
            }
        }
    }

    // --------------------- Update --------------------------
    public void updateRecordWithIndex(int index,String colName,String newValue){
        Record record=this.searchByIndex(index);
        for (Cell cell:record.getColumns()){
            if (Objects.equals(cell.getColumnName(), colName)){
                cell.setValue(newValue);
            }
        }
    }
}
