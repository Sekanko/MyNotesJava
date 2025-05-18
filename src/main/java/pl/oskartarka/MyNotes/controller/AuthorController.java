package pl.oskartarka.MyNotes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.oskartarka.MyNotes.model.Author;
import pl.oskartarka.MyNotes.model.Note;
import pl.oskartarka.MyNotes.service.AuthorService;

import java.util.List;
@Tag(name = "Authors", description = "Operations on authors")
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
    public ResponseEntity<Void> createAuthor(@RequestBody Author author) {
        authorService.createAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all authors")
    @ApiResponse(responseCode = "200", description = "All authors were found and returned",
            content = @Content(
                    schema = @Schema(implementation = Note.class),
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                            [
                                              {
                                                "id": 1,
                                                "name": "Foo Bar"
                                              },
                                              {
                                                "id": 2,
                                                "name": "Bar Bazz"
                                              }
                                            ]
                                            """
                    )
            )
    )
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @Operation(summary = "Get author by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author was found and returned",
                    content = @Content(
                            schema = @Schema(implementation = Note.class),
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 1,
                                              "name": "Bar Bazz"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Author was not found",content = @Content)
    })
    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
    }
}
