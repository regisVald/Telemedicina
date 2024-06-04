package com.example.mediconnect;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePostActivity extends AppCompatActivity {
    private EditText editTextPostTitle;
    private EditText editTextPostContent;
    private Button buttonPublishPost;
    private DatabaseReference postsReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        editTextPostTitle = findViewById(R.id.editTextPostTitle);
        editTextPostContent = findViewById(R.id.editTextPostContent);
        buttonPublishPost = findViewById(R.id.buttonPublishPost);

        postsReference = FirebaseDatabase.getInstance().getReference("forum/posts");

        buttonPublishPost.setOnClickListener(v -> {
            String title = editTextPostTitle.getText().toString().trim();
            String content = editTextPostContent.getText().toString().trim();
            if (!title.isEmpty() && !content.isEmpty()) {
                publishPost(title, content);
            } else {
                Toast.makeText(CreatePostActivity.this, "Please fill in both title and content", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void publishPost(String title, String content) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        long timestamp = System.currentTimeMillis();

        Post post = new Post(title, content, userId, timestamp);
        postsReference.push().setValue(post).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(CreatePostActivity.this, "Post published successfully", Toast.LENGTH_SHORT).show();
                finish();  // Close the activity and return to the previous screen
            } else {
                Toast.makeText(CreatePostActivity.this, "Failed to publish post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
