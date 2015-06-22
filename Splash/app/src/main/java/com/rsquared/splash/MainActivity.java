package com.rsquared.splash;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    ImageView im;
    String variableValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       im=(ImageView)findViewById(R.id.imageView);
        im.setImageResource(R.drawable.giflogo_00000);
       send(0);



    }
    public void send(final int a){
        Timer timer = new Timer();


        timer.schedule( new TimerTask(){

            public void run() {

                //code for testing it without ble, replace it for production

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        variableValue="giflogo_"+String.format("%05d",a);

                        im.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
                        Log.e("hll","a: "+variableValue);
                        send((a+1)%209);
                    }
                });

            }
        }, 200);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
