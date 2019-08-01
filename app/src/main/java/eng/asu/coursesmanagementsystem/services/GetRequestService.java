package eng.asu.coursesmanagementsystem.services;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequestService {
    static String getRequest(String itemName, String link, String[] params){
        String ans="";
        HttpURLConnection urlConnection = null;
        try {
            if(params.length >=0)
                link = link + "?" + params[0] + "=" + params[1];
            URL url = new URL(link);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("JsonStub-User-Key", "a5a3a085-c9bd-462a-83c5-77620f963745");
            urlConnection.setRequestProperty("JsonStub-Project-Key", "296b28d6-dd31-45c9-810d-cef77decfe75");
            urlConnection.getResponseMessage();


            int responseCode = urlConnection.getResponseCode();
            String responseMessage = urlConnection.getResponseMessage();
            ans = readStream(urlConnection.getInputStream());
            JSONObject jsonans = new JSONObject(ans);
            ans = jsonans.getString(itemName);

        }
        catch (IOException | JSONException e){
            e.printStackTrace();
        }
        finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }

        return ans;

    }

    private static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
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
}
