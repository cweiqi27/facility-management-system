package facilitymanagementsystem.client;

import java.util.Scanner;

/**
 *
 * @author Chan Wei Qi
 * 
 * helper methods that can be used in client classes
 * for DRY purpose
 */
public class ClientHelper {
  public ClientHelper() {}
  
  public int optionScanner(int n, Scanner scanner) {
    System.out.print("> ");
    return scanner.nextInt();
  }
  
  public String optionScanner(char s, Scanner scanner) {
    System.out.print("> ");
    return scanner.next();
  }
  
  public String optionScanner(String s, Scanner scanner) {
    System.out.print("> ");
    return scanner.nextLine();
  }
}
