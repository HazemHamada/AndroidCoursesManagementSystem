package eng.asu.coursesmanagementsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.interfaces.ClassAsyncGetInterface;
import eng.asu.coursesmanagementsystem.interfaces.OnCourseListener;
import eng.asu.coursesmanagementsystem.model.Course;
import eng.asu.coursesmanagementsystem.services.CoursesAsyncGet;
import eng.asu.coursesmanagementsystem.utils.SharedCache;

import static eng.asu.coursesmanagementsystem.utils.SharedCache.accountItem;
import static eng.asu.coursesmanagementsystem.utils.SharedCache.loginItem;
import static eng.asu.coursesmanagementsystem.utils.SharedCache.logoutItem;

public class ListingActivity extends AppCompatActivity implements OnCourseListener {
    private RecyclerView recyclerView;
    private CoursesAdapter mAdapter;

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
        Intent intent = new Intent(ListingActivity.this , LoginActivity.class);
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
        setContentView(R.layout.activity_course_listing);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mAdapter = new CoursesAdapter(this);

        CoursesAsyncGet cag = new CoursesAsyncGet();

        cag.execute(new ClassAsyncGetInterface() {
            @Override
            public String[] getParams() {
                /** get track id from intent of home **/
                return new String[0];
            }

            @Override
            public void postExecution(Course[] courses) {

                mAdapter.setCourses(courses);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCourseClick(int position) {
        Course[] courses = mAdapter.getCourses();
        Course course = courses[position];
        Intent intent = new Intent(ListingActivity.this, CourseOverview.class);
        intent.putExtra("course", course);
        startActivity(intent);
    }
}
