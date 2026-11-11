package com.example.rgb;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final Button colorOutput   = findViewById(R.id.ColorOutput);
        final SeekBar redSeekBar   = findViewById(R.id.seekBarRed);
        final SeekBar greenSeekBar = findViewById(R.id.seekBarGreen);
        final SeekBar blueSeekBar  = findViewById(R.id.seekBarBlue);
        final TextView redOutput   = findViewById(R.id.RedOutput);
        final TextView greenOutput = findViewById(R.id.GreenOutput);
        final TextView blueOutput  = findViewById(R.id.BlueOutput);
        final Button saveButton    = findViewById(R.id.SaveButton);
        final TextView mainOutput  = findViewById(R.id.MainOutput);

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redOutput.setText(String.format("%s", progress));
                colorOutput.setBackgroundColor(Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenOutput.setText(String.format("%s", progress));
                colorOutput.setBackgroundColor(Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueOutput.setText(String.format("%s", progress));
                colorOutput.setBackgroundColor(Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        saveButton.setOnClickListener(v-> {
            mainOutput.setText(String.format("%s, %s, %s", redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress()));
            mainOutput.setBackgroundColor(Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress()));
        });
    }
}