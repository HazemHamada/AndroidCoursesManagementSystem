package eng.asu.coursesmanagementsystem.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.model.Course;

public class CoursesAdapter extends RecyclerView.Adapter<CourseItemView> {

    private Course[] courses;

    public CoursesAdapter() {

    }

    public CoursesAdapter(Course[] courses) {
        this.courses = courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_course,
                viewGroup, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return new CourseItemView(itemView, viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseItemView coursesItemView, int i) {
        coursesItemView.bindCourse(courses[i]);
    }

    @Override
    public int getItemCount() {
        try{
            return courses.length;
        } catch(NullPointerException e){
            return 0;
        }
    }
}

