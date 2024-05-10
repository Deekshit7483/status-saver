package com.whatsappsaver.sdm;

import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import arabware.libs.getThumbnail.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private double n = 0;
	
	private LinearLayout linear1;
	private CardView cardview1;
	private TextView textview1;
	private ImageView imageview2;
	
	private TimerTask t;
	private Intent i = new Intent();
	private ObjectAnimator object = new ObjectAnimator();
	private ObjectAnimator b = new ObjectAnimator();
	private ObjectAnimator c = new ObjectAnimator();
	private ObjectAnimator d = new ObjectAnimator();
	private ObjectAnimator bd = new ObjectAnimator();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		cardview1 = findViewById(R.id.cardview1);
		textview1 = findViewById(R.id.textview1);
		imageview2 = findViewById(R.id.imageview2);
	}
	
	private void initializeLogic() {
		object.setTarget(cardview1);
		object.setPropertyName("alpha");
		object.setFloatValues((float)(0), (float)(1));
		object.setDuration((int)(500));
		object.start();
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						object.setTarget(cardview1);
						object.setPropertyName("scaleX");
						object.setFloatValues((float)(1), (float)(30));
						object.setDuration((int)(1200));
						object.start();
						_imageScale();
						_lscaleY();
					}
				});
			}
		};
		_timer.schedule(t, (int)(3000));
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	public void _imageScale() {
		d.setTarget(imageview2);
		d.setPropertyName("scaleX");
		d.setFloatValues((float)(1), (float)(0));
		d.setDuration((int)(300));
		d.start();
		if (true) {
			bd.setTarget(imageview2);
			bd.setPropertyName("scaleY");
			bd.setFloatValues((float)(1), (float)(0));
			bd.setDuration((int)(300));
			bd.start();
		}
	}
	
	
	public void _lscaleY() {
		c.setTarget(cardview1);
		c.setPropertyName("scaleY");
		c.setFloatValues((float)(1), (float)(30));
		c.setDuration((int)(1200));
		c.start();
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						i.setClass(getApplicationContext(), HomeActivity.class);
						startActivity(i);
					}
				});
			}
		};
		_timer.schedule(t, (int)(350));
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