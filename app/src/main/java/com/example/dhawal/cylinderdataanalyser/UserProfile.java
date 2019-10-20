package com.example.dhawal.cylinderdataanalyser;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 101;
    CircleImageView profilepic;
EditText username,number,usergender,userage;
Button save,edit;
Uri uriProfilePic;
ProgressBar progressBar;
String ProfilePicUrl;
FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        profilepic=findViewById(R.id.profilePic);
        username=findViewById(R.id.name);
        number=findViewById(R.id.contact);
        usergender=findViewById(R.id.gender);
        userage=findViewById(R.id.age);
        save=findViewById(R.id.profbutton1);
        edit=findViewById(R.id.profbutton2);
        progressBar=findViewById(R.id.profprog1);
        mFirebaseAuth=FirebaseAuth.getInstance();

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showImageChooser();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            saveUserInformation();
            }
        });
        loadUserInformation();
    }

    private void loadUserInformation() {
        FirebaseUser user=mFirebaseAuth.getCurrentUser();
       if (user!=null) {
           if (user.getPhotoUrl() != null) {
               Glide.with(this).load(user.getPhotoUrl().toString()).into(profilepic);
           }
           if (user.getDisplayName() != null) {
               username.setText(user.getDisplayName());
               /*number.setText(user.getDisplayName());
               usergender.setText(user.getDisplayName());
               userage.setText(user.getDisplayName());*/
           }
       }
    }



    private void saveUserInformation(){
        String name=username.getText().toString();
        /*String usernumber=number.getText().toString();
        String gender=usergender.getText().toString();
        String age=userage.getText().toString();*/
        if (name.isEmpty()){
            username.setError("name required");
            username.requestFocus();
            return;
        }
        /*else if (usernumber.isEmpty()){
            number.setError("10 digit number required");
            number.requestFocus();
            return;
        }
        else if (gender.isEmpty()){
            usergender.setError("gender required");
            usergender.requestFocus();
            return;
        }
        else if (age.isEmpty()){
            userage.setError("age required");
            userage.requestFocus();
            return;
        }*/
            FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
            if (firebaseUser!=null&& uriProfilePic!=null){
                UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder()
                        .setDisplayName(name)/*.setDisplayName(usernumber).setDisplayName(gender).setDisplayName(age)*/
                        .setPhotoUri(Uri.parse(String.valueOf(uriProfilePic)))
                        .build();
                firebaseUser.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                      if (task.isSuccessful()){
                          Toast.makeText(UserProfile.this, "profile updated", Toast.LENGTH_SHORT).show();
                      }
                    }
                });
            }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
           uriProfilePic= data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfilePic);
                profilepic.setImageBitmap(bitmap);
                uploadImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStorage() {
        StorageReference profilePicRef=
                FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis()+".jpg");
                if (uriProfilePic!=null){
                    progressBar.setVisibility(View.VISIBLE);
                    profilePicRef.putFile(uriProfilePic).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            ProfilePicUrl=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(UserProfile.this, "error occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
    }

    private void showImageChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"), CHOOSE_IMAGE);
    }
}
