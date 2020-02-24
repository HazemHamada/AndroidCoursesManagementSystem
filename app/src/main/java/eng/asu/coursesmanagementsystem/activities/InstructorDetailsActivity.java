package eng.asu.coursesmanagementsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.interfaces.ClassAsyncGetInterface;
import eng.asu.coursesmanagementsystem.interfaces.OnCourseListener;
import eng.asu.coursesmanagementsystem.model.Course;
import eng.asu.coursesmanagementsystem.model.Instructor;
import eng.asu.coursesmanagementsystem.model.Track;
import eng.asu.coursesmanagementsystem.services.CoursesAsyncGet;
import eng.asu.coursesmanagementsystem.services.TrackService;
import eng.asu.coursesmanagementsystem.utils.SharedCache;

import static eng.asu.coursesmanagementsystem.utils.SharedCache.accountItem;
import static eng.asu.coursesmanagementsystem.utils.SharedCache.loginItem;
import static eng.asu.coursesmanagementsystem.utils.SharedCache.logoutItem;

public class InstructorDetailsActivity extends AppCompatActivity implements ClassAsyncGetInterface{

    private TextView Vname;
    private TextView Vemail;
    private TextView Vbio;
    private ImageView Vimage;
    private RecyclerView recyclerView;
    private Instructor instructor;

    private List<Track> trackList = new ArrayList<>();
    private CoursesAdapter courseAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
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
        Intent intent = new Intent(InstructorDetailsActivity.this , LoginActivity.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        //String url = "http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png";
        //Instructor i=new Instructor( 1,"name", "email","bio", url );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_details);
        Vname=findViewById(R.id.name);
        Vemail=findViewById(R.id.email);
        Vbio=findViewById(R.id.bio);
        Vimage=findViewById(R.id.image);
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);

        courseAdapter = new CoursesAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(courseAdapter);

        Intent intent = getIntent();
        instructor = (Instructor)intent.getSerializableExtra("instructor");
        CoursesAsyncGet cag = new CoursesAsyncGet();
        cag.execute(this);
        //instructor=i;
        if( instructor != null) {
            Vname.setText(instructor.getName());
            Vemail.setText(instructor.getEmail());
            Vbio.setText(instructor.getBio());


            Glide.with(this)
                    .load("http://3.80.183.111/images/"+instructor.getImageUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(Vimage);

        }

    }

    @Override
    public String[] getParams() {
        Intent intent = getIntent();
        if(intent.hasExtra("instructor")){
            return new String[]{"instructorid", "" + instructor.getId()};



        }
        return new String[0];
    }

    @Override
    public void postExecution(Course[] courses) {
        courseAdapter.setCourses(courses);
    }

}


