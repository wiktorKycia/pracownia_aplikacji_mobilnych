package com.example.iot_control;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public EditText et_nr_prania;
    public TextView tv_nr_prania;
    public Button bt_pralka;

    public Button bt_roomba;
    public boolean bt_roomba_clicked = false;
    public TextView tv_roomba;

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

        et_nr_prania = findViewById(R.id.editText_nr_prania);
        tv_nr_prania = findViewById(R.id.textView_nr_prania);
        bt_pralka = findViewById(R.id.buttonPralka);

        bt_pralka.setOnClickListener(v -> {
            int nr_prania = Integer.parseInt(et_nr_prania.getText().toString());

            if (nr_prania > 0 && nr_prania < 13)
            {
                tv_nr_prania.setText(String.format("Numer prania: %s", nr_prania));
            }
        });

        bt_roomba = findViewById(R.id.buttonRoomba);
        tv_roomba = findViewById(R.id.textView_roomba_is_on);

        bt_roomba.setOnClickListener(v -> {
            bt_roomba_clicked = !bt_roomba_clicked;
            if(bt_roomba_clicked)
            {
                bt_roomba.setText("Włącz");
                tv_roomba.setText("Odkurzacz wyłączony");
            } else {
                bt_roomba.setText("Wyłącz");
                tv_roomba.setText("Odkurzacz włączony");
            }
        });
    }
}