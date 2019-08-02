package eng.asu.coursesmanagementsystem.model;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.activities.ListingActivity;

public class TrackItemView extends RecyclerView.ViewHolder {
    private Button name;
    private int id;

    public TrackItemView(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.trackBtn);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListingActivity.class);
                intent.putExtra("track",id);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void bindTrack(Track t){name.setText(t.getName()); this.id = t.getId();}
}
