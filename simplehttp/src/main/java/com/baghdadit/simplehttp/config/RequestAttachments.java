package com.baghdadit.simplehttp.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RequestAttachments {

    private List<Attachment> attachments;

    public RequestAttachments() {
        this.attachments = new ArrayList<>();
    }

    public RequestAttachments add(File file, String name, String memeType) {
        this.attachments.add(new Attachment(file, name, memeType));
        return this;
    }

    public List<Attachment> all() {
        return this.attachments;
    }

}
