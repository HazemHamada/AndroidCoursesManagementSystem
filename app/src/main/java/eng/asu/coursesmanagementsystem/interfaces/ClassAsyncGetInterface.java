package eng.asu.coursesmanagementsystem.interfaces;

import java.util.ArrayList;

import eng.asu.coursesmanagementsystem.model.Course;

public interface ClassAsyncGetInterface {
    String[] getParams();
    void postExecution(Course[] courses);
}