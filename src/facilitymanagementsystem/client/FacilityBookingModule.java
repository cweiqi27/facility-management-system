
package facilitymanagementsystem.client;

import facilitymanagementsystem.adt.BookingList;
import facilitymanagementsystem.adt.ListInterface;
import facilitymanagementsystem.entity.FacilityBook;
import facilitymanagementsystem.entity.FacilityDetail;
import java.util.Scanner;


public class FacilityBookingModule {
    private final ListInterface<FacilityBook> bookingList = new BookingList<>(); 
    
    public FacilityBookingModule() {
        record();
        booking();
    }

    private void record() {
	bookingList.add(new FacilityDetail(1001,"Tan Jack        ","Basketball Facility   ","2"));
	bookingList.add(new FacilityDetail(1002,"Tan Yong Jie    ","Badminton Facility    ","1"));
	bookingList.add(new FacilityDetail(1003,"Timi             ","GymRoom Facility      ","2"));
	bookingList.add(new FacilityDetail(1004,"Lisa            ","Volleyball Facility   ","1"));
	bookingList.add(new FacilityDetail(1005,"MiXiiao         ","Basketball Facility   ","1"));
    }
    
    //View the added booking list
    private void view() {
        System.out.println("\t\t\t\t Booking List\n\t**************************************************");
        System.out.printf("%2s\t%-20s\t%-24s\t%-15s\t\n","ID","Name","Facility","Duration");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(bookingList.toString());
        System.out.println("-------------------------------------------------------------------------");     
        
        booking();
    }
    
    //Display the facility list
    private void view2() {
        System.out.println("Facility List\n**************");
        System.out.println("1. Basketball Facility ");
        System.out.println("2. Badminton Facility ");
        System.out.println("3. GymRoom Facility ");
        System.out.println("4. Volleyball Facility ");
        System.out.println("5. Football Facility \n**************");    
    }
    
    
    //Display the facility list
    private void display() {
        System.out.println("Welcome to facilities list  \n**************");
        System.out.println("1. Basketball Facility ");
        System.out.println("2. Badminton Facility ");
        System.out.println("3. GymRoom Facility ");
        System.out.println("4. Volleyball Facility ");
        System.out.println("5. Football Facility \n**************");
        
        String response;
        Scanner in = new Scanner(System.in);
        
       
        do{
            System.out.println("Back?(y or n)");
            response = in.nextLine();
        }while(checkYesNo(response)== false);
        booking();
        
    }
    
    private void booking() {
        
        System.out.println("Welcome to facilities booking  \n**************");
        System.out.println("1. Display Facility ");
        System.out.println("2. Make Facility Booking ");
        System.out.println("3. Cancel Facility Booked ");
        System.out.println("4. Search Facility Booked ");
        System.out.println("5. Edit Facility Booked ");
        System.out.println("6. View Facility Booked \n**************");
        
        int num1;
        Scanner number1 = new Scanner(System.in);
        
        System.out.println("Which select you want?");
        num1 = number1.nextInt();
        switch (num1) {
            case 1:
                System.out.println("Welcome to List Facility Page.");
                display();
                break;
            case 2:
                System.out.println("Welcome to make facility booking page.");
                add();
                break;
            case 3:
                System.out.println("Welcome to cancel facility booking page.");
                delete();
                break;
            case 4:
                System.out.println("Welcome to search facility booked page.");
                search();
                break;
            case 5:
                System.out.println("Welcome to edit facility booked page.");
                edit();
                break;
            case 6:
                System.out.println("Welcome to view facility booked page.");
                view();
                break;
            default:
                System.err.printf("Reenter correct answer. (1-5)");
                break;
        }
    }
    
    //add
    private void add() {
        System.out.println("**************");
        System.out.println("Booking Facility Page \n**************\n");
        Scanner in = new Scanner(System.in);
        String response;
        boolean flag1;
            do{
                flag1 = false;
                
                System.out.print("Your name:");
                String name = in.nextLine();
                System.out.print("The time duration (1-2): ");
                String duration = in.nextLine();
                
                String facility = null;
                view2();
                System.out.print("Choose Facility: ");
                int num = in.nextInt();
            switch (num) {
                case 1 -> facility = "Basketball Facility";
                case 2 -> facility = "Badminton Facility";
                case 3 -> facility = "GymRoom Facility";
                case 4 -> facility = "Volleyball Facility";
                case 5 -> facility = "Football Facility";
                default -> { System.err.printf("Reenter correct answer. (1-5)");
                        return;
                        }
            }
            
                
                
                int Id = 1005;
                Id++;
                System.out.println("New Booking Is Added Successfully !!!\n");
                bookingList.add(new FacilityDetail(Id, name, facility, duration));
                do{
                    System.out.print("\nDid you need add more new booking?");
                    response = in.nextLine();
                }while(checkYesNo(response)== false);
            }while(Character.toUpperCase(response.charAt(0)) == 'Y');
        view();
        booking();
    }
    
