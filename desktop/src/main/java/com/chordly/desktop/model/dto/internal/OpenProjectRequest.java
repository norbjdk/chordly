package com.chordly.desktop.model.dto.internal;

import java.io.File;

public class OpenProjectRequest {
    private File file;

    public OpenProjectRequest() {}

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
