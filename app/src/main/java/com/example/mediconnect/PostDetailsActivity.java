package com.example.mediconnect;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostDetailsActivity extends AppCompatActivity {
    private TextView textViewPostTitle;
    private TextView textViewPostContent;
    private RecyclerView recyclerViewComments;
    private EditText editTextComment;
    private Button buttonPostComment;
    private DatabaseReference postReference;
    private DatabaseReference commentsReference;
    private CommentAdapter commentAdapter;
    private String postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        textViewPostTitle = findViewById(R.id.textViewPostTitle);
        textViewPostContent = findViewById(R.id.textViewPostContent);
        recyclerViewComments = findViewById(R.id.recyclerViewComments);
        editTextComment = findViewById(R.id.editTextComment);
        buttonPostComment = findViewById(R.id.buttonPostComment);

        postId = getIntent().getStringExtra("postId");
        postReference = FirebaseDatabase.getInstance().getReference("forum/posts").child(postId);
        commentsReference = postReference.child("comments");

        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Comment> options =
                new FirebaseRecyclerOptions.Builder<Comment>()
                        .setQuery(commentsReference, Comment.class)
                        .build();

        commentAdapter = new CommentAdapter(options);
        recyclerViewComments.setAdapter(commentAdapter);

        postReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Post post = snapshot.getValue(Post.class);
                if (post != null) {
                    textViewPostTitle.setText(post.getTitle());
                    textViewPostContent.setText(post.getContent());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });

        buttonPostComment.setOnClickListener(v -> {
            String commentContent = editTextComment.getText().toString().trim();
            if (!commentContent.isEmpty()) {
                postComment(commentContent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        commentAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        commentAdapter.stopListening();
    }

    private void postComment(String content) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Comment comment = new Comment(userId, content, System.currentTimeMillis());
        commentsReference.push().setValue(comment);
        editTextComment.setText("");
    }
}
