package com.barisalgul.instravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private EditText tvUsername, tvPassword;
    private String userName, userPassword;
    private LinearLayout llSignContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        tvPassword = findViewById(R.id.tvPassword);
        tvUsername = findViewById(R.id.tvUserName);

        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            Intent intent = new Intent(SignActivity.this, FeedActivity.class);
            startActivity(intent);
            finish();
        } else {
            llSignContainer = findViewById(R.id.llSignContainer);
            llSignContainer.setVisibility(View.VISIBLE);
        }

    }

    public void signup(View view) {

        userName = tvUsername.getText().toString().trim();
        userPassword = tvPassword.getText().toString().trim();

        if(userName.isEmpty() || userPassword.isEmpty()){
            Toast.makeText(SignActivity.this, getString(R.string.error_fill_blanks), Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(userName, userPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Toast.makeText(SignActivity.this, "User Created", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SignActivity.this, FeedActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signin(View view) {

        userName = tvUsername.getText().toString().trim();
        userPassword = tvPassword.getText().toString().trim();

        if(userName.isEmpty() || userPassword.isEmpty()){
            Toast.makeText(SignActivity.this, getString(R.string.error_fill_blanks), Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Intent intent = new Intent(SignActivity.this, FeedActivity.class);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(SignActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void forgotPassword(View view) {
        //TODO
    }
}
