package com.example.gra_w_kosci_autor00000000000;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public int[] numbers = new int[5];
    public int score = 0;

    public int randomization_score = 0;

    public ImageView image1 = findViewById(R.id.imageView1);
    public ImageView image2 = findViewById(R.id.imageView2);
    public ImageView image3 = findViewById(R.id.imageView3);
    public ImageView image4 = findViewById(R.id.imageView4);
    public ImageView image5 = findViewById(R.id.imageView5);

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

        Button button = findViewById(R.id.button);
        TextView randomization_score_textview = findViewById(R.id.textView2);
        TextView game_score_textview = findViewById(R.id.textView3);

        button.setOnClickListener(v -> {
            for (int i = 0; i < 5; i++)
            {
                numbers[i] = (int)(Math.random() * 6) + 1;
            }
            randomization_score = calculateScore(numbers);
            score += randomization_score;

            randomization_score_textview.setText(String.format("%s%s", randomization_score_textview.getText(), randomization_score));
            game_score_textview.setText(String.format("%s%s", game_score_textview.getText(), score));

            setImages();
        });

        Button resetButton = findViewById(R.id.button2);
        resetButton.setOnClickListener(v -> {
            randomization_score = 0;
            score = 0;
            numbers = new int[]{0, 0, 0, 0, 0};
            setImages();
        });

    }
    protected void setImages()
    {
        setImage(image1, numbers[0]);
        setImage(image2, numbers[1]);
        setImage(image3, numbers[2]);
        setImage(image4, numbers[3]);
        setImage(image5, numbers[4]);
    }

    protected void setImage(ImageView imageView, int number)
    {
        switch (number)
        {
            case 0: {
                imageView.setImageResource(R.drawable.question);
                break;
            }
            case 1: {
                imageView.setImageResource(R.drawable.k1);
                break;
            }
            case 2: {
                imageView.setImageResource(R.drawable.k2);
                break;
            }
            case 3: {
                imageView.setImageResource(R.drawable.k3);
                break;
            }
            case 4: {
                imageView.setImageResource(R.drawable.k4);
                break;
            }
            case 5: {
                imageView.setImageResource(R.drawable.k5);
                break;
            }
            case 6: {
                imageView.setImageResource(R.drawable.k6);
                break;
            }
        }
    }

    protected int calculateScore(int[] numbers)
    {
        int result = 0;
        for (int i = 1; i <= 6; i++)
        {
            int count = 0;
            for (int number : numbers)
            {
                if (number == i)
                {
                    count++;
                }
            }
            if (count > 1)
            {
                result += count * i;
            }
        }
        return result;
    }
}