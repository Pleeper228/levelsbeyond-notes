package com.levelsbeyond.services;

import com.levelsbeyond.api.Note;
import com.levelsbeyond.core.NoteEntity;
import com.levelsbeyond.db.HibernateNoteDAO;
import com.levelsbeyond.db.NoteDAO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NoteServiceImplTest {
    private NoteDAO noteDAO;
    private NoteServiceImpl noteServiceImpl;

    @Before
    public void setup() {
        noteDAO = mock(HibernateNoteDAO.class);
        noteServiceImpl = new NoteServiceImpl(noteDAO);
    }

    @Test
    public void getNotes() {
        List<NoteEntity> notes = new ArrayList<>();
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(1L);
        noteEntity.setBody("Test");
        notes.add(noteEntity);
        when(noteDAO.getNotes()).thenReturn(notes);
        List<Note> returnedNotes = noteServiceImpl.getNotes();
        Note note = new Note(noteEntity.getId(), noteEntity.getBody());
        assertThat(returnedNotes, hasItem(note));
        verify(noteDAO).getNotes();
    }

    @Test
    public void findById() {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(1L);
        noteEntity.setBody("Test");
        when(noteDAO.findById(noteEntity.getId())).thenReturn(Optional.of(noteEntity));
        Optional<Note> returnedNote = noteServiceImpl.findById(noteEntity.getId());
        assertThat(returnedNote.get().getId(), is(noteEntity.getId()));
        assertThat(returnedNote.get().getBody(), is(noteEntity.getBody()));
        verify(noteDAO).findById(noteEntity.getId());
    }

    @Test
    public void createNote() {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(1L);
        noteEntity.setBody("Test");
        when(noteDAO.createNote(noteEntity.getBody())).thenReturn(noteEntity);
        Note returnedNote = noteServiceImpl.createNote(noteEntity.getBody());
        assertThat(returnedNote.getId(), is(noteEntity.getId()));
        assertThat(returnedNote.getBody(), is(noteEntity.getBody()));
        verify(noteDAO).createNote(noteEntity.getBody());
    }

    @Test
    public void updateNote() {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(1L);
        noteEntity.setBody("Test");
        Note note = new Note(1L, "Test");
        when(noteDAO.updateNote(noteEntity.getId(), noteEntity.getBody())).thenReturn(noteEntity);
        Note returnedNote = noteServiceImpl.updateNote(note.getId(), note);
        assertThat(returnedNote.getId(), is(noteEntity.getId()));
        assertThat(returnedNote.getBody(), is(noteEntity.getBody()));
        verify(noteDAO).updateNote(noteEntity.getId(), noteEntity.getBody());
    }

    @Test
    public void deleteNote() {
        Long longId = 1L;
        noteServiceImpl.deleteNote(longId);
        verify(noteDAO).deleteNote(longId);
    }
}
