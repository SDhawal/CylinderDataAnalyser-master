package com.example.dhawal.cylinderdataanalyser;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;

import org.joda.time.DateTime;

public class Today extends Fragment implements DatePickerListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.fragment_today, container, false);

        HorizontalPicker picker = (HorizontalPicker) v.findViewById(R.id.datePicker);

        // initialize it and attach a listener
        picker
                .setListener(this)
                .init();
        return v;
    }

    @Override
    public void onDateSelected(DateTime dateTime) {
        Toast.makeText(getContext(),"HorizontalPicker"+ "Selected date is " + dateTime.toString(),Toast.LENGTH_LONG).show();

    }
}
