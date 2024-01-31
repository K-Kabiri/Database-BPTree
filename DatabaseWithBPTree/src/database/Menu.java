package database;

public class Menu {
    public void printMainMenu(){
        System.out.println("""
                    [1] create new table
                    [2] select existed table
                    [3] EXIT
                    """);
    }

    public void printTableMenu(){
        System.out.println("""
                    [1] insert new record
                    [2] search by row index
                    [3] search by specific key
                    [4] search with input range
                    [5] delete by index of row
                    [6] delete by other fields
                    [7] update a record
                    [8] print table
                    [9] BACK
                    """);
    }
}
