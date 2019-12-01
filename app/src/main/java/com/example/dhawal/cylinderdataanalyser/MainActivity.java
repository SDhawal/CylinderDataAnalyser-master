package com.example.dhawal.cylinderdataanalyser;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tv;
    FirebaseAuth mFirebaseAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment =null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    selectedFragment = new Today();
                    break;
                   // return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_dashboard);
                    selectedFragment = new Calendar_Frag();
                    break;
                   // return true;
                case R.id.navigation_notifications:
                   /* Intent intent=new Intent(getApplicationContext(),More.class);
                    startActivity(intent);*/
                   selectedFragment = new FragMore();
                   break;
                    //return true;
            }
           // return false;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Today()).commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFirebaseAuth=FirebaseAuth.getInstance();
    }
    @Override
    protected void onStart() {
        if (mFirebaseAuth == null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        super.onStart();
    }

}
