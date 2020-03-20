package com.example.thoughts;

import java.util.UUID;

public class Thought {

    private String mTitle;
    private String mContent;
    private UUID mId;

    public Thought() {
        this(UUID.randomUUID());
    }

    public Thought(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
