package com.michas.spiewnik01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * Created by MichalS on 2017-11-20.
 */

public class DisplaySong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String dupa = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);

        TextView textViewTitle = findViewById(R.id.songTitle);
        textViewTitle.setText(message);
        TextView textViewSong = findViewById(R.id.songText);
        textViewSong.setMovementMethod(new ScrollingMovementMethod());
        textViewSong.setText(dupa);
    }
}
