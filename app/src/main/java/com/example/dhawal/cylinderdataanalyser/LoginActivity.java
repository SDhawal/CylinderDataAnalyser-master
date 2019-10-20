package com.example.dhawal.cylinderdataanalyser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button Login_btn;
    EditText Username_Login,Password_Login;
    TextView signin;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth=FirebaseAuth.getInstance();
        Login_btn=findViewById(R.id.login_btn);
        Username_Login=findViewById(R.id.username_login);
        Password_Login=findViewById(R.id.psswd_login);
        signin=findViewById(R.id.tvsignup);
        mAuthStateListner=new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser !=null)
                {
                    Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= Username_Login.getText().toString();
                String pwd=Password_Login.getText().toString();
                if (email.isEmpty()&& pwd.isEmpty()){
                    Username_Login.setError("Please Enter email id");
                    Username_Login.requestFocus();
                    Password_Login.setError("Please Enter password");
                    Password_Login.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty()&&pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login unsuccessful!, Try again", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this, "Error occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,SignUp.class);
                startActivity(i);
            }
        });
    }
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }
}
/*if(mFirebaseAuth.getCurrentUser() !=null)
        {finish();
        startActivity(new Intent(this,MainActivity.class));}*/