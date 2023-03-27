/**
 * The Main class contains the main method that handles the menus and user inputs to get the program working
 * @author Anuj Ketkar
 */

import java.io.FileNotFoundException;
import java.util.*;

public class Main 
{
  public static void main(String[] args) throws FileNotFoundException {
    Scanner input = new Scanner(System.in);
    System.out.println("Would you like to create your own study set? (y/n)");
    String ownSet = input.next();
    System.out.println("How many items in the study set");
    boolean exit = false;
    int size = 0;
    while(!exit) {
      if (input.hasNextInt())
      {
        size = input.nextInt();
        if(size < 1 || size > 1000)
        {
          System.out.println("Please choose a number from 1-1000");
        }
        else {
          exit = true;
        }
      }
      else {
        System.out.println("Incorrect format try again");
        input.next();
      }
    }
    StudySet set = new StudySet();
    set.createList(ownSet.toLowerCase().equals("yes") || ownSet.equals("y"), size);
    boolean quit = false;
    while(!quit)
    {
      System.out.println("\nMENU:\n 1. Study Set\n 2. Take Quiz on Set\n " +
              "3. View Favorites\n 4. New List \n 5. Quit program");
      int menu = 0;
      boolean exit2 = false;
      while(!exit2) {
        if (input.hasNextInt()) {
          menu = input.nextInt();
          if(menu < 1 || menu > 5)
          {
            System.out.println("Please select a number from 1-5");
          }
          else {
            exit2 = true;
          }
        } else {
          System.out.println("Incorrect format try again");
          input.next();
        }
      }
      if(menu == 1)
        set.runStudy();
      if(menu == 2)
        set.runQuiz();
      if(menu == 3)
        set.viewFavorites();
      if(menu == 4)
      {
        System.out.println("Would you like to create your own study set? (y/n)");
        String newSelfMade = input.next();
        System.out.println("How many items in the study set");
        int newSize = input.nextInt();
        set.createList(newSelfMade.toLowerCase().equals("yes") || newSelfMade.equals("y"), newSize);
      }
      if(menu==5)
        quit = true;
    }
  }
}

