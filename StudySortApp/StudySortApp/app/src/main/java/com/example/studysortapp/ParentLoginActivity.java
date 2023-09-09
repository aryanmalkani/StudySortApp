package com.example.studysortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ParentLoginActivity extends AppCompatActivity {



    EditText edtUserName, edtUserPassword;
    TextView btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        loadIDS();

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUserName.getText().toString();
                String password = edtUserPassword.getText().toString();
                if (user.equals("pqr") && password.equals("pqr")){
                    Intent intent = new Intent(ParentLoginActivity.this, MainActivity.class);
                    intent.putExtra("user", "parent");
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ParentLoginActivity.this, "Please enter valid details...", Toast.LENGTH_SHORT).show();
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