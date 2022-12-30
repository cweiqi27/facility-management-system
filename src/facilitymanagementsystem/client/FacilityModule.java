/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facilitymanagementsystem.client;

import facilitymanagementsystem.adt.ArrayList;
import facilitymanagementsystem.adt.ListInterface;
import facilitymanagementsystem.entity.Facility;
import facilitymanagementsystem.entity.Review;
import java.util.Scanner;
/**
 *
 * @author Thomas
 */
public class FacilityModule {
    private final Scanner s = new Scanner(System.in);
    ListInterface <Facility> list=new ArrayList<>(); //Creating arraylist 
    
    public FacilityModule() {}
    
    public void start() {
      facilityModuleMenu();
    }
    
    private void addNewFacility(ListInterface<Facility> list){
        int a,option;
        String b,c,d,e;
        
        int f,g;
        String h,i,j;
        boolean done = false;

        System.out.println("\t\tFacility List: ");
        System.out.println("-------------------------------------------------");
        System.out.println(String.format("%-5s %-15s %-10s %-10s %-20s","FacId","facName","facType","facVenue","Review"));
        System.out.println(list.toString());  
        while(!done){ // Check whether user enter 1,2 or 3 only
            System.out.println("Please Select Option To Add");
            System.out.println("1. Add Accrodingly");
            System.out.println("2. Add At Specific Position");
            System.out.println("3. Back To Main Menu");
            while(!s.hasNextInt()){ // Check whether user input interger only
                 System.out.print("Please 1,2 or 3 only ");
                 s.next();
               }
            option = s.nextInt();
            if(option == 1){
                System.out.print("Please Enter Your Facility Id(Numbers only): ");
                while(!s.hasNextInt()){ // Check whether user input interger only
                  System.out.print("Please Enter A Valid Input(INTEGER Only!)): ");
                  s.next();
                }
                a = s.nextInt();
                s.nextLine();   

                System.out.print("Please Enter Your Facility Name: ");
                b = s.nextLine();

                System.out.print("Please Enter Your Facility Type(Indoor/Outdoor): ");
                c = s.nextLine();

                System.out.print("Please Enter Your Facility Venue: ");
                d = s.nextLine();

                System.out.print("Please Enter Your Facility Review: ");
                e=s.nextLine();
                if(e.isEmpty()){ // Check if review input is empty, then will store "no review yet"
                    e="No Review Yet";
                }
                list.add(new Facility(a,b,c,d,new Review())); //Store data
                System.out.println("Added To List Successfully!!!\n");
                done=true;
            }else if (option == 2){
                System.out.print("Please Enter The Row You Want To Add: ");
                while(!s.hasNextInt()){ // Check whether user input interger only
                   System.out.print("Please Enter A Valid Input(INTEGER Only!)): ");
                   s.next();
                }         
                f = s.nextInt();
                s.nextLine();

                System.out.print("Please Enter The Fac Id You Want To Add: ");
                while(!s.hasNextInt()){ // Check whether user input interger only
                System.out.print("Please Enter A Valid Input(INTEGER Only!)): ");
                s.next();
                }    
                g = s.nextInt();
                s.nextLine();

                System.out.print("Please Enter The Fac Name You Want To Add: ");
                h = s.nextLine();

                System.out.print("Please Enter The Fac Type You Want To Add: ");
                i = s.nextLine();

                System.out.print("Please Enter The Fac Venue You Want To Add: ");
                j = s.nextLine();

                System.out.print("Please Enter Your Facility Review: ");
                e=s.nextLine();
                if(e.isEmpty()){ // Check if review input is empty, then will store "no review yet"
                    e="No Review Yet";
                }
                if(list.add(f, new Facility(g,h,i,j,new Review()))){ // If successfully add print
                    System.out.println("Added Successfully!!!");
                    done=true;
                }else //else added fail
                    System.out.println("Added Failed");
                    System.out.println("\t\tFacility List: ");
                    System.out.println("-------------------------------------------------");
                    System.out.println(String.format("%-5s %-15s %-10s %-10s %-20s","FacId","facName","facType","facVenue","Review"));
                    System.out.println(list.toString()); 
            }else if (option == 3){ // Back to main menu
                facilityModuleMenu();
                done=true;
            }else{
                    System.out.println("Invalid Input (Choose 1,2 or 3 Only !) \n");
                    done = false;
                }
        }
    }
    
