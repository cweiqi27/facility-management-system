
package facilitymanagementsystem.client;

import facilitymanagementsystem.adt.ArrayList;
import facilitymanagementsystem.adt.ArrayStack;
import facilitymanagementsystem.adt.ListInterface;
import facilitymanagementsystem.adt.StackInterface;
//import facilitymanagementsystem.entity.Facility;
import facilitymanagementsystem.entity.Admin;
import java.util.Scanner;
//import java.util.Iterator;

public class AdminModule {
  Scanner scanner = new Scanner(System.in);
  private ListInterface<Admin> adminList = new ArrayList<>();
  private StackInterface<String> adminStack = new ArrayStack<>();
  private int adminIndex = 1;
  
  public AdminModule() {}

  public void start() {
    adminModuleMenu();
  }

  private void adminModuleMenu() {
    System.out.println("==================");
    System.out.println("      ADMIN       ");
    System.out.println("==================");
    System.out.println("(1)Create admin"); 
    System.out.println("(2)Edit admin");
    System.out.println("(3)Delete admin");
    System.out.println("(4)Generate Report");
    
    int option = scanner.nextInt();
    
    switch(option) {
        case 1 -> {
          createAdmin();
          break;
        }
        case 2 -> {
          editAdmin();
          break;
        }
        case 3 -> {
          deleteAdmin();
          break;
        }
        case 4 -> {
          generateReport();
          break;
        }
        case 5 -> {
          latestRecord();
          break;
        }
        default->{}
      }
  }


  private void createAdmin() {
  }
  
  private void editAdmin() {
  }
  
  private void deleteAdmin() {
  }
  
  private void generateReport() {
  }
  
  private String[] latestRecord() {
    String[] admin = new String[adminStack.size()];
     int i = 0;
     while(adminStack.peek() != null || i < 5) { // 5 for top 5 latest record
        admin[i] = adminStack.pop();
        i++;
     }
    return admin;
  }
}
