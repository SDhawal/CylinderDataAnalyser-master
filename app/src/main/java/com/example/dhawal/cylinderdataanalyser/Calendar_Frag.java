package com.example.dhawal.cylinderdataanalyser;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;


public class Calendar_Frag extends Fragment {
    private String mParam1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v= inflater.inflate(R.layout.fragment_calendar_, container, false);
        Date today=new Date();
        Calendar nextyear= Calendar.getInstance();
        nextyear.add(Calendar.YEAR,1);

        CalendarPickerView datePicker=v.findViewById(R.id.calendar);
        datePicker.init(today,nextyear.getTime()).withSelectedDate(today);

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                //String selectedDate= DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Calendar calSelected= Calendar.getInstance();
                calSelected.setTime(date);

                String selectedDate=""+calSelected.get(Calendar.DAY_OF_MONTH)+ " "+(calSelected.get(Calendar.MONTH)
                        +" "+(calSelected.get(Calendar.YEAR)));

                Toast.makeText(getActivity(),selectedDate,Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onDateUnselected(Date date) {

            }
        });
        return v;
    }

}
