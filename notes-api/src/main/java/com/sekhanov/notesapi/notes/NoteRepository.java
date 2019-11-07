package com.sekhanov.notesapi.notes;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * NoteRepository
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

    
}