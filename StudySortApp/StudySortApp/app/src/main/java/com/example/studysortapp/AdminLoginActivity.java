package com.example.studysortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {



    EditText edtUserName, edtUserPassword;
    TextView btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        loadIDS();

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUserName.getText().toString();
                String password = edtUserPassword.getText().toString();
                if (user.equals("xyz") && password.equals("xyz")){
                    Intent intent = new Intent(AdminLoginActivity.this, MainActivity.class);
                    intent.putExtra("user", "admin");
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AdminLoginActivity.this, "Please enter valid details...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void loadIDS() {
        edtUserName = findViewById(R.id.edt_user_name);
        edtUserPassword = findViewById(R.id.edt_user_password);
        btnEnter = findViewById(R.id.btnEnter);
    }
}