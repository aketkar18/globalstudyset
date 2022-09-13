/**
 * The StudySet class creates and manages a queue of cards
 * that allows users to study by viewing or taking a quiz
 * @author Anuj Ketkar
 * Teacher Name: Mrs. Ishman
 * Period: 0
 * Due Date: 5-11-2020
 */

import java.io.FileNotFoundException;
import java.util.*;

public class StudySet
{
    private Queue<Card> cardList;
    private Set<Card> favCards;

    /**
     * Creates StudySet object
     */
    public StudySet()
    {
        cardList = new LinkedList<Card>();
        favCards = new TreeSet<Card>();
    }

    /**
     * Creates a list of cards for the study set
     * @param selfMade whether or not the student will be entering their own info
     * @param size the size of the study set
     * @throws FileNotFoundException
     */
    public void createList(boolean selfMade, int size) throws FileNotFoundException {
        cardList.clear();
        Scanner scan = new Scanner(System.in);
        if(selfMade)
        {
            while(size > 0)
            {
                System.out.print("Enter text for front: \n");
                String front = scan.next();
                System.out.print("Enter text for back: \n");
                String back = scan.next();
                cardList.add(new Card(front,back));
                System.out.print("Card with front: \"" + front + "\" and back: \"" + back + "\" created \n \n");
                size--;
            }
        }
        else
        {
            System.out.println("Enter language for front");
            System.out.println("1: English\n2: Spanish\n3: Japanese\n"+
                    "4: Indonesian\n5: Korean\n");
            boolean exit = false;
            int lang1 = 0;
            while(!exit) {
                if (scan.hasNextInt())
                {
                    lang1 = scan.nextInt();
                    if(lang1 < 1 || lang1 > 5)
                    {
                        System.out.println("Please select a number from 1-5");
                    }
                    else {
                        exit = true;
                    }
                }
                else {
                    System.out.println("Incorrect format try again");
                    scan.next();
                }
            }
            System.out.println("Enter language for back");
            System.out.println("1: English\n2: Spanish\n3: Japanese\n"+
                    "4: Indonesian\n5: Korean\n");
            int lang2 = 0;
            exit = false;
            while(!exit) {
                if (scan.hasNextInt())
                {
                    lang2 = scan.nextInt();
                    if(lang2 < 1 || lang2 > 5)
                    {
                        System.out.println("Please select a number from 1-5");
                    }
                    else {
                        exit = true;
                    }
                }
                else {
                    System.out.println("Incorrect format try again");
                    scan.next();
                }
            }

            while(size > 0)
            {
                cardList.add(new Card(lang1,lang2));
                size--;
            }
            System.out.println("Successfully created random study set");
        }
    }

    /**
     * Runs the mode where users can look at cards in the study set
     * Command list will be explained in main class
     */
    public void runStudy()
    {
        System.out.println("\nTo study your flashcards: \n flip: see back and go to next card \n " +
                "skip: skip this card without seeing back \n fav: store this card in your favorites \n edit: edit this card" +
                "\n remove: remove this card");
        Queue<Card> copy = new LinkedList<>(cardList);
        Scanner scan = new Scanner(System.in);
        while (!copy.isEmpty())
        {
            System.out.println("\n" + copy.peek().getFront());
            String command = scan.next();
            if(command.equals("flip"))
            {
                System.out.println(copy.peek().getBack());
                copy.remove();
            }
            else if(command.equals("skip"))
            {
                copy.remove();
            }
            else if(command.equals("fav"))
            {
                favCards.add(copy.peek());
                System.out.println("Added to favorites");
            }
            else if(command.equals("edit"))
            {
                System.out.println("Enter front");
                String front = scan.next();
                System.out.println("Enter back");
                String back = scan.next();

                copy.peek().editCard(front,back);
            }
            else if(command.equals("remove"))
            {
                for(int x = 0; x < cardList.size(); x++ )
                {
                    Card card = cardList.remove();
                    if(!card.equals(copy.peek()))
                    {
                        cardList.add(card);
                    }
                }
                copy.remove();
                System.out.println("Successfully removed \n");
            }
            else
            {
                System.out.println("Invalid command, try again.");
            }
            if(copy.isEmpty())
                System.out.println("\nEnd of study set");
        }
    }

    /**
     * Runs the mode where users can take a quiz on the study set
     */
    public void runQuiz()
    {
        Queue<Card> copy = new LinkedList<>(cardList);
        Scanner scan = new Scanner(System.in);
        while (!copy.isEmpty())
        {
            System.out.println(copy.peek().getFront());
            System.out.println("Type whats on the back of the card");
            copy.peek().setAccessed();
            String answer = scan.nextLine();
            if(answer.equals(copy.peek().getBack()))
            {
                System.out.println("Correct! \n");
                copy.remove();
            }
            else
            {
                System.out.println("Incorrect. The correct answer was: ");
                System.out.println(copy.peek().getBack() + "\n");
                copy.peek().setFailure();
                copy.remove();
            }
        }

    }

    /**
     * Views the card kept as favorites in the study set
     */
    public void viewFavorites()
    {
        if(favCards.isEmpty())
        {
            System.out.println("There are no items in your favorites \n");
        }
        else
        {
            System.out.println("Favorites: \n");
            Iterator<Card> iter = favCards.iterator();
            while(iter.hasNext())
            {
                Card card = iter.next();
                System.out.println(card.toString());
                System.out.println("Accessed: " + card.getAccessed());
                System.out.println("Failure: " + card.getFailure());
                System.out.printf("Failure Rate: %.2f %n", card.getFailureRate());
            }
        }
    }
}