package eng.asu.coursesmanagementsystem.services;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import eng.asu.coursesmanagementsystem.interfaces.ClassAsyncGetInterface;
import eng.asu.coursesmanagementsystem.model.Course;

public class CoursesAsyncGet extends AsyncTask<ClassAsyncGetInterface,Integer, Course[]> {
    private ClassAsyncGetInterface callback;
    @Override
    protected Course[] doInBackground(ClassAsyncGetInterface... classAsyncGetInterfaces) {
        callback = classAsyncGetInterfaces[0];
        String[] params = callback.getParams();
        return getCourses(params);
    }


    @Override
    protected void onPostExecute(Course[] courses) {
        // just added super to see why there is an error
        super.onPostExecute(courses);
        callback.postExecution(courses);
    }


    private Course[] getCourses(String[] params){

        String json = GetRequestService.getRequest("courses","http://jsonstub.com/load-courses",params);
        return new Gson().fromJson(json, Course[].class);
    }

}
