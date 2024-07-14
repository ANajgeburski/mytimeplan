package pl.mytimeplan.mtp_app.exceptionhandler;

public class NoMatchingStarsException extends RuntimeException{

    public NoMatchingStarsException() {
        super("No stars match the request");
    }
}
