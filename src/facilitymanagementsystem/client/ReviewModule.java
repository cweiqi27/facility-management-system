package facilitymanagementsystem.client;

import facilitymanagementsystem.adt.ArrayList;
import facilitymanagementsystem.adt.BinaryTreeInterface;
import facilitymanagementsystem.adt.ListInterface;
import facilitymanagementsystem.entity.Facility;
import facilitymanagementsystem.entity.Review;
import facilitymanagementsystem.entity.User;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Chan Wei Qi
 */
public class ReviewModule {
  private final Scanner scanner = new Scanner(System.in);
  private final ListInterface<Review> reviewList = new ArrayList<>();
  private final ListInterface<User> userList = new ArrayList<>();
  private final ListInterface<Facility> facilityList = new ArrayList<>();
  private int currentIndex = 1;
  private final ClientHelper clientHelper = new ClientHelper();
  private final WordTree wordTree = new WordTree();
  private final BinaryTreeInterface<String> tree = wordTree.getTree();
  
  public ReviewModule() throws IOException {
    initializeDummyData();
    reviewMenu();
  }
  
  private void initializeDummyData() {
    int userIndex = 1;
    int facilityIndex = 1;
    
    User user1 = new User(userIndex++, "John Doe", "Male", "0123456789");
    User user2 = new User(userIndex++, "Jane Doe", "Female", "0123456724");
    User user3 = new User(userIndex++, "Frederic Chopin", "Male", "23418591");
    User user4 = new User(userIndex++, "Alexander Scriabin", "Male", "1239812390");
    User user5 = new User(userIndex++, "Clara Schumann", "Female", "0169696789");
    User user6 = new User(userIndex++, "Sergei Rachmaninoff", "Male", "0169656789");
    User user7 = new User(userIndex++, "Franz Liszt", "Male", "0189696789");
    
    userList.add(user1);
    userList.add(user2);
    userList.add(user3);
    userList.add(user4);
    userList.add(user5);
    userList.add(user6);
    userList.add(user7);
    
    Facility facility1 = new Facility(facilityIndex++, "Sports Complex", "Indoor", "A01");
    Facility facility2 = new Facility(facilityIndex++, "Basketball Court", "Outdoor", "B01");
    Facility facility3 = new Facility(facilityIndex++, "Classroom", "Indoor", "C01");
    Facility facility4 = new Facility(facilityIndex++, "Discussion Room", "Indoor", "D01");
    Facility facility5 = new Facility(facilityIndex++, "ICT Lab", "Indoor", "E01");
    
    facilityList.add(facility1);
    facilityList.add(facility2);
    facilityList.add(facility3);
    facilityList.add(facility4);
    facilityList.add(facility5);
  }
  
  private void reviewMenu() throws IOException {
    System.out.println("\n================");
    System.out.println("     REVIEW     ");
    System.out.println("================");
    System.out.println("(1) Create review");
    System.out.println("(2) Edit review");
    System.out.println("(3) Delete review");
    System.out.println("(4) Display all reviews");
    System.out.println("(5) Report");
    System.out.println("(6) Dictionary");
    System.out.println("Select option -");
    
    try {
      int option = clientHelper.optionScanner(0, scanner);
      scanner.nextLine();

      switch(option) {
        case 1 -> addReview();
        case 2 -> updateReview();
        case 3 -> deleteReview();
        case 4 -> displayAllReview();
        case 5 -> generateReport();
        case 6 -> dictionaryMenu();
        default -> {
        }
      }
    } catch(Exception e) {
      System.out.println("Error occured");
      System.out.println("Exception: " + e);
      scanner.nextLine();
    }
    
    continuePrompt();
  }
  
  private void addReview() {
    System.out.println("\n================");
    System.out.println(" CREATE REVIEW");
    System.out.println("================");
    
    Review review = new Review();
    review.setReviewId(currentIndex);
    
    System.out.println("Users: ");
    
        
    System.out.print("Title: ");
    String title = scanner.nextLine();
    review.setReviewTitle(replaceWords(title));
    
    System.out.print("\nContent: ");
    String content = scanner.nextLine();
    review.setReviewContent(replaceWords(content));
    
    System.out.print("\nRating: ");
    int rating = scanner.nextInt();
    review.setRating(rating);
    
    review.setType();
    
    System.out.println("This review will be created: ");
    displaySingleReview(review);
    System.out.println("Create(y) Cancel(n)");
    
    if(!clientHelper.optionScanner('c', scanner).equals("y") ) {
      System.out.println("\nReview creation cancelled");
      return;
    }
    
    reviewList.add(review);
    currentIndex++;
    System.out.println("\nReview created");
  }
  
