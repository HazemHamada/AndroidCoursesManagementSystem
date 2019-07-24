package eng.asu.coursesmanagementsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.model.Course;
import eng.asu.coursesmanagementsystem.model.Instructor;

public class CourseOverview extends AppCompatActivity {

    Button manageCourse;
    TextView instructorName, courseName, courseDescription, minGPA;
    ImageView courseImage, instructorImage;
    Course course;
    Instructor instructor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    public void onLogin(MenuItem mi)
    {
        Intent intent = new Intent(CourseOverview.this , loginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_overview);
        manageCourse = findViewById(R.id.manageCourse);
        instructorName = findViewById(R.id.instructorName);
        instructorImage = findViewById(R.id.instructorImg);
        courseName = findViewById(R.id.courseName);
        courseImage = findViewById(R.id.courseImg);
        courseDescription = findViewById(R.id.description);
        minGPA = findViewById(R.id.minGpa);

        Intent intent = getIntent();
        course = (Course)intent.getSerializableExtra("course");
        instructor = course.getInstructor();

        SpannableString content = new SpannableString(instructor.getName());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        instructorName.setText(content);
        courseName.setText(course.getName());
        courseDescription.setText(course.getDescription());
        minGPA.setText("Minimum GPA to enroll is "+course.getMinGPA());
        Glide.with(this).load(instructor.getImageUrl()).apply(RequestOptions.circleCropTransform()).into(instructorImage);
        Glide.with(this).load(course.getImageUrl()).into(courseImage);

        instructorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseOverview.this, InstructorDetailsActivity.class);
                intent.putExtra("instructor",instructor);
                startActivity(intent);
            }
        });
    }
}
