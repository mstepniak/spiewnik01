package com.michas.spiewnik01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        TextView textViewTitle = findViewById(R.id.about);
        String text = "Aplikacja stworzona dla:\nPTTK nr 7 przy UEK\n\nAutor:\nMichał Stępniak\nmichas1991@gmail.com\nUwagi i błędy proszę kierować na maila\n\n Wersja: v0.2";
        textViewTitle.setText(text);
    }
}
