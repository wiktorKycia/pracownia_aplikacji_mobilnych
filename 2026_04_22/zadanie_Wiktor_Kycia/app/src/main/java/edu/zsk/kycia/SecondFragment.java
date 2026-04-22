package edu.zsk.kycia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        Button dialogButton = view.findViewById(R.id.dialogFragmentOpenButton);
        dialogButton.setOnClickListener(v -> {
            if (getActivity() instanceof LoggedInActivity) {
                ((LoggedInActivity) getActivity()).openDialog();
            }
        });

        return view;
    }
}