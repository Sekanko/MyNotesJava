package pl.oskartarka.MyNotes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.oskartarka.MyNotes.model.Note;
import pl.oskartarka.MyNotes.service.NoteService;
@Tag(name = "Notes", description = "Operations on notes")
@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @Operation(summary = "Create new note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Note created"),
            @ApiResponse(responseCode = "404", description = "Author of the note was not found"),
            @ApiResponse(responseCode = "400", description = "Note title or author was empty")
    })
    @PostMapping("/notes")
    public ResponseEntity<Void> createNote(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Note that you want to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Note.class),
                            examples = @ExampleObject(value =
                                    """
                                            {
                                              "title": "REMEMBER",
                                              "content": "Tomorrow is your mother-in-law's birthday",
                                              "author": {
                                                "id": 1,
                                                "name": "Foo Bar"
                                              }
                                            }
                                            
                                            """
                            )
                    )
            )
            @RequestBody
            Note note
    ) {
        noteService.createNote(note);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all notes")
    @ApiResponse(
            responseCode = "200",
            description = "All notes were found and returned",
            content = @Content(
                    schema = @Schema(implementation = Note.class),
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    [
                                      {
                                        "id": 1,
                                        "title": "Shop List",
                                        "content": "Buy milk, eggs, ...",
                                        "createdAt": "2025-05-18T17:37:46.463183",
                                        "author": {
                                          "id": 1,
                                          "name": "Foo Bar"
                                        }
                                      },
                                      {
                                        "id": 2,
                                        "title": "REMEMBER",
                                        "content": "Tomorrow is your mother-in-law's birthday",
                                        "createdAt": "2025-05-18T17:39:13.73781",
                                        "author": {
                                          "id": 1,
                                          "name": "Foo Bar"
                                        }
                                      }
                                    ]
                """
                    )
            )
    )
    @GetMapping("/notes")
    public ResponseEntity<Iterable<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    @Operation(summary = "Find note by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note was found and returned",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Note.class)
                    )),
            @ApiResponse(responseCode = "404", description = "Note was not found",
                content = @Content
            )
    })
    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(
            @Parameter(description = "ID of the note you want to find", required = true)
            @PathVariable
            Long id
    ) {
        return new ResponseEntity<>(noteService.getNote(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete note by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Note was deleted"),
            @ApiResponse(responseCode = "404", description = "Note was not found")
    })
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(
            @Parameter(description = "ID of the note you want to delete", required = true)
            @PathVariable
            Long id
    ) {
        noteService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
