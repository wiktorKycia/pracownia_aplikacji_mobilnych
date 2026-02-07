package com.example.a2026_02_04;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

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

//        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(v -> {
//            EditText emailEntry = (EditText) findViewById(R.id.editTextEmail);
//            String email = emailEntry.getText().toString();
//
//            if(Pattern.matches("^[a-zA-Z0-9.-_]+@[a-zA-Z0-9.-_]+$", email))
//            {
//                EditText nameEntry = findViewById(R.id.editTextText2);
//                String name = nameEntry.getText().toString();
//
//                EditText surnameEntry = findViewById(R.id.editTextText3);
//                String surname = nameEntry.getText().toString();
//
//                if (name.equals("Ryszard") && surname.equals("Pyssa"))
//                {
//
//                }
//            }
//        });
    }
}