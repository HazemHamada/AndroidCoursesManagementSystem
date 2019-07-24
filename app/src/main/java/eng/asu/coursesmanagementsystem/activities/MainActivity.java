package eng.asu.coursesmanagementsystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.model.Track;
import eng.asu.coursesmanagementsystem.services.TrackService;

public class MainActivity extends AppCompatActivity implements TrackService.TrackBind {

    private RecyclerView recyclerView;

    private static List<Track> trackList = new ArrayList<>();
    private static TracksAdapter trackAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    public void onLogin(MenuItem mi)
    {
        Intent intent = new Intent(MainActivity.this , loginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);

        trackAdapter = new TracksAdapter();

        new TrackService(this).execute("http://jsonstub.com/load-tracks");

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setAdapter(trackAdapter);
        recyclerView.setLayoutManager(mlayoutManager);

    }

    @Override
    public void setTracks(List<Track> t)
    {
        trackAdapter.setTrackList(t);
    }




}