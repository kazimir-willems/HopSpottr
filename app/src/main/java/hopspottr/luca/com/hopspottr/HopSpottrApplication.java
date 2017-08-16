package hopspottr.luca.com.hopspottr;

import android.app.Application;
import android.graphics.Bitmap;

public class HopSpottrApplication extends Application {
    private static HopSpottrApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static synchronized HopSpottrApplication getInstance() {
        return instance;
    }
}