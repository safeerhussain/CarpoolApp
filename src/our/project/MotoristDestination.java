package our.project;

import java.text.DecimalFormat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MotoristDestination extends Activity {

	 private Button back;
	 private Button det;
	 private GoogleMap gm;
	 public static LatLng destinationpoint = null;

	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.motoristdestination);
	        gm = ( (MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	        det = (Button)findViewById(R.id.details);
	        
	        det.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	 Intent ii = new Intent(getApplicationContext(), Trip_Details.class );
	            	 startActivity(ii);
	            }
	        });
	        gm.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
				
				@Override
				public void onMapClick(LatLng arg0) {
					gm.clear();
					gm.addMarker(new MarkerOptions()
					.title("Destination Point Selected!")
					.position(arg0));
					
					destinationpoint = arg0;
					
				}
			});

	 }
}
