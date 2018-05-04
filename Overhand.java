package week09;

/**
 * Overhand Interface.
 * COSC241 Group Assignment, April 2017.
 * @author Daniela Lemow, Megan Seto and Nikolah Pearce.
 */

public interface Overhand {

    /**
     * Makes a new deck consisting of a set number of cards.
     * Sets originalDeck to be a copy of this deck.
     * @param  size the size of the intended deck.
     */
    public void makeNew(int size);

    /**
     * Returns current state of deck, changing this won't affect original deck.
     * @return deckCopy, the current deck.
     */
    public int[] getCurrent();

    /**
     * Overhand shuffle method, should be no worse than O(n).
     * Shuffles current state of deck, according to array of block sizes given.
     * @param blocks an array of integers to shuffle that represent blocks.
     * on where to split the deck when shuffling it.
     */
    public void shuffle (int[] blocks);

    /**
     * Calculates and returns the minimum number of shuffles, using one set.
     * of blocks, until the deck returns to it's original state.
     * @param blocks an array of integers that represent blocks.
     * on where to split the deck when shuffling it.
     * @return the number of minimum shuffles.
     */
    public int order(int[] blocks);

    /**
     * Counts and returns the number of unbroken pairs in the shuffled deck.
     * @return number of unbroken pairs in current deck state.
     */
    public int unbrokenPairs();
                             
}
