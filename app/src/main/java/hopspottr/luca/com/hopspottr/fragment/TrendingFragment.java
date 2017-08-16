package hopspottr.luca.com.hopspottr.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hopspottr.luca.com.hopspottr.R;
import hopspottr.luca.com.hopspottr.adapter.TrendingAdapter;
import hopspottr.luca.com.hopspottr.model.TrendingItem;
import hopspottr.luca.com.hopspottr.util.SharedPrefManager;

public class TrendingFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ListView listItems;

    private TextView btnRestaurants;
    private TextView btnBars;
    private TextView btnCoffee;
    private TextView btnHookahBars;

    private Button btnInvite;
    private TextView tvHeaderTitle;
    private TrendingAdapter adapter;

    public TrendingFragment() {
        // Required empty public constructor
    }

    public static TrendingFragment newInstance(String param1, String param2) {
        TrendingFragment fragment = new TrendingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_trending, container, false);

        adapter = new TrendingAdapter(getActivity());

        adapter.addData(new TrendingItem(SharedPrefManager.getInstance(getActivity()).getAvatarUrl(), 40, "Westfield San Francisco", 1.3f, "865 Market Street, San Francisco", 5, 5, 5));

        listItems = (ListView) v.findViewById(R.id.list_item);

        View footerView =  inflater.inflate(R.layout.footer, null, false);
        View headerView =  inflater.inflate(R.layout.header, null, false);
        listItems.addFooterView(footerView);
        //listItems.addHeaderView(headerView);

        listItems.setAdapter(adapter);

        btnRestaurants = (TextView) v.findViewById(R.id.btn_restaurant);
        btnBars = (TextView) v.findViewById(R.id.btn_bars_nightlife);
        btnCoffee = (TextView) v.findViewById(R.id.btn_coffee_tea);
        btnHookahBars = (TextView) v.findViewById(R.id.btn_hookah_bars);

        adapter.notifyDataSetChanged();

        initUI();

        return v;
    }

    private void initUI() {
        btnRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBackground();

                btnRestaurants.setBackground(getResources().getDrawable(R.drawable.button_item_pressed));
                btnRestaurants.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        });

        btnBars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBackground();

                btnBars.setBackground(getResources().getDrawable(R.drawable.button_item_pressed));
                btnBars.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        });

        btnCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBackground();

                btnCoffee.setBackground(getResources().getDrawable(R.drawable.button_item_pressed));
                btnCoffee.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        });

        btnHookahBars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultBackground();

                btnHookahBars.setBackground(getResources().getDrawable(R.drawable.button_item_pressed));
                btnHookahBars.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        });
    }

    private void setDefaultBackground() {
        btnRestaurants.setBackground(getResources().getDrawable(R.drawable.button_item));
        btnRestaurants.setTextColor(getResources().getColor(R.color.colorBlack));

        btnBars.setBackground(getResources().getDrawable(R.drawable.button_item));
        btnBars.setTextColor(getResources().getColor(R.color.colorBlack));

        btnCoffee.setBackground(getResources().getDrawable(R.drawable.button_item));
        btnCoffee.setTextColor(getResources().getColor(R.color.colorBlack));

        btnHookahBars.setBackground(getResources().getDrawable(R.drawable.button_item));
        btnHookahBars.setTextColor(getResources().getColor(R.color.colorBlack));
    }
}
