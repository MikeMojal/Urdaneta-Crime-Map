package edu.ucuccs.urdanetacrimemap;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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
import com.google.maps.android.ui.IconGenerator;

public class Map extends Fragment {
	// implements LoaderCallbacks<Cursor>

	public LatLng URDANETA = new LatLng(15.9757835, 120.5705031);
	private static View view;
//	private MenuItem refreshMenuItem;
	boolean a, b, c, d, e, f, g;
	GroundOverlay groundOverlay;
	SharedPreferences pref;
	GoogleMap GMap;
	Marker marker;

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

			if (checkInternetConnection()) {
				ShowMap();

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
		SearchManager searchManager = (SearchManager) getActivity()
				.getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getActivity().getComponentName()));
		searchView.setQueryHint("Search Barangay");

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.action_search:

			return true;
		case R.id.action_crime:

			Crime();
			return true;
			// case R.id.action_refresh:
			//
			// refreshMenuItem = item;
			// // load the data from server
			// new SyncData().execute();
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

		GMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		CameraPosition cam = new CameraPosition.Builder().target(URDANETA)
				.zoom(12).bearing(50).tilt(30).build();
		GMap.moveCamera(CameraUpdateFactory.newCameraPosition(cam));
		BitmapDescriptor image = BitmapDescriptorFactory
				.fromResource(R.drawable.logo);

		GMap.addGroundOverlay(new GroundOverlayOptions().image(image)
				.position(URDANETA, width, height).transparency((float) 0.8));

		IconGenerator iconFactory = new IconGenerator(getActivity());
		
		iconFactory.setStyle(IconGenerator.STYLE_GREEN);
		addIcon(iconFactory, "San Vicente", new LatLng(15.9782426, 120.5715607));
		
		iconFactory.setStyle(IconGenerator.STYLE_BLUE);
		addIcon(iconFactory, "Labit Proper",
				new LatLng(15.9532486, 120.5253162));
		
		iconFactory.setStyle(IconGenerator.STYLE_RED);
		addIcon(iconFactory, "Labit West", new LatLng(15.9584046, 120.5206084));
		
		iconFactory.setStyle(IconGenerator.STYLE_ORANGE);
		addIcon(iconFactory, "Cabaruan", new LatLng(15.9458491, 120.5247965));
		
		iconFactory.setStyle(IconGenerator.STYLE_PURPLE);
		addIcon(iconFactory, "Oltama", new LatLng(15.9491914, 120.5061713));

		iconFactory.setStyle(IconGenerator.STYLE_WHITE);
		addIcon(iconFactory, "Sugcong", new LatLng(15.9388908, 120.5268028));
		
		iconFactory.setStyle(IconGenerator.STYLE_BLUE);
		addIcon(iconFactory, "Catablan", new LatLng(15.9688731, 120.5000988));

		pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		a = pref.getBoolean("prefChkMurder", false);
		b = pref.getBoolean("prefChkCarnapping", false);
		c = pref.getBoolean("prefChkHomicide", false);
		d = pref.getBoolean("prefChkTheft", false);
		e = pref.getBoolean("prefChkPhysicalinjury", false);
		f = pref.getBoolean("prefChkRape", false);
		g = pref.getBoolean("prefChkRobbery", false);
		if (a == true) {
			Murder();
		} else {
			a = false;

		}
		if (b == true) {
			Carnap();
		} else {
			b = false;

		}
		if (c == true) {
			Homicide();
		} else {
			c = false;

		}
		if (d == true) {
			Theft();
		} else {
			d = false;

		}
		if (e == true) {
			Physical();
		} else {
			e = false;

		}
		if (f == true) {
			Rape();
		} else {
			f = false;

		}
		if (g == true) {
			Roberry();
		} else {
			g = false;

		}
	}

	private void addIcon(IconGenerator iconFactory, String text, LatLng position) {

		MarkerOptions markerOptions = new MarkerOptions()
				.icon(BitmapDescriptorFactory.fromBitmap(iconFactory
						.makeIcon(text))).position(position)
				.anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());

		GMap.addMarker(markerOptions);

	}

	private void Crime() {
		Intent i = new Intent(getActivity(), Crime.class);
		startActivity(i);
		getActivity().overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
	}

	public void Murder() {
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9758392, 120.5717577)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.murder)));
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9742406, 120.5715413)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.murder)));

	}

	public void Carnap() {
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9741025, 120.5708826)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.carnapping)));
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9731451, 120.5611968)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.carnapping)));

	}

	public void Roberry() {
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9729014, 120.5635558)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.robbery)));
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9718257, 120.5669392)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.robbery)));

	}

	public void Rape() {
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9743509, 120.5565057)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.rape)));
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9725317, 120.5576579)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.rape)));

	}

	public void Theft() {
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9491914, 120.5061713)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.theft)));
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9584046, 120.5206080)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.theft)));

	}

	public void Homicide() {
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.97895, 120.5661855)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.homicide)));
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9784735, 120.5645754)).icon(
				BitmapDescriptorFactory.fromResource(R.drawable.homicide)));

	}

	public void Physical() {
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9663872, 120.572975))
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.physicalinjury)));
		GMap.addMarker(new MarkerOptions().position(
				new LatLng(15.9699832, 120.5741364))
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.physicalinjury)));

	}
	// private class SyncData extends AsyncTask<String, Void, String> {
	// @Override
	// protected void onPreExecute() {
	// // set the progress bar view
	// refreshMenuItem.setActionView(R.layout.action_progressbar);
	//
	// refreshMenuItem.expandActionView();
	// }
	//
	// @Override
	// protected String doInBackground(String... params) {
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// @Override
	// protected void onPostExecute(String result) {
	// refreshMenuItem.collapseActionView();
	// // remove the progress bar view
	// refreshMenuItem.setActionView(null);
	// }
	// };

	// private void handleIntent(Intent intent) {
	// if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
	// doSearch(intent.getStringExtra(SearchManager.QUERY));
	// } else if (intent.getAction().equals(Intent.ACTION_VIEW)) {
	// getPlace(intent.getStringExtra(SearchManager.EXTRA_DATA_KEY));
	// }
	// }
	//
	// protected void onNewIntent(Intent intent) {
	// super.startActivity(intent);
	// handleIntent(intent);
	// }
	//
	// private void getPlace(String query) {
	// // TODO Auto-generated method stub
	// Bundle data = new Bundle();
	// data.putString("query", query);
	// }
	//
	// private void doSearch(String query) {
	// // TODO Auto-generated method stub
	// Bundle data = new Bundle();
	// data.putString("query", query);
	// }
	//
	// @Override
	// public android.support.v4.content.Loader<Cursor> onCreateLoader(int arg0,
	// Bundle query) {
	// CursorLoader cLoader = null;
	//
	// if (arg0 == 0)
	// cLoader = new CursorLoader(getActivity().getBaseContext(),
	// PlaceProvider.SEARCH_URI, null, null,
	// new String[] { query.getString("query") }, null);
	// else if (arg0 == 1)
	// cLoader = new CursorLoader(getActivity().getBaseContext(),
	// PlaceProvider.DETAILS_URI, null, null,
	// new String[] { query.getString("query") }, null);
	// return cLoader;
	//
	// }
	//
	// @Override
	// public void onLoadFinished(android.support.v4.content.Loader<Cursor>
	// arg0,
	// Cursor c) {
	// showLocations(c);
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onLoaderReset(android.support.v4.content.Loader<Cursor> arg0)
	// {
	// // TODO Auto-generated method stub
	// }
	//
	// private void showLocations(Cursor c) {
	// MarkerOptions markerOptions = null;
	// LatLng position = null;
	// GMap.clear();
	// while (c.moveToNext()) {
	// markerOptions = new MarkerOptions();
	// position = new LatLng(Double.parseDouble(c.getString(1)),
	// Double.parseDouble(c.getString(2)));
	// markerOptions.position(position);
	// markerOptions.title(c.getString(0));
	// GMap.addMarker(markerOptions);
	// }
	// if (position != null) {
	// CameraUpdate cameraPosition = CameraUpdateFactory
	// .newLatLng(position);
	// GMap.animateCamera(cameraPosition);
	// }
	// }

}
