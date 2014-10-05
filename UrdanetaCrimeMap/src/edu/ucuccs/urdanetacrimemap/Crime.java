package edu.ucuccs.urdanetacrimemap;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Crime extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.mypreference);
		
	}

}
