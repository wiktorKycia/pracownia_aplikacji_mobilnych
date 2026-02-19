package edu.zsk.wiktor_kycia_4d;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReportedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reported);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            String name = bundle.getString("imie");
            String surname = bundle.getString("nazwisko");
            String class_ = bundle.getString("klasa");

            TextView name_text = findViewById(R.id.reported_name);
            TextView surname_text = findViewById(R.id.reported_surname);
            TextView class_text = findViewById(R.id.reported_class);

            name_text.setText(name);
            surname_text.setText(surname);
            class_text.setText(class_);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.save_note_button);
        button.setOnClickListener(v -> {
            finish();
        });
    }
}