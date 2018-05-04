package week09;
import java.util.*;

/**
 * Overhand Shuffler class.
 * COSC241 Group Assignment, April 2017.
 * @author Daniela Lemow, Megan Seto and Nikolah Pearce.
 */

public class OverhandShuffler implements Overhand {

    /** Array of ints representing a copy of the original deck, to compare. */
    private int[] originalDeck = {};
    
    /** Array of integers representing a deck of cards. */
    private int[] deck = {};    
    
    /**
     * Makes a new deck consisting of a set number of cards.
     * Sets originalDeck to be a copy of this deck.
     * @param  size the size of the intended deck.
     */
    public void makeNew(int size) {
        deck = new int[size];
        originalDeck = new int[size];
        for (int i = 0; i < size; i++) {
            deck[i] = i;
            originalDeck[i] = i;
        }
    }

    /**
     * Returns current state of deck, changing this won't affect original deck.
     * @return deckCopy, the current deck.
     */
    public int[] getCurrent() {
        int[] deckCopy = new int[deck.length];
        for (int i = 0; i < deck.length; i++) {
            deckCopy[i] = deck[i];
        }
        return deckCopy;
    }

    /**
     * Checks array of blocks, returns true if all blocks are positive ints.
     * and the total num of blocks equal to the total size of deck.
     * @param  blocks the block array to be checked.
     * @return true if blocks satisfy all requirement.
     */
    public boolean correctBlocks(int[] blocks) {
        int totalBlocks = 0;
        boolean check = true;
        for (int i = 0; i < blocks.length; i++) {
            totalBlocks += blocks[i];
            if (blocks[i] <=  0) {
                check = false;
            }
        }
        return (totalBlocks == this.deck.length) && check;
    }
    
    /**
     * Overhand shuffle method, should be no worse than O(n).
     * Shuffles current state of deck, according to array of block sizes given.
     * @param blocks an array of integers to shuffle that represent blocks.
     * on where to split the deck when shuffling it.
     */
    public void shuffle(int[] blocks) {
        if (!correctBlocks(blocks)){
            throw new BlockSizeException("Block array incorrect, has -ve num " +
                                         "or doesnt add to total deck size.");
        }
        
        int[] emptyArray = new int[deck.length];
        int currentIndex = 0;  //Index of where last block split in deck array
        int indexToAddAt = deck.length; //Index of last card added to emptyCopy
        
        for (int block : blocks) {
            indexToAddAt -= block; //Set index to add less than FAR END
            System.arraycopy(deck, currentIndex, emptyArray,
                             indexToAddAt, block);
            currentIndex += block; //Decrease currentIndex by blocklen just done
        }
        this.deck = emptyArray; //Set the actual deck to be the shuffled
    }
    
    
    /**
     * Calculates and returns the minimum number of shuffles, using one set.
     * of blocks, until the deck returns to it's original state.
     * @param blocks an array of integers that represent blocks.
     * on where to split the deck when shuffling it.
     * @return the number of minimum shuffles.
     */
    public int order(int[] blocks) {
        if (!correctBlocks(blocks)){
            throw new BlockSizeException("Block array incorrect, has -ve num" +
                                         " or doesnt add to total deck size.");
        }
        int[] copyOfDeckInCurrentState = {};
        int timesShuffled = 1 ;
        copyOfDeckInCurrentState = this.deck.clone();
        this.deck = this.originalDeck.clone();
        shuffle(blocks);
        while (!Arrays.equals(this.originalDeck, this.deck)) {
            //passes in original deck & DECK THATS BEEN SHUFFLED ONCE by us
            shuffle(blocks);
            timesShuffled++;
        }
        this.deck = copyOfDeckInCurrentState.clone();
        return timesShuffled;
    }

    

    /**
     * Counts and returns the number of unbroken pairs in the shuffled deck.
     * @return number of unbroken pairs in current deck state.
     */
    public int unbrokenPairs() {
        int numUnbrokenPairs = 0;
        for (int i = 1; i < deck.length; i++) {
            if (deck[i] == (deck[i-1]+1)) {
                numUnbrokenPairs++;
            }
        }
        return numUnbrokenPairs; 
    }


    
    /**
     * Repeats previous sequence of shuffles on the deck.
     * @return the array of integers that is the new current state.
     * after repeating the last sequence of shuffles.
     */
    public int[] tryRepeat() {
        int[] newState = new int[deck.length];
        int card;
        for (int i = 0; i < deck.length; i++) {
            card = deck[i];
            newState[i] = deck[card];
        }
        return newState;
    }


   
    /**
     * Performs a random overhand shuffle on the deck of cards.
     * using a break probability of 0.1.
     */
    public void randomShuffle() {
        
        int[] emptyArray = new int[deck.length];
        int currentIndex = 0;  //Index of where last block split in deck array
        int indexToAddAt = deck.length; //Index of last card added to emptyCopy
        int lastBreakIndex = 0; //ndex of deck where a break last occured
        int blockSize = 0; //Holds created block size determined by probability
        
        for (int i = 0; i < deck.length; i++) {
            double probability = Math.random(); //Holds rand double between 0-1
            final double prob = 0.1;
            blockSize++;
            currentIndex++;
            if (probability < prob || i == deck.length-1) {
                // Only enter if probability is less than 0.1
                // or if on the last iteration (regardless of probability).
                indexToAddAt -= blockSize;
                if (lastBreakIndex == 0) {
                    currentIndex -= blockSize;
                } else {
                    currentIndex -= blockSize-1;
                }
                // copy subarray of deck to new location in emptyArray
                System.arraycopy(this.deck, currentIndex,
                                 emptyArray, indexToAddAt, blockSize);
                lastBreakIndex += blockSize;
                blockSize = 0; // reset blockSize
                currentIndex = i;
            }
        }
        this.deck = emptyArray; 
    }

    /**
     * Returns number of random shuffles needed before unbroken pairs less than.
     * The given parameter.
     * @param unbrokenPairs min number of broken pairs allowed. 
     * @return int representing shuffles needed to break pairs
     */
    public int countShuffles(int unbrokenPairs) {
        int timesShuffled = 0;
        int currentUnbrokenPairs = unbrokenPairs();
        while (currentUnbrokenPairs > unbrokenPairs) {
            randomShuffle();
            currentUnbrokenPairs = unbrokenPairs();
            timesShuffled++;
        }
        return timesShuffled;
    }

    /**
     * Loads the deck of cards using the given numbers passed in.
     * @param cards an array of integers to load into the deck of cards.
     */
    public void load(int[] cards) {
        this.deck = new int[cards.length];
        for (int i = 0; i < cards.length; i++) {
            this.deck[i] = cards[i];
        }
        this.originalDeck = new int[cards.length];
        for (int i = 0; i < cards.length; i++) {
            originalDeck[i] = i;
        }
    }
}
