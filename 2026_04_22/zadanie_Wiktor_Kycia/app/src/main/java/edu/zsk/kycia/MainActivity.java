package edu.zsk.kycia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private SQLiteDatabase db;

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

        initDb();

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
                Intent i = new Intent(this, LoggedInActivity.class);
                startActivity(i);
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
        Cursor cursor = db.rawQuery("SELECT password FROM users WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            String dbPassword = cursor.getString(0);
            cursor.close();
            return dbPassword.equals(password);
        }
        else
        {
            cursor.close();
            return false;
        }
    }

    private void initDb() {
        db = openOrCreateDatabase("UsersDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM users", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count == 0) {
            ContentValues values = new ContentValues();
            values.put("email", "admin@example.com");
            values.put("password", "admin");
            db.insert("users", null, values);

            values = new ContentValues();
            values.put("email", "user1@example.com");
            values.put("password", "user1");
            db.insert("users", null, values);

            values = new ContentValues();
            values.put("email", "user2@example.com");
            values.put("password", "user2");
            db.insert("users", null, values);

            values = new ContentValues();
            values.put("email", "user3@example.com");
            values.put("password", "user3");
            db.insert("users", null, values);
        }
        cursor.close();
    }
}