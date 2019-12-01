package com.example.dhawal.cylinderdataanalyser;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.timessquare.CalendarPickerView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;


public class Calendar_Frag extends Fragment {
    private String mParam1;
    CalendarView calendarView;
    TextView totalWeight, cylinWeight, percentageWeight;

    DatabaseReference rootRef;

    //   CalendarPickerView datePicker;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_calendar_, container, false);
        calendarView = v.findViewById(R.id.calender_view);
        rootRef = FirebaseDatabase.getInstance().getReference();

        totalWeight = v.findViewById(R.id.total_weight);
        cylinWeight = v.findViewById(R.id.weight_gas);
        percentageWeight = v.findViewById(R.id.gas_percentage);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull @android.support.annotation.NonNull CalendarView view, int year, int month, int dayOfMonth) {

                month = month + 1;
                String str = "" + dayOfMonth + "-" + month + "-" + year;

                Toast.makeText(getContext(),str,Toast.LENGTH_LONG).show();

                rootRef.child("value").child(str)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@android.support.annotation.NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue() != null) {
                                    String s = dataSnapshot.getValue().toString();

                                    totalWeight.setText(s);
                                    float wt  = Float.parseFloat(s) - 16;
                                    cylinWeight.setText(""+wt);
                                    // Toast.makeText(getContext(),""+(Float.parseFloat(s) -  16 ),Toast.LENGTH_LONG).show();
                                    float p = (((Float.parseFloat(s) -  16 )*100/ 14) );

                                    NumberFormat nf = DecimalFormat.getPercentInstance();
                                    nf.setMaximumFractionDigits(2);
                                    String p2 = nf.format(p);

                                    percentageWeight.setText(""+p);


                                }

                            }

                            @Override
                            public void onCancelled(@android.support.annotation.NonNull DatabaseError databaseError) {

                            }
                        });




            }
        });

        return v;
    }


}
