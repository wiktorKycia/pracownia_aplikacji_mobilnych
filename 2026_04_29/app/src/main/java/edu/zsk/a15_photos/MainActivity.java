package edu.zsk.a15_photos;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

        LinearLayout photoContainer = findViewById(R.id.photoContainer);

        // Używamy dostępnych ikon jako przykładów (np. systemowych lub launcherów)
        int[] images = {
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground,
                android.R.drawable.ic_menu_gallery,
                android.R.drawable.ic_menu_camera,
                android.R.drawable.ic_menu_slideshow,
                android.R.drawable.ic_menu_view,
                android.R.drawable.ic_menu_manage,
                android.R.drawable.ic_menu_info_details,
                android.R.drawable.ic_menu_search,
                android.R.drawable.ic_menu_share,
                android.R.drawable.ic_menu_add,
                android.R.drawable.ic_menu_delete,
                android.R.drawable.ic_menu_edit,
                android.R.drawable.ic_menu_save,
                android.R.drawable.ic_menu_help
        };

        for (int imageResId : images) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300, 300);
            lp.setMargins(8, 8, 8, 8);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(imageResId);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setClickable(true);
            imageView.setFocusable(true);
            imageView.setBackgroundResource(android.R.drawable.list_selector_background);

            imageView.setOnClickListener(v -> {
                PhotoDialogFragment dialog = PhotoDialogFragment.newInstance(imageResId);
                dialog.show(getSupportFragmentManager(), "photo_dialog");
            });

            photoContainer.addView(imageView);
        }
    }
}
