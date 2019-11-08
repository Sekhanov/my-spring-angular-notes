package com.sekhanov.notesapi.notes;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * NotesController
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/notes", produces = "application/json")
public class NotesController {

    @Autowired
    private NoteService noteService;

    // @GetMapping(value = "/test")
    // public String testApi() {
    //     return "some result";
    // }

    @GetMapping
    public ResponseEntity<List<Note>>  getAllNotes() {
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNodeById(@PathVariable("id") Long id) {
        Optional<Note> node = noteService.findById(id);
        if(node.isPresent()) {
            return ResponseEntity.ok(node.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Note> createTodo(@RequestBody Note note) {
        Note savedNote = noteService.save(note);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedNote.getId())
        .toUri();
        return ResponseEntity.created(location).body(savedNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody Note note) {
        Optional<Note> optionalNote = noteService.findById(note.getId());
        if(!optionalNote.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        noteService.save(note);
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable("id") Long id) {
        noteService.delete(id);
        return ResponseEntity.ok().build();
    }


}