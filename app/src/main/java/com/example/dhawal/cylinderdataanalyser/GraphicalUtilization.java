package com.example.dhawal.cylinderdataanalyser;

import android.graphics.Color;
import android.renderscript.Sampler;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GraphicalUtilization extends AppCompatActivity {
    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical_utilization);
        //        calendarView = findViewById(R.id.cld);

        graphView=findViewById(R.id.graph);
        tv1=findViewById(R.id.tv_weight);
//        tv2=findViewById(R.id.tv_date);
        series=new LineGraphSeries<>(getData());
        graphView.addSeries(series);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
               {
                    @Override
                     public String formatLabel(double value, boolean isValueX) {
                           if(isValueX)
                             {
                                return sdf.format(new Date((long) value));
                             }
                           else {
                            return super.formatLabel(value, isValueX);
                                }
                             }
                            }
        );


        graphView.getGridLabelRenderer().setHumanRounding(false);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(2);

        series.setDrawBackground(true);    //for line graph
        series.setDrawDataPoints(true);
        series.setDrawAsPath(true);


        graphView.getViewport().setXAxisBoundsManual(true);
//        graphView.getViewport().setMinX(25/9/2019);
//        graphView.getViewport().setMaxX(28/9/2019);
////        graphView.getViewport().setMinY(12);
//        graphView.getViewport().setMaxY(32);
//        graphView.getViewport().setScrollable(true);
//        graphView.getViewport().setScrollableY(true);
        graphView.getViewport().setScalable(true);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                String s="Weight of Cylinder is "+dataPoint.getY()+" Kg";
                tv1.setText(s);

//                tv2.setText((int) dataPoint.getX());
//                Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }
        });


//        series.setSpacing(10);
//        series.setColor(Color.MAGENTA);
//        series.setDrawValuesOnTop(true);
//        series.setValuesOnTopColor(Color.RED);
//        series.setTitle("Trends");



    }



    private DataPoint[] getData() {
        DataPoint[] dp=new DataPoint[]{
                new DataPoint( new Date(119,8,25 ),30),
                new DataPoint( new Date(119,8,26 ),29.7),
                new DataPoint( new Date(119,8,27 ),29.5),
                new DataPoint( new Date(119,8,28 ),29.3),
                new DataPoint( new Date(119,8,29 ),29),
                new DataPoint( new Date(119,8,30 ),28.7),
                new DataPoint( new Date(119,9,1 ),28.3),
                new DataPoint( new Date(119,9,2 ),27),
                new DataPoint( new Date(119,9,3 ),26.9),
                new DataPoint( new Date(119,9,4 ),26.8),
                new DataPoint( new Date(119,9,5 ),26.6),
                new DataPoint( new Date(119,9,6 ),26.5),
                new DataPoint( new Date(119,9,7 ),26.1),
                new DataPoint( new Date(119,9,8 ),25.8),
                new DataPoint( new Date(119,9,9 ),25.7),
                new DataPoint( new Date(119,9,10 ),25.6),
                new DataPoint( new Date(119,9,11 ),25.4),
                new DataPoint( new Date(119,9,12 ),25),
                new DataPoint( new Date(119,9,13 ),24.8),
                new DataPoint( new Date(119,9,14 ),24.5),
                new DataPoint( new Date(119,9,15 ),24.3),
                new DataPoint( new Date(119,9,16 ),24.1),
                new DataPoint( new Date(119,9,17 ),23),
                new DataPoint( new Date(119,9,18 ),22.7),
                new DataPoint( new Date(119,9,19 ),22.5),
                new DataPoint( new Date(119,9,20 ),22.3),
                new DataPoint( new Date(119,9,21 ),21.5),
                new DataPoint( new Date(119,9,22 ),21),
                new DataPoint( new Date(119,9,23 ),20.8),
                new DataPoint( new Date(119,9,24 ),20.4),
                new DataPoint( new Date(119,9,25 ),20.2),
                new DataPoint( new Date(119,9,26 ),19.8),
                new DataPoint( new Date(119,9,27 ),19.5),
                new DataPoint( new Date(119,9,28 ),19.3),
                new DataPoint( new Date(119,9,29 ),19.1),
                new DataPoint( new Date(119,9,30 ),18.9),
                new DataPoint( new Date(119,9,31 ),18.8),
                new DataPoint( new Date(119,10,1 ),18.5),
                new DataPoint( new Date(119,10,2 ),18.4),
                new DataPoint( new Date(119,10,3 ),18.1),
                new DataPoint( new Date(119,10,4 ),17.9),
                new DataPoint( new Date(119,10,5 ),17.6),
                new DataPoint( new Date(119,10,6 ),17.3),
                new DataPoint( new Date(119,10,7 ),17),
                new DataPoint( new Date(119,10,8 ),30),
                new DataPoint( new Date(119,10,9 ),29.8),
                new DataPoint( new Date(119,10,10 ),29.4),
                new DataPoint( new Date(119,10,11 ),29.2),
                new DataPoint( new Date(119,10,12 ),29),
                new DataPoint( new Date(119,10,13 ),28.6),
                new DataPoint( new Date(119,10,14 ),28.4),
                new DataPoint( new Date(119,10,15 ),28.2),
                new DataPoint( new Date(119,10,16 ),27.7),
                new DataPoint( new Date(119,10,17 ),27.5),
                new DataPoint( new Date(119,10,18 ),27.4),
                new DataPoint( new Date(119,10,19 ),27.2),
                new DataPoint( new Date(119,10,20 ),27),
                new DataPoint( new Date(119,10,21 ),26.7),
                new DataPoint( new Date(119,10,22 ),26.5)
//                new DataPoint( new Date(119,10,23 ),26.4),
//                new DataPoint( new Date(119,10,24 ),26.2),
//                new DataPoint( new Date(119,10,25 ),25.9),
//                new DataPoint( new Date(119,10,26 ),25.7),
//                new DataPoint( new Date(119,10,27 ),25.5),
//                new DataPoint( new Date(119,10,28 ),25.3),
//                new DataPoint( new Date(119,10,29 ),24.8),
//                new DataPoint( new Date(119,10,30 ),24.7),
//                new DataPoint( new Date(119,11,1 ),24.6),
//                new DataPoint( new Date(119,11,2 ),24.4),
//                new DataPoint( new Date(119,10,3 ),24.2)

        };
        return dp;
    }
}
