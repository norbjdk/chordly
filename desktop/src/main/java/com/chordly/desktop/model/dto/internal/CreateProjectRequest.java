package com.chordly.desktop.model.dto.internal;

import com.chordly.desktop.model.entity.ProjectEntity;

public class CreateProjectRequest {
    private String title;
    private String album;
    private String author;
    private ProjectEntity.Template template;

    public CreateProjectRequest() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ProjectEntity.Template getTemplate() {
        return template;
    }

    public void setTemplate(ProjectEntity.Template template) {
        this.template = template;
    }
}
