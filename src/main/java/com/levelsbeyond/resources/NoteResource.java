package com.levelsbeyond.resources;

import com.levelsbeyond.api.Note;
import com.levelsbeyond.services.NoteService;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/api/notes")
@Produces(MediaType.APPLICATION_JSON)
public class NoteResource {
    private final NoteService noteService;

    public NoteResource(NoteService noteService) {
        this.noteService = noteService;
    }

    @GET
    @UnitOfWork
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Note> findById(@PathParam("id") LongParam id) {
        return noteService.findById(id.get());
    }

    @POST
    @UnitOfWork
    public Note createNote(@Valid Note note) {
        return noteService.createNote(note.getBody());
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Note updateNote(@PathParam("id") LongParam id, Note note) {
        if (note.getId() == null) {
            throw new WebApplicationException("Body id must be must be defined.", 422);
        }
        if (!note.getId().equals(id.get())) {
            throw new WebApplicationException("Params id and body id must match.", 400);
        }

        return noteService.updateNote(id.get(), note);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void deleteNote(@PathParam("id") LongParam id) {
        noteService.deleteNote(id.get());
    }
}
