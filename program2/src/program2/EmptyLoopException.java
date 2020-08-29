package program2;


/**
 * EmptyLoopException class is an unchecked exception. It is thrown by methods
 * in the LinkedLoop<E> class when they attempt to act on a loop with size 0.
 *
 * @author Stefen Showers
 */

//The EmptyLoopException is an unchecked exception. It is thrown by some 
//methods (as described in the Loop<E> interface documentation).
public class EmptyLoopException extends RuntimeException{
	public EmptyLoopException() {
        super();
    }
    
    public EmptyLoopException(String msg) {
        super(msg);
    }
}
