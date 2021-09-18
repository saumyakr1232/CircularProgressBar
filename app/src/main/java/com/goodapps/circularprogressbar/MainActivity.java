package com.goodapps.circularprogressbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CircleView circleView;
    private Button animateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleView = findViewById(R.id.progress_circular);
        animateButton = findViewById(R.id.btnAnimate);

        animateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circleView.animateProgress();
            }
        });
    }


}