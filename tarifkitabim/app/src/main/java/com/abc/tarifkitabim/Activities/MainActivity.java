package com.abc.tarifkitabim.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.abc.tarifkitabim.R;


public class MainActivity extends AppCompatActivity {

    private ConstraintLayout startbtn;
    private ImageView imageChicken;
    private Animation downtoup;
    private Animation lefttocenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbtn=findViewById(R.id.startbtn);
        imageChicken=findViewById(R.id.imageChicken);

        downtoup= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.downtoup);
        lefttocenter= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.lefttocenter);

        startbtn.setAnimation(downtoup);
        imageChicken.setAnimation(lefttocenter);

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,FirstActivity.class));
                
            }
        });


    }
}