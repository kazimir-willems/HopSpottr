package hopspottr.luca.com.hopspottr.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "HopSpottrPreference";
    private static final String TAG_AVATAR_URL = "avatar_url";
    private static final String TAG_FULL_NAME = "full_name";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will save the device token to shared preferences
    public boolean saveAvatarUrl(String avatarUrl){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_AVATAR_URL, avatarUrl);
        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences
    public String getAvatarUrl(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_AVATAR_URL, null);
    }

    public boolean saveFullName(String name){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_FULL_NAME, name);
        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences
    public String getFullName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_FULL_NAME, null);
    }
}