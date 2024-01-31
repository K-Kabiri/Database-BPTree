package database;

import database.Menu;
import exception.*;
import database.model.Cell;
import database.model.DataType;
import database.model.Record;
import database.model.Table;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserPanel {

    // ---------------- field -----------------
    private Table currentTable = null;
    private final ArrayList<Table> tables;
    private final Menu menu = new Menu();
    private final Scanner sc = new Scanner(System.in);

    // ------------ constructor --------------
    public UserPanel() {
        this.tables = new ArrayList<>();
    }

    // ----------- getter & setter -------------
    public ArrayList<Table> getTables() {
        return tables;
    }

    public Table getCurrentTable() {
        return currentTable;
    }

    // ---------------- Methods ----------------
    public void mainMenu() {
        int command;
        while (true) {
            menu.printMainMenu();
            command = sc.nextInt();

            switch (command) {

                // create new table
                case 1 -> {
                    createTableManager();
                    tableMenuManager();
                }

                // select existed table
                case 2 -> {
                    findSelectedTable();
                    tableMenuManager();
                }

                case 3 -> {
                    System.exit(0);
                }
            }
        }
    }

    public void tableMenuManager() {
        menu.printTableMenu();
        int command;

        while (true) {
            command = sc.nextInt();
            switch (command) {
                // insert new record
                case 1 -> {
                    this.insertNewRecord();
                    menu.printTableMenu();
                }

                // search by row index
                case 2 -> {
                    this.searchByIndex();
                    menu.printTableMenu();
                }

                // search by specific key
                case 3 -> {
                    this.searchBySpecificKey();
                    menu.printTableMenu();
                }

                // search with input range
                case 4 -> {
                    this.searchWithInputRange();
                    menu.printTableMenu();
                }

                // delete by index of row
                case 5 -> {
                    this.deleteByIndex();
                    menu.printTableMenu();
                }

                // delete by other fields
                case 6 -> {
                    this.deleteByOtherField();
                    menu.printTableMenu();
                }
                // update a record
                case 7 -> {
                    this.updateRecord();
                    menu.printTableMenu();
                }
                // print table
                case 8 -> {
                    System.out.println("Fields : " + this.printAllColName());
                    System.out.println(printRecordArrayList(currentTable.getRecords()));
                    menu.printTableMenu();
                }
                case 9 -> {
                    currentTable=null;
                    this.mainMenu();
                }
                default -> {
                    menu.printTableMenu();
                }
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
     /*
       This method receive necessary information for creating a new table
     */
    public void createTableManager() {
        System.out.println("> Enter the title of your new table...");
        String tableTitle = sc.next();
        System.out.println("> Enter number of column...");
        int numCol = sc.nextInt();
        System.out.println("> Do you wanna create a new table with specific key type ? ( Yes or No )");
        String hasKey = sc.next();
        hasKey = hasKey.toUpperCase();
        if (Objects.equals(hasKey, "YES")) {
            System.out.println("> Enter the type key which you want...");
            String keyType = sc.next();
            keyType=keyType.toUpperCase();
            createNewTableWithSelectedKey(keyType, tableTitle, numCol, Boolean.TRUE);
        } else if (Objects.equals(hasKey, "NO")) {
            System.out.println("Ok! table with Integer index has been created for you...");
            createNewTableWithSelectedKey("INTEGER", tableTitle, numCol, Boolean.FALSE);
        }
    }

    /*
       create a new table with given title
       creat first row (name of columns) and add it to table
       consider this table as currentTable.
     */

    private void createNewTableWithSelectedKey(String keyType, String tableTitle, int numCol, boolean hasSpecificKey) {
        currentTable = new Table(tableTitle, numCol, hasSpecificKey, DataType.valueOf(keyType));
        Record firstRow = new Record(0);
        createFirstRow(firstRow, keyType, numCol);
        currentTable.getRecords().add(firstRow);
        tables.add(currentTable);

    }

     /*
        This method create first row(name of columns)
        if it has specific key we receive information of key column and then add other columns of the table
        otherwise we only add columns and consider 'index' column as key column.
     */

    private void createFirstRow(Record firstRow, String keyType, int numCol) {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(new Cell<>(DataType.INTEGER, 0, "Index"));

        // if the table has specific key, give it from user
        if (currentTable.isHasSpecificKey()) {
            System.out.println("> Enter Name of key column...");
            String nameOfKeyCol = sc.next();
            currentTable.setKeyColumnName(nameOfKeyCol);
            cells.add(new Cell<>(DataType.valueOf(keyType), null, nameOfKeyCol));
            for (int i = 0; i < numCol - 1; i++) {
                System.out.println("> Enter DataType and Name of column...");
                String dataType = sc.next();
                dataType=dataType.toUpperCase();
                String colName = sc.next();
                cells.add(new Cell<>(DataType.valueOf(dataType), null, colName));
            }
            firstRow.setColumns(cells);
            currentTable.creatBPTreeWithKey();
        }

        // if the table has not specific key, index of each row is the key
        else {
            currentTable.setKeyColumnName("Index");
            for (int i = 0; i < numCol; i++) {
                System.out.println("> Enter DataType and Name of column...");
                String dataType = sc.next();
                dataType=dataType.toUpperCase();
                String colName = sc.next();
                cells.add(new Cell<>(DataType.valueOf(dataType), null, colName));
            }
            firstRow.setColumns(cells);
        }
    }
    //------------------------------------------------------------------------------------------------------------------

      /*
        create each cell of new row
        create a new record and add all cells to it
        insert this record to table (call insert func)
      */

    private void insertNewRecord() {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(new Cell<>(DataType.INTEGER, currentTable.getRowIndex(), "INTEGER"));
        for (int i = 1; i < currentTable.getNumberOfColumn(); i++) {
            Cell firstRow = currentTable.getRecords().get(0).getColumns().get(i);

            if (Objects.equals(firstRow.getDataType(), DataType.valueOf("INTEGER"))) {
                System.out.println("> Enter the value of " + firstRow.getColumnName() + " ...");
                cells.add(new Cell<>(firstRow.getDataType(), sc.nextInt(), firstRow.getColumnName()));

            } else if (Objects.equals(firstRow.getDataType(), DataType.valueOf("CHARACTER"))) {
                System.out.println("> Enter the value of " + firstRow.getColumnName() + " ...");
                cells.add(new Cell<>(firstRow.getDataType(), sc.next(), firstRow.getColumnName()));

            } else if (Objects.equals(firstRow.getDataType(), DataType.valueOf("DOUBLE"))) {
                System.out.println("> Enter the value of " + firstRow.getColumnName() + " ...");
                cells.add(new Cell<>(firstRow.getDataType(), sc.nextDouble(), firstRow.getColumnName()));

            } else if (Objects.equals(firstRow.getDataType(), DataType.valueOf("BOOLEAN"))) {
                System.out.println("> Enter the value of " + firstRow.getColumnName() + " ...");
                cells.add(new Cell<>(firstRow.getDataType(), Boolean.valueOf(sc.next()), firstRow.getColumnName()));

            } else if (Objects.equals(firstRow.getDataType(), DataType.valueOf("STRING"))) {
                System.out.println("> Enter the value of " + firstRow.getColumnName() + " ...");
                cells.add(new Cell<>(firstRow.getDataType(), sc.next(), firstRow.getColumnName()));
            }
        }
        Record newRecord = new Record(currentTable.getRowIndex());
        newRecord.setColumns(cells);

        currentTable.insertRecord(newRecord);
    }

    //------------------------------------------------------------------------------------------------------------------

    /*
    This method uses in searching by index
     */
    private void searchByIndex() {
        System.out.println("> Enter the index ...");
        int index = sc.nextInt();
        if (index<currentTable.getRowIndex()) {
            Record record = currentTable.searchByIndex(index);
            System.out.println(record.toString());
        }
        else {
            try {
                throw new InvalidIndex();
            } catch (InvalidIndex e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    This method uses in searching by specific key
     */
    private void searchBySpecificKey() {
        System.out.println("> Which field do you wanna search in?");
        System.out.println("Fields : " + this.printAllColName());
        String colName = sc.next();
        System.out.println("> Enter the value you look for...");
        String value = sc.next();
        ArrayList<Record> record = currentTable.searchByColName(colName, value, value);
        System.out.println(printRecordArrayList(record));
    }

    private String printAllColName() {
        StringBuilder sb = new StringBuilder();
        for (Cell cell : currentTable.getRecords().get(0).getColumns()) {
            sb.append(cell.getColumnName() + "  ");
        }
        return sb.toString();
    }

    /*
    This method uses in searching with input range
     */
    private void searchWithInputRange() {
        System.out.println("> Which field do you wanna search in?");
        System.out.println("Fields : " + this.printAllColName());
        String colName = sc.next();
        System.out.println("> Enter the lower bound...");
        String lowerBound = sc.next();
        System.out.println("> Enter the upper bound...");
        String upperBound = sc.next();
        ArrayList<Record> record = currentTable.searchByColName(colName, lowerBound, upperBound);
        System.out.println(printRecordArrayList(record));
    }

    private String printRecordArrayList(ArrayList<Record> records) {
        StringBuilder sb = new StringBuilder();
        for (int i=1;i< records.size();i++) {
            sb.append("         ").append(records.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    //------------------------------------------------------------------------------------------------------------------

    public void deleteByIndex(){
        System.out.println("> Enter the Index of row you wanna delete...");
        int index = sc.nextInt();
        try {
            try {
                if(currentTable.deleteByIndex(index))
                    System.out.println("Deleting operation has been done successfully !");
            } catch (NonExistentKey e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (EmptyTree e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteByOtherField(){
        System.out.println("> Which field do you wanna deleted by?");
        System.out.println("Fields : " + this.printAllColName());
        String colName = sc.next();
        System.out.println("> Enter the value...");
        String value = sc.next();
        try {
            try {
                if(currentTable.deleteByField(colName , value ) )
                    System.out.println("Deleting operation has been done successfully!");
            } catch (NonExistentKey e) {
                System.out.println(e.getMessage());
            }
        } catch (EmptyTree e) {
            System.out.println(e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /*
    This method uses for finding the table which user want
     */
    public void findSelectedTable(){
        System.out.println("> Enter the name of table you wanna select...");
        String tableTitle = sc.next();
        for(Table table : tables){
            if(Objects.equals(table.getTableTitle(), tableTitle))
                currentTable = table;
        }
        if (currentTable==null) {
            System.out.println(new InvalidTableName().getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    private void updateRecord(){
        System.out.println("Fields : " + this.printAllColName());
        System.out.println(this.printRecordArrayList(currentTable.getRecords()));
        System.out.println("> Which index and field do you wanna update ?");
        int index=sc.nextInt();
        String colName = sc.next();
        System.out.println("> Enter the new value...");
        String newValue = sc.next();
        currentTable.updateRecordWithIndex(index,colName,newValue);
    }
}
