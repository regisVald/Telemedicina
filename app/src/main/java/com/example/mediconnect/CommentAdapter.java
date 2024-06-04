package com.example.mediconnect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CommentAdapter extends FirebaseRecyclerAdapter<Comment, CommentAdapter.CommentViewHolder> {

    public CommentAdapter(@NonNull FirebaseRecyclerOptions<Comment> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CommentViewHolder holder, int position, @NonNull Comment model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCommentContent;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCommentContent = itemView.findViewById(R.id.textViewCommentContent);
        }

        void bind(Comment comment) {
            textViewCommentContent.setText(comment.getContent());
        }
    }
}
