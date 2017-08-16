package hopspottr.luca.com.hopspottr.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import hopspottr.luca.com.hopspottr.HopSpottrApplication;
import hopspottr.luca.com.hopspottr.R;
import hopspottr.luca.com.hopspottr.model.TrendingItem;
import hopspottr.luca.com.hopspottr.util.SharedPrefManager;

public class TrendingAdapter extends BaseAdapter {

	private Context ctx;
	private ArrayList<TrendingItem> datas;
	
	public TrendingAdapter(Context aCtx) {
		ctx = aCtx;
		datas = new ArrayList<TrendingItem>();
		// initTestData();
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Log.d("ChatAdapter", "getView function , dataset changed");

		TrendingItem data = datas.get(position);
		if (data == null)
			return null;

		ViewHolder holder;
		view = LayoutInflater.from(ctx).inflate(R.layout.trending_adapter, null);

		holder = new ViewHolder();
		holder.ivPhoto = (CircularImageView) view.findViewById(R.id.iv_photo);
		holder.tvFollowers = (TextView) view.findViewById(R.id.tv_follwers);
		holder.tvName = (TextView) view.findViewById(R.id.tv_name);
		holder.tvDistance = (TextView) view.findViewById(R.id.tv_distance);
		holder.tvAddress = (TextView) view.findViewById(R.id.tv_address);
		holder.crowdLevelProgress = (RoundCornerProgressBar) view.findViewById(R.id.crowd_level);
		holder.noiseLevelProgress = (RoundCornerProgressBar) view.findViewById(R.id.noise_level);
		holder.waitLevelProgress = (RoundCornerProgressBar) view.findViewById(R.id.wait_level);

		view.setTag(holder);

		holder = (ViewHolder) view.getTag();

		holder.initHolder(data);

		return view;
	}

	private class ViewHolder {
		private CircularImageView ivPhoto;
		private TextView tvFollowers;
		private TextView tvName;
		private TextView tvDistance;
		private TextView tvAddress;
		private RoundCornerProgressBar crowdLevelProgress;
		private RoundCornerProgressBar noiseLevelProgress;
		private RoundCornerProgressBar waitLevelProgress;

		public void initHolder(final TrendingItem data) {
			Picasso.with(ctx)
					.load(data.getPhotoUrl())
					.into(ivPhoto);
			//ImageLoader.getInstance().displayImage(data.getPhotoUrl(), ivPhoto, HopSpottrApplication.img_opt_photo);
			tvFollowers.setText(String.valueOf(data.getFollower()));
			tvName.setText(data.getName());
			tvDistance.setText(String.valueOf(data.getDistance()));
			tvAddress.setText(data.getAddress());
//			crowdLevelProgress.setProgress(data.getCrowdLevel());
//			noiseLevelProgress.setProgress(data.getNoiseLevel());
//			waitLevelProgress.setProgress(data.getWaitLevel());
		}
	}

	public void clearData() {
		datas.clear();
		return;
	}

	public void addData(TrendingItem data) {
		datas.add(data);
	}
	
	public void removeData(TrendingItem data) {
		datas.remove(data);
	}
}
