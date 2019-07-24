package eng.asu.coursesmanagementsystem.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.model.Track;
import eng.asu.coursesmanagementsystem.services.TrackService;
import eng.asu.coursesmanagementsystem.utils.SharedCache;

import static eng.asu.coursesmanagementsystem.utils.SharedCache.accountItem;
import static eng.asu.coursesmanagementsystem.utils.SharedCache.loginItem;
import static eng.asu.coursesmanagementsystem.utils.SharedCache.logoutItem;

public class MainActivity extends AppCompatActivity implements TrackService.TrackBind {

    private RecyclerView recyclerView;

    private List<Track> trackList = new ArrayList<>();
    private TracksAdapter trackAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        super.onCreateOptionsMenu(menu);
        SharedCache sharedCache = new SharedCache(this);
        loginItem = menu.findItem(R.id.loginIcon);
        logoutItem = menu.findItem(R.id.logoutIcon);
        accountItem = menu.findItem(R.id.myCoursesIcon);
        if(sharedCache.loadData().equals(""))
        {
            logoutItem.setVisible(false);
            accountItem.setVisible(false);
        }
        else
        {
            loginItem.setVisible(false);
        }
        return true;
    }

    public void onLogin(MenuItem mi)
    {
        Intent intent = new Intent(MainActivity.this , LoginActivity.class);
        startActivity(intent);
    }

    public void onLogout(MenuItem mi)
    {
       (new SharedCache(this)).deleteData();
        logoutItem.setVisible(false);
        accountItem.setVisible(false);
        loginItem.setVisible(true);
        Toast.makeText(getApplicationContext(),"Logged out Successfully",Toast.LENGTH_SHORT).show();
        invalidateOptionsMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
