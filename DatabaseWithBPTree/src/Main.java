import java.util.Scanner;

public class Main {
    public static UserPanel userPanel = new UserPanel();
    public static Menu menu = new Menu();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        menu.printMainMenu();
        int command;
        do{
          command = sc.nextInt();

            switch (command) {

                // create new table
                case 1 -> {
                    userPanel.createTableManager();
                    userPanel.tableMenuManager();
                }

                // select existed table
                case 2 -> {
                }
            }
          menu.printMainMenu();
        } while(command == 3);
    }
}
