package com.baghdadit.simplehttp.config;

import java.io.File;

public class Attachment {

    private File file;
    private String name;
    private String mediaType;


    public Attachment(File file, String name, String mediaType) {
        this.file = file;
        this.name = name;
        this.mediaType = mediaType;
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getMediaType() {
        return mediaType;
    }
}
