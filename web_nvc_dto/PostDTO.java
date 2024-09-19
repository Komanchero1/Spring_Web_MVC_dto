package org.example;

public class PostDTO {
    private Long id;
    private String title;
    private String content;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
