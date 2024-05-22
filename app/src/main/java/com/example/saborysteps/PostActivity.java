package com.example.saborysteps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity implements CommentAdapter.OnReplyClickListener {

    private RecyclerView recyclerViewComments;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList;
    private EditText editTextComment;
    private Button buttonPostComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerViewComments = findViewById(R.id.recyclerViewComments);
        editTextComment = findViewById(R.id.editTextComment);
        buttonPostComment = findViewById(R.id.buttonPostComment);

        // Inicializar la lista de comentarios
        commentList = new ArrayList<>();

        // Configurar el RecyclerView
        commentAdapter = new CommentAdapter(commentList, this);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewComments.setAdapter(commentAdapter);

        buttonPostComment.setOnClickListener(v -> postComment());
    }

    private void postComment() {
        String commentText = editTextComment.getText().toString().trim();
        if (!commentText.isEmpty()) {
            String username = "Usuario"; // Aquí puedes obtener el nombre de usuario real del usuario autenticado
            Comment comment = new Comment(commentText, username);
            commentList.add(comment);
            commentAdapter.notifyItemInserted(commentList.size() - 1);
            editTextComment.setText("");
        } else {
            Toast.makeText(this, "El comentario no puede estar vacío", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onReplyClick(Comment comment) {
        showReplyDialog(comment);
    }

    private void showReplyDialog(Comment parentComment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_reply, null);
        EditText editTextReply = view.findViewById(R.id.editTextReply);
        Button buttonPostReply = view.findViewById(R.id.buttonPostReply);

        builder.setView(view);
        AlertDialog dialog = builder.create();

        buttonPostReply.setOnClickListener(v -> {
            String replyText = editTextReply.getText().toString().trim();
            if (!replyText.isEmpty()) {
                String username = "Usuario"; // Aquí puedes obtener el nombre de usuario real del usuario autenticado
                Comment reply = new Comment(replyText, username);
                parentComment.addReply(reply);
                commentAdapter.notifyDataSetChanged(); // Refrescar el adaptador para mostrar la respuesta
                dialog.dismiss();
            } else {
                Toast.makeText(this, "La respuesta no puede estar vacía", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
