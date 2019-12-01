package com.example.dhawal.cylinderdataanalyser;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Today extends Fragment implements DatePickerListener {
    String weight;
    TextView day , datemon ,percent;
    DatabaseReference rootRef;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    CircularProgressBar progressBar;
    //  List<String> weights;
    CardView predict;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.fragment_today, container, false);
        Toast.makeText(getContext(),"Select Date",Toast.LENGTH_LONG).show();

        rootRef = FirebaseDatabase.getInstance().getReference();
        percent = v.findViewById(R.id.percent);
        //weights = new ArrayList<String>();
        predict = v.findViewById(R.id.predict);


        HorizontalPicker picker = (HorizontalPicker) v.findViewById(R.id.datePicker);

        day = v.findViewById(R.id.day);
        datemon = v.findViewById(R.id.Dtyear);


        final Date d = new Date();
        datemon.setText(DateFormat.format("d, MMMM", d.getTime()));
        day.setText(DateFormat.format("EEEE",d.getTime()));

        progressBar = v.findViewById(R.id.progress_bar);


        final String str = (String) DateFormat.format("dd-MM-yyyy",d.getTime());
        getData(str);

        // progressBar.setBackgroundColor(Color.RED);
        progressBar.setForegroundStrokeColor(Color.GREEN);
        progressBar.setProgressAnimationDuration(10);
        progressBar.setAnimateProgress(true);


        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                predict,
                PropertyValuesHolder.ofFloat("scaleX", 0.7f),
                PropertyValuesHolder.ofFloat("scaleY", 0.7f)
        );
        scaleDown.setDuration(1500);
        scaleDown.setRepeatMode(ValueAnimator.REVERSE);
        scaleDown.setRepeatCount(ValueAnimator.INFINITE);
        scaleDown.start();



        // initialize it and attach a listener
        picker
                .setListener(this)
                .init();

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeight((String) DateFormat.format("dd-MM-yyyy",d.getTime()));
            }
        });
//        for(int i=0;i > -7;i--)
//        {
//
//            getWeight(getYesterdayDateString(i));
//        }

        //     Toast.makeText(getContext(),weight, Toast.LENGTH_SHORT).show();

        return v;
    }
//
//    private String getPrediction() {
//
//        float sum = 0;
//        for(int i=0;i<6;i++)
//        {
//            float w1 = Float.parseFloat(weights.get(i));
//            float w2 = Float.parseFloat(weights.get(i+1));
//
//            sum += w2 - w1;
//
//        }
//
//        String p = "" + (sum/6);
//
//        return p;
//    }

    @Override
    public void onDateSelected(DateTime dateSelected) {

        day.setText(dateSelected.toString("EEEE", Locale.getDefault()));
        String s = dateSelected.toString("dd", Locale.getDefault())+ ", " + dateSelected.toString("MMMM", Locale.getDefault());
        datemon.setText(s);

        String str = dateSelected.toString("dd-MM-yyyy",Locale.getDefault());
        // Toast.makeText(getContext(),str,Toast.LENGTH_LONG).show();
        mProgressStatus = 0;
        getData(str);


    }


    void getData(String str)
    {
        if(str.equals("01-12-2019"))
        {
            str = "1-12-2019";
        }
        if(str.equals("02-12-2019"))
        {
            str = "2-12-2019";
        }
        if(str.equals("03-12-2019"))
        {
            str = "3-12-2019";
        }


        Toast.makeText(getContext(), rootRef.child("value").child(str).getKey(), Toast.LENGTH_SHORT).show();
        rootRef.child("value").child(str)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue() != null) {
                            String s = dataSnapshot.getValue().toString();

                            // Toast.makeText(getContext(),""+(Float.parseFloat(s) -  16 ),Toast.LENGTH_LONG).show();
                            final int p = (int) (((Float.parseFloat(s) -  16 )*100/ 14) );

                            percent.setText("" + p+"%");
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    while (mProgressStatus < p) {
                                        mProgressStatus++;
                                        android.os.SystemClock.sleep(25);
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                progressBar.setProgress(mProgressStatus);
                                            }
                                        });
                                    }
                                }
                            }).start();

                            //progressBar.setProgress(p);
                            if(p > 60)
                            {
                                progressBar.setForegroundStrokeColor(Color.GREEN);

                            }
                            if(p <= 60 && p > 25)
                            {
                                progressBar.setForegroundStrokeColor(Color.parseColor("#FFA500"));
                            }
                            else if(p <= 25)
                            {
                                progressBar.setForegroundStrokeColor(Color.RED);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    private String getYesterdayDateString(int i) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, i);
        return (String) dateFormat.format(cal.getTime());
    }

    void getWeight(String str)
    {
        if(str.equals("01-12-2019"))
        {
            str = "1-12-2019";
        }
        if(str.equals("02-12-2019"))
        {
            str = "2-12-2019";
        }
        if(str.equals("03-12-2019"))
        {
            str = "3-12-2019";
        }

        rootRef.child("value").child(str).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                weight = dataSnapshot.getValue().toString();
                double wt = Double.parseDouble(weight) - 16;
                int days = 0;
                while(wt > 0)
                {
                    days += 1;
                    wt -= 0.3;
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, days);

                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(getContext());


                builder.setMessage("Your Cylinder is predicted to be exhausted before "+ dateFormat.format(cal.getTime())+"\n i.e., Within " + days + " days" );


                builder.setTitle("Prediction !");


                builder.setCancelable(false);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