  private void updateReview() {
    System.out.println("\n================");
    System.out.println(" EDIT REVIEW");
    System.out.println("================");
    
    // if review does not exist, return void, else display review table
    if(!displayAllReview()) return;
    
    Review review = reviewList.get(0);
    
    System.out.println("Type in Review ID to select the Review to be edited: ");
    int inputId = clientHelper.optionScanner(0, scanner);
    boolean isExist = false;
    for(int i = 0; i < reviewList.size(); i++) {
      Review reviewEntry = reviewList.get(i);
      if(reviewEntry.getReviewId() == inputId) {
        review = reviewEntry;
        isExist = true;
      }
    }
    
    if(!isExist) {
      System.out.println("Invalid review ID");
      updateReview();
    }
    
    System.out.println("\nSelect field to be edited: ");
    System.out.println("(1) Title");
    System.out.println("(2) Content");
    System.out.println("(3) Rating");
    int option = clientHelper.optionScanner(0, scanner);
    scanner.nextLine();
    
    switch (option) {
      case 1 -> {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        review.setReviewTitle(replaceWords(title));
        review.setTimestampUpdate();
        break;
      }
      case 2 -> {
        System.out.print("Content: ");
        String content = scanner.nextLine();
        review.setReviewContent(replaceWords(content));
        review.setTimestampUpdate();
        break;
      }
      case 3 -> {
        System.out.print("Rating: ");
        review.setRating(scanner.nextInt());
        review.setType();
        review.setTimestampUpdate();
        break;
      }
      default -> {
        return;
      }
    }
  }
  
  private void deleteReview() {
    System.out.println("\n================");
    System.out.println(" DELETE REVIEW");
    System.out.println("================");
    
    // if review does not exist, return void, else display review table
    if(!displayAllReview()) return;
    
    System.out.println("(1) Delete record by ID");
    System.out.println("(2) Clear all");
    
    if(scanner.nextInt() == 2) {
      System.out.println("Are you sure you want to delete all the reviews in the system?");
      System.out.println("This action is irreversible.");
      System.out.println("Delete(y) Cancel(n)");
      if(clientHelper.optionScanner('c', scanner).equals("y")) {
        reviewList.clear();
        System.out.println("All review data has been successfully wiped");
        return;
      }
      deleteReview();
    }
    
    Review review = reviewList.get(0);
    
    System.out.println("Type in Review ID to select the Review to be deleted: ");
    int inputId = clientHelper.optionScanner(0, scanner);
    boolean isExist = false;
    int i = 0;
    while(!isExist || i < reviewList.size()) {
      Review reviewEntry = reviewList.get(i);
      if(reviewEntry.getReviewId() == inputId) {
        review = reviewEntry;
        isExist = true;
      }
    }
    
    if(!isExist) {
      System.out.println("Invalid review ID");
      deleteReview();
    }

    System.out.println("This data will be deleted: ");
    displaySingleReview(review);
    System.out.println("(y)Delete (n)Cancel");
    String option = clientHelper.optionScanner('c', scanner);

    if(option.equals("y")) {
      reviewList.remove(reviewList.indexOf(review));
      System.out.println("Review deleted.");
    }
  }
  
  private boolean displayAllReview() {
    if(reviewList.isEmpty()) {
      System.out.println("No review found.\n");
      return false;
    } 
    
    displayReviewHeader();
    
    for(int i = 0; i < reviewList.size(); i++) {
      Review review = reviewList.get(i);
      displayDataColumn(review);
    }
    
    return true;
  }
  
  private void displaySingleReview(Review review) {
    System.out.println("ID: " + review.getReviewId());
    System.out.println("Title: " + review.getReviewTitle());
    System.out.println("Content: " + review.getReviewContent());
    System.out.println("Rating: " + review.getRating());
    System.out.println("Type: " + review.getType());
  }
  
