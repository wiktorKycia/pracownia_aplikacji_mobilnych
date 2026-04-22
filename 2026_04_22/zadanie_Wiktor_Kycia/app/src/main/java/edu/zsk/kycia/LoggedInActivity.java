package edu.zsk.kycia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoggedInActivity extends AppCompatActivity {

    public final String CHANNEL_ID = "2137";
    public final String CHANNEL_NAME = "Zadanie podsumowujące";

    public String activeFragment;
    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logged_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout fragmentHolder = findViewById(R.id.fragmentHolder);

        fragmentManager = new FragmentManager(){};
        Fragment frag = fragmentManager.findFragmentById(R.id.fragment_first);

        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(fragmentHolder.getId(), frag, "fragment_first");
        trans.commit();

        Button changeFragmentButton = findViewById(R.id.button);
        changeFragmentButton.setOnClickListener(v->{
            changeFragment();
        });

    }
    private void changeFragment()
    {

    }
}