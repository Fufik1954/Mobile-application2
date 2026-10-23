package ru.mirea.kartyshovav.scrollviewapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout wrapper = findViewById(R.id.wrapper);

        long value = 1;
        for (int i = 0; i < 100; i++) {
            View view = getLayoutInflater().inflate(R.layout.item, null, false);
            TextView text = view.findViewById(R.id.textView);
            text.setText(String.format("Элемент %d: %d", i + 1, value));
            wrapper.addView(view);

            value *= 2;
        }
    }
}