  private void generateReport() {
    System.out.println("\n================");
    System.out.println(" GENERATE REPORT");
    System.out.println("================");
    System.out.println("(1)Review List by User");
    System.out.println("(2)Review List by Facility");
    System.out.println("(3)Latest Reviews");
    System.out.println("(4)Longest review streak");
    System.out.println("(5)Review scores");
    System.out.println("Select option: -");
    int option = clientHelper.optionScanner(0, scanner);
    
    switch (option) {
      case 1 -> {
        displayAllReview();
        break;
      }
      case 2 -> {
        int reviewNum = Math.min(5, reviewList.size());
        System.out.print("Last " + reviewNum + " reviews being made:\n");
        Review reviewArr[] = latestReviews(reviewNum);
        displayReviewHeader();
        for(Review review: reviewArr) {
          displayDataColumn(review);
        }
        break;
      }
      case 3 -> {
        System.out.print("Longest POSITIVE streak: " + longestReviewStreak("POSITIVE") + "\n");
        System.out.print("Longest NEUTRAL streak: " + longestReviewStreak("NEUTRAL") + "\n");
        System.out.print("Longest NEGATIVE streak: " + longestReviewStreak("NEGATIVE") + "\n");
        break;
      }
      case 4 -> {
        int[] reviewScores = reviewScoreReport();
        System.out.print("Highest score: " + reviewScores[0] + "\n");
        System.out.print("Lowest score: " + reviewScores[1] + "\n");
        System.out.print("Average score: " + reviewScores[2] + "\n");
      }
      default -> {
        return;
      }
    }
  }
     
  // dictionary 'submodule' that lets user to insert, search, and delete
  // word from the AVL tree
  private void dictionaryMenu() {
    System.out.println("\n================");
    System.out.println("   DICTIONARY  ");
    System.out.println("================");
    System.out.println("(1)Insert word");
    System.out.println("(2)Search word");
    System.out.println("(3)Delete word");
    System.out.println("Select option -");
    
    int option = clientHelper.optionScanner(0, scanner);
    scanner.nextLine();

    switch(option) {
      case 1 -> addWord();
      case 2 -> searchWord();
      case 3 -> deleteWord();
      default -> {}
    }
  }
  
  // Insert word into the tree
  private void addWord() {
    System.out.print("\nNew word: ");
    String input = scanner.next();
    
    if(isWord(input)) 
      System.out.println("\nWord already exist.");
    else
      wordTree.insertWord(input);
  }
  
  // Search word in the tree
  private void searchWord() {
    System.out.print("Search word: ");
    String input = scanner.next();
    
    if(isWord(input)) {
      System.out.println(input.toUpperCase() + " exists");
      System.out.print("Number of times " + input.toUpperCase() + 
          // frequency is the amount of times the node of the input has been visited in specific
          // user trigerred functions, such as insert, remove, and search
          " has been searched: " + wordTree.getWordFrequency(input) + "\n");
    } else {
      // if does not exist, offer to insert the word into the tree
      System.out.println("The word " + input.toUpperCase() + " does not exist in the dictionary.");
      System.out.println("Do you want to add " + input.toUpperCase() + " into the dictionary? (y)(n)");
      String choice = clientHelper.optionScanner('c', scanner);
      if(choice.toLowerCase().equals("y")) 
        wordTree.insertWord(input);
      scanner.nextLine();
    }
  }
  
  // delete word method
  private void deleteWord() {
    System.out.print("Delete word: ");
    String input = scanner.next();
    
    // if the input does not exist in the tree, askl the user if they want to add the word into
    // the tree instead
    if(!isWord(input)) {
      System.out.println("The word " + input.toUpperCase() + " does not exist in the dictionary.");
      System.out.println("Do you want to add " + input.toUpperCase() + " into the dictionary instead? (y)(n)");
      String choice = clientHelper.optionScanner('c', scanner);
      if(choice.toLowerCase().equals("y")) 
        wordTree.insertWord(input);
      scanner.nextLine();
    // remove the word from the tree if yes
    } else {
      System.out.print("This word will be deleted: " + input.toUpperCase() + "\n");
      System.out.println("Delete(y) Cancel(n)");
      String choice = clientHelper.optionScanner('c', scanner);
      if(choice.toLowerCase().equals("y")) 
        wordTree.removeWord(input);
      scanner.nextLine();
    }
  }
  
