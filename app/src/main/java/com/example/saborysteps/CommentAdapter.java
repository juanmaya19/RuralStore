package com.example.saborysteps;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<Comment> commentList;
    private OnReplyClickListener onReplyClickListener;

    public CommentAdapter(List<Comment> commentList, OnReplyClickListener onReplyClickListener) {
        this.commentList = commentList;
        this.onReplyClickListener = onReplyClickListener;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView commentTextView;
        private TextView usernameTextView;
        private Button buttonReply;
        private RecyclerView recyclerViewReplies;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            commentTextView = itemView.findViewById(R.id.commentTextView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            buttonReply = itemView.findViewById(R.id.buttonReply);
            recyclerViewReplies = itemView.findViewById(R.id.recyclerViewReplies);
        }

        public void bind(Comment comment) {
            commentTextView.setText(comment.getText());
            usernameTextView.setText(comment.getUsername());

            buttonReply.setOnClickListener(v -> {
                onReplyClickListener.onReplyClick(comment);
            });

            if (!comment.getReplies().isEmpty()) {
                recyclerViewReplies.setVisibility(View.VISIBLE);
                recyclerViewReplies.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
                recyclerViewReplies.setAdapter(new CommentAdapter(comment.getReplies(), onReplyClickListener));
            } else {
                recyclerViewReplies.setVisibility(View.GONE);
            }
        }
    }

    public interface OnReplyClickListener {
        void onReplyClick(Comment comment);
    }
}
