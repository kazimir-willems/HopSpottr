package hopspottr.luca.com.hopspottr;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by RUYI on 6/11/2017.
 */

public class HopSpottrApplication extends Application {
    private static HopSpottrApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(12))
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static synchronized HopSpottrApplication getInstance() {
        return instance;
    }
}