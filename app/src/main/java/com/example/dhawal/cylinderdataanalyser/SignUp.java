package com.example.dhawal.cylinderdataanalyser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignUp extends AppCompatActivity {
    Button CreatAccountbtn;
    EditText InputUsername, InputPassword;
    TextView tvLogin;
    FirebaseAuth mFirebaseAuth;

    // ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        InputUsername = findViewById(R.id.username_signup);
        InputPassword = findViewById(R.id.psswd_signup);
        CreatAccountbtn = findViewById(R.id.create_account_btn);
        mFirebaseAuth=FirebaseAuth.getInstance();
        tvLogin=findViewById(R.id.tvlogin);

        CreatAccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= InputUsername.getText().toString();
                String pwd=InputPassword.getText().toString();
                if (email.isEmpty()&& pwd.isEmpty()){
                    InputUsername.setError("Please Enter email id");
                    InputUsername.requestFocus();
                    InputPassword.setError("Please Enter password");
                    InputPassword.requestFocus();
                }

                else if (!(email.isEmpty()&&pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Signup Unsuccessful!,Please try again", Toast.LENGTH_SHORT).show();
                            }
                            else
                            { finish();
                              startActivity(new Intent(SignUp.this,MainActivity.class));
                              Toast.makeText(SignUp.this, "Welcome!!", Toast.LENGTH_SHORT).show();}
                        }
                    });
                }
                else{
                    Toast.makeText(SignUp.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignup =new Intent(SignUp.this,LoginActivity.class);
                startActivity(intSignup);
            }
        });
     /*   InputConfirmPsswd=findViewById(R.id.confirmpsswd_signup);
        InputPhoneNo=findViewById(R.id.phoneno_signup);*/

     /*   loadingBar=new ProgressDialog(this);

        CreatAccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });*/
    }


}
    /*  public void CreateAccount()
    {
        String username=InputUsername.getText().toString();
        String password=InputPassword.getText().toString();
        String confirm_password=InputPassword.getText().toString();
        String phoneno=InputPhoneNo.getText().toString();
        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this,"Please enter username..",Toast.LENGTH_SHORT).show();
        }
       else if(TextUtils.isEmpty(phoneno))
        {
            Toast.makeText(this,"Please enter your phone number..",Toast.LENGTH_SHORT).show();
        }
       else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
        }
       else if(TextUtils.isEmpty(confirm_password))
        {
            Toast.makeText(this,"Please enter confirm password",Toast.LENGTH_SHORT).show();
        }
       else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait while we are checking credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            ValidatePhone(username,phoneno,password,confirm_password);
        } */


   /* public void ValidatePhone(final String username, final String phoneno, final String password, final String confirm_password)
    {
        final DatabaseReference Rootref;
        Rootref= FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("Users").child(phoneno).exists()))
                {
                    HashMap<String,Object>userDataMap=new HashMap<>();
                    userDataMap.put("phoneno",phoneno);
                    userDataMap.put("username",username);
                    userDataMap.put("password",password);
                    userDataMap.put("confirm password",confirm_password);

                    Rootref.child("Users").child(phoneno).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete( Task<Void> task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(SignUp.this,"Congratulations! Your account has been created.",Toast.LENGTH_SHORT).show();
                                       loadingBar.dismiss();
                                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(intent);

                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(SignUp.this,"Network Error: Please try after some time..",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });





                }
                else
                {
                    Toast.makeText(SignUp.this,"This"+""+phoneno+""+"already exists!",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(SignUp.this,"Please try again using another phone number..",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),FirstScreen.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



