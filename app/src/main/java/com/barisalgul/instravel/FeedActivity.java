package com.barisalgul.instravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.barisalgul.instravel.adapter.FeedRecyclerAdapter;
import com.barisalgul.instravel.model.PostModel;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser firebaseUser;
    ArrayList<PostModel> postModels;
    PostModel postModel;
    FeedRecyclerAdapter feedRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        postModels = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        getDataFromFireStore();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        feedRecyclerAdapter = new FeedRecyclerAdapter(postModels);
        recyclerView.setAdapter(feedRecyclerAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.options_menu_item_upload:
                Intent intentUpload = new Intent(FeedActivity.this, UploadActivity.class);
                startActivity(intentUpload);
                break;
            case R.id.options_menu_item_sign_out:
                firebaseAuth.signOut();

                Intent intentSign = new Intent(FeedActivity.this, SignActivity.class);
                startActivity(intentSign);
                finish();
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.insta_options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void getDataFromFireStore() {

        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(FeedActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

                if (queryDocumentSnapshots != null) {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();

                        String comment = (String) data.get("comment");
                        String useremail = (String) data.get("useremail");
                        String downloadUrl = (String) data.get("downloadurl");
                        Timestamp date = (Timestamp) data.get("date");

                        postModel = new PostModel(useremail, comment, downloadUrl, date);
                        postModels.add(postModel);

                        feedRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
