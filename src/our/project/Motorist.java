package our.project;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Motorist extends Activity implements GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener,OnMyLocationChangeListener {
     Location mCurrentLocation;
     LocationClient mlocationClient;
     double i=0,j=0,k=0;
     GoogleMap gm;
     Button myloc;
     Button dest;
     public static LatLng ll = null;
     
     public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.motorist);
			gm = ( (MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			myloc = (Button) findViewById(R.id.Loc);
			dest = (Button) findViewById(R.id.dest);

			
			if (gm==null)
			{
				Toast.makeText(this, "Google map is not avialable", Toast.LENGTH_LONG).show();
			}
			
			else
			{
				mlocationClient = new LocationClient(this, this, this);
				mlocationClient.connect();
			}

			dest.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent ii = new Intent(getApplicationContext(), MotoristDestination.class );
					startActivity(ii);
				}
			});
			
			myloc.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					//gm.setMyLocationEnabled(true);
					MyCurrentLocation();
				}
			});
			gm.setOnMyLocationChangeListener(this);
	        gm.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
				
				@Override
				public void onMapClick(LatLng arg0) {
					gm.clear();
					gm.addMarker(new MarkerOptions()
					.title("Start Point Selected!")
					.position(arg0));
					
					ll = arg0;
					
				}
			});

		}
		
		public void MyCurrentLocation()
		{
			gm.clear();
			Location current = mlocationClient.getLastLocation();
			if (current==null)
			{
				Toast.makeText(this, "Current Location Not present", Toast.LENGTH_SHORT).show();
			}
			else
			{
				ll = new LatLng(current.getLatitude(), current.getLongitude());
				CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 18);
				gm.animateCamera(update);
				MarkerOptions mo = new MarkerOptions();
				mo.position(ll); 
				mo.title("Start Point Selected"); 
				mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
				gm.addMarker(mo);
			}
		}

		@Override
		public void onMyLocationChange(Location location) {
			// TODO Auto-generated method stub
		}
		
		@Override
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.menu.main, menu);
	        return true;
	    }

		@Override
		public void onConnectionFailed(ConnectionResult result) {
		}

		@Override
		public void onConnected(Bundle connectionHint) {
			Toast.makeText(this, "Connected to Location Service", Toast.LENGTH_LONG).show();
		}

		@Override
		public void onDisconnected() {
			Toast.makeText(this, "Disconnected from Location Service", Toast.LENGTH_LONG).show();
			
		}
}
