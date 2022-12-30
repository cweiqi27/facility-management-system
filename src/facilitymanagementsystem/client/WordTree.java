package facilitymanagementsystem.client;

import facilitymanagementsystem.adt.AVLTree;
import facilitymanagementsystem.adt.BinaryTreeInterface;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 *
 * @author Chan Wei Qi
 */
public final class WordTree {
  private final BinaryTreeInterface<String> tree = new AVLTree<>();
  
  protected WordTree() throws IOException {
    initializeTree();
  }
  
  protected void initializeTree() throws IOException {
    // read from a text file that consists of all the words with alphabets in the english language
    String currentDir = System.getProperty("user.dir");
    Path filePath = Paths.get(currentDir, "public/words_alpha.txt");
    String content = Files.readString(filePath);
    
    // split the content and insert them accordingly into the AVL tree
    for(String word: content.split("\\R")) 
      this.tree.insert(word);
  }
  
  protected BinaryTreeInterface<String> getTree() {
    return this.tree;
  }
  
  protected int getTreeSize() {
    return tree.getSize();
  }
  
  protected void insertWord(String word) {
    tree.insert(word);
  }
  
  protected void removeWord(String word) {
    tree.remove(word);
  }
  
  protected boolean searchWord(String word) {
    return tree.contains(word);
  }
  
  protected int getWordFrequency(String word) {
    return tree.getFrequency(word);
  }
  
  protected String[] getAllWords() {
    Iterator<String> iterator = tree.getIterator();
    String[] words = new String[tree.getSize()];
    int i = 0;
    
    while(iterator.hasNext()) {
      words[i] = iterator.next();
      i++;
    }
    
    return words;
  }
}
