package com.example.vet;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public ListView speciesList;
    public EditText ownerNameAndSurnameEditText;
    public TextView petAgeTextView;
    public SeekBar petAgeSeekBar;
    public EditText visitGoalEditText;
    public EditText visitTimeEditText;
    public Button okButton;

    public TextView resultText;

    public String selectedSpecies;

    private static final String[] species = {"Pies", "Kot", "Świnka morska"};

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

        speciesList = findViewById(R.id.speciesList);
        ownerNameAndSurnameEditText = findViewById(R.id.ownerNameAndSurname);
        petAgeTextView = findViewById(R.id.petAgeText);
        petAgeSeekBar = findViewById(R.id.petAgeSeekBar);
        visitGoalEditText = findViewById(R.id.visitGoal);
        visitTimeEditText = findViewById(R.id.visitTime);
        okButton = findViewById(R.id.okButton);
        resultText = findViewById(R.id.result);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, species);
        speciesList.setAdapter(adapter);

        speciesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = parent.getItemAtPosition(position);
                if(listItem != null)
                {
                    switch(listItem.toString())
                    {
                        case "Pies":
                            petAgeSeekBar.setMax(18);
                            selectedSpecies = "Pies";
                            break;
                        case "Kot":
                            petAgeSeekBar.setMax(20);
                            selectedSpecies = "Kot";
                            break;
                        case "Świnka morska":
                            petAgeSeekBar.setMax(9);
                            selectedSpecies = "Świnka morska";
                            break;
                    }
                }
            }
        });

        petAgeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                petAgeTextView.setText(String.format("%s", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        okButton.setOnClickListener(v -> {
            String result = "";

            result += ownerNameAndSurnameEditText.getText().toString();

            result += ", " + selectedSpecies;

            result += ", " + petAgeTextView.getText().toString();

            result += ", " + visitGoalEditText.getText().toString();

            result += ", " + visitTimeEditText.getText().toString();

            resultText.setText(result);
        });
    }
}