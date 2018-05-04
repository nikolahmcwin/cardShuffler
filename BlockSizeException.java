package week09;
import java.io.*;

/**
* Block Size Exception class.
* COSC241 Group Assignment, April 2017.
* @author Daniela Lemow, Megan Seto and Nikolah Pearce.
*/

public class BlockSizeException extends RuntimeException {

    /** Prints message for BlockSizeException.
     * @param message user sees for exception.
     */
    public BlockSizeException(String message) {
        super(message);
    }
    
    /**Declared to stop warning when compiling using -d. -Xlint in terminal */
    public static final long serialVersionUID = 417;
}
 
