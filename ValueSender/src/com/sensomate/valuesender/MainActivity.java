package com.sensomate.valuesender;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


@SuppressLint("NewApi") public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        bar.setTitle(Html.fromHtml("<font color='#000000'></font>"));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        
    }

    public void clicked(View v)
    {
    	  EditText e=(EditText)findViewById(R.id.editText1);
    	  if(!e.getText().toString().matches(""))
    	  { 	
    		  	Toast.makeText(getApplicationContext(),"Message Sent", Toast.LENGTH_LONG).show();
    	   		UploadASyncTask upload = new UploadASyncTask();
    	  		upload.execute();
    	  }
    	  else
    	  {
    		  Toast.makeText(getApplicationContext(), "Enter a valid string",Toast.LENGTH_SHORT).show();
    	  }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    

private class UploadASyncTask extends AsyncTask<Void,Void, Void>{

	@Override
	protected Void doInBackground(Void... params) {
            try{
            	
            	
               EditText e=(EditText)findViewById(R.id.editText1);
               TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
               JSONObject location = new JSONObject();
                
                
               location.put("uid", e.getText().toString());
               location.put("capturedAt", System.currentTimeMillis());
               location.put("deviceid", mngr.getDeviceId());
               
            	
            	
            
                HttpClient httpclient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost("https://sensomate-checkin.herokuapp.com/attendance/pushtodb");

                String json = "";

                json = location.toString();

                StringEntity se = new StringEntity(json);
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(se);

                httpPost.setHeader("User-Agent", "NFC-DETECTOR/1.0");
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
              

                HttpResponse httpResponse = httpclient.execute(httpPost);

                InputStream inputStream = httpResponse.getEntity().getContent();
            //    Toast.makeText(getApplicationContext(), inputStream, Toast.LENGTH_SHORT).
              

            }catch(Exception e){
            //	Toast.makeText(getApplicationContext(), "Error in Upload, Check Network",Toast.LENGTH_SHORT).show();
                Log.e("ERROR IN SEVER UPLOAD", e.getMessage());
            }
            return null;


        }
}    
    
   
}
