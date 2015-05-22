package mio.kon.sdk.Exception;

/**
 * Created by mio on 15-5-11.
 */
public class ApiException extends RuntimeException {

    public ApiException(String detailMessage) {
        super (detailMessage);
    }
}
