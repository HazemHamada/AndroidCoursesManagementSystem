package eng.asu.coursesmanagementsystem.model;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.activities.ListingActivity;
import eng.asu.coursesmanagementsystem.activities.loginActivity;

public class TrackItemView extends RecyclerView.ViewHolder {
    private Button name;

    public TrackItemView(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.trackBtn);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), loginActivity.class);
                intent.putExtra("track",getAdapterPosition());
                view.getContext().startActivity(intent);
            }
        });
    }

    public void bindTrack(Track t){name.setText(t.getName());}
}
