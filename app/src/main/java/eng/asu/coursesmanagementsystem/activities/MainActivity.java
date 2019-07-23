package eng.asu.coursesmanagementsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.interfaces.ClassAsyncGetInterface;
import eng.asu.coursesmanagementsystem.model.Course;
import eng.asu.coursesmanagementsystem.services.CoursesAsyncGet;
import eng.asu.coursesmanagementsystem.services.GetRequestService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CoursesAsyncGet runner = new CoursesAsyncGet();
        ClassAsyncGetInterface[] params ;
        runner.execute(new ClassAsyncGetInterface(){
            @Override
            public String[] getParams() {
                return new String[0];
            }

            @Override
            public void postExecution(Course[] courses) {
                for(Course c: courses)
                    System.out.println(c.getName());
            }
        });
    }

}
