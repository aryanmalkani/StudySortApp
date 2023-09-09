package com.example.studysortapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MotivationVideosActivity extends AppCompatActivity {

    ListView listView;
    LottieAnimationView lav;
    ArrayAdapter<String> adapter;

    ArrayList<String> lvKeys = new ArrayList<>();
    ArrayList<DBDataModel> lvValues = new ArrayList<>();
    DatabaseReference dbRef;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation_videos);

        dbRef = FirebaseDatabase.getInstance().getReference();
        listView = findViewById(R.id.lvMVideos);
        lav = findViewById(R.id.LAV);
        adapter = new ArrayAdapter<>(MotivationVideosActivity.this, R.layout.m_videos_content, R.id.itemTitle);
        listView.setAdapter(adapter);

        Animation animation = AnimationUtils.loadAnimation(MotivationVideosActivity.this, android.R.anim.slide_in_left);
        listView.setAnimation(animation);


        getDetails();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    Uri uri = Uri.parse(lvValues.get(position).getLink());
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(MainActivity.currUser.equals("admin")){
                    showDialog(position);
                }
                return true;
            }

            private void showDialog(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MotivationVideosActivity.this)
                        .setMessage("Are you sure want to delete this?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String key = lvKeys.get(position);
                                dbRef.child("MotivationVideos").child(key).removeValue();
                                adapter.remove(lvValues.get(position).getTitle());
                                lvKeys.remove(position);
                                lvValues.remove(position);
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.show();
            }
        });

    }

    private void getDetails() {
        lvKeys.clear();
        lvValues.clear();

        dbRef.child("MotivationVideos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                lvKeys.add(snapshot.getKey());
                DBDataModel dbDataModel = snapshot.getValue(DBDataModel.class);
                lvValues.add(dbDataModel);
                adapter.add(dbDataModel.getTitle());
                lav.setVisibility(View.GONE);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MotivationVideosActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}