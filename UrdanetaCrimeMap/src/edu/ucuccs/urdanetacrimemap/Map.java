package edu.ucuccs.urdanetacrimemap;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Map extends Fragment {

	// private MenuItem refreshMenuItem;
	GroundOverlay groundOverlay;
	SharedPreferences pref;
	GoogleMap GMap;
	Marker marker, marker1, marker2, marker3, marker4, marker5, marker6,
			marker7, marker8, marker9, marker10, marker11, marker12, marker13,
			anonas, bactade, bayaoas, bolaoen, cab, cabuloan, Camanang,
			Camantiles, Casantaan, Catablan, Cayambanan, Consolacion, Dilan,
			DR, Proper, Labit, Mabanogbog, Macalong, Nancalobasaan,
			Nancamaliran, NancamaliranW, Nancayasan, Oltama, Palina, PalinaW,
			Pinmaludpod, Poblacion, Jose, Vicente, Santa, Santo, Sugcong,
			Tipuso, Tulong;
	final String APPLICATION_ID = "xB32VWCVyNtjZ0uWYOXoqHHS6UIJPOsTpFQWQ1t9";
	final String CLIENT_KEY = "AznRfuKQM2tebUEuvUgjn7NwkJQqWYYtGCCG8NCC";
	public LatLng URDANETA = new LatLng(15.9757835, 120.5705031);
	private static View view;
	boolean a, b, c, d, e, f, g, h, i, j, k, l, m;
	private static final String[] COUNTRIES = new String[] { "Anonas",
			"Bactad East", "Bayaoas", "Bolaoen", "Cabaruan", "Cabuloan",
			"Camanang", "Camantiles", "Casantaan", "Catablan", "Cayambanan",
			"Consolacion", "Dilan Paurido", "Dilan Paurido",
			"Dr.Pedro T Orata", "Labit Proper", "Labit West", "Mabanogbog",
			"Macalong", "Nancalobasaan", "Nancamaliran East",
			"Nancamaliran West", "Nancayasan", "Oltama", "Palina East",
			"Palina West", "Pinmaludpod", "Poblacion", "San Jose",
			"San Vicente", "Santa Lucia", "Santo Domingo", "Sugcong", "Tipuso",
			"Tulong" };

	public Map() {
		setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
				parent.removeView(view);
		}
		try {
			view = inflater.inflate(R.layout.activity_map, container, false);
			Parse.initialize(getActivity(), APPLICATION_ID, CLIENT_KEY);

			if (checkInternetConnection()) {
				new SyncData().execute();
				ShowMap();

				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						getActivity(),
						android.R.layout.simple_dropdown_item_1line, COUNTRIES);

				final AutoCompleteTextView txt = (AutoCompleteTextView) view
						.findViewById(R.id.editText1);
				txt.setAdapter(adapter);

				txt.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						GMap.clear();
						String brgy = txt.getText().toString();
						if (brgy.equals("Anonas")) {

							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9945942, 120.5805945))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							anonas = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9945942,
											120.5805945)));
							anonas.setTitle("Welcome To Barangay Anonas");
							txt.setText("");

						} else if (brgy.equals("Bactad East")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9804942, 120.6209245))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							bactade = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9804942,
											120.6209245)));

							bactade.setTitle("Welcome To Barangay Bactad East");
							txt.setText("");
						} else if (brgy.equals("Bayaoas")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.979504, 120.582644))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							bayaoas = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.979504, 120.582644)));
							bayaoas.setTitle("Welcome To Barangay Bayaoas");
							txt.setText("");

						} else if (brgy.equals("Bolaoen")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9961705, 120.5885965))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							bolaoen = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9961705,
											120.5885965)));
							bolaoen.setTitle("Welcome To Barangay Bolaoen ");
							txt.setText("");

						} else if (brgy.equals("Cabaruan")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9334397, 120.50023))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Marker cab = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9334397, 120.50023)));
							cab.setTitle("Welcome To Barangay Cabaruan ");
							txt.setText("");
						} else if (brgy.equals("Cabuloan")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.97699, 120.600957))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							cabuloan = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.97699, 120.600957)));
							cabuloan.setTitle("Welcome To Barangay Cabuloan ");
							txt.setText("");

						} else if (brgy.equals("Camanang")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.976617, 120.571445))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Camanang = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.976617, 120.571445)));
							Camanang.setTitle("Welcome To Barangay Camanang");
							txt.setText("");

						} else if (brgy.equals("Camantiles")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.989276, 120.545538))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Camantiles = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.989276, 120.545538)));
							Camantiles
									.setTitle("Welcome To Barangay Camantiles");
							txt.setText("");

						} else if (brgy.equals("Casantaan")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.989276, 120.545538))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Marker Casantaan = GMap
									.addMarker(new MarkerOptions()
											.position(new LatLng(15.989276,
													120.545538)));
							Casantaan.setTitle("Welcome To Barangay Casantaan");
							txt.setText("");

						} else if (brgy.equals("Catablan")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.97117, 120.489056))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Catablan = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.97117, 120.489056)));
							Catablan.setTitle("Welcome To Barangay Catablan");
							txt.setText("");

						} else if (brgy.equals("Cayambanan")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9931077, 120.5865061))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Cayambanan = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9931077,
											120.5865061)));
							Cayambanan
									.setTitle("Welcome To Barangay Cayambanan");
							txt.setText("");

						} else if (brgy.equals("Consolacion")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.954108, 120.599086))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Consolacion = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.954108, 120.599086)));
							Consolacion
									.setTitle("Welcome To Barangay Consolacion");
							txt.setText("");

						} else if (brgy.equals("Dilan Paurido")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.975253, 120.581995))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Dilan = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.975253, 120.581995)));
							Dilan.setTitle("Welcome To Barangay Dilan Paurido");
							txt.setText("");

						} else if (brgy.equals("Dr.Pedro T Orata")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9804942, 120.6209245))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							DR = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9804942,
											120.6209245)));
							DR.setTitle("Welcome To Barangay Dr.Pedro T Orata(Bactad Proper)");
							txt.setText("");

						} else if (brgy.equals("Labit Proper")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.954627, 120.529549))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Proper = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.954627, 120.529549)));
							Proper.setTitle("Welcome To Barangay Labit Proper");
							txt.setText("");

						} else if (brgy.equals("Labit West")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9587521, 120.5184393))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Labit = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9587521,
											120.5184393)));
							Labit.setTitle("Welcome To Barangay Labit West");
							txt.setText("");

						} else if (brgy.equals("Mabanogbog")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9770076, 120.558461))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Mabanogbog = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9770076, 120.558461)));
							Mabanogbog
									.setTitle("Welcome To Barangay Mabanogbog");
							txt.setText("");

						} else if (brgy.equals("Macalong")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9612081, 120.6059257))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Macalong = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9612081,
											120.6059257)));
							Macalong.setTitle("Welcome To Barangay Macalong");
							txt.setText("");

						} else if (brgy.equals("Nancalobasaan")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9612081, 120.6059257))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Nancalobasaan = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9612081,
											120.6059257)));
							Nancalobasaan
									.setTitle("Welcome To Barangay Nancalobasaan");
							txt.setText("");

						} else if (brgy.equals("Nancamaliran East")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9743699, 120.5528958))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Nancamaliran = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9743699,
											120.5528958)));
							Nancamaliran
									.setTitle("Welcome To Barangay Nancamaliran East");
							txt.setText("");

						} else if (brgy.equals("Nancamaliran West")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9800589, 120.5334592))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							NancamaliranW = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9800589,
											120.5334592)));
							NancamaliranW
									.setTitle("Welcome To Barangay Nancamaliran West");
							txt.setText("");

						} else if (brgy.equals("Nancayasan")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.954417, 120.577015))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Nancayasan = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.954417, 120.577015)));
							Nancayasan
									.setTitle("Welcome To Barangay Nancayasan");
							txt.setText("");

						} else if (brgy.equals("Oltama")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9478903, 120.503996))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Oltama = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9478903, 120.503996)));
							Oltama.setTitle("Welcome To Barangay Oltama");
							txt.setText("");

						} else if (brgy.equals("Palina East")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9475918, 120.5470026))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Palina = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9475918,
											120.5470026)));
							Palina.setTitle("Welcome To Barangay Palina East");
							txt.setText("");

						} else if (brgy.equals("Palina West")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.948279, 120.5435863))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							PalinaW = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.948279, 120.5435863)));
							PalinaW.setTitle("Welcome To Barangay Palina West");
							txt.setText("");

						} else if (brgy.equals("Pinmaludpod")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9799342, 120.5361619))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Pinmaludpod = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9799342,
											120.5361619)));
							Pinmaludpod
									.setTitle("Welcome To Barangay Pinmaludpod");
							txt.setText("");

						} else if (brgy.equals("Poblacion")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9762269, 120.5673577))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Poblacion = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9762269,
											120.5673577)));
							Poblacion.setTitle("Welcome To Barangay Poblacion");
							txt.setText("");

						} else if (brgy.equals("San Jose")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9803947, 120.5129878))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Jose = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9803947,
											120.5129878)));
							Jose.setTitle("Welcome To Barangay San Jose");
							txt.setText("");

						} else if (brgy.equals("San Vicente")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9803076, 120.5708532))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Vicente = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9803076,
											120.5708532)));
							Vicente.setTitle("Welcome To Barangay Vicente");
							txt.setText("");

						} else if (brgy.equals("Santa Lucia")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.958736, 120.5550609))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Santa = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.958736, 120.5550609)));
							Santa.setTitle("Welcome To Barangay Santa Lucia");
							txt.setText("");

						} else if (brgy.equals("Santo Domingo")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.9556261, 120.5656763))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Santo = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.9556261,
											120.5656763)));
							Santo.setTitle("Welcome To Barangay Santo Domingo");
							txt.setText("");

						} else if (brgy.equals("Sugcong")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.938831, 120.5275123))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Sugcong = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.938831, 120.5275123)));
							Sugcong.setTitle("Welcome To Barangay Sugcong");
							txt.setText("");

						} else if (brgy.equals("Tipuso")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(15.967771, 120.610721))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Tipuso = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(15.967771, 120.610721)));
							Tipuso.setTitle("Welcome To Barangay Tipuso");
							txt.setText("");

						} else if (brgy.equals("Tulong")) {
							CameraPosition cam = new CameraPosition.Builder()
									.target(new LatLng(16.0060326, 120.5758315))
									.bearing(50).zoom(14).tilt(30).build();
							GMap.animateCamera(CameraUpdateFactory
									.newCameraPosition(cam));
							Tulong = GMap.addMarker(new MarkerOptions()
									.position(new LatLng(16.0060326,
											120.5758315)));
							Tulong.setTitle("Welcome To Barangay Tulong");
							txt.setText("");

						}
					}

				});

			} else {
				showAlertDialog(getActivity(), "No Internet Connection",
						"You don't have internet connection.", false);
			}

		} catch (InflateException e) {

		}
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (checkInternetConnection()) {
			ShowMap();
		}
	}

	private boolean checkInternetConnection() {
		boolean connected = false;
		ConnectivityManager check = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (check != null) {
			NetworkInfo[] info = check.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						connected = true;
					}
		}
		return connected;
	}

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.map, menu);

		// SearchManager searchManager = (SearchManager) getActivity()
		// .getSystemService(Context.SEARCH_SERVICE);
		// SearchView searchView = (SearchView)
		// menu.findItem(R.id.action_search)
		// .getActionView();
		// searchView.setSearchableInfo(searchManager
		// .getSearchableInfo(getActivity().getComponentName()));
		// searchView.setQueryHint("Search Barangay");
		//
		// searchView.setOnQueryTextListener(new OnQueryTextListener() {
		//
		// @Override
		// public boolean onQueryTextChange(String brgy) {
		//
		// if (brgy.equals("Anonas")) {
		//
		// CameraPosition cam = new CameraPosition.Builder()
		// .target(Labit).zoom(14).build();
		// GMap.animateCamera(CameraUpdateFactory
		// .newCameraPosition(cam));
		// }
		// // TODO Auto-generated method stub
		// return false;
		// }
		//
		// @Override
		// public boolean onQueryTextSubmit(String query) {
		// // TODO Auto-generated method stub
		// return false;
		// }
		//
		// });

	}

	public void populate() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Barangay");
		query.whereEqualTo("name", "Anonas");
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException arg1) {
				for (int i = 0; i < listahan.size(); i++) {
					Toast.makeText(
							getActivity(),
							listahan.get(i).getParseGeoPoint("location")
									.getLatitude()
									+ "", Toast.LENGTH_LONG).show();
				}

			}

		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		// case R.id.action_search:
		//
		// return true;
		case R.id.action_crime:

			Crime();
			return true;
		case R.id.action_refresh:
			try {
				if (checkInternetConnection()) {

					// load the data from server
					new SyncData().execute();
					ShowMap();
					GMap.clear();
				} else {
					showAlertDialog(getActivity(), "No Internet Connection",
							"You don't have internet connection.", false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void slideIt(int opt) {
		if (opt != 0) {
			getActivity().overridePendingTransition(
					android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
		}
	}

	private void ShowMap() {

		setHasOptionsMenu(true);
		float width = 130;
		float height = 130;
		GMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		// GMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		// CameraPosition cam = new CameraPosition.Builder().target(URDANETA)
		// .zoom(14).bearing(50).tilt(30).build();
		CameraPosition cam = new CameraPosition.Builder().target(URDANETA)
				.zoom(14).build();
		GMap.moveCamera(CameraUpdateFactory.newCameraPosition(cam));
		BitmapDescriptor image = BitmapDescriptorFactory
				.fromResource(R.drawable.logo);

		GMap.addGroundOverlay(new GroundOverlayOptions().image(image)
				.position(URDANETA, width, height).transparency((float) 0.8));

		pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		a = pref.getBoolean("prefChkCarnapping", true);
		b = pref.getBoolean("prefChkDrugs", true);
		c = pref.getBoolean("prefChkExplosives", true);
		d = pref.getBoolean("prefChkFirearms", true);
		e = pref.getBoolean("prefChkHomicide", true);
		f = pref.getBoolean("prefChkKidnapping", true);
		g = pref.getBoolean("prefChkMurder", true);
		h = pref.getBoolean("prefChkParricide", true);
		i = pref.getBoolean("prefChkPolice", true);
		j = pref.getBoolean("prefChkPhysicalinjury", true);
		k = pref.getBoolean("prefChkRape", true);
		l = pref.getBoolean("prefChkRobbery", true);
		m = pref.getBoolean("prefChkTheft", true);
		try {
			if (a == true) {
				Carnap();

			} else if (a == false) {
				try {
					a = false;
					// marker1.remove();
				} catch (InflateException e) {

				}

			}
			if (b == true) {
				Drug();
			} else {
				b = false;

			}
			if (c == true) {
				Explosive();
			} else {
				c = false;
				;
			}
			if (d == true) {
				Fire();
			} else {
				d = false;

			}
			if (e == true) {
				Homicide();
			} else {
				e = false;

			}
			if (f == true) {
				Kidnap();
			} else {
				f = false;

			}
			if (g == true) {
				Murder();
			} else {
				g = false;

			}
			if (h == true) {
				Paracide();
			} else {
				h = false;

			}
			if (i == true) {
				Police();
			} else {
				i = false;

			}
			if (j == true) {
				Physical();
			} else {
				j = false;

			}
			if (k == true) {
				Rape();
			} else {
				k = false;

			}
			if (l == true) {
				Roberry();
			} else {
				l = false;

			}
			if (m == true) {
				Theft();
			} else {
				m = false;

			}
		} catch (InflateException e) {

		}

	}

	private void Crime() {
		Intent i = new Intent(getActivity(), Crime.class);
		startActivity(i);
		getActivity().overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
	}

	public void Carnap() {

		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Carnap");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker1 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.carnapping))

					);

					markers.add(marker1);
					marker1.setTitle("Carnapping");
				}

			}

		});

	}

	public void Drug() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Drug");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker2 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.drugs))

					);

					markers.add(marker2);
					marker2.setTitle("Illegal Drugs");
				}

			}

		});
	}

	public void Explosive() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Explosive");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker3 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.explosives))

					);

					markers.add(marker3);
					marker3.setTitle("Explosive Device");
				}

			}

		});
	}

	public void Fire() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Fire");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker4 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.firearms))

					);

					markers.add(marker4);
					marker4.setTitle("Fire Arms");
				}

			}

		});
	}

	public void Kidnap() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Kidnap");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker5 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.kidnapping))

					);

					markers.add(marker5);
					marker5.setTitle("Kidnapping");
				}

			}

		});
	}

	public void Homicide() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Homicide");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker6 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.homicide))

					);

					markers.add(marker6);
					marker6.setTitle("Homicide");
				}

			}

		});

	}

	public void Murder() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Murder");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker7 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.murder))

					);

					markers.add(marker7);
					marker7.setTitle("Murder");
				}

			}

		});
	}

	public void Paracide() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Paracide");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker8 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.parricide))

					);

					markers.add(marker8);
					marker8.setTitle("Parracide");
				}

			}

		});
	}

	public void Police() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Police");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker9 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.police))

					);

					markers.add(marker9);
					marker9.setTitle("Police Station");
				}

			}

		});
	}

	public void Physical() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Physical");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker10 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.physicalinjury))

					);

					markers.add(marker10);
					marker10.setTitle("Physical Injury(Torture)");
				}

			}

		});

	}

	public void Rape() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Rape");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker11 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.rape))

					);

					markers.add(marker11);
					marker11.setTitle("Rape");
				}

			}

		});

	}

	public void Roberry() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Roberry");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker12 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.robbery))

					);

					markers.add(marker12);
					marker12.setTitle("Robbery");
				}

			}

		});

	}

	public void Theft() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Theft");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException e) {
				// TODO Auto-generated method stub

				List<Marker> markers = new ArrayList<Marker>();

				for (int i = 0; i < listahan.size(); i++) {

					listahan.get(i).getParseGeoPoint("location");

					ParseGeoPoint geo = listahan.get(i).getParseGeoPoint(
							"location");

					marker13 = GMap.addMarker(new MarkerOptions().position(
							new LatLng(geo.getLatitude(), geo.getLongitude()))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.theft))

					);

					markers.add(marker13);
					marker13.setTitle("Theft");
				}

			}

		});

	}

	private class SyncData extends AsyncTask<String, Void, String> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// set the progress bar view
			super.onPreExecute();
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Loading Crime Map");
			dialog.setMessage("Please wait...");
			dialog.show();
			dialog.setCancelable(false);
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				ShowMap();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(String result) {
			dialog.cancel();
			ShowMap();
		}
	};

}
