package com.whatsappsaver.sdm;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import arabware.libs.getThumbnail.*;
import com.google.android.material.appbar.AppBarLayout;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class SavedActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String videopath = "";
	private String lmage = "";
	private double num = 0;
	
	private ArrayList<String> srt = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> maps = new ArrayList<>();
	
	private GridView gridview1;
	private TextView textview2;
	
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.saved);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		gridview1 = findViewById(R.id.gridview1);
		textview2 = findViewById(R.id.textview2);
	}
	
	private void initializeLogic() {
		if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/Status saver"))) {
			FileUtil.listDir(FileUtil.getExternalStorageDir().concat("/Status saver"), srt);
			textview2.setVisibility(View.GONE);
			gridview1.setVisibility(View.VISIBLE);
		}
		else {
			textview2.setVisibility(View.VISIBLE);
			gridview1.setVisibility(View.GONE);
		}
		for(int _repeat21 = 0; _repeat21 < (int)(srt.size()); _repeat21++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("file", srt.get((int)(num)));
				maps.add(_item);
			}
			
		}
		gridview1.setAdapter(new Gridview1Adapter(maps));
		gridview1.setNumColumns((int)2);
	}
	
	public void _sharevideo(final String _path) {
		videopath = _path;
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		sendIntent.setType("video/mp4");
		sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(videopath));
		startActivity(Intent.createChooser(sendIntent, "Share"));
	}
	
	
	public void _sharelmage(final String _path) {
		lmage = _path;
		Uri imgPath= Uri.parse(lmage);
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_STREAM, imgPath);
		sendIntent.setType("image/jpg");
		Intent shareIntent = Intent.createChooser(sendIntent, null);
		startActivity(shareIntent);
	}
	
	
	public void _setBitmapFromVideo(final ImageView _imageview, final String _path) {
		class ContextClass {
			
			private Context myContext;
			
			public ContextClass(Context activity) {
				myContext = activity;
			}
			
			public ContextClass(Fragment fragment) {
				myContext = fragment.getActivity();
			}
			
			public ContextClass(DialogFragment fragment) {
				myContext = fragment.getActivity();
			}
			
			public final Context getContext() {
				return myContext;
			}
		}
		_imageview.setImageBitmap(new VideoThumbnail(new ContextClass(this).getContext()).fromPath(_path));
	}
	
	public class Gridview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.custom, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final ImageView imageview4 = _view.findViewById(R.id.imageview4);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			
			imageview3.setVisibility(View.INVISIBLE);
			imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(srt.get((int)(_position)), 1024, 1024));
			if (srt.get((int)(_position)).endsWith(".jpg")) {
				imageview1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						i.putExtra("jpg", "saved");
						i.putExtra("img", srt.get((int)(_position)));
						i.setClass(getApplicationContext(), ImageActivity.class);
						startActivity(i);
					}
				});
				imageview2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_sharelmage(srt.get((int)(_position)));
					}
				});
			}
			else {
				if (srt.get((int)(_position)).endsWith(".mp4")) {
					_setBitmapFromVideo(imageview1, srt.get((int)(_position)));
					imageview1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							i.putExtra("video", "saved");
							i.putExtra("vdo", srt.get((int)(_position)));
							i.setClass(getApplicationContext(), VideoActivity.class);
							startActivity(i);
						}
					});
					imageview2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							_sharevideo(srt.get((int)(_position)));
						}
					});
				}
				else {
					
				}
			}
			{android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
					0xFF66BB6A,
					0xFF66BB6A,
				});
				gd.setCornerRadius(20);
				linear1.setBackgroundDrawable(gd);
				linear1.setElevation(5);};
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}