    private void removeFacility(ListInterface<Facility> list){
        int decision,position;
        boolean done = false;
        char yn;
        System.out.println("\t\tFacility List: ");
        System.out.println("-------------------------------------------------");
        System.out.println(String.format("%-5s %-15s %-10s %-10s %-20s","FacId","facName","facType","facVenue","Review"));
        System.out.println(list.toString());
        
        while(!done){ // Check whether user enter 1,2 or 3 only
            System.out.println("1. Remove All");
            System.out.println("2. Remove One");
            System.out.println("3. Back To Main Menu");
            System.out.println("Please Choose 1,2 or 3: ");
            while(!s.hasNextInt()){ // Check whether user input interger only
                 System.out.print("Please Enter A Valid Input(INTEGER Only!)): ");
                 s.next();
               }
            decision = s.nextInt();
            if(decision == 1){
                System.out.print("Are You Sure You Want To Remove All The Data?: ");
                yn = s.next().charAt(0);
                if(yn == 'Y' || yn == 'y'){
                    list.clear();
                    System.out.println("Removed Successfully!!!");
                    System.out.println("\t\tFacility List: ");
                    System.out.println("-------------------------------------------------");
                    System.out.println(String.format("%-5s %-15s %-10s %-10s %-20s","FacId","facName","facType","facVenue","Review"));
                    System.out.println(list.toString());  
                    System.out.println("Empty List!!\n");
                    done = true;
                }else{
                    done = false;
                }
            }else if(decision == 2){
                System.out.print("Please Enter The Position You Want To Remove: ");
                position = s.nextInt();
                list.remove(position);
                System.out.println("Removed Successfully!!!\n");
                System.out.println("\t\tFacility List: ");
                System.out.println(String.format("%-5s %-15s %-10s %-10s %-20s","FacId","facName","facType","facVenue","Review"));
                System.out.println("-------------------------------------------------");
                System.out.println(list.toString());  
                done=true;
            }else if(decision == 3){
               facilityModuleMenu(); //Back to main menu
               done=true;
            } 
            else{
                System.out.println("Invalid Input (Choose 1 or 2 Only !) \n");
                done = false;
            }
        }
    }

    private void replaceFacility(ListInterface<Facility> list){
        int a,b;
        String c,d,e; 

        System.out.println("\t\tFacility List: ");
        System.out.println("-------------------------------------------------");
        System.out.println(String.format("%-5s %-15s %-10s %-10s %-20s","FacId","facName","facType","facVenue","Review"));
        System.out.println(list.toString());
        System.out.println("Enter -1 To Back ");
        System.out.print("Please Enter The Position You Want To Edit: ");
        while(!s.hasNextInt()){
            System.out.print("Please Enter A Valid Input(INTEGER Only!)): ");
             s.next();
          }
        a =s.nextInt();
        s.nextLine();       

        if(a<0){ // Back To main menu if input <0
            facilityModuleMenu();
        }
        System.out.print("Please Enter The Facility Id To Edit: ");
        while(!s.hasNextInt()){ // Check whether user input interger only
            System.out.print("Please Enter A Valid Input(INTEGER Only!)): ");
            s.next();
          }
        b = s.nextInt();
        s.nextLine();

        System.out.print("Please Enter The Facility Name To Edit: ");
        c = s.nextLine();
        System.out.print("Please Enter The Facility Type To Edit: ");
        d = s.nextLine();
        System.out.print("Please Enter The Facility Venue To Edit: ");
        e = s.nextLine();

        if(list.replace(a, new Facility(b,c, d, e))){
            System.out.println("Updated Successfully!!!");
            System.out.println("\n\t\tFacility List: ");
            System.out.println("-------------------------------------------------");
            System.out.println(String.format("%-5s %-15s %-10s %-10s %-20s","FacId","FacName","FacType","FacVenue","Review"));
            System.out.println(list.toString());
        }else{
            System.out.println("Updated Failed!!! Please Try Again !!!");
        }
    }
    
    private void generateReport(ListInterface<Facility> list){
        System.out.println("Facility Report");
        System.out.println("---------------");
        System.out.println("Total Numbers Of Record(s): " + list.size() + " Record(s)\n");
        System.out.println(String.format("%-5s %-15s %-10s %-10s %-20s","FacId","facName","facType","facVenue","Review"));
        System.out.println(list.toString());
    }
    
    private void facilityModuleMenu() {
        // TODO code application logic here
        boolean continueInput = true;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("*******************************************");
        System.out.println("Welcome to TARUMT Facility Booking Service");
        System.out.println("*******************************************");
        
        list.add(new Facility(123,"Badminton", "Indoor", "A01",new Review()));
        list.add(new Facility(456,"Swimming", "Indoor", "SP01",new Review()));
        list.add(new Facility(789,"Basketball", "Outdoor", "MP01",new Review()));
        
        do {
                System.out.println("\n--------------");
                System.out.println("Selection List");
                System.out.println("--------------");
                System.out.println("1. Add Facility");
                System.out.println("2. Remove Facility");
                System.out.println("3. Edit Facility");
                System.out.println("4. Check Facility");
                System.out.println("5. Generate Report and Exit");
                System.out.print("\nPlease Select Your Function: ");
                
                while(!sc.hasNextInt()){
                    System.out.print("Please Enter A Valid Input(INTEGER Only!)): ");
                    sc.next();
                  }
                int input= sc.nextInt();
                switch (input) {
                    case 1:
                        addNewFacility(list);
                        break;
                    case 2:
                        removeFacility(list);
                        break;
                    case 3:
                        replaceFacility(list);
                        break;
                    case 4:
                        System.out.println("\t\tFacility List: ");
                        System.out.println("-------------------------------------------------");
                        System.out.println(String.format("%-5s %-15s %-10s %-10s %-20s","FacId","FacName","FacType","FacVenue","Review"));
                        System.out.println(list.toString());
                        break;
                    case 5:
                        generateReport(list);
                        continueInput = false;
                        break;
                    default:
                        System.out.println("Wrong Input (1-6 Only !) ");
                        break;
            }
        } while (continueInput);
            System.out.println("Thank You For Using TARUMT Facility Module!!!");
    }
}
