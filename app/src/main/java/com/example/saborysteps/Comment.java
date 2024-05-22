package com.example.saborysteps;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    private int id;
    private String text;
    private String username;
    private int parentId;
    private List<Comment> replies;

    // Constructor para comentarios que se obtienen de la base de datos
    public Comment(int id, String text, String username, int parentId) {
        this.id = id;
        this.text = text;
        this.username = username;
        this.parentId = parentId;
        this.replies = new ArrayList<>();
    }

    // Constructor para crear un nuevo comentario raíz (sin padre)
    public Comment(String text, String username) {
        this.text = text;
        this.username = username;
        this.parentId = -1; // Indica que es un comentario raíz
        this.replies = new ArrayList<>();
    }

    // Constructor para crear una nueva respuesta a un comentario existente
    public Comment(String text, String username, int parentId) {
        this.text = text;
        this.username = username;
        this.parentId = parentId;
        this.replies = new ArrayList<>();
    }

    public void addReply(Comment reply) {
        replies.add(reply);
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getParentId() { return parentId; }
    public void setParentId(int parentId) { this.parentId = parentId; }
    public List<Comment> getReplies() { return replies; }
}
