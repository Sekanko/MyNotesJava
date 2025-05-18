package pl.oskartarka.MyNotes.exceptions;

public class RequiredFieldWereNotFilledException extends RuntimeException{
    public RequiredFieldWereNotFilledException(String message) {
        super(message);
    }
}
