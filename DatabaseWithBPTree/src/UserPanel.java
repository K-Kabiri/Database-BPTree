import model.Cell;
import model.DataType;
import model.Record;
import model.Table;

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

    public void tableMenuManager() {
        menu.printTableMenu();
        int command;
        do {
            command = sc.nextInt();
            switch (command) {
                // insert new record
                case 1 -> {
                    insertNewRecord();

                }

                // search by row index
                case 2 -> {
                    // code

                }

                // search by specific key
                case 3 -> {
                    // code

                }

                // search with input range
                case 4 -> {
                    // code

                }

                // delete by index of row
                case 5 -> {
                    // code

                }

                // delete by other fields
                case 6 -> {
                    // code

                }
            }
            menu.printTableMenu();
        } while (command == 7);
    }

    //------------------------------------------------------------------------------------------------------------------
    public void createTableManager() {
        System.out.println("> Enter the title of your new table...");
        String tableTitle = sc.next();
        System.out.println("> Enter number of column...");
        int numCol = sc.nextInt();
        System.out.println("> Do you wanna create a new table with specific key type ? ( Yes or No )");
        String hasKey = sc.next();
        if (Objects.equals(hasKey, "Yes")) {
            System.out.println("> Enter the type key which you want...");
            String keyType = sc.next();
            createNewTableWithSelectedKey(keyType, tableTitle, numCol, Boolean.TRUE);
        } else if (Objects.equals(hasKey, "No")) {
            System.out.println("Ok! table with Integer index has been created for you...");
            createNewTableWithSelectedKey("Integer", tableTitle, numCol, Boolean.FALSE);
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

    private void createFirstRow(Record firstRow, String keyType, int numCol) {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(new Cell<>(DataType.Integer, 0, "Index"));

        // if the table has specific key, give it from user
        if (currentTable.isHasSpecificKey()) {
            System.out.println("> Enter Name of key column...");
            String nameOfKeyCol = sc.next();
            currentTable.setKeyColumnName(nameOfKeyCol);
            System.out.println(currentTable.getKeyColumnName());
            cells.add(new Cell<>(DataType.valueOf(keyType), null, nameOfKeyCol));
            for (int i = 0; i < numCol - 1; i++) {
                System.out.println("> Enter DataType and Name of column...");
                String dataType = sc.next();
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
        cells.add(new Cell<>(DataType.Integer, currentTable.getRowIndex(), "Index"));
        for (int i = 1; i < currentTable.getNumberOfColumn(); i++) {
            Cell firstRow = currentTable.getRecords().get(0).getColumns().get(i);

            if (Objects.equals(firstRow.getDataType(), DataType.valueOf("Integer"))) {
                System.out.println("> Enter the value...");
                cells.add(new Cell<>(firstRow.getDataType(), sc.nextInt(), firstRow.getColumnName()));

            } else if (Objects.equals(firstRow.getDataType(), DataType.valueOf("Character"))) {
                System.out.println("> Enter the value...");
                cells.add(new Cell<>(firstRow.getDataType(), sc.next(), firstRow.getColumnName()));

            } else if (Objects.equals(firstRow.getDataType(), DataType.valueOf("Double"))) {
                System.out.println("> Enter the value...");
                cells.add(new Cell<>(firstRow.getDataType(), sc.nextDouble(), firstRow.getColumnName()));

            } else if (Objects.equals(firstRow.getDataType(), DataType.valueOf("Boolean"))) {
                System.out.println("> Enter the value...");
                cells.add(new Cell<>(firstRow.getDataType(), Boolean.valueOf(sc.next()), firstRow.getColumnName()));

            } else if (Objects.equals(firstRow.getDataType(), DataType.valueOf("String"))) {
                System.out.println("> Enter the value...");
                cells.add(new Cell<>(firstRow.getDataType(), sc.next(), firstRow.getColumnName()));
            }
        }
        Record newRecord = new Record(currentTable.getRowIndex());
        newRecord.setColumns(cells);

        currentTable.insertRecord(newRecord);
    }
}
