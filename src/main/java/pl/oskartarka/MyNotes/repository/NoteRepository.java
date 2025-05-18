package pl.oskartarka.MyNotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskartarka.MyNotes.model.entity.Note;
public interface NoteRepository extends JpaRepository<Note, Long> {
}
