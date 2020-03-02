package com.timer.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.timer.timer.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CountDownTimer countDownTimer;
    private long timerTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.startButton.setOnClickListener(v -> {


            String hoursTextViewString = binding.hoursTextView.getText().toString();
            if (!hoursTextViewString.isEmpty()) {
                int hours = Integer.parseInt(hoursTextViewString) * 3600;
                timerTime = timerTime + hours;
            }

            String minutesTextViewS = binding.minutesTextView.getText().toString();
            if (!minutesTextViewS.isEmpty()) {
                int minutes = Integer.parseInt(minutesTextViewS) * 60;
                timerTime = timerTime + minutes;

            }

            String secondsTextViewS = binding.secondsTextView.getText().toString();
            if (!secondsTextViewS.isEmpty()) {
                int secs = Integer.parseInt(secondsTextViewS) % 60;
                timerTime = timerTime + secs;

            }

            setTimer(timerTime);
            countDownTimer.start();
        });

        binding.stopButton.setOnClickListener(v -> {
            countDownTimer.cancel();
            timerTime = 0;
        });

        binding.resetButton.setOnClickListener(v -> {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            timerTime = 0;
            binding.hoursTextView.getText().clear();
            binding.minutesTextView.getText().clear();
            binding.secondsTextView.getText().clear();
            setTimer(timerTime);
        });
    }

    private void setTimer(long time) {
        time = 1000 * time;
        countDownTimer = new CountDownTimer(time, 1) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                binding.hoursTextView.setText(String.format("%d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished)));
                binding.minutesTextView.setText(String.format("%d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                binding.secondsTextView.setText(String.format("%d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)));

            }


            @Override
            public void onFinish() {

                //TODO add saud and mesege
            }
        };
    }
}
