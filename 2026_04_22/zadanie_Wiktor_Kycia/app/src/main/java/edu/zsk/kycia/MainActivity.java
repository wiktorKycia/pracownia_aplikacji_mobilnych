package edu.zsk.kycia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

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

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v->{
            EditText emailInput = findViewById(R.id.emailInput);
            EditText passwordInput = findViewById(R.id.paswordInput);

            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            if(email.isEmpty() || password.isEmpty())
            {
                Toast.makeText(this, "Wypełnij wszystkie pola!", Toast.LENGTH_LONG).show();
            }
            else if(checkCredentials(email, password))
            {
                Toast.makeText(this, "Zalogowano!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Niepoprawne dane logowania!", Toast.LENGTH_LONG).show();
                passwordInput.setText("");
            }
        });
    }
    private boolean checkCredentials(String email, String password)
    {
        if(Objects.equals(email, "admin@example.com") && Objects.equals(password, "admin"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}