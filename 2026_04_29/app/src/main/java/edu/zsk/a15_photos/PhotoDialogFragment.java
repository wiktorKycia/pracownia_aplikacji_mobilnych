package edu.zsk.a15_photos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PhotoDialogFragment extends DialogFragment {

    private static final String ARG_IMAGE_RES_ID = "image_res_id";

    public static PhotoDialogFragment newInstance(int imageResId) {
        PhotoDialogFragment fragment = new PhotoDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RES_ID, imageResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_photo, container, false);
        ImageView imageView = view.findViewById(R.id.dialogImageView);

        if (getArguments() != null) {
            int imageResId = getArguments().getInt(ARG_IMAGE_RES_ID);
            imageView.setImageResource(imageResId);
        }

        return view;
    }
}