  // method that replace the mispelled words
  private String replaceWords(String passage) {
    ListInterface<String> wrongWordList = new ArrayList<>();
    ListInterface<String> tempWordList = new ArrayList<>();
    Queue<String> separatorQueue = getSeperatorQueue(passage);
    
    // split the passage/ paragraph into string of arrays, then add them all into
    // temporary word list, if it does not exist in the tree then also add them 
    // into wrong word list
    String words = passage.toLowerCase();
    for(String word: splitPassage(words)) {
      if(!isWord(word)) 
        wrongWordList.add(word);
      tempWordList.add(word);
    }
    
    if(!wrongWordList.isEmpty()) {
      System.out.println("Wrong word detected: these words might be misspelled or do not exist in the English dictionary.");
      System.out.println("Do you want to replace the wrong words? (y)(n)");
      String choice = clientHelper.optionScanner('c', scanner);
      scanner.nextLine();
      if(choice.toLowerCase().equals("y")) 
        return replaceWords(tempWordList, wrongWordList, separatorQueue);
       
      // if user select no, clear the temporary list and wrong word list, the returns the original word
      wrongWordList.clear();
      tempWordList.clear();
    }
    
    return passage;
  }
  
  // overloading method to decouple replace words functions
  private String replaceWords(ListInterface<String> tempWordList, ListInterface<String> wrongWordList, Queue separatorQueue) {
    String passage = "";
    
    for(int i = 0; i < wrongWordList.size(); i++) {
      String wrongWord = wrongWordList.get(i);
      int wrongWordIndex = tempWordList.indexOf(wrongWord);

      System.out.println("\nMispelled word: " + wrongWord);
      System.out.println("Word[s] suggestion[s]: ");
      String[] suggestedWords = suggestWords(wrongWord);
      int choiceNext;
      if(suggestedWords == null) {
        System.out.println("\nNo suggestions are to be found.");
        System.out.println("\nReplace with your own word:");
        choiceNext = 2;
      } else {
        for(String word: suggestedWords) {
          if(word != null) System.out.println("- " + word);
        }
        System.out.println("(1) Replace with suggested word");
        System.out.println("(2) Replace with your own word");
        choiceNext = clientHelper.optionScanner(0, scanner);
      }

      // lets user choose a suggested word to be replaced from the array of words
      if(choiceNext == 1) {
        System.out.println("\nSelect option:");
        for(int j = 0; j < suggestedWords.length; j++) {
          if(suggestedWords[j] != null)
            System.out.println(j + 1 + ". " + suggestedWords[j]);
        }
        tempWordList.replace(wrongWordIndex + 1, suggestedWords[clientHelper.optionScanner(0, scanner) - 1]);
        scanner.nextLine();
      } else {
        // prompt user to type in their own word to be replaced
        String customWord = clientHelper.optionScanner('c', scanner).toLowerCase();
        tempWordList.replace(wrongWordIndex + 1, customWord);
        scanner.nextLine();
        // if the user's input does not exist in the tree, ask them if they want to add it into the dictionary
        if(!isWord(customWord)) {
          System.out.println("The word " + customWord.toUpperCase() + " does not exist in the dictionary.");
          System.out.println("Do you want to add " + customWord.toUpperCase() + " into the dictionary? (y)(n)");
          String choice = clientHelper.optionScanner('c', scanner);
          if(choice.toLowerCase().equals("y")) 
            wordTree.insertWord(customWord);
          scanner.nextLine();
        }
      }
    }
        
    for(int i = 0; i < tempWordList.size(); i++) {
      String word = tempWordList.get(i);
      passage += word;
      if(!passage.equals("") && separatorQueue.peek() != null)
        passage += separatorQueue.poll();
    }
    
    return passage;
  }
  
  // helper method that splits the passage into words using regex, split when space, comma, and full stop
  private String[] splitPassage(String passage) {
    return passage.split("[ .,]+");
  }
  
  // put separators (comma, whitespace, dot) into a queue
  private Queue<String> getSeperatorQueue(String passage) {
    Queue<String> separatorQueue = new ArrayDeque<>();
    for(int i = 0; i < passage.length(); i++) {
      char c = passage.charAt(i);
      if(c == ' ' || c == ',' || c == '.') 
        separatorQueue.add(Character.toString(c));
    }
    
    return separatorQueue;
  }
  
  // helper method that checks if the word is correctly spelt or is a real word
  private boolean isWord(String word) {
    return wordTree.searchWord(word);
  }
    
  // returns an array of word suggestions
  private String[] suggestWords(String word) {
    Iterator<String> iterator = tree.getIterator();
    String[] suggestedWords = new String[50];
    int maxDistance = 2; // maximum allowed Levenshtein distance
    int i = 0;
    boolean isNull = true;
    
    // suggest max fifty words 
    while(iterator.hasNext() && i < 50) {
      String nextWord = iterator.next();
      int distance = computeLevenshteinDistance(word, nextWord);
      if(distance <= maxDistance) {
        suggestedWords[i] = nextWord;
        i++;
      }
    }
    
    for(String suggestedWord: suggestedWords) {
      if(suggestedWord != null)
        isNull = false;
    }
    
    return isNull ? null : suggestedWords;
  }
  
