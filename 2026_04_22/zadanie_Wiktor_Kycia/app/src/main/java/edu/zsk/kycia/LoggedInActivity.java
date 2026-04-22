package edu.zsk.kycia;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoggedInActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "2137";
    private static final String CHANNEL_NAME = "Zadanie podsumowujące";

    private String activeFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        fragmentManager = getSupportFragmentManager();

        activeFragment = "first";
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, new FirstFragment());
        transaction.commit();

        Button changeFragmentButton = findViewById(R.id.button);
        changeFragmentButton.setOnClickListener(v -> changeFragment());

        Button showNotificationButton = findViewById(R.id.button2);
        showNotificationButton.setOnClickListener(v -> sendNotification());
    }

    private void changeFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if ("first".equals(activeFragment)) {
            activeFragment = "second";
            transaction.replace(R.id.fragmentHolder, new SecondFragment());
        } else {
            activeFragment = "first";
            transaction.replace(R.id.fragmentHolder, new FirstFragment());
        }
        transaction.commit();
    }

    @SuppressLint("NotificationPermission")
    private void sendNotification() {
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Powiadomienie")
                .setContentText("Wiadomość powiadomienia")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager.notify(1, builder.build());
    }

    public void openDialog() {
        AppDialogFragment dialog = new AppDialogFragment();
        dialog.setCancelable(true);
        dialog.show(fragmentManager, "AppDialogFragment");
    }
}
