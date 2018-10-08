package com.levelsbeyond.services;

import com.levelsbeyond.api.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    List<Note> getNotes();
    Optional<Note> findById(Long id);
    Note createNote(String noteBody );
    Note updateNote(Long id, Note note);
    void deleteNote(Long id);
}
