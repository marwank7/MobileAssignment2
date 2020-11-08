package com.example.mobileassignment2;



import android.content.Intent;
import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private EditText hr;
    private EditText min;
    private EditText sec;
    private Button timer;





    private int seconds = 0;

    private boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        Intent intent = getIntent();

        hr = findViewById(R.id.hrs);
        min = findViewById(R.id.mins);
        sec = findViewById(R.id.secs);




        if(savedInstanceState !=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }


        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("seconds", seconds);
        bundle.putBoolean("running", running);
    }


    public void onClickStart(View view) {
        running = true;
        int hours = Integer.parseInt(hr.getText().toString());
        int minutes = Integer.parseInt(min.getText().toString());
        int snds = Integer.parseInt(sec.getText().toString());

        seconds = hours*3600 + minutes*60 + snds;
    }

    public void onClickPause(View view) {
        running = false;
    }

    public void onClickStop(View view) {
        int hours = Integer.parseInt(hr.getText().toString());
        int minutes = Integer.parseInt(min.getText().toString());
        int snds = Integer.parseInt(sec.getText().toString());

        seconds = hours*3600 + minutes*60 + snds;
        running = false;

    }

    /*public int onClickTimer(){
        int hours = Integer.parseInt(hr.getText().toString());
        int minutes = Integer.parseInt(min.getText().toString());
        int snds = Integer.parseInt(sec.getText().toString());

        seconds = hours*3600 + minutes*60 + snds;

        return seconds;
    }*/



    private void runTimer(){
        final TextView txtView = (TextView) findViewById(R.id.txtView);
        final Handler handler = new Handler();

        //seconds = onClickTimer();
        handler.post(new Runnable() {




            @Override
            public void run() {



                int Hours = seconds/3600;
                int Minutes = seconds % 3600 /60;
                int Snds = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%02d:%02d:%02d", Hours, Minutes, Snds);
                txtView.setText(time);
                if(running && seconds>0){
                    --seconds;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }



}