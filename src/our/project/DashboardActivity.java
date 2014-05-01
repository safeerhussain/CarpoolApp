package our.project;


import our.project.library.UserFunctions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DashboardActivity extends Activity 
{
	    UserFunctions userFunctions;
	    Button btnLogout,rider,motorist;
	    //private AdView adView;
	    Bundle bundle;
	    public static String uid = null;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        bundle = getIntent().getExtras();
	        uid = bundle.getString("u_id");
	        
	        btnLogout = (Button)findViewById(R.id.Logout);
	        rider=(Button)findViewById(R.id.rider);
	        motorist=(Button)findViewById(R.id.motorist);
	        RelativeLayout layout = (RelativeLayout)findViewById(R.id.Layout);

	        // Add the adView to it.
	        //layout.addView(adView);

	        
	        rider.setOnClickListener(new View.OnClickListener()
	        {
				public void onClick(View v) {
					 Intent i = new Intent(getApplicationContext(),Rider.class);
		                startActivity(i);
				}
			});
	        motorist.setOnClickListener(new View.OnClickListener()
	        {
				public void onClick(View v) {
					 Intent i = new Intent(DashboardActivity.this, Motorist.class);
					 startActivity(i);
				}
			});
	        btnLogout.setOnClickListener(new View.OnClickListener()
	        {
				public void onClick(View v) {
					 Intent i = new Intent(getApplicationContext(), LoginActivity.class);
		                startActivity(i);
		                finish();
				}
			});
	    }

}