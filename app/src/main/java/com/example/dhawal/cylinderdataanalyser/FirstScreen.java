package com.example.dhawal.cylinderdataanalyser;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class FirstScreen extends AppCompatActivity {
    Button join_now_btn,main_login_btn;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        join_now_btn=findViewById(R.id.joinnow_btn);
        main_login_btn=findViewById(R.id.main_login_btn);
        mContext=this;
        join_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);

            }
        });
        main_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }

        });
        AnimateBell();

    }
    public void AnimateBell()
    {
        Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
        ImageView imgBell= (ImageView) findViewById(R.id.imgbell);
        imgBell.setImageResource(R.drawable.cylinder);
        imgBell.setAnimation(shake);
    }
}
