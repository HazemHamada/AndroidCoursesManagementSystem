package eng.asu.coursesmanagementsystem.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eng.asu.coursesmanagementsystem.R;
import eng.asu.coursesmanagementsystem.model.Track;
import eng.asu.coursesmanagementsystem.model.TrackItemView;

public class TracksAdapter extends RecyclerView.Adapter<TrackItemView>
{
    private List<Track> trackList;

    public TracksAdapter()
    {

    }

    public void setTrackList(List<Track> tracks) {
        trackList = tracks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrackItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_track, parent, false);
        return new TrackItemView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackItemView holder, int position)
    {
        holder.bindTrack(trackList.get(position));
    }

    @Override
    public int getItemCount()
    {
        try
        {return trackList.size();}
        catch
        (Exception e){return 0;}
    }
}

