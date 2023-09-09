package com.example.studysortapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChoseUserActivity extends AppCompatActivity {

    CardView cvStudent, cvParent, cvAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_user);

        cvStudent = findViewById(R.id.cvStudent);
        cvParent = findViewById(R.id.cvParent);
        cvAdmin = findViewById(R.id.cvAdmin);

        cvStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoseUserActivity.this, LoginActivity.class));
            }
        });
        cvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoseUserActivity.this, ParentLoginActivity.class));
            }
        });
        cvAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoseUserActivity.this, AdminLoginActivity.class));
            }
        });
    }
}