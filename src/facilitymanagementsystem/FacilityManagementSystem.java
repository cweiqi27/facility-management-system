package facilitymanagementsystem;

import facilitymanagementsystem.client.ClientHelper;
import facilitymanagementsystem.client.FacilityBookingModule;
import facilitymanagementsystem.client.FacilityModule;
import facilitymanagementsystem.client.ReviewModule;
import facilitymanagementsystem.client.UserModule;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Thomas, Wen Jie, Jia Heng, Yuu Sheng, Chan Wei Qi
 */
public class FacilityManagementSystem { 
  public static void main(String[] args) throws IOException {   
    ClientHelper clientHelper = new ClientHelper();
    Scanner scanner = new Scanner(System.in);
    
    FacilityModule facilityModule = new FacilityModule();
    UserModule userModule = new UserModule();
    FacilityBookingModule facilityBookingModule = new FacilityBookingModule();
    ReviewModule reviewModule = new ReviewModule();
       
    
    System.out.println("*******************************************");
    System.out.println("Welcome to TARUMT Facility Booking Service");
    System.out.println("*******************************************");
    System.out.println("(1) Facility Module");
    System.out.println("(2) User Module");
    System.out.println("(3) Booking Module");
    System.out.println("(4) Review Module");
    System.out.println("(5) Admin Module");
    
    try {
      int option = clientHelper.optionScanner(0, scanner);
    
      switch(option) {
        case 1 -> {
          facilityModule.start();
          break;
        }
        case 2 -> {
          userModule.start();
          break;
        }
        case 3 -> {
          facilityBookingModule.start();
          break;
        }
        case 4 -> {
          reviewModule.start();
          break;
        }
        case 5 -> {

        }
        default->{}
      }

      System.out.println("Back to Main Menu? (y)(n)");
      String contOption = clientHelper.optionScanner('c', scanner);
      if(contOption.equalsIgnoreCase("y")) 
        main(args);
      else
        System.out.println("Thank You For Using TARUMT Facility Module!!!");
      
    } catch(Exception e) {
      System.out.println("Error occured");
      System.out.println("Exception: " + e);
      scanner.nextLine();
    }
    
  }
    
}