  // use the Levenshtein distance formula to calculate the shortest possible distance between the words
  private int computeLevenshteinDistance(String x, String y) {
    int[][] dp = new int[x.length() + 1][y.length() + 1];

    // use dynamic programming approach to loop through the matrix and find
    // the minimum value of distance
    for (int i = 0; i <= x.length(); i++) {
      for (int j = 0; j <= y.length(); j++) {
        if (i == 0) {
          dp[i][j] = j;
        }
        else if (j == 0) {
          dp[i][j] = i;
        }
        else {
          dp[i][j] = min(dp[i - 1][j - 1] 
            + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
            dp[i - 1][j] + 1, 
            dp[i][j - 1] + 1);
        }
      }
    }

    return dp[x.length()][y.length()];
  }
  
  private int costOfSubstitution(char a, char b) {
    return a == b ? 0 : 1;
  }
  
  private int min(int... numbers) {
    return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
  }
  
  private void continuePrompt() throws IOException {
    System.out.println("Continue? (y)(n)");
    String contOption = clientHelper.optionScanner('c', scanner);
    if(contOption.equals("y")) reviewMenu();
  }
  
  private int longestReviewStreak(String type) {
    int streak = 0;
    int maxStreak = 0;
    for(int i = 0; i < reviewList.size(); i++) {
      Review review = reviewList.get(i);
      if(review.getType().toString().equals(type)) 
        streak++;
      else {
        maxStreak = Math.max(streak, maxStreak);
        streak = 0;
      }
    }
    return Math.max(streak, maxStreak);
  }
  
  private Review[] latestReviews(int entries) {
    Review[] reviewArr = new Review[entries];
    int i = 0;
    
    for(int j = reviewList.size() - 1; j > reviewList.size() - (entries + 1); j--) {
      reviewArr[i] = reviewList.get(j);
      i++;
    }
    
    return reviewArr;
  }
  
  private void displayReviewHeader() {
    if(reviewList.isEmpty()) {
      System.out.println("No review found.\n");
      return;
    } 
    
    System.out.println("-------------------------------------------------------------------------"
        + "---------------------------------------------------"
        + "-----------------------------------"
        + "---------------------------------------------------");
    System.out.printf("%5s %-20s %-50s %-5s %15s %40s %40s\n", "ID", "Title", 
        "Content", "Rating", "Type", "Created", "Last updated");
    System.out.println("-------------------------------------------------------------------------"
        + "---------------------------------------------------"
        + "-----------------------------------"
        + "---------------------------------------------------");
  }
  
  private void displayDataColumn(Review review) {
    if(reviewList.isEmpty()) {
      System.out.println("No review found.\n");
      return;
    } 
    
    System.out.printf("%5s %-20s %-50s %-5s %15s %40s %40s\n", review.getReviewId(), 
          review.getReviewTitle(), review.getReviewContent(), review.getRating(),
          review.getType().toString(), review.getTimestampCreateFormatted(), review.getTimestampUpdateFormatted());
  }
  
  private void displayDataColumn(User user) {
    if(userList.isEmpty()) {
      System.out.println("No user found.\n");
      return;
    }
    
    System.out.printf("%5s %-20s %-10s %-20s \n", user.getUserId(), 
          user.getName(), user.getGender(), user.getPhone());
  }
  
  private void displayDataColumn(Facility facility) {
    if(facilityList.isEmpty()) {
      System.out.println("No facility found.\n");
      return;
    }
    
    System.out.printf("%5s %-20s %-10s %-20s \n", facility.getFacId(), 
          facility.getfacName(), facility.getfacType(), facility.getfacVenue());
  }
  
  private int[] reviewScoreReport() {
    int[] reviewScores = new int[3];
    int i = 0;
    int highestScore = 0;
    int lowestScore = 5;
    int sumScore = 0;
    
    for(int j = 0; j < reviewList.size(); j++) {
      Review review = reviewList.get(j);
      highestScore = Math.max(highestScore, review.getRating());
      lowestScore = Math.min(lowestScore, review.getRating());
      sumScore += review.getRating();
    }
    
    reviewScores[0] = highestScore; // highest score
    reviewScores[1] = lowestScore; // lowest score
    reviewScores[2] = sumScore / reviewList.size(); // average score
    
    return reviewScores;
  }
}
