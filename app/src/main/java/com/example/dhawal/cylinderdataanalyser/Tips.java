package com.example.dhawal.cylinderdataanalyser;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Tips extends AppCompatActivity {
Button previous,nextbtn;
ImageView tipsimg;

    int[] list_tips={R.drawable.tip1,R.drawable.tip2,R.drawable.tip3,R.drawable.tip4,R.drawable.tip5,
            R.drawable.tip6,R.drawable.tip7,R.drawable.tip8,R.drawable.tip9,R.drawable.tip10,
            R.drawable.tip11,R.drawable.tip12,R.drawable.tip13,R.drawable.tip14,R.drawable.tip15,
    };
int page=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        previous=findViewById(R.id.prev);
        nextbtn=findViewById(R.id.next);
        tipsimg=findViewById(R.id.tips);

        tipsimg.setImageResource(list_tips[page]);
    }
    public void nextImage(View view){
        if (page<14){
        page++;
        tipsimg.setImageResource(list_tips[page]);
        }
        else{
            Toast.makeText(this, "Last image", Toast.LENGTH_SHORT).show();
        }
    }
    public void prevImage(View view){
        if (page>0){
        page--;
        tipsimg.setImageResource(list_tips[page]);
        }
        else {
            Toast.makeText(this, "First image", Toast.LENGTH_SHORT).show();
        }
    }
}
