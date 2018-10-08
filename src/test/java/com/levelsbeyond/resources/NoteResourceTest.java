package com.levelsbeyond.resources;

import com.levelsbeyond.api.Note;
import com.levelsbeyond.services.NoteService;
import io.dropwizard.jersey.params.LongParam;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NoteResourceTest {
    private NoteService noteService;
    private NoteResource noteResource;

    @Before
    public void setup() {
        noteService = mock(NoteService.class);
        noteResource = new NoteResource(noteService);
    }

    @Test
    public void getNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1L, "Test"));
        when(noteService.getNotes()).thenReturn(notes);
        List<Note> returnedNotes = noteResource.getNotes();
        assertThat(returnedNotes, is(notes));
        verify(noteService).getNotes();
    }

    @Test
    public void findById() {
        Note note = new Note(1L, "Test");
        when(noteService.findById(note.getId())).thenReturn(Optional.of(note));
        LongParam longParamId = new LongParam(note.getId().toString());
        Optional<Note> returnedNote = noteResource.findById(longParamId);
        assertThat(returnedNote.get(), is(note));
        verify(noteService).findById(note.getId());
    }

    @Test
    public void createNote() {
        Note note = new Note(1L, "Test");
        when(noteService.createNote(note.getBody())).thenReturn(note);
        Note returnedNote = noteResource.createNote(note);
        assertThat(returnedNote, is(note));
        verify(noteService).createNote(note.getBody());
    }

    @Test
    public void updateNote() {
        Note note = new Note(1L, "Test");
        when(noteService.updateNote(note.getId(), note)).thenReturn(note);
        LongParam longParamId = new LongParam(note.getId().toString());
        Note returnedNote = noteResource.updateNote(longParamId, note);
        assertThat(returnedNote, is(note));
        verify(noteService).updateNote(note.getId(), note);
    }

    @Test
    public void updateNote_nullId() {
        boolean didCatch = false;
        LongParam longParam = new LongParam("1");
        try {
            noteResource.updateNote(longParam, new Note());
        } catch (WebApplicationException e) {
            didCatch = true;
            assertThat(e.getResponse().getStatus(), is(422));
        }
        assertThat(didCatch, is(true));
    }

    @Test
    public void updateNote_paramsIdAndIdMatch() {
        boolean didCatch = false;
        Note note = new Note(1L, "Test");
        LongParam longParamId = new LongParam("2");
        try {
            noteResource.updateNote(longParamId, note);
        } catch (WebApplicationException e) {
            didCatch = true;
            assertThat(e.getResponse().getStatus(), is(400));
        }
        assertThat(didCatch, is(true));
    }

    @Test
    public void deleteNote() {
        LongParam longParamId = new LongParam("1");
        noteResource.deleteNote(longParamId);
        verify(noteService).deleteNote(longParamId.get());
    }
}
