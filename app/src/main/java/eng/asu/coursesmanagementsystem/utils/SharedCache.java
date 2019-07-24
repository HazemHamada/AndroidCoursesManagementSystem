package eng.asu.coursesmanagementsystem.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedCache {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXT="text";

    public SharedCache(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }



    public void saveDate(String userEmail){
        editor.putString(TEXT,userEmail);
        editor.apply();

    }

    public String loadData(){
        String email = sharedPreferences.getString(TEXT,"");
        return email;
    }


}
