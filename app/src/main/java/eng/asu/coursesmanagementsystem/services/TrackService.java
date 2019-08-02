package eng.asu.coursesmanagementsystem.services;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import eng.asu.coursesmanagementsystem.activities.MainActivity;
import eng.asu.coursesmanagementsystem.model.Track;

public class TrackService extends AsyncTask <String, String, List<Track>>{

    private TrackBind trackBind;

    public TrackService(TrackBind t) {
        trackBind = t;
    }

    @Override
    protected List<Track> doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection = null;
        List<Track>tracks= new ArrayList<>();

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("JsonStub-User-Key", "a5a3a085-c9bd-462a-83c5-77620f963745");
            urlConnection.setRequestProperty("JsonStub-Project-Key", "296b28d6-dd31-45c9-810d-cef77decfe75");

            int responseCode = urlConnection.getResponseCode();
            String responseMessage = urlConnection.getResponseMessage();

            if(responseCode == HttpURLConnection.HTTP_OK){
                String responseString = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient-Response", responseString);
                Gson gson = new Gson();
                tracks = parseTrackData(responseString);
            }else{
                Log.v("CatalogClient", "Response code:"+ responseCode);
                Log.v("CatalogClient", "Response message:"+ responseMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }
        return tracks;
    }

    private List<Track> parseTrackData(String jString){

        List<Track> TrackList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JSONObject jObj = new JSONObject(jString);
            JSONArray items = jObj.getJSONArray("tracks");
            if(items != null) {
                    TrackList = (gson.fromJson(items.toString(),new TypeToken<List<Track>>(){}.getType()));
            }
        } catch (JSONException e) {
            Log.e("CatalogClient", "unexpected JSON exception", e);
        }
        return TrackList;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(List<Track> Tracks) {
        super.onPostExecute(Tracks);

        trackBind.setTracks(Tracks);
    }

    public interface TrackBind{
        void setTracks(List<Track> t);
    }
}


