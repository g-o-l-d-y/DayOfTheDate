package com.sample.dayofthedate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText etdob;
    Button btnday;
    TextView tvresults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etdob=findViewById(R.id.etdob);
        btnday=findViewById(R.id.btnday);
        tvresults=findViewById(R.id.tvresults);
        tvresults.setVisibility(View.GONE);
        btnday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dob = etdob.getText().toString().trim();
                int day,month,year,yearcode,monthcode,centurycode,leapyearcode;
                String resultday="";
                day=Integer.parseInt(dob.substring(0,2));
                month=Integer.parseInt(dob.substring(2,4));
                year=Integer.parseInt(dob.substring(4,8));

                yearcode=year%100;
                yearcode=(yearcode+(yearcode/4))%7;

                if(month==1 || month==10)
                    monthcode=0;
                else if(month==2 || month==3 || month==11)
                    monthcode=3;
                else if(month==4 || month==7)
                    monthcode=6;
                else if(month==5)
                    monthcode=1;
                else if(month==6)
                    monthcode=4;
                else if(month==8)
                    monthcode=2;
                else
                    monthcode=5;

                centurycode=year/100;
                if((centurycode-1)%4==0)
                    centurycode=4;
                else if((centurycode-2)%4==0)
                    centurycode=2;
                else if((centurycode-3)%4==0)
                    centurycode=0;
                else
                    centurycode=6;

                if((year%4==0 && year%100!=0)||(year%400==0))
                    leapyearcode=1;
                else
                    leapyearcode=0;
                day=(yearcode+monthcode+centurycode+day+leapyearcode)%7;
                if(leapyearcode==1)
                    day-=1;
                if(day==0)
                    resultday=getString(R.string.sunday);
                else if(day==1)
                    resultday=getString(R.string.monday);
                else if(day==2)
                    resultday=getString(R.string.tuesday);
                else if(day==3)
                    resultday=getString(R.string.wednesday);
                else if(day==4)
                    resultday=getString(R.string.thursday);
                else if(day==5)
                    resultday=getString(R.string.friday);
                else if(day==6)
                    resultday=getString(R.string.saturday);

                tvresults.setText(resultday);
                tvresults.setVisibility(View.VISIBLE);

            }
        });
    }
}
