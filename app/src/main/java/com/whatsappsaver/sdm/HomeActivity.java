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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import android.os.*;
import java.util.concurrent.*;

public class HomeActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private String videopath = "";
	private String lmage = "";
	private double num = 0;
	private String savepath = "";
	private double pos = 0;
	private String path = "";
	private String URL = "";
	
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	
	private GridView gridview1;
	private LinearLayout _drawer_linear1;
	private LinearLayout _drawer_linear7;
	private LinearLayout _drawer_linear2;
	private LinearLayout _drawer_linear5;
	private LinearLayout _drawer_linear6;
	private ImageView _drawer_imageview1;
	private TextView _drawer_textview1;
	private ImageView _drawer_imageview4;
	private TextView _drawer_textview4;
	private ImageView _drawer_imageview5;
	private TextView _drawer_textview5;
	
	private Intent i = new Intent();
	private Intent share = new Intent();
	private Intent abtus = new Intent();
	private Intent rate = new Intent();
	private Intent privacy = new Intent();
	private Intent save = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(HomeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		gridview1 = findViewById(R.id.gridview1);
		_drawer_linear1 = _nav_view.findViewById(R.id.linear1);
		_drawer_linear7 = _nav_view.findViewById(R.id.linear7);
		_drawer_linear2 = _nav_view.findViewById(R.id.linear2);
		_drawer_linear5 = _nav_view.findViewById(R.id.linear5);
		_drawer_linear6 = _nav_view.findViewById(R.id.linear6);
		_drawer_imageview1 = _nav_view.findViewById(R.id.imageview1);
		_drawer_textview1 = _nav_view.findViewById(R.id.textview1);
		_drawer_imageview4 = _nav_view.findViewById(R.id.imageview4);
		_drawer_textview4 = _nav_view.findViewById(R.id.textview4);
		_drawer_imageview5 = _nav_view.findViewById(R.id.imageview5);
		_drawer_textview5 = _nav_view.findViewById(R.id.textview5);
		
		_drawer_linear1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_drawer_linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.setClass(getApplicationContext(), SavedActivity.class);
				startActivity(save);
			}
		});
		
		_drawer_linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				abtus.setClass(getApplicationContext(), AboutusActivity.class);
				startActivity(abtus);
			}
		});
		
		_drawer_linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				privacy.setAction(Intent.ACTION_VIEW);
				privacy.setData(Uri.parse("https://sdmcoder.blogspot.com/2022/02/whats-app-status-saver.html"));
				startActivity(privacy);
			}
		});
		
		_drawer_textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.setClass(getApplicationContext(), SavedActivity.class);
				startActivity(save);
			}
		});
		
		_drawer_textview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				abtus.setClass(getApplicationContext(), AboutusActivity.class);
				startActivity(abtus);
			}
		});
		
		_drawer_textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				privacy.setAction(Intent.ACTION_VIEW);
				privacy.setData(Uri.parse("https://sdmcoder.blogspot.com/2022/02/whats-app-status-saver.html"));
				startActivity(privacy);
			}
		});
	}
	
	private void initializeLogic() {
		/*ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    executor.execute(() -> {
        
try {*/   
		        
		if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/WhatsApp/Media/.Statuses/.nomedia"))) {
			FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/WhatsApp/Media/.Statuses/.nomedia"));
		}
		else {
			if (FileUtil.isExistFile("storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/.Statuses/.nomedia")) {
				FileUtil.deleteFile("storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/.Statuses/.nomedia");
			}
		}
		if (Build.VERSION.SDK_INT >= 29) {
			FileUtil.listDir("storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/.Statuses", str);
		}
		else {
			FileUtil.listDir(FileUtil.getExternalStorageDir().concat("/WhatsApp/Media/.Statuses"), str);
		}
		for(int _repeat50 = 0; _repeat50 < (int)(str.size()); _repeat50++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("file", str.get((int)(num)));
				map.add(_item);
			}
			
		}
		gridview1.setAdapter(new Gridview1Adapter(map));
		gridview1.setNumColumns((int)2);
		{android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
				0xFFF5F5F5,
				0xFFF5F5F5,
			});
			gd.setCornerRadius(12);
			_drawer_linear2.setBackgroundDrawable(gd);
			_drawer_linear2.setElevation(8);};
		{android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
				0xFFF5F5F5,
				0xFFF5F5F5,
			});
			gd.setCornerRadius(12);
			_drawer_linear5.setBackgroundDrawable(gd);
			_drawer_linear5.setElevation(8);};
		{android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
				0xFFF5F5F5,
				0xFFF5F5F5,
			});
			gd.setCornerRadius(12);
			_drawer_linear6.setBackgroundDrawable(gd);
			_drawer_linear6.setElevation(8);};
	}
	
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
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
			
			imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(str.get((int)(_position)), 1024, 1024));
			if (str.get((int)(_position)).endsWith(".jpg")) {
				imageview3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						savepath = FileUtil.getExternalStorageDir().concat("/Status saver/".concat(Uri.parse(str.get((int)(_position))).getLastPathSegment()));
						if (FileUtil.isExistFile(savepath)) {
							savepath = FileUtil.getExternalStorageDir().concat("/Status saver/").concat(Uri.parse(str.get((int)(_position))).getLastPathSegment());
						}
						else {
							FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/Status saver/"));
						}
						FileUtil.copyFile(str.get((int)(_position)), savepath);
						SketchwareUtil.showMessage(getApplicationContext(), "Saved");
					}
				});
				imageview2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_sharelmage(str.get((int)(_position)));
					}
				});
				imageview1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						i.putExtra("jpg", "unsaved");
						i.putExtra("img", str.get((int)(_position)));
						i.setClass(getApplicationContext(), ImageActivity.class);
						startActivity(i);
					}
				});
			}
			else {
				if (str.get((int)(_position)).endsWith(".mp4")) {
					_setBitmapFromVideo(imageview1, str.get((int)(_position)));
					imageview3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							savepath = FileUtil.getExternalStorageDir().concat("/Status saver/".concat(Uri.parse(str.get((int)(_position))).getLastPathSegment()));
							if (FileUtil.isExistFile(savepath)) {
								savepath = FileUtil.getExternalStorageDir().concat("/Status saver").concat(Uri.parse(str.get((int)(_position))).getLastPathSegment());
							}
							else {
								FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/Status saver/"));
							}
							FileUtil.copyFile(str.get((int)(_position)), savepath);
							SketchwareUtil.showMessage(getApplicationContext(), "Saved");
						}
					});
					imageview2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							_sharevideo(str.get((int)(_position)));
						}
					});
					imageview1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							i.putExtra("video", "unsaved");
							i.putExtra("vdo", str.get((int)(_position)));
							i.setClass(getApplicationContext(), VideoActivity.class);
							startActivity(i);
						}
					});
				}
				else {
					
				}
			}
			{android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
					0xFF4CAF50,
					0xFF4CAF50,
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