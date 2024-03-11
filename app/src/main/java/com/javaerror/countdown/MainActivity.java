package com.javaerror.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView countdown_timer;
    CountDownTimer timer;
    Button start;
    EditText editTextText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdown_timer= findViewById(R.id.countdown_timer);
        start= findViewById(R.id.start);
        editTextText= findViewById(R.id.editTextText);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    timer.cancel();
                }
                startTime();

                
            }
        });
    }

    private void startTime() {




        if (editTextText.length() == 0) {

            editTextText.setError("please give your time");
        } else {


        String timerset = editTextText.getText().toString();
        long timeValue = Long.parseLong(timerset);




            timer = new CountDownTimer(timeValue * 1000 * 60, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    long hours = (millisUntilFinished / 1000) / 3600;
                    long minutes = ((millisUntilFinished / 1000) % 3600) / 60;
                    long seconds = (millisUntilFinished / 1000) % 60;

                    String timeformate = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
                    countdown_timer.setText(timeformate);

                }

                @Override
                public void onFinish() {
                    countdown_timer.setText("00:00:00");
                    Toast.makeText(MainActivity.this, "Time's up", Toast.LENGTH_SHORT).show();
                    MediaPlayer alarm = MediaPlayer.create(MainActivity.this, R.raw.sound);
                    alarm.start();



                }
            }.start();


        }
    }

}