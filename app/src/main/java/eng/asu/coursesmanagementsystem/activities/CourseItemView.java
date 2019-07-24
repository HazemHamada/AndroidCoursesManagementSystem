package eng.asu.coursesmanagementsystem.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.interfaces.OnCourseListener;
import eng.asu.coursesmanagementsystem.model.Course;

public class CourseItemView extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView image;
    TextView courseNameTextView;
    View parent;
    OnCourseListener ocl;

    public CourseItemView(@NonNull View itemView, View parent, OnCourseListener ocl) {
        super(itemView);
        this.parent = parent;
        this.ocl = ocl;
        courseNameTextView = itemView.findViewById(R.id.course_name);
        image = itemView.findViewById(R.id.course_image);
        itemView.setOnClickListener(this);
    }

    public void bindCourse(Course course){
        String firstCaptialCourse = course.getName().replace('c','C');
        courseNameTextView.setText(firstCaptialCourse);
        Glide.with(parent).load(course.getImageUrl()).into(image);
    }

    @Override
    public void onClick(View view) {
        ocl.onCourseClick(getAdapterPosition());
    }
}
