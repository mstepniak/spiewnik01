package com.michas.spiewnik01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by MichalS on 2017-11-20.
 */

public class DisplaySong extends AppCompatActivity {

    TextView textViewSong;
    int textSize = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String songText = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);

        TextView textViewTitle = findViewById(R.id.songTitle);
        textViewTitle.setText(title);
        textViewSong = findViewById(R.id.songText);
        textViewSong.setMovementMethod(new ScrollingMovementMethod());
        textViewSong.setText(songText);
        textViewSong.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.font_size, menu);
        MenuItem minus = menu.findItem(R.id.minus);
        MenuItem plus = menu.findItem(R.id.plus);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.minus:
                if (textSize >= 13){
                    textViewSong.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize-1);
                    textSize--;
                }
                return true;

            case R.id.plus:
                if (textSize <=19){
                    textViewSong.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize+1);
                    textSize++;
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}