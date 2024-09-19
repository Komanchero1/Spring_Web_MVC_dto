package org.example.model;

public class Post {
    private long id;
    private String title;
    private String content;
    private boolean removed;

    public Post() {
    }

    public Post(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.removed = false; // по умолчанию пост не удален
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}