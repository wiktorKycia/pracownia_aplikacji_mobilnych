package com.example.a2026_02_04;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class FirstFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            FragmentActivity mainActivity = getActivity();
            assert mainActivity != null;
            EditText emailEntry = (EditText) view.findViewById(R.id.editTextEmail);
            String email = emailEntry.getText().toString();

            if(Pattern.matches("^[a-zA-Z0-9.-_]+@[a-zA-Z0-9.-_]+$", email))
            {
                EditText nameEntry = view.findViewById(R.id.editTextText2);
                String name = nameEntry.getText().toString();

                EditText surnameEntry = view.findViewById(R.id.editTextText3);
                String surname = nameEntry.getText().toString();

                if (name.equals("Ryszard") && surname.equals("Pyssa"))
                {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }

}