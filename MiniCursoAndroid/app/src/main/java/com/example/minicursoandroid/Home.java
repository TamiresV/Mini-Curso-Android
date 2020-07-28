package com.example.minicursoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView tvEmailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvEmailUser = findViewById(R.id.tvEmailUser);
        String emailUser = getIntent().getStringExtra("EMAIL");
        tvEmailUser.setText(emailUser);
    }
}