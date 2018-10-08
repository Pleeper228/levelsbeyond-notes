package com.levelsbeyond.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Note {
    @JsonProperty
    private Long id;
    @JsonProperty
    @NotNull
    private String body;

    public Note() {}

    public Note(Long id, String body) {
        this.setId(id);
        this.setBody(body);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) &&
                Objects.equals(body, note.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body);
    }
}
