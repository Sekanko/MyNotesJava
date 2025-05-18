package pl.oskartarka.MyNotes.service;

import org.springframework.stereotype.Service;
import pl.oskartarka.MyNotes.exceptions.AuthorNotFoundException;
import pl.oskartarka.MyNotes.exceptions.NoteNotFoundException;
import pl.oskartarka.MyNotes.exceptions.RequiredFieldWereNotFilledException;
import pl.oskartarka.MyNotes.model.Note;
import pl.oskartarka.MyNotes.repository.AuthorRepository;
import pl.oskartarka.MyNotes.repository.NoteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final AuthorRepository authorRepository;


    public NoteService(NoteRepository noteRepository, AuthorRepository authorRepository) {
        this.noteRepository = noteRepository;
        this.authorRepository = authorRepository;
    }

    public void createNote(Note note) {
        if (note.getTitle() == null || note.getTitle().isEmpty() || note.getAuthor() == null) {
            throw new RequiredFieldWereNotFilledException("Note title was empty or null");
        }

        if (note.getAuthor().getId() == null
                || authorRepository.findById(note.getAuthor().getId()).isEmpty()) {
            throw new AuthorNotFoundException();
        }

        note.setCreatedAt(LocalDateTime.now());
        noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNote(Long id) {
        Note note = noteRepository.findById(id).orElse(null);

        if (note == null) {
            throw new NoteNotFoundException();
        }

        return note;
    }

    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)){
            throw new NoteNotFoundException();
        }
        noteRepository.deleteById(id);
    }
}
