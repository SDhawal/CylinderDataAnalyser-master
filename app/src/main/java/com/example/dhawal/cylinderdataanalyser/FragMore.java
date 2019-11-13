package com.example.dhawal.cylinderdataanalyser;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;


public class FragMore extends ListFragment {
ListView list;
FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
String[] mainStr={"User","Alert","Graphic Utilization","Tips","Settings","Contact/Support","Logout"};
Integer[] imgid={R.drawable.ic_account_24dp,R.drawable.ic_alert_black_24dp,R.drawable.ic_graphic_black_24dp,R.drawable.ic_tips_black_24dp
,R.drawable.ic_settings_black_24dp,R.drawable.ic_support_black_24dp,R.drawable.ic_logout_black_24dp};
ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String, String>>();
SimpleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HashMap<String,String> map=new HashMap<String, String>();
        for (int i=0;i<mainStr.length;i++)
        {
            map=new HashMap<String, String>();
            map.put("MainStr",mainStr[i]);
            map.put("Icons",Integer.toString(imgid[i]));
            data.add(map);
        }
        String[] from={"MainStr","Icons"};
        int[] to={R.id.more_tv,R.id.more_images};
        adapter=new SimpleAdapter(getActivity(),data,R.layout.fragment_item,from,to);
        setListAdapter(adapter);
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container,savedInstanceState);
    }
    public void onStart()
    {
        super.onStart();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
               if (pos==0)
               {
                   Intent intent=new Intent(getActivity(),UserProfile.class);
                   startActivity(intent);
               }
               else if(pos==1)
               {
                   Toast.makeText(getContext(), "Alert", Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(getActivity(),Alert.class);
                   startActivity(intent);
               }
               else if(pos==2)
               {
                   Toast.makeText(getContext(), "Graphic utilization", Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(getActivity(),GraphicalUtilization.class);
                   startActivity(intent);
               }
               else if(pos==3)
               {
                   Toast.makeText(getContext(), "Tips", Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(getActivity(),Tips.class);
                   startActivity(intent);
               }
               else if(pos==4)
               {
                   Toast.makeText(getContext(), "Setting", Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(getActivity(),Settings.class);
                   startActivity(intent);
               }
               else if(pos==5)
               {
                   Toast.makeText(getContext(), "Contact", Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(getActivity(),ContactUs.class);
                   startActivity(intent);
               }
               else if(pos==6)
               {
                    FirebaseAuth.getInstance().signOut();
                    Intent intoMain=new Intent(getActivity(),FirstScreen.class);
                    startActivity(intoMain);

               }



            }
        });
    }
}
