package com.example.studysortapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private CardView cvNotesBooks, cvPuzzleGame, cvTMVideos, cvMCVideos, cvMVideos, cvNovels, cvHRIntQuest, cvMCQQuest, cvCounsellor;
    private Intent intent;
    public static String currUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadVar();
        intent = getIntent();
        currUser = intent.getStringExtra("user");

        callSetOnClickListner();
    }

    private void callSetOnClickListner() {
        cvNotesBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotesBooksActivity.class));
            }
        });
        cvPuzzleGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PuzzleGameActivity.class));
            }
        });
        cvTMVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TimeManagementVideosActivity.class));
            }
        });
        cvMCVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MindChangeActivity.class));
            }
        });
        cvMVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MotivationVideosActivity.class));
            }
        });
        cvNovels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NovelActivity.class));
            }
        });
        cvCounsellor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CounsellorActivity.class));
            }
        });
        cvHRIntQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HRInterviewQnAActivity.class));
            }
        });
        cvMCQQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MCQQuestionsActivity.class));
            }
        });
    }

    private void loadVar() {
        cvNotesBooks = findViewById(R.id.cvNotesBooks);
        cvPuzzleGame = findViewById(R.id.cvPuzzleGame);
        cvTMVideos = findViewById(R.id.cvTMVideos);
        cvMCVideos = findViewById(R.id.cvMCVideos);
        cvMVideos = findViewById(R.id.cvMVideos);
        cvNovels = findViewById(R.id.cvNovels);
        cvCounsellor = findViewById(R.id.cvCounsellor);
        cvHRIntQuest = findViewById(R.id.cvHRIntQnA);
        cvMCQQuest = findViewById(R.id.cvMCQQuest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.user_details, menu);

        MenuItem parent =menu.findItem(R.id.optUserParent);
        MenuItem admin = menu.findItem(R.id.optUserAdmin);
        MenuItem optAlarm = menu.findItem(R.id.optAlarm);

        if(currUser.equals("parent")){
            parent.setVisible(true);
            optAlarm.setVisible(true);
        }
        else if(currUser.equals("admin")){
            admin.setVisible(true);
            optAlarm.setVisible(false);
        }
        else{
            parent.setVisible(false);
            admin.setVisible(false);
            optAlarm.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.optNotesBooks:
                startActivity(new Intent(MainActivity.this, NotesBooksActivity.class));
                return true;
            case R.id.optPuzzleGame:
                startActivity(new Intent(MainActivity.this, PuzzleGameActivity.class));
                return true;
            case R.id.optTimeManagtVideos:
                startActivity(new Intent(MainActivity.this, TimeManagementVideosActivity.class));
                return true;
            case R.id.optMotivationVideos:
                startActivity(new Intent(MainActivity.this, MotivationVideosActivity.class));
                return true;
            case R.id.optMindChangeVideos:
                startActivity(new Intent(MainActivity.this, MindChangeActivity.class));
                return true;
            case R.id.optNovel:
                startActivity(new Intent(MainActivity.this, NovelActivity.class));
                return true;
            case R.id.optHRQnA:
                startActivity(new Intent(MainActivity.this, HRInterviewQnAActivity.class));
                return true;
            case R.id.optMCQQuestion:
                    startActivity(new Intent(MainActivity.this, MCQQuestionsActivity.class));
                return true;
            case R.id.optUserParent:
                startActivity(new Intent(MainActivity.this, ParentActivity.class));
                return true;
            case R.id.optUserAdmin:
                startActivity(new Intent(MainActivity.this, AdminActivity.class));
                return true;
            case R.id.optAlarm:
                Intent intent1 = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                startActivity(intent1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}