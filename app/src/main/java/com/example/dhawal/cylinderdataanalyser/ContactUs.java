package com.example.dhawal.cylinderdataanalyser;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactUs extends AppCompatActivity {
Button mylpg,onindane,onbharat,onhp,smsindane,smsbharat,smshp,emailus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        mylpg=findViewById(R.id.button1);
        onindane=findViewById(R.id.button2);
        onbharat=findViewById(R.id.button3);
        onhp=findViewById(R.id.button4);
        smsindane=findViewById(R.id.button5);
        smsbharat=findViewById(R.id.button6);
        smshp=findViewById(R.id.button7);
        emailus=findViewById(R.id.button8);
    }

    public void myLPG(View view) {
        String url="http://mylpg.in/index.aspx";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    public void onlineIndane(View view) {
        String url = "https://access.ex.indianoil.in/oam/server/obrareq.cgi?encquery%3DjbTdZFPXtdgLoWMkqzYNQixv7eUFa%2F%2BBq%2Bh7VloWBg1TI4cZ9jLISivoUgw%2BJRaiF85cbhB%2FLNosevWSjnmTIDCKcpWkzr3nPfnYH9LCDxCfY%2BlkqThaEVXRYnkRtBGRnxD%2FfuZ8fXPl8z6HkCxxx5d1CyEIKx22ndhb%2F%2Bnxnyz%2FQui%2FhQ5%2Flo4DwAPZobbeM9J3%2FNw2bAULk5wR%2FmwlBqseHNamW%2B71CMe%2BhTFwJ4wPVx91tj1GpTdH7RIeGjbaKD7wstW1mKBvuORdgT4dKGtWWASZJBIcL8lx4V8qyOmEFEbetGUqHZ7FJkk0Q8w089JwSUBGb9M7RMIR6fF4%2FA%3D%3D%20agentid%3DWebcenter_CX%20ver%3D1%20crmethod%3D2&ECID-Context=1.005aAZVwkm4Bp2o5oVT4iY0002Oo0041ct%3BkXjE";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void onlineBharat(View view) {
        String url = "https://my.ebharatgas.com/bharatgas/QuickBook/BookAndPay?source=MB";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void onlineHP(View view) {
        String url = "https://myhpgas.in/myHPGas/PortalLogin.aspx?ReturnUrl=%2fmyHPGas%2fHPGas%2fUser%2fBookRefill.aspx";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void smsIndane(View view) {
        String url = "https://indane.co.in/enquiry.php";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void smsBharat(View view) {
        String url = "https://www.bharatpetroleum.com/pdf/BPCL_LPG_sms_Booking.pdf";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void smsHp(View view) {
        String url = "https://www.hindustanpetroleum.com/hpanytime";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void emailUs(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:sawal.14770@lpu.co.in,sofiaroradh@gmail.com,akankshavermafdk20@gmail.com,vamsik586@gmail.com,dhawalsharma26@icloud.com"));
        startActivity(Intent.createChooser(emailIntent, "Send feedback"));
    }
}
