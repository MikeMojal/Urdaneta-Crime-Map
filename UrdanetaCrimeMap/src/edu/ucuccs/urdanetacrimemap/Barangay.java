package edu.ucuccs.urdanetacrimemap;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class Barangay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barangay);
		ActionBar actionBar = getActionBar();

		// Enabling Back navigation on Action Bar icon
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

}
