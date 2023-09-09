package com.example.studysortapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText edtTitle, edtLink;
    private Button btnAddData, btnOnOffAddData;
    private String[] strCat = {"Select Category","NotesBooks", "PuzzleGame", "TimeManagementVideos", "MindChangeVideos", "MotivationVideos", "Novel", "HRInterviewQnA", "MCQQuestions", "Counsellor"};
    private ArrayAdapter<String> adapter;
    private int selectedCatID;

    private LinearLayout lldata;

    private DatabaseReference dbReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        loadVar();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCatID = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnOnOffAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnOffAddData.setVisibility(View.GONE);
                lldata.setVisibility(View.VISIBLE);
            }
        });

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedCatID != 0){
                    String title = edtTitle.getText().toString();
                    String link = edtLink.getText().toString();
                    if(!title.equals(null) && !link.equals(null)){
                        dbReference.child(strCat[selectedCatID]).push().setValue(new DBDataModel(title, link));
                        dbReference.child(strCat[selectedCatID]).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Toast.makeText(AdminActivity.this, "Data is inserted", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(AdminActivity.this, "Error | Data is not inserted", Toast.LENGTH_SHORT).show();
                            }
                        });
                        edtTitle.setText(null);
                        edtLink.setText(null);
                    }
                    else{
                        Toast.makeText(AdminActivity.this, "Please insert data in field", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(AdminActivity.this, "Please select correct category", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadVar() {
        spinner = findViewById(R.id.spinnSelectCat);
        edtLink = findViewById(R.id.edtLink);
        edtTitle = findViewById(R.id.edtTitle);
        btnAddData = findViewById(R.id.btnAddData);
        btnOnOffAddData = findViewById(R.id.btnOnOffAddData);
        lldata = findViewById(R.id.lldata);
        adapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_spinner_item, strCat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        dbReference = FirebaseDatabase.getInstance().getReference();
    }
}