package facilitymanagementsystem.client;

import facilitymanagementsystem.entity.User;
import facilitymanagementsystem.adt.LinkedList;
import facilitymanagementsystem.adt.ListInterface;
import java.util.Scanner;

public class UserModule {
    public UserModule() {}
    
    public void start() {
      userModuleMenu();
    }

    private Scanner scan = new Scanner(System.in);
    
    // This part for add new user
    private void addNewUser(ListInterface<User> userList) {

        System.out.println("+++++++++++++++++++++++++");
        System.out.println("         Add User        ");
        System.out.println("+++++++++++++++++++++++++");
        System.out.println("\nUser Details");
        System.out.println("=========================");
        
        System.out.print("User Id: ");
        while(!scan.hasNextInt()){ // Check whether user input interger only
            System.out.print("Enter User Id with(XXXXXXX(integer))format : ");
            scan.next();
        }
        int userId = scan.nextInt();
        scan.nextLine();
        
        System.out.print("User Name : ");
        String name = scan.nextLine();

        System.out.print("Gender (Full) : ");
        String gender = scan.nextLine();

        System.out.print("Phone Number (Format = 999-9999999) : ");
        String phone = scan.nextLine();

        System.out.println("\n\nUser Information");
        System.out.println("=======================");
        System.out.println("User Id: " + userId);
        System.out.println("User Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phone);

        System.out.print("\nConfirm to add? (Y = Yes / N = No) : ");
        char yn = scan.next().charAt(0);

        if (yn == 'Y' || yn == 'y') {
            userList.add(new User(userId, name, gender, phone));
            System.out.println("\nAdded successfully!");
        } else {
            System.out.println("Invalid Option!");
            System.out.println("\nUnsuccessful!");
        }
    }
    
    // This part for remove user
    private void removeUser(ListInterface<User> userList) {

        System.out.println("++++++++++++++++++++++++");
        System.out.println("       Remove User      ");
        System.out.println("++++++++++++++++++++++++\n");

        System.out.printf("%-10s %-30s %-10s %-20s", "User Id", "User Name", "Gender", "Phone");
        System.out.println("\n-----------------------------------------------------------------");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).toString());
        }
        System.out.println("\n");
        System.out.println("1) Delete user");
        System.out.println("2) Delete all the user in the list");
        System.out.print("\nPlease enter you choice (1 or 2) : ");
        int decision = scan.nextInt();
        if (decision == 1){
            System.out.print("Enter a position(user) you want to remove (start from 1) : ");
            int removeUser = scan.nextInt();
            scan.nextLine();
            userList.remove(removeUser);
            System.out.println("\nThe user has been deleted successfully!");
            
        }else if (decision == 2) {
            userList.clear();
            System.out.println("\nAll the user has been clear successfully!");
        } else {
            System.out.println("Invalid Option!");
        }
    }
    
    // This part for edit user
    private void editUser(ListInterface<User> userList) {

        boolean found = false;
        char continues;

        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("       Edit User Details      ");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("\nPlease enter the name of the user that wanted edit");
        System.out.print("\nUser Name : ");
        String modifyUser = scan.nextLine();
        
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getName().equals(modifyUser)){

                found = true;
                System.out.printf("%-10s %-30s %-10s %-20s", "User Id", "User Name", "Gender", "Phone");
                System.out.println("\n-----------------------------------------------------------------");
                System.out.println(userList.get(i).toString());

                do {
                    System.out.println("\nWhich information you want to edit?");
                    System.out.println("===================================");
                    System.out.println("1) User Id");
                    System.out.println("2) User Name");
                    System.out.println("3) Gender");
                    System.out.println("4) Phone Number");
                    System.out.print("\nPlease enter your choice (1,2,3 or 4) : ");
                    int choice = scan.nextInt();
                    scan.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("\nUser Id:");
                            int newUserId = scan.nextInt();
                            userList.get(i).setUserId(newUserId);
                            System.out.println("\nUpdated successfully!");
                            break;
                            
                        case 2:
                            System.out.print("\nUser Name:");
                            String newName = scan.nextLine();
                            userList.get(i).setName(newName);
                            System.out.println("\nUpdated successfully!");
                            break;
                            
                        case 3:
                            System.out.print("\nGender:");
                            String newGender = scan.nextLine();
                            userList.get(i).setGender(newGender);
                            System.out.println("\nUpdated successfully!");
                            break;
                            
                        case 4:
                            System.out.print("\nPhone Number:");
                            String newPhone = scan.nextLine();
                            userList.get(i).setPhone(newPhone);
                            System.out.println("\nUpdated successfully!");
                            break;
                            
                        default:
                            break;
                    }
                    System.out.print("Do you want to continue update? (Y = Yes / N = No): ");
                    continues = scan.next().charAt(0);
                } while (continues == 'Y' || continues == 'y');
            }
        }
        if (found == false) {
            System.out.println("Invalid User!");
        }
    }

    // This part for display and search the user
    private void displayUser(ListInterface<User> userList) {

        boolean found = false;

        System.out.println("+++++++++++++++++++++++++");
        System.out.println("       User Details      ");
        System.out.println("+++++++++++++++++++++++++\n");
        System.out.println("1) Display all user");
        System.out.println("2) Search by user name\n");
        System.out.print("Choices(1 or 2): ");
        int choice = scan.nextInt();
        scan.nextLine();
        System.out.println("\n");

        if (choice == 1) {
            System.out.printf("%-10s %-30s %-10s %-20s", "User Id", "User Name", "Gender", "Phone");
            System.out.println("\n-----------------------------------------------------------------");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println(userList.get(i).toString());
            }
            System.out.println("\nHave " + userList.size() + " record(s) found!");
        }
        else if(choice == 2) {
            System.out.print("Plase enter the user name: ");
            String searchUser = scan.nextLine();
            
            System.out.printf("%-10s %-30s %-10s %-20s", "User Id", "User Name", "Gender", "Phone");
            System.out.println("\n-----------------------------------------------------------------");
            for(int i = 0; i < userList.size(); i++){
                if(userList.get(i).getName().equals(searchUser)){
                    System.out.println(userList.get(i).toString());
                    found = true;
                }
            }
            if(found == false){
                System.out.println("No record(s) found!");
            }
        }else{
            System.out.println("Please enter Choices (1 or 2) only~~");
        }
    }
    
    // This part for Generate the user report
    private void generateReport(ListInterface<User> userList) {
        
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println("       Generate Report      ");
        System.out.println("++++++++++++++++++++++++++++\n"); 
        System.out.printf("%-10s %-30s %-10s %-20s", "User Id", "User Name", "Gender", "Phone");
        System.out.println("\n-----------------------------------------------------------------");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).toString());
        }
        System.out.println("\nHave " + userList.size() + " record(s) found!");
    }
    
    // Main
    private void userModuleMenu() {
        ListInterface<User> userList = new LinkedList<>();
        Scanner scan = new Scanner(System.in);
        User user = new User();
        
        User[] userArr = {
            new User(2212225, "Choo Wen Jie", "Male", "017-7514000"),
            new User(2212345, "Gwee June Ping", "Male", "017-7515000"),
            new User(2216789, "Xiao Bin Zhi", "Male", "017-7516000"),
            new User(2213333, "Ali Ah Kau", "Male", "017-7517000"),
            new User(2214444, "Mei Mei Ah", "Female", "017-7518000")};

        userList.add(userArr[0]);
        userList.add(userArr[1]);
        userList.add(userArr[2]);
        userList.add(userArr[3]);
        userList.add(userArr[4]);
        
        char continues;
           
        do{ 
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("       Welcome to TARUMT Facility Booking Service      ");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\n1) Add New User");
            System.out.println("2) Remove User");
            System.out.println("3) Edit User");
            System.out.println("4) Display User");
            System.out.println("5) Generate User Report");
            System.out.println("6) Exit System");
            
            System.out.print("\nOption (1, 2, 3, 4, 5 or 6) : ");
            int option = scan.nextInt();

                switch(option) {
                    case 1:
                        addNewUser(userList);
                        break;

                    case 2:
                        removeUser(userList);
                        break;

                    case 3:
                        editUser(userList);
                        break;
                        
                    case 4:
                        displayUser(userList);
                        break;
                        
                    case 5:
                        generateReport(userList);
                        break;
                        
                    case 6:
                        System.out.println("\nThank you for using our system~~");
                        System.out.println("Hope to see you again~~<3");
                        
                        break;
                        
                    default:
                        System.out.println("Please enter (1, 2, 3, 4, 5 or 6) only Thank you~");
                        break;
                }
                System.out.print("\nDo you want to continue? (Y = Yes / N = No): ");
                continues = scan.next().charAt(0);
            }while(continues == 'Y' || continues == 'y');
        
            if(continues == 'N' || continues == 'n'){
                System.out.println("\nThank you for using our system~~");
                System.out.println("Hope to see you again~~<3");
            }
    }
}