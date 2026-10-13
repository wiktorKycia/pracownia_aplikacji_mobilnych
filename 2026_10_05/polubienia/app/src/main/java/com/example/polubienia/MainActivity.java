package com.example.polubienia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public int Polubienia = 0;

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

        Button polub = findViewById(R.id.polub_button);
        Button usun = findViewById(R.id.usun_button);

        TextView polubienia_text = findViewById(R.id.polubienia_textView);

        polub.setOnClickListener(v -> {
            Polubienia++;
            polubienia_text.setText(String.format("%s polubień", Polubienia));
        });

        usun.setOnClickListener(v -> {
            if (Polubienia > 0)
            {
                Polubienia--;
                polubienia_text.setText(String.format("%s polubień", Polubienia));
            }
        });

    }
}