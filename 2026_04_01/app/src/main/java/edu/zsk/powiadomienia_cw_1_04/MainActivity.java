package edu.zsk.powiadomienia_cw_1_04;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
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

        // Create notification channel for Android 8.0+
        createNotificationChannel();

        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(v -> this.onClick(v));

        button2.setOnClickListener(v -> this.onClick2(v));
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "default_channel";
            String channelName = "Default Channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    public void onClick(View view)
    {
        Intent secondActivity = new Intent(this, Activity2.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, secondActivity, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        Uri ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification notification = new NotificationCompat.Builder(this, "default_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Powiadomienie")
                .setContentText("treść")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(ringtone)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int id = 1;
        notificationManager.notify(id, notification);
    }

    public void onClick2(View view)
    {
        Intent newActivity = new Intent(this, Activity3.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, newActivity, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        Uri ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification notification = new NotificationCompat.Builder(this, "default_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Powiadomienie")
                .setContentText("treść")
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setSound(ringtone)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int id = 1;
        notificationManager.notify(id, notification);
    }


}