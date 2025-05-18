package pl.oskartarka.MyNotes.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Schema(description = "Note title", example = "Shop list")
    private String title;
    @Schema(description = "Note content", example = "Buy milk, eggs, ...")
    @Lob
    private String content;
    @Schema(description = "Note creation date", example = "2025-05-18T12:49:17.038606")
    private LocalDateTime createdAt;

    @Schema(description = "Note author", example = """
            {
                "id": "1",
                "name": "Foo Bar"
            }
            """)
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
