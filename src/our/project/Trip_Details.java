package our.project;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

import our.project.library.UserFunctions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TimePicker;
import android.widget.Toast;

public class Trip_Details extends Activity {

	private Button submit;
	private EditText datee;
	private EditText cap;
	private TimePicker timee;
	
	private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
    
    String date , hrs, mins, secs, time, capacity, pref,  lat_end, lon_end;
    String all;

    private static String u_id = null;
    private static String dloc = null;
    private static String sloc = null;
    private static LatLng locdata = null;
    private static LatLng dlocdata = null;
    private static String lat_start = null;
    private static String lon_start = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_details);
        
		DashboardActivity da = new DashboardActivity();
		Motorist m = new Motorist();
		MotoristDestination md = new MotoristDestination();
		
		u_id = da.uid;
		locdata = m.ll;
		dlocdata = md.destinationpoint;
		
		sloc = locdata.toString();
		dloc = dlocdata.toString();
		
		//Toast.makeText(getBaseContext(), u_id, Toast.LENGTH_LONG).show();
		
        String delims = "[(,)]";
        String[] ssloc = sloc.split(delims);
        String[] ddloc = dloc.split(delims);
        
        lat_start = ssloc[1];
        lon_start = ssloc[2];
        lat_end = ddloc[1];
        lon_end = ddloc[2];
        
        datee = (EditText)findViewById(R.id.date);
        cap = (EditText)findViewById(R.id.cap);
        timee = (TimePicker)findViewById(R.id.time);
        
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
        	public void onCheckedChanged(RadioGroup group, int checkedId)
        	{
        		RadioButton rb1 = (RadioButton)findViewById(R.id.radio1);
        		RadioButton rb2 = (RadioButton)findViewById(R.id.radio2);
        		RadioButton rb3 = (RadioButton)findViewById(R.id.radio3);
        		
        		if (rb1.isChecked())
        		{
        			pref = "1";
        			
        		}
        		else if (rb2.isChecked())
        		{
        			pref = "2";
        		}
        		else if (rb3.isChecked())
        		{
        			pref = "3";
        		}
        		
        	}
        });
        
        date = datee.getText().toString();
        hrs = timee.getCurrentHour().toString();
        mins = timee.getCurrentMinute().toString();
        secs = "00";
        
        time = hrs + ":" + mins + ":" + secs;
        capacity = cap.toString();
	}
	
	//OnClick Method for "Done" Button.
	public void submit(View v)
	{
		UserFunctions userFunction = new UserFunctions();
        JSONObject json = userFunction.tripDetails(u_id, lat_start, lon_start, lat_end, lon_end, capacity, pref, date, time);

        
//        // check for login response
//        try {
//            if (json.getString(KEY_SUCCESS) != null) {
//                String res = json.getString(KEY_SUCCESS);
//                if(Integer.parseInt(res) == 1){
//                    // user successfully logged in
//                    // Store user details in SQLite Database
//                	//Databasehandler db = new Databasehandler(getApplicationContext());
//                    //JSONObject json_user = json.getJSONObject("user");
//                     
//                    // Clear all previous data in database
//                    //userFunction.logoutUser(getApplicationContext());
//                    //db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));                       
//                     
//                    // Launch Dashboard Screen
//                    Intent dashboard = new Intent(getApplicationContext(), DashboardActivity.class);
//                     
//                    // Close all views before launching Dashboard
//                    dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(dashboard);
//                     
//                    // Close Login Screen
//                    finish();
//                }else{
//                    // Error in login
//                 
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //all = u_id + " , " + sloc + " , " +  dloc + " , " +  date + " , " +  hrs + " , " +  mins + " , " +  time + " , " +  capacity + " , " +  pref + " , " +  lat_start + " , " +  lon_start + " , " +  lat_end + " , " +  lon_end;
		//Toast.makeText(getBaseContext(), all, Toast.LENGTH_LONG).show();
	}

}
