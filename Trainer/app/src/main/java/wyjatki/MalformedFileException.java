package wyjatki;

/**
 * Created by Lubo on 04.06.14.
 */
public class MalformedFileException extends Exception {
    Exception mInnerException;

    public MalformedFileException(String message) {
        super(message);
    }

    public MalformedFileException(String message, Exception innerException) {
        super(message);
        mInnerException = innerException;
    }

    public Exception getInnerException() {
        return mInnerException;
    }
}
