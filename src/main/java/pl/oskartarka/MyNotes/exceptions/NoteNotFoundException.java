package pl.oskartarka.MyNotes.exceptions;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException() {
        super("Note not found");
    }
}
