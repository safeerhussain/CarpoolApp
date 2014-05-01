package our.project.library;

import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
 
import android.content.Context;
 
public class UserFunctions {
     
    private JSONParser jsonParser;
     
    // Testing in localhost using wamp or xampp
    // use http://10.0.2.2/ to connect to your localhost ie http://localhost/
    private static String loginURL = "http://bigostudios.pk/carpool/";
    private static String registerURL = "http://bigostudios.pk/carpool/";
     
    private static String login_tag = "login";
    private static String trip_tag = "trip";
    private static String register_tag = "register";
    private static String rider_tag = "rider";
     
    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
     
    /**
     * function make Login Request
     * @param email
     * @param password
     * */
    public JSONObject rider(){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", rider_tag));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }
    public JSONObject loginUser(String id, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("uid", id));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }
    public JSONObject tripDetails(String u_id, String lat_start, String lon_start, String lat_end, String lon_end,
    		String capacity, String pref, String date, String time){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
       
        params.add(new BasicNameValuePair("tag", trip_tag));
        params.add(new BasicNameValuePair("uid", u_id));
        params.add(new BasicNameValuePair("lat_start", lat_start));
        params.add(new BasicNameValuePair("lon_start", lon_start));
        params.add(new BasicNameValuePair("lat_end", lat_end));
        params.add(new BasicNameValuePair("lon_end", lon_end));
        params.add(new BasicNameValuePair("capacity", capacity));
        params.add(new BasicNameValuePair("pref", pref));
        params.add(new BasicNameValuePair("date", date));
        params.add(new BasicNameValuePair("time", time));
                     
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }
     
    /**
     * function make Login Request
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject registerUser(String name, String email, String password, String mobile, String vehicle){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("mobile", mobile));
        params.add(new BasicNameValuePair("vehicle", vehicle));
         
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        // return json
        return json;
    }
     
    /**
     * Function get Login status
     * */
    public boolean isUserLoggedIn(Context context){
        Databasehandler db = new Databasehandler(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }
     
    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        Databasehandler db = new Databasehandler(context);
        db.resetTables();
        return true;
    }
     
}