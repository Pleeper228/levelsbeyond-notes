package com.levelsbeyond.db;

import com.levelsbeyond.core.NoteEntity;

import java.util.List;
import java.util.Optional;

public interface NoteDAO {
    List<NoteEntity> getNotes();

    Optional<NoteEntity> findById(Long id);

    NoteEntity createNote(String noteBody);

    NoteEntity updateNote(Long id, String noteBody);

    void deleteNote(Long id);
}
