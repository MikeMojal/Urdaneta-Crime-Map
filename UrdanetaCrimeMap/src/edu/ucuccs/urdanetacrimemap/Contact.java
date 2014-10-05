package edu.ucuccs.urdanetacrimemap;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Contact extends Fragment {
	ImageView btncall, btntxt;
	ImageView fb, twit;
	Intent myIntent;

	public Contact() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_contact, container,
				false);
		btncall = (ImageView) view.findViewById(R.id.btncall);
		btntxt = (ImageView) view.findViewById(R.id.btntxt);
		fb = (ImageView) view.findViewById(R.id.fb);
		twit = (ImageView) view.findViewById(R.id.twit);
		btncall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:09302397866"));
					startActivity(callIntent);
				} catch (ActivityNotFoundException activityException) {
					Log.e("dialing-example", "Call failed", activityException);
				}

			}

		});
		btntxt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("android.intent.action.VIEW");
				intent.putExtra("sms_body", "");
				Uri data = Uri.parse("sms:09302397866");
				intent.setData(data);
				startActivity(intent);
			}
		});
		fb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				myIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("https://www.facebook.com/UrdanetaCityOfficial"));
				startActivity(myIntent);
			}

		});
		twit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				myIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("https://twitter.com/urdanetaOFCL"));
				startActivity(myIntent);
			}

		});
		return view;
	}

}