    //delete
    private void delete() {
        System.out.println("**************");
        System.out.println("Delete The Item Page \n**************\n");
        String response;
    	boolean existence;
    	do{	
            Scanner s = new Scanner(System.in);
            System.out.print("Enter the Booking Name to delete: ");
            String code = s.nextLine();
            existence = false;
            int num;
            for(int z=0;z<bookingList.size();z++){
                if(bookingList.get(z).getName().contains(code)){
                    num = z;
                    existence = true;
                    String name = bookingList.get(z).getName();
                    System.out.print("Confirm deletion of " +name+" booked facility? (Y/N) >");
                    String decision = s.nextLine();
                    if(checkYesNo(decision) == true){
                        if(Character.toUpperCase(decision.charAt(0)) == 'Y'){
                            bookingList.remove(num);
                            System.out.println("Booking Is Deleted Successfully!!!");
                        }
                    }
	    	}
	    }
	    if (existence == false){
                System.out.print("\nThe Item ID does not exist");
	    }
	    do{
                System.out.print("\nDid you need delete more item?");
                response = s.nextLine();
            }while(checkYesNo(response)== false && checkYesNo(response) == false);
    	}while(Character.toUpperCase(response.charAt(0)) == 'Y');
        booking();
    }
    
    //search
    private void search() {
        System.out.println("Searching Facility Booking Page \n**************\n");
        String response;
    	boolean existence;
    	do{
            Scanner s = new Scanner(System.in);
            System.out.print("Enter the booking user name you wish to search: ");
            String code = s.nextLine();
	    existence = false;
            System.out.println("\t\t\t\t SEARCH RESULT \n\t\t**************************************************");
            System.out.printf("%2s\t%-20s\t%-17s\t%16s\t\n","ID","Name","Facility","Duration");
            System.out.println("---------------------------------------------------------------------------------");
	    for(int h=0;h<bookingList.size();h++){
	    	if(bookingList.get(h).getName().contains(code)){
                    existence = true;
                    System.out.println(bookingList.get(h));
	    	}
	    }
            System.out.println("----------------------------------------------------------------------------------");
	    if (existence == false){
	    	System.out.println("The booking name is non-existent");
	    }
	    do{
		System.out.print("\nDid you need to searching more booking?");
		response = s.next();
            }while(checkYesNo(response)== false);
    	}while(Character.toUpperCase(response.charAt(0)) == 'Y');
        booking();
    }
    
    //edit
    private void edit() {
        System.out.println("Change The Booked Facility Page \n**************\n");
        Scanner in = new Scanner(System.in);
        String response;
        boolean existence;
            do{
                String facility,duration;
                System.out.print("Enter Your Name: ");
                String code = in.nextLine();
                existence = false;
                for(int h=0;h<bookingList.size();h++){
                    if(bookingList.get(h).getName().contains(code)){
                        existence = true;
                        System.out.print("Enter the time duration (1-2): ");
                        duration = in.nextLine();
                        bookingList.get(h).setDuration(duration);
                        view2();
                        System.out.print("Enter the new Booking Facility: ");
                        int num = in.nextInt();
                        switch (num) {
                            case 1 -> {
                                facility = "Basketball Facility";
                                bookingList.get(h).setFacility(facility);
                            }
                            case 2 -> {
                                facility = "Badminton Facility";
                                bookingList.get(h).setFacility(facility);
                            }
                            case 3 -> {
                                facility = "GymRoom Facility";
                                bookingList.get(h).setFacility(facility);
                            }
                            case 4 -> {
                                facility = "Volleyball Facility";
                                bookingList.get(h).setFacility(facility);
                            }
                            case 5 -> {
                                facility = "Football Facility";
                                bookingList.get(h).setFacility(facility);
                            }
                            default -> {
                                System.err.printf("Reenter correct answer. (1-5)");
                                return;
                            }
                        }
                        
                    }
                }
                if (existence == false){
                    System.out.println("The item name is non-existent.");
                }
                do{
                    System.out.print("Did want to modify more booking?");
                    response = in.nextLine();
                }while(checkYesNo(response)== false);
            }while(Character.toUpperCase(response.charAt(0)) == 'Y' );
            booking();
    }
    
    private boolean checkYesNo(String response){
    	if(response.length() != 1){
    		System.out.print("Only 'Y'and 'N' are accepted.\n");
    		return false;
    	}
    	else if(Character.toUpperCase(response.charAt(0)) == 'Y' || Character.toUpperCase(response.charAt(0)) == 'N')
    		return true;
    	else{
    		System.out.print("Only 'Y'and 'N' are accepted.\n");
    		return false;
    	}
    }
    
}
