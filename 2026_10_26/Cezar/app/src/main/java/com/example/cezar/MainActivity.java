package com.example.cezar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
jak otworzyć zapisany plik w android studio?
wyszukaj Device Explorer
przejdź do data/data/com.example.cezar/files
tam będzie plik encryptedValue.txt
kliknij go 2 razy i się pokaże podgląd tutaj
 */

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

        EditText inputKey = findViewById(R.id.keyInput);
        EditText inputText = findViewById(R.id.textInput);
        TextView outputText = findViewById(R.id.outputText);
        Button encryptButton = findViewById(R.id.encryptButton);
        Button saveButton = findViewById(R.id.saveButton);

        encryptButton.setOnClickListener(v->{
            int key = Integer.parseInt(inputKey.getText().toString());
            String text = inputText.getText().toString();

            outputText.setText(encrypt(text, key));
        });

        saveButton.setOnClickListener(v->{
            var context = this;
            try {
                FileOutputStream fos = context.openFileOutput("encryptedValue.txt", context.MODE_PRIVATE);
                fos.write(outputText.getText().toString().getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    protected String encrypt(String text, int key)
    {
        String output = "";

        while(key > 26)
            key -= 26;
        while(key < -26)
            key += 26;

        for(int i = 0; i < text.length(); i++)
        {
            char letter = text.charAt(i);
            int code = (int)letter;
            int newCode = code + key;

            if(newCode > 122)
                newCode -= 26;
            else if(newCode < 97)
                newCode += 26;

            if(code >= 97 && code <= 122)
                output += (char)(newCode);
        }

        return output;
    }
}