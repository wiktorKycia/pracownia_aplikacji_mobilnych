package edu.zsk.wiktor_kycia_4d;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;

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

        Button button = findViewById(R.id.save_note_button);
        button.setOnClickListener(v -> {
            EditText name_input = findViewById(R.id.name_input);
            EditText surname_input = findViewById(R.id.surname_input);
            EditText class_input = findViewById(R.id.class_input);

            if(name_input.getText().toString().isEmpty()
            || surname_input.getText().toString().isEmpty()
            || class_input.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                String name = name_input.getText().toString();
                String surname = surname_input.getText().toString();
                String class_ = class_input.getText().toString();

                var i = new Intent();

                var pd = new ProgressDialog("Dodaję uwagę");
            }
        });
    }
}