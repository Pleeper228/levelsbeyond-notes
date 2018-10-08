package com.levelsbeyond.services;

import com.levelsbeyond.api.Note;
import com.levelsbeyond.core.NoteEntity;
import com.levelsbeyond.db.NoteDAO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NoteServiceImpl implements NoteService {
    private final NoteDAO noteDAO;

    public NoteServiceImpl(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    public List<Note> getNotes() {
        List<NoteEntity> notes = noteDAO.getNotes();
        return notes.stream()
            .map(this::toNote)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Note> findById(Long id) {
        Optional<NoteEntity> noteEntity = noteDAO.findById(id);
        return noteEntity.map(this::toNote);
    }

    @Override
    public Note createNote(String noteBody) {
        NoteEntity noteEntity = noteDAO.createNote(noteBody);
        return toNote(noteEntity);
    }

    @Override
    public Note updateNote(Long id, Note note) {
        NoteEntity noteEntity = noteDAO.updateNote(id, note.getBody());
        return toNote(noteEntity);
    }

    @Override
    public void deleteNote(Long id) {
        noteDAO.deleteNote(id);
    }

    private Note toNote(NoteEntity noteEntity) {
        return new Note(noteEntity.getId(), noteEntity.getBody());
    }
}
