package hopspottr.luca.com.hopspottr.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import hopspottr.luca.com.hopspottr.R;
import hopspottr.luca.com.hopspottr.fragment.ActivityFragment;
import hopspottr.luca.com.hopspottr.fragment.FavoriteFragment;
import hopspottr.luca.com.hopspottr.fragment.NewsFragment;
import hopspottr.luca.com.hopspottr.fragment.SearchFragment;
import hopspottr.luca.com.hopspottr.fragment.TrendingFragment;
import hopspottr.luca.com.hopspottr.util.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    private TextView tvFullName;
    private CircularImageView ivPhoto;

    private ImageView ivSearch;
    private ImageView ivNews;
    private ImageView ivTrending;
    private ImageView ivFavorite;
    private ImageView ivActivity;

    private LinearLayout searchLayout;
    private LinearLayout newsLayout;
    private LinearLayout trendingLayout;
    private LinearLayout favoriteLayout;
    private LinearLayout activityLayout;

    private TextView tvTitle;

    private boolean bShowTrack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ivSearch = (ImageView) findViewById(R.id.iv_search);
        ivNews = (ImageView) findViewById(R.id.iv_news);
        ivTrending = (ImageView) findViewById(R.id.iv_trending);
        ivFavorite = (ImageView) findViewById(R.id.iv_favorite);
        ivActivity = (ImageView) findViewById(R.id.iv_activity);

        searchLayout = (LinearLayout) findViewById(R.id.search_layout);
        newsLayout = (LinearLayout) findViewById(R.id.news_layout);
        trendingLayout = (LinearLayout) findViewById(R.id.trending_layout);
        favoriteLayout = (LinearLayout) findViewById(R.id.favorite_layout);
        activityLayout = (LinearLayout) findViewById(R.id.activity_layout);

        tvTitle = (TextView) findViewById(R.id.toolbar_title);

        new SlidingRootNavBuilder(this)
                .withDragDistance(220)
                .withRootViewScale(1.0f)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_drawer)
                .inject();

        initUI();

        tvFullName = (TextView) findViewById(R.id.tv_name);
        ivPhoto = (CircularImageView) findViewById(R.id.iv_photo);

        tvFullName.setText(SharedPrefManager.getInstance(this).getFullName());
        Picasso.with(this)
                .load(SharedPrefManager.getInstance(this).getAvatarUrl())
                .into(ivPhoto);

        getSupportActionBar().setTitle("");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_fragment, SearchFragment.newInstance("", ""))
                .commit();
        MainActivity.this.invalidateOptionsMenu();
    }

    private void initUI() {
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultIcon();
                ivSearch.setBackground(getResources().getDrawable(R.drawable.ic_search_selected));

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_fragment, SearchFragment.newInstance("", ""))
                        .commit();
                bShowTrack = false;
                MainActivity.this.invalidateOptionsMenu();
            }
        });

        newsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultIcon();
                ivNews.setBackground(getResources().getDrawable(R.drawable.ic_news_selected));

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_fragment, NewsFragment.newInstance("", ""))
                        .commit();
                tvTitle.setText(getResources().getString(R.string.title_news_feed));

                bShowTrack = false;
                MainActivity.this.invalidateOptionsMenu();
            }
        });

        trendingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultIcon();
                ivTrending.setBackground(getResources().getDrawable(R.drawable.ic_trending_selected));

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_fragment, TrendingFragment.newInstance("", ""))
                        .commit();

                tvTitle.setText(R.string.title_trending);

                bShowTrack = true;
                MainActivity.this.invalidateOptionsMenu();
            }
        });

        favoriteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultIcon();
                ivFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_selected));

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_fragment, FavoriteFragment.newInstance("", ""))
                        .commit();
                tvTitle.setText(R.string.title_favorites);

                bShowTrack = true;
                MainActivity.this.invalidateOptionsMenu();
            }
        });

        activityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultIcon();
                ivActivity.setBackground(getResources().getDrawable(R.drawable.ic_active_selected));

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_fragment, ActivityFragment.newInstance("", ""))
                        .commit();
                tvTitle.setText(R.string.title_activity);

                bShowTrack = false;
                MainActivity.this.invalidateOptionsMenu();
            }
        });
    }

    private void setDefaultIcon() {
        ivSearch.setBackground(getResources().getDrawable(R.drawable.ic_search));
        ivNews.setBackground(getResources().getDrawable(R.drawable.ic_news));
        ivTrending.setBackground(getResources().getDrawable(R.drawable.ic_trending));
        ivFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite));
        ivActivity.setBackground(getResources().getDrawable(R.drawable.ic_activity));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        if (menu != null) {
            if(!bShowTrack) {
                menu.findItem(R.id.menu_track_location).setVisible(false);
            } else {
                menu.findItem(R.id.menu_track_location).setVisible(true);
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

        }

        return super.onOptionsItemSelected(item);
    }
}
