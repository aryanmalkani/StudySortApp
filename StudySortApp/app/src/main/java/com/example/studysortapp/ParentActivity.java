package com.example.studysortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class ParentActivity extends AppCompatActivity {

    TimePicker tp;
    Button btnSetAlarm;
    EditText edtAMsg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        tp = findViewById(R.id.tp);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        edtAMsg = findViewById(R.id.edtAMsg);

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, tp.getHour());
                intent.putExtra(AlarmClock.EXTRA_MINUTES, tp.getMinute());
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, edtAMsg.getText().toString());
                try{
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }}