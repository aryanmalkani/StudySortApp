package com.example.studysortapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CounsellorActivity extends AppCompatActivity {


    LinearLayout llCall, llCall1, llCall2, llCall3, llInfo, llInfo1, llInfo2, llInfo3;

    TextView tvPhone, tvPhone1, tvPhone2, tvPhone3;
    int callCode = 100;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsellor);

        getVars();

        checkPermission(Manifest.permission.CALL_PHONE);

        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tvPhone.getText().toString()));
                    startActivity(intent);
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        llCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tvPhone1.getText().toString()));
                    startActivity(intent);
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        llCall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tvPhone2.getText().toString()));
                    startActivity(intent);
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        llCall3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tvPhone3.getText().toString()));
                    startActivity(intent);
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        llInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Uri uri = Uri.parse("https://drive.google.com/drive/folders/1k3zHFDqxC4Shrh-37T0vPH8C94ZZ-NXT?usp=share_link");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        llInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Uri uri = Uri.parse("https://drive.google.com/drive/folders/1gcriwcTzoA4I3Mu2nUrkBaUhjcstiFN1?usp=share_link");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        llInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Uri uri = Uri.parse("https://drive.google.com/drive/folders/1keCxFt1WWJfqbtCHH4mRaEuOsuMg6ifv?usp=share_link\n");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        llInfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Uri uri = Uri.parse("https://drive.google.com/drive/folders/1gxIa7A5o_kNz2fuzLu_617N0XRkyz9zK?usp=share_link");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



    }

    private void getVars() {
        llCall = findViewById(R.id.llCall);
        llCall1 = findViewById(R.id.llCall1);
        llCall2 = findViewById(R.id.llCall2);
        llCall3 = findViewById(R.id.llCall3);
        tvPhone = findViewById(R.id.tvPhone);
        tvPhone1 = findViewById(R.id.tvPhone1);
        tvPhone2 = findViewById(R.id.tvPhone2);
        tvPhone3 = findViewById(R.id.tvPhone3);
        llInfo = findViewById(R.id.llInfo);
        llInfo1 = findViewById(R.id.llInfo1);
        llInfo2 = findViewById(R.id.llInfo2);
        llInfo3 = findViewById(R.id.llInfo3);

    }

    public void checkPermission(String permission){
        int grant = ContextCompat.checkSelfPermission(CounsellorActivity.this, permission);
        if(grant == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(CounsellorActivity.this, new String[] { permission }, callCode);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult( requestCode, permissions, grantResults);
    }
}