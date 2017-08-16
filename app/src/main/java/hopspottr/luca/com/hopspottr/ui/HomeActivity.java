package hopspottr.luca.com.hopspottr.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import hopspottr.luca.com.hopspottr.HopSpottrApplication;
import hopspottr.luca.com.hopspottr.R;
import hopspottr.luca.com.hopspottr.fragment.ActivityFragment;
import hopspottr.luca.com.hopspottr.fragment.FavoriteFragment;
import hopspottr.luca.com.hopspottr.fragment.NewsFragment;
import hopspottr.luca.com.hopspottr.fragment.SearchFragment;
import hopspottr.luca.com.hopspottr.fragment.TrendingFragment;
import hopspottr.luca.com.hopspottr.util.SharedPrefManager;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

    private boolean bShowTrack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

        initUI();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList(null);

        View header = navigationView.getHeaderView(0);

        tvFullName = (TextView) header.findViewById(R.id.tv_name);
        ivPhoto = (CircularImageView) header.findViewById(R.id.iv_photo);

        tvFullName.setText(SharedPrefManager.getInstance(this).getFullName());
        Picasso.with(this)
                .load(SharedPrefManager.getInstance(this).getAvatarUrl())
                .into(ivPhoto);
        //ImageLoader.getInstance().displayImage(SharedPrefManager.getInstance(this).getAvatarUrl(), ivPhoto, HopSpottrApplication.img_opt_photo);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_fragment, SearchFragment.newInstance("", ""))
                .commit();
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
                HomeActivity.this.invalidateOptionsMenu();
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
                getSupportActionBar().setTitle(R.string.title_news_feed);

                bShowTrack = false;
                HomeActivity.this.invalidateOptionsMenu();
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

                getSupportActionBar().setTitle(R.string.title_trending);

                bShowTrack = true;
                HomeActivity.this.invalidateOptionsMenu();
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
                getSupportActionBar().setTitle(R.string.title_favorites);

                bShowTrack = true;
                HomeActivity.this.invalidateOptionsMenu();
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
                getSupportActionBar().setTitle(R.string.title_activity);

                bShowTrack = false;
                HomeActivity.this.invalidateOptionsMenu();
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
