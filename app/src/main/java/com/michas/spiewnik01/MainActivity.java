package com.michas.spiewnik01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.michals.spiewnik01.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.michals.spiewnik01.MESSAGE2";
    ListView ListViewCountry;
    ArrayAdapter<String> adapter;
    public static HashMap<String, String> m_li = new HashMap<>();


    Object listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("songs");
            ArrayList<String> arraySongs = new ArrayList<>();

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String song_value = jo_inside.getString("song");
                String text_value = jo_inside.getString("text");

                m_li.put(song_value, text_value);
                arraySongs.add(song_value);

            }
            ListViewCountry = (ListView)findViewById(R.id.ListViewCountry);
            adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arraySongs); //formList

            ListViewCountry.setAdapter(adapter);
            ListViewCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    listItem = ListViewCountry.getItemAtPosition(i);
                    sendMessage();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        Intent intent = new Intent(this, DisplaySong.class);
        intent.putExtra(EXTRA_MESSAGE, listItem.toString());
        intent.putExtra(EXTRA_MESSAGE2, m_li.get(listItem.toString()));
        startActivity(intent);
    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("songs.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);

        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
