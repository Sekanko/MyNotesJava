package pl.oskartarka.MyNotes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.oskartarka.MyNotes.model.entity.Author;
import pl.oskartarka.MyNotes.model.request.AuthorRequest;
import pl.oskartarka.MyNotes.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @Operation(summary = "Create new author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author created"),
            @ApiResponse(responseCode = "400", description = "Author name was empty")
    })
    @PostMapping("/authors")
    public ResponseEntity<Void> createAuthor(@RequestBody AuthorRequest author) {
        authorService.createAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all authors")
    @ApiResponse(responseCode = "200", description = "All authors were found and returned")
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @Operation(summary = "Get author by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author was found and returned"),
            @ApiResponse(responseCode = "404", description = "Author was not found")
    })
    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthor(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
