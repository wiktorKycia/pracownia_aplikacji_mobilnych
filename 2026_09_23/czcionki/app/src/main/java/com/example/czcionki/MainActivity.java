package com.example.czcionki;

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

    public int textSize = 0;
    public String[] greetings = {"Dzień dobry", "Good morning", "Buenos dias"};
    public int greetingIndex = 0;

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

        final SeekBar seekBar = findViewById(R.id.seekBar);
        final TextView textViewSize = findViewById(R.id.textViewSize);
        final TextView textViewGreeting = findViewById(R.id.textViewGreeting);
        final Button button = findViewById(R.id.button);

        seekBar.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    textSize = progress;
                    textViewSize.setText(String.format("Rozmiar: %s", textSize));
                    textViewGreeting.setTextSize(textSize);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            }
        );

        button.setOnClickListener(v -> {
            if(++greetingIndex > 2)
            {
                greetingIndex = 0;
            }

            textViewGreeting.setText(greetings[greetingIndex]);
        });
    }
}