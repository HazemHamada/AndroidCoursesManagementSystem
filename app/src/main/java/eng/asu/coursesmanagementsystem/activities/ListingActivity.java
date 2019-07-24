package eng.asu.coursesmanagementsystem.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.interfaces.ClassAsyncGetInterface;
import eng.asu.coursesmanagementsystem.model.Course;
import eng.asu.coursesmanagementsystem.services.CoursesAsyncGet;

public class ListingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CoursesAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_listing);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mAdapter = new CoursesAdapter();

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

        //There is a problem with the context in the next line.
        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mAdapter);
    }
}
