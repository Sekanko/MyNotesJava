package pl.oskartarka.MyNotes.service;

import org.springframework.stereotype.Service;
import pl.oskartarka.MyNotes.exceptions.AuthorNotFoundException;
import pl.oskartarka.MyNotes.exceptions.RequiredFieldWereNotFilledException;
import pl.oskartarka.MyNotes.model.Author;
import pl.oskartarka.MyNotes.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void createAuthor(Author author) {
        if (author.getName() == null || author.getName().isEmpty()) {
            throw new RequiredFieldWereNotFilledException("Author name was empty or null");
        }

        authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthor(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            throw new AuthorNotFoundException();
        }
        return author;
    }
}
