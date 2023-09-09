package com.example.studysortapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class NotesBooksActivity extends AppCompatActivity {

    ListView lvNotesBooks;
    LottieAnimationView lav;
    ArrayAdapter<String> adapter;

    ArrayList<String> notesBooksKeys = new ArrayList<>();
    ArrayList<DBDataModel> notesBooksValues = new ArrayList<>();
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_books);

        dbRef = FirebaseDatabase.getInstance().getReference();
        lvNotesBooks = findViewById(R.id.lvNotesBooks);
        lav = findViewById(R.id.LAV);
        adapter = new ArrayAdapter<>(NotesBooksActivity.this, R.layout.notes_books_content, R.id.itemTitle);
        lvNotesBooks.setAdapter(adapter);

        Animation animation = AnimationUtils.loadAnimation(NotesBooksActivity.this, android.R.anim.slide_in_left);
        lvNotesBooks.setAnimation(animation);


        getDetails();

        lvNotesBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    Uri uri = Uri.parse(notesBooksValues.get(position).getLink());
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        lvNotesBooks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(MainActivity.currUser.equals("admin")){
                    showDialog(position);
                }
                return true;
            }

            private void showDialog(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NotesBooksActivity.this)
                        .setMessage("Are you sure want to delete this?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String key = notesBooksKeys.get(position);
                                dbRef.child("NotesBooks").child(key).removeValue();
                                adapter.remove(notesBooksValues.get(position).getTitle());
                                notesBooksKeys.remove(position);
                                notesBooksValues.remove(position);
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
        notesBooksKeys.clear();
        notesBooksValues.clear();

        dbRef.child("NotesBooks").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                notesBooksKeys.add(snapshot.getKey());
                DBDataModel dbDataModel = snapshot.getValue(DBDataModel.class);
                notesBooksValues.add(dbDataModel);
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
                Toast.makeText(NotesBooksActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}