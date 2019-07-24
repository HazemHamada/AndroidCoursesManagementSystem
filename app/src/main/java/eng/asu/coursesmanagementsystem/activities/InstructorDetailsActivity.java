package eng.asu.coursesmanagementsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.model.Instructor;
import eng.asu.coursesmanagementsystem.services.DownloadImageTask;

public class InstructorDetailsActivity extends AppCompatActivity {

    private TextView Vname, Vemail, Vphone, Vbio;
    private ImageView Vimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String url = "http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png";
        //Instructor i=new Instructor( 1,"name", "email", "phone","bio", url );


        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_details);
        Vname=findViewById(R.id.name);
        Vemail=findViewById(R.id.email);
        Vphone=findViewById(R.id.phone);
        Vbio=findViewById(R.id.bio);
        Vimage=findViewById(R.id.image);
        //Intent intent=getIntent();
        Instructor instructor;
        instructor= (Instructor) getIntent().getSerializableExtra("Instructor");
        //instructor=i;
        if( instructor != null) {
            Vname.setText(instructor.getName());
            Vemail.setText(instructor.getEmail());
            Vphone.setText(instructor.getPhone());
            Vbio.setText(instructor.getBio());
            // show The Image in a ImageView
            new DownloadImageTask(Vimage).execute(instructor.getImageUrl());
        }

    }

}


