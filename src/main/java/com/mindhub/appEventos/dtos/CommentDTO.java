package com.mindhub.appEventos.dtos;

import com.mindhub.appEventos.models.Comment;

public class CommentDTO {
    private Long id;
    private String comment;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
    }

    public CommentDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }
}
