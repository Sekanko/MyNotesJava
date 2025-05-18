package pl.oskartarka.MyNotes.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException() {
        super("Author not found");
    }
}
