package edu.zsk.zadanko_18_03;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "vet_visit_channel";
    private static final int NOTIFICATION_ID = 1001;

    private String selectedSpecies = "Pies";

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

        createNotificationChannel();

        EditText ownerNameEditText = findViewById(R.id.owner_name_and_surname);
        EditText visitPurposeEditText = findViewById(R.id.cel_wizyty);
        EditText timeEditText = findViewById(R.id.czas);
        TextView ageValueTextView = findViewById(R.id.ile_lat_value);
        TextView resultTextView = findViewById(R.id.result);
        SeekBar ageSeekBar = findViewById(R.id.wiek);
        ListView speciesListView = findViewById(R.id.gatunek_listview);
        Button okButton = findViewById(R.id.ok_button);

        String[] speciesOptions = getResources().getStringArray(R.array.gatunek_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, speciesOptions);
        speciesListView.setAdapter(adapter);

        ageSeekBar.setMax(18);
        ageSeekBar.setProgress(0);
        ageValueTextView.setText(String.valueOf(ageSeekBar.getProgress()));

        speciesListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedSpecies = speciesOptions[position];

            int maxAge;
            switch (selectedSpecies) {
                case "Kot":
                    maxAge = 20;
                    break;
                case "Świnka morska":
                    maxAge = 9;
                    break;
                case "Pies":
                default:
                    maxAge = 18;
                    break;
            }

            ageSeekBar.setMax(maxAge);
            if (ageSeekBar.getProgress() > maxAge) {
                ageSeekBar.setProgress(maxAge);
            }
            ageValueTextView.setText(String.valueOf(ageSeekBar.getProgress()));
        });

        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageValueTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        okButton.setOnClickListener(v -> {
            String owner = ownerNameEditText.getText().toString();
            String species = selectedSpecies;
            String age = String.valueOf(ageSeekBar.getProgress());
            String purpose = visitPurposeEditText.getText().toString();
            String time = timeEditText.getText().toString();

            String output = owner + ", " + species + ", " + age + ", " + purpose + ", " + time;
            resultTextView.setText(output);

            showResultNotification(output);
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Wizyty u weterynarza",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Powiadomienia z podsumowaniem formularza");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void showResultNotification(String output) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Podsumowanie wizyty")
                .setContentText(output)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(output))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
