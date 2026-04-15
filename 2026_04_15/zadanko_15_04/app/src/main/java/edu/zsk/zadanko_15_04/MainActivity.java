package edu.zsk.zadanko_15_04;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public int iterator = 0;

    // Keys for saving state
    private static final String KEY_ITERATOR = "iterator_count";
    private static final String KEY_TXT1_TEXT = "txt1_content";

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

        Button button = findViewById(R.id.button);
        EditText nameInput = findViewById(R.id.name);
        EditText emailInput = findViewById(R.id.emailAddress);
        TextView txt1 = findViewById(R.id.txt1);
        TextView txt2 = findViewById(R.id.txt2);

        // Restore state if savedInstanceState is not null
        if (savedInstanceState != null) {
            iterator = savedInstanceState.getInt(KEY_ITERATOR);
            txt1.setText(savedInstanceState.getString(KEY_TXT1_TEXT));
            txt2.setText("Kliknąłeś przycisk " + iterator + " razy");
        }

        button.setOnClickListener(v-> {
            String name = nameInput.getText().toString();
            String email = emailInput.getText().toString();

            if(name.isEmpty() || email.isEmpty())
            {
                Toast.makeText(this, "Najpierw uzupełnij swoje dane", Toast.LENGTH_SHORT).show();
            }
            else
            {
                iterator++;
                txt2.setText("Kliknąłeś przycisk " + iterator + " razy");
                txt1.setText("Witaj " + name + "! Twój adres email to: " + email);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the iterator value
        outState.putInt(KEY_ITERATOR, iterator);

        // Save the current text of txt1
        TextView txt1 = findViewById(R.id.txt1);
        outState.putString(KEY_TXT1_TEXT, txt1.getText().toString());
    }
}