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
import java.util.regex.*;
import org.json.*;

public class VideoActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String videopath = "";
	private String savepath = "";
	
	private LinearLayout linear1;
	private LinearLayout linear4;
	private VideoView videoview1;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private ImageView imageview1;
	private ImageView imageview2;
	
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.video);
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
		linear1 = findViewById(R.id.linear1);
		linear4 = findViewById(R.id.linear4);
		videoview1 = findViewById(R.id.videoview1);
		MediaController videoview1_controller = new MediaController(this);
		videoview1.setMediaController(videoview1_controller);
		linear3 = findViewById(R.id.linear3);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		imageview1 = findViewById(R.id.imageview1);
		imageview2 = findViewById(R.id.imageview2);
		
		videoview1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer _mediaPlayer) {
				videoview1.start();
			}
		});
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_sharevideo(getIntent().getStringExtra("vdo"));
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				savepath = FileUtil.getExternalStorageDir().concat("/Status saver/".concat(Uri.parse(getIntent().getStringExtra("vdo")).getLastPathSegment()));
				if (FileUtil.isExistFile(savepath)) {
					savepath = FileUtil.getExternalStorageDir().concat("/Status saver").concat(Uri.parse(getIntent().getStringExtra("vdo")).getLastPathSegment());
				}
				else {
					FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/Status saver/"));
				}
				FileUtil.copyFile(getIntent().getStringExtra("vdo"), savepath);
				SketchwareUtil.showMessage(getApplicationContext(), "Saved");
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
		videoview1.setVideoURI(Uri.parse(getIntent().getStringExtra("vdo")));
		videoview1.start();
		if (getIntent().getStringExtra("video").equals("saved")) {
			linear6.setVisibility(View.INVISIBLE);
		}
		else {
			if (getIntent().getStringExtra("video").equals("unsaved")) {
				linear6.setVisibility(View.VISIBLE);
			}
		}
		{android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
				0xFF20BF55,
				0xFF20BF55,
			});
			gd.setCornerRadius(100);
			linear3.setBackgroundDrawable(gd);
			linear3.setElevation(5);};
		{android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
				0xFF20BF55,
				0xFF20BF55,
			});
			gd.setCornerRadius(100);
			linear6.setBackgroundDrawable(gd);
			linear6.setElevation(5);};
	}
	
	public void _sharevideo(final String _path) {
		videopath = _path;
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		sendIntent.setType("video/mp4");
		sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(videopath));
		startActivity(Intent.createChooser(sendIntent, "Share"));
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