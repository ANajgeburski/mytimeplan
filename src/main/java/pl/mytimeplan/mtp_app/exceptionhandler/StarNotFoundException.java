package pl.mytimeplan.mtp_app.exceptionhandler;

public class StarNotFoundException extends RuntimeException{

    public StarNotFoundException(Integer id) {
        super("Could not find star " + id);
    }
}
