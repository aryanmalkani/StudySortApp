package com.example.studysortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {



    EditText edtUserName, edtUserPassword;
    TextView btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadIDS();

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUserName.getText().toString();
                String password = edtUserPassword.getText().toString();
                if (user.equals("abc") && password.equals("abc")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user", "student");
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Please enter valid details...", Toast.LENGTH_SHORT).show();
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