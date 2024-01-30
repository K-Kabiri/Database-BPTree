import model.Table;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserPanel {

    // ---------------- field -----------------
    private final ArrayList<Table> tables;
    private final Menu menu = new Menu();
    private final Scanner sc = new Scanner(System.in);

    // ------------ constructor --------------
    public UserPanel(){
        this.tables = new ArrayList<>();
    }

    // ----------- getter & setter -------------
    public ArrayList<Table> getTables() {
        return tables;
    }

    // ---------------- Methods ----------------

    public void tableMenuManager(){
        menu.printTableMenu();
        int command;
        do{
            command = sc.nextInt();
            switch (command) {
                // insert new record
                case 1 -> {
                    // code

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
        } while(command == 7);
    }

    public void createTableManager(){
        System.out.println("Enter the title of your new table...");
        String tableTitle = sc.next();
        System.out.println("Do you wanna create a new table with specific key type ? ( Yes or No )");
        String answer = sc.next();
        if(Objects.equals(answer, "Yes")){
            System.out.println("Enter the type key which you want...");
            String tableType = sc.next();
            createNewTableWithSelectedKey(tableType , tableTitle);
        }
        else if(Objects.equals(answer, "No")){
            System.out.println("Ok! table with Integer index has been created for you...");
            createNewTableWithSelectedKey("Integer" , tableTitle);
        }
    }
    private void createNewTableWithSelectedKey(String tableType , String tableTitle){
        if(Objects.equals(tableType, "Integer")) {
            Table<Integer> newtable = new Table<>(tableTitle);
        }
        else if(Objects.equals(tableType, "Character")) {
            Table<Character> newtable = new Table<>(tableTitle);
        }
        else if(Objects.equals(tableType, "Double")) {
            Table<Double> newtable = new Table<>(tableTitle);
        }
        else if(Objects.equals(tableType, "Boolean")) {
            Table<Boolean> newtable = new Table<>(tableTitle);
        }
        else if(Objects.equals(tableType, "String")) {
            Table<String> newtable = new Table<>(tableTitle);
        }
    }
}
