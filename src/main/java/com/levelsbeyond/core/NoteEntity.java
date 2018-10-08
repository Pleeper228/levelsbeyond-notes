package com.levelsbeyond.core;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "note")
@NamedQueries({
        @NamedQuery(name = "com.levelsbeyond.core.NoteEntity.getNotes",
                query = "select e from NoteEntity e")
})
public class NoteEntity {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "body")
    private String body;

    public NoteEntity() {
    }

    public NoteEntity(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
