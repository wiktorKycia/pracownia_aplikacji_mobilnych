package edu.zsk.kycia;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {

    private static final String[] LIST_ITEMS = {
            "Programowanie obiektowe",
            "Programowanie Aplikacji Mobilnych",
            "Programowanie Aplikacji Internetowych",
            "Programowanie Aplikacji Webowych",
            "Programowanie Aplikacji Desktopowych"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                LIST_ITEMS
        );

        ListView listView = findViewById(R.id.list);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
    }
}