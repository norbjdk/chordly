package com.chordly.desktop.model.entity;

import com.chordly.desktop.model.music.Part;
import org.audiveris.proxymusic.ScorePartwise;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectEntity {
    String projectTitle;
    String projectAlbum;
    String projectAuthor;
    String localPath;
    ScorePartwise scorePartwise;
    LocalDateTime createDate;
    private List<Part> parts;


    public enum Template {
        TREBLE, BASS, GRAND
    }

    public ProjectEntity() {
        createDate = LocalDateTime.now();
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectAlbum() {
        return projectAlbum;
    }

    public void setProjectAlbum(String projectAlbum) {
        this.projectAlbum = projectAlbum;
    }

    public String getProjectAuthor() {
        return projectAuthor;
    }

    public void setProjectAuthor(String projectAuthor) {
        this.projectAuthor = projectAuthor;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public ScorePartwise getScorePartwise() {
        return scorePartwise;
    }

    public void setScorePartwise(ScorePartwise scorePartwise) {
        this.scorePartwise = scorePartwise;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "Project: " + projectTitle + " created: " + createDate;
    }
}
