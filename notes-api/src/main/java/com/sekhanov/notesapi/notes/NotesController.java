package com.sekhanov.notesapi.notes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NotesController
 */
@RestController
@RequestMapping(value = "/api/notes", produces = "application/json")
public class NotesController {

    @GetMapping(value = "/test")
    public String testApi() {
        return "some result";
    }
}