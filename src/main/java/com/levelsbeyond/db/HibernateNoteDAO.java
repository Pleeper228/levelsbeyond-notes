package com.levelsbeyond.db;

import com.levelsbeyond.core.NoteEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

public class HibernateNoteDAO extends AbstractDAO<NoteEntity> implements NoteDAO {
    public HibernateNoteDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<NoteEntity> getNotes() {
        return list((Query<NoteEntity>) namedQuery("com.levelsbeyond.core.NoteEntity.getNotes"));
    }

    @Override
    public Optional<NoteEntity> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    @Override
    public NoteEntity createNote(String noteBody) {
        NoteEntity noteEntity = new NoteEntity(noteBody);
        return persist(noteEntity);
    }

    @Override
    public NoteEntity updateNote(Long id, String noteBody) {
        NoteEntity noteEntity = getNoteById(id);
        noteEntity.setBody(noteBody);
        currentSession().update(noteEntity);
        return noteEntity;
    }

    @Override
    public void deleteNote(Long id) {
        currentSession().delete(getNoteById(id));
    }

    private NoteEntity getNoteById(Long id) throws NotFoundException {
        Optional<NoteEntity> noteEntity = findById(id);
        if (noteEntity.isPresent()) {
            return noteEntity.get();
        } else {
            throw new NotFoundException(String.format("Note %d not found.", id));
        }
    }
}
