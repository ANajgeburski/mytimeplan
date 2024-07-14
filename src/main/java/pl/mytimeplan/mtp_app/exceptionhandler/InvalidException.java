package pl.mytimeplan.mtp_app.exceptionhandler;

public class InvalidException extends RuntimeException {

    public InvalidException(Integer id) {
        super("id cannot be less than 1  " + id);
    }
}
