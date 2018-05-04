package week09;
import java.util.*;

/**
* Overhand Shuffler Application class.
* COSC241 Group Assignment, April 2017.
* @author Daniela Lemow, Megan Seto and Nikolah Pearce.
*/

public class OverhandApp {
    
    /**
     * Main method.
     * @param args command line (not used).
     */ 
    public static void main(String[] args) {
        newDeck = new OverhandShuffler();
        StringBuilder s = new StringBuilder();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            handleLine(s, input.nextLine());
        }
    }


    /**static variable for OverhandShuffler called newDeck. */
    private static OverhandShuffler newDeck;


    /** Makes an array containing numbers from 0 to input number.
     * @param input from scanner/user.
     * @return nums array with numbers from 0-n.
     */
    private static int[] getNums(Scanner input) {
        List<Integer> numlist = new ArrayList<Integer>();
        while (input.hasNextInt()) {
            numlist.add(input.nextInt());
        }
        int[] nums = new int[numlist.size()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = numlist.get(i);
        }
        return nums;
    }

    /** Manipulates deck based on abbreviated first letter input which.
     * represents a full command.
     * @param str builds string representation of card deck.
     * @param input from user manipulates deck.
     */
    public static void handleLine(StringBuilder str, String input) {
        Scanner scan = new Scanner(input);
        String msg = "Number of shuffles: ";
        if (scan.hasNext()) {
            int[] blocks;
            String command = scan.next();
            switch (command) {
                case "make-new": case "m": //Makes deck of cards w/ given size 
                    if (scan.hasNextInt()) {
                        int size = scan.nextInt();
                        newDeck.makeNew(size);
                    }
                    break;
                case "print": case "p": //Prints string showing current deck
                    int[] currentDeck = newDeck.getCurrent();
                    str.append("[");
                    for (int i = 0; i < currentDeck.length; i++) {
                        if (i == currentDeck.length-1 ) {
                            str.append(currentDeck[i]);
                        } else {
                            str.append(currentDeck[i]+ ", ");
                        }
                    }
                    str.append("]");
                    System.out.println(str);
                    str.setLength(0);
                    break;
                case "shuffle": case "s": //Does overhand shuffle on card deck
                    blocks  = getNums(scan);
                    newDeck.shuffle(blocks);
                    break;
                case "order": case "o": // Print the result of calling order()
                    blocks  = getNums(scan);
                    System.out.println(newDeck.order(blocks));
                    break;
                case "unbroken-pairs": case "u": //Prints num of unbroken pairs
                    System.out.println(newDeck.unbrokenPairs());
                    break;
                case "random-shuffle": case "r": //Randomly o-h-shuffles deck
                    newDeck.randomShuffle();
                    break;
                case "count-shuffles": case "c": //Prints countShuffles() result
                    if (scan.hasNextInt()) {
                        int numUnbrokenPairs = scan.nextInt();
                        System.out.println(msg + newDeck.countShuffles
                                           (numUnbrokenPairs));
                    }
                    break;
                case "load": case "l": // Load deck of cards with array number
                    int[] cards = getNums(scan);
                    newDeck.load(cards);
                    break;
                case "try-repeat": case "t": // Call the tryRepeat() method
                    int[] newStateOfDeck = newDeck.tryRepeat();
                    str.append("[");
                    for (int i = 0; i < newStateOfDeck.length; i++) {
                        if (i == newStateOfDeck.length-1 ) {
                            str.append(newStateOfDeck[i]);
                        } else {
                            str.append(newStateOfDeck[i]+ ", ");
                        }
                    }
                    str.append("]");
                    System.out.println(str);
                    str.setLength(0);
                    break;
            }
        }
    }
}
