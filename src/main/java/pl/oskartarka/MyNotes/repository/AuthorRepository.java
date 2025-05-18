package pl.oskartarka.MyNotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskartarka.MyNotes.model.Author;
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
