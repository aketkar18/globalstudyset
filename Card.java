/**
 * The Card class creates an object that holds a strings for a front side and back side
 * that are the same phrase in different languages using data.csv
 * @author Anuj Ketkar
 * Teacher Name: Mrs. Ishman
 * Period: 0
 * Due Date: 5-07-2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Card implements Comparable<Card>
{
    private String front;
    private String back;
    private int numAccessed;
    private int numFailure;

    /**
     * Constructs a card object with user given front and back
     * @param front the phrase on the front of the card
     * @param back the phrase on the back which is a translation
     */
    public Card(String front, String back)
    {
        this.front = front;
        this.back = back;
        numAccessed = 0;
        numFailure = 0;
    }

    /**
     * Constructs a random card object given integers for the language
     * @param lang1 the language code for the front of the card
     * @param lang2 the language code for the back
     * @throws FileNotFoundException
     */
    public Card(int lang1, int lang2) throws FileNotFoundException {
        /*
         * Language codes:
         * 1: English
         * 2: Spanish
         * 3: Japanese
         * 4: Indonesian
         * 5: Korean
         */
        numAccessed = 0;
        numFailure = 0;
        File dataFile = new File("data.csv");
        Scanner inputSteam = new Scanner(dataFile);
        int rand = (int) (Math.random() * 1000) + 2;
        String line = new String();
        for(int k = 0; k < rand; k++)
        {
            line = inputSteam.nextLine();
        }
        String[] phrase =  line.split(",");
        front = phrase[lang1-1];
        back = phrase[lang2-1];
        inputSteam.close();
    }

    public void editCard(String newFront, String newBack)
    {
        front = newFront;
        back = newBack;
    }

    /**
     * Gets the front of the card
     * @return the front
     */
    public String getFront()
    {
        return front;
    }

    /**
     * Gets the front of the card
     * @return the back
     */
    public String getBack()
    {
        return back;
    }

    /**
     * Increments the number of times accessed
     */
    public void setAccessed()
    {
        numAccessed++;
    }

    /**
     * Gets the number of times accessed
     * @return the times accessed
     */
    public int getAccessed()
    {
        return numAccessed;
    }

    /**
     * Increments the number of times failed
     */
    public void setFailure()
    {
        numFailure++;
    }

    /**
     * Gets the number of times failed
     * @return the times failed
     */
    public int getFailure()
    {
        return numFailure;
    }

    /**
     * Gets the failure rate
     * @return a percentage of times failed
     */
    public double getFailureRate()
    {
        return ((double) numFailure / numAccessed) * 100;
    }

    /**
     * Checks if card is the same as other
     * @param c the card to compare it to
     * @return true if the cards have the same front and back
     */
    public boolean equals(Card c)
    {
        if (this.getFront().equals(c.getFront()) && this.getBack().equals(c.getBack()))
            return true;
        else
            return false;
    }

    /**
     * Compares the cards on the basis of failure rate
     * @param c the card to compare it to
     * @return 0 if equal, 1 if this is greater than the other, and -1 if this is less than the other
     */
    @Override
    public int compareTo(Card c)
    {
       if(this.equals(c))
       {
           return 0;
       }
       else if(this.getFailureRate() > c.getFailureRate())
       {
           return 1;
       }
       else
       {
           return -1;
       }
    }

    /**
     * Displays a cards as a string in the format: front || back
     * @return the card in string form
     */
    public String toString()
    {
        return String.format("%s || %s",front,back);
    }
}