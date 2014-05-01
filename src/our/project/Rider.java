package our.project;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

import our.project.library.JSONParser;
import our.project.library.UserFunctions;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Rider extends Activity implements GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener,OnMyLocationChangeListener {
     
	Location mCurrentLocation;
	LocationClient mlocationClient;
	double i=0,j=0,k=0;
	GoogleMap gm;
	Button myloc;
	
	private static SmsManager sm;
	
	//LatLng c;
	//int trip_count;
	//int u_id=0,cap=0,pref=0;double lats=0,late=0,lons=0,lone=0;
	//String datetime;
	
	public static LatLng RiderLocation = null;
	String[] normallats; String[] normallngs ; String[] normaluids; String[] normaltripids;
	String[] normaltimes; String[] normalnames; String[] normalvehicle; String[] normalmobile;
	
	public static ArrayList<String> tripids = new ArrayList<String>();
	public static ArrayList<String> uids = new ArrayList<String>();
	public static ArrayList<String> latts = new ArrayList<String>();
	public static ArrayList<String> lngss = new ArrayList<String>();
	public static ArrayList<String> timelist = new ArrayList<String>();
	public static ArrayList<String> name = new ArrayList<String>();
	public static ArrayList<String> vehicle = new ArrayList<String>();
	public static ArrayList<String> mobile = new ArrayList<String>();
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.rider_main);
		gm = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		myloc = (Button)findViewById(R.id.Loc);
		
		if (gm==null)
		{
			Toast.makeText(this, "Google map is not avialable", Toast.LENGTH_LONG).show();
		}
		
		else
		{
			mlocationClient = new LocationClient(this, this, this);
			mlocationClient.connect();
		}
			
		myloc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//gm.setMyLocationEnabled(true);
				MyCurrentLocation();
			}
		});
		gm.setOnMyLocationChangeListener(this);
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
				  UserFunctions userFunction = new UserFunctions();
				  JSONParser jsonn = new JSONParser();
	              JSONObject json1 = userFunction.rider();
	                try {
	                	String riderdata = jsonn.json;
	                    String delims = "[{:,}]";
	                    String[] ssloc = riderdata.split(delims);
	                    
	                    for (int i=0; i<ssloc.length; i++)
	                    {
	                    	if(ssloc[i].equalsIgnoreCase("\"name\""))
	                    	{
	                    		name.add(ssloc[i+1]);
	                    	}
	                    	if(ssloc[i].equalsIgnoreCase("\"vehicle\""))
	                    	{
	                    		vehicle.add(ssloc[i+1]);
	                    	}
	                    	if(ssloc[i].equalsIgnoreCase("\"mobile\""))
	                    	{
	                    		mobile.add(ssloc[i+1]);
	                    	}
	                    	if(ssloc[i].equalsIgnoreCase("\"trip_id\""))
	                    	{
	                    		tripids.add(ssloc[i+1]);
	                    	}
	                    	if(ssloc[i].compareToIgnoreCase("\"u_id\"")==0)
	                    	{
	                    		uids.add(ssloc[i+1]);
	                    	}
	                    	if(ssloc[i].compareToIgnoreCase("\"lat_start\"")==0)
	                    	{
	                    		latts.add(ssloc[i+1]);
	                    	}
	                    	if(ssloc[i].compareToIgnoreCase("\"lat_end\"")==0)
	                    	{
	                    		latts.add(ssloc[i+1]);
	                    	}
	                    	if(ssloc[i].compareToIgnoreCase("\"lon_start\"")==0)
	                    	{
	                    		lngss.add(ssloc[i+1]);
	                    	}
	                    	if(ssloc[i].compareToIgnoreCase("\"lon_end\"")==0)
	                    	{
	                    		lngss.add(ssloc[i+1]);
	                    	}
	                    	if(ssloc[i].compareToIgnoreCase("\"datetime\"")==0)
	                    	{
	                    		timelist.add(ssloc[i+1]);
	                    		timelist.add(ssloc[i+2]);
	                    		timelist.add(ssloc[i+3]);
	                    	}
	                    }
	                    
	                    normallats = new String[999];
	                    String delim = "[\" \"]";
	                    int ii=0;
	                    for(String line : latts)
	                    {
	                        StringTokenizer st = new StringTokenizer(line, "\" \"");
	                        String tok = st.nextToken();
	                        normallats[ii] = tok;
	                        ii++;
	                    }
	                    
	                    normallngs = new String[999];
	                    ii=0;
	                    for(String line : lngss)
	                    {
	                        StringTokenizer st = new StringTokenizer(line, "\" \"");
	                        String tok = st.nextToken();
	                        normallngs[ii] = tok;
	                        ii++;
	                    }
	                    
	                    normaltripids = new String[999];
	                    ii=0;
	                    for(String line : tripids)
	                    {
	                        StringTokenizer st = new StringTokenizer(line, "\" \"");
	                        String tok = st.nextToken();
	                        normaltripids[ii] = tok;
	                        ii++;
	                    }
	                    
	                    normaluids = new String[999];
	                    ii=0;
	                    for(String line : uids)
	                    {
	                        StringTokenizer st = new StringTokenizer(line, "\" \"");
	                        String tok = st.nextToken();
	                        normaluids[ii] = tok;
	                        ii++;
	                    }
	                    
	                    normaltimes = new String[999];
	                    ii=0;
	                    for(String line : timelist)
	                    {
	                        StringTokenizer st = new StringTokenizer(line, "\"\"");
	                        String tok = st.nextToken();
	                        normaltimes[ii] = tok;
	                        ii++;
	                    }
	                    
	                    normalnames = new String[999];
	                    ii=0;
	                    for(String line : name)
	                    {
	                        StringTokenizer st = new StringTokenizer(line, "\" \"");
	                        String tok = st.nextToken();
	                        normalnames[ii] = tok;
	                        ii++;
	                    }
	                    
	                    normalmobile = new String[999];
	                    ii=0;
	                    for(String line : mobile)
	                    {
	                        StringTokenizer st = new StringTokenizer(line, "\" \"");
	                        String tok = st.nextToken();
	                        normalmobile[ii] = tok;
	                        ii++;
	                    }
	                    
	                    normalvehicle = new String[999];
	                    ii=0;
	                    for(String line : vehicle)
	                    {
	                        StringTokenizer st = new StringTokenizer(line, "\" \"");
	                        String tok = st.nextToken();
	                        normalvehicle[ii] = tok;
	                        ii++;
	                    }
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				RiderLocation = new LatLng(current.getLatitude(), current.getLongitude());
				CameraUpdate update = CameraUpdateFactory.newLatLng(RiderLocation);
				gm.animateCamera(update);
				MarkerOptions mo = new MarkerOptions();
				mo.position(RiderLocation); 
				mo.title("My Current Position");
				mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				gm.addMarker(mo);
				      
				
				for(int j=0; j<normallats.length; j++)
				{
					if(normallats[j]!= null)
					{
						MarkerOptions m1 = new MarkerOptions();
						m1.position(new LatLng(Double.parseDouble(normallats[j]), Double.parseDouble(normallngs[j]))); 
						m1.snippet(normaltimes[j] + ":" + normaltimes[j+1] + ":" + normaltimes[j+2]);
						m1.title(normalnames[j] + " " + normalmobile[j] + " " + normalvehicle[j]);
						m1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
						gm.addMarker(m1);
					}
					else break;
				}
				gm.setOnMarkerClickListener(new OnMarkerClickListener()
                {
					@Override
					public boolean onMarkerClick(Marker arg0) {
						
						sm = SmsManager.getDefault();

						String delims = "[\" \"]";

	                    String[] number = arg0.getTitle().toString().split(delims);
						StringTokenizer stt = new StringTokenizer(arg0.getTitle().toString()," ");

		                String token = stt.nextToken();
		                token = stt.nextToken();
		                
		                if(arg0.getTitle().equals("My Current Position"))
						{
							Toast.makeText(getBaseContext(), "You selected yourself", Toast.LENGTH_LONG).show();
						} // if marker source is clicked
		               
		            
		                try
						{
								sm.sendTextMessage(token, null, "I want to join you on your trip. Following are the details: \n" 
					                + arg0.getTitle() + "\n "+ arg0.getSnippet()+ "\n \n"+ "Please reply if you accept.\nAutomatically sent by " +
					                		"CarpoolApp"  , null, null);
			               
					            sm.sendTextMessage("00923212215235", null, "I want to join you on your trip. Following are the details: \n" 
						                + arg0.getTitle() + "\n "+ arg0.getSnippet()+ "\n \n"+ "Please reply if you accept.\nAutomatically sent by " +
						                		"CarpoolApp"  , null, null);

							}       
							
						catch(Exception e)
						{
							Toast.makeText(getApplicationContext(), "NS:-"+token+"-", Toast.LENGTH_LONG).show();
								
						}
						
						return true;
						
					}
                }); 
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
