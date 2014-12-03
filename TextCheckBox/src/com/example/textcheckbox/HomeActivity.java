package com.example.textcheckbox;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	ArrayList<Integer>uuid;
	ArrayList<RelativeLayout>llist;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
	        ActionBar actionBar = getActionBar();
	        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f1c40f")));
	        checker();
	    }
	 @Override
	 	protected void onResume()
	 	{
		 	super.onResume();
	     //Restore state here
		 	checker();
	 	}
	 
	 @Override
	 	protected void onRestart() {
		// TODO Auto-generated method stub
		 	super.onRestart();
		 	checker();
	 	}
	@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.home, menu);
	        return true;
	    }
	  
	   public void checker()
	   {
			 DatabaseHandler db = new DatabaseHandler(this);
			 if(db.getNotesCount()==0)
			 {
				 setContentView(R.layout.activity_none);
				 TextView t=(TextView)findViewById(R.id.textView1);
				 Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/newfontblod.ttf");
				 t.setTypeface(tf);
				 
			 }
			 else
			 {
				 setContentView(R.layout.activity_home);
				 List<Notes>notes = db.getAllNotes();
				 uuid=new ArrayList<Integer>();
				 llist=new ArrayList<RelativeLayout>();
				 LinearLayout ll=(LinearLayout)findViewById(R.id.linear1);
				 for(int i=0;i<db.getNotesCount();i++)
				 {
					uuid.add(i,notes.get(i).getID());
					RelativeLayout LL=new RelativeLayout(this);
					final RelativeLayout l=LL;
					LL.setClickable(true);
					AutoResizeTextView t=new AutoResizeTextView(this);
					TextView sharedwith=new TextView(this);
					TextView sharedwithname=new TextView(this);
					ImageView editicon=new ImageView(this);
					editicon.setImageResource(R.drawable.edit1);
					RelativeLayout sharedwithlayout=new RelativeLayout(this); 
					Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/newfontlight.ttf");
					t.setTypeface(tf);
					sharedwith.setTypeface(tf);
					sharedwithname.setTypeface(tf,Typeface.BOLD_ITALIC);
					
					 
					String html_text=notes.get(i).getNote();
					Spanned s=Html.fromHtml(html_text);
					
					String namel="";
					
					Document doc = Jsoup.parse(html_text);
					Elements contents=doc.select("p");
					for (Element topic : contents) {
						 if(topic.hasClass("textf"))
						 {
							 namel=namel+topic.text();
						 }
						 else if(topic.hasClass("namechecked"))
						 {
							namel=namel+getResources().getString(R.string.checked)+topic.text(); 
						 }
						 else
							 namel=namel+getResources().getString(R.string.unchecked)+topic.text(); 
						 namel=namel+"\n ";
					 }
					
					
					 //t.setGravity(Gravity.CENTER);
					//LL.setOrientation(LinearLayout.VERTICAL);
					 RelativeLayout.LayoutParams params
				     = new RelativeLayout.LayoutParams(
				    		 LayoutParams.FILL_PARENT,
				       100);
				    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				    sharedwithlayout.setLayoutParams(params);
					 
				    LL.setPadding(10, 0, 0, 0);
				    sharedwithlayout.setPadding(20, 0, 0, 0);
				    if(i%2==0)
		            LL.setBackgroundColor(Color.parseColor("#f2ca27"));
				    else if(i%3==0)
				    	LL.setBackgroundColor(Color.parseColor("#7f8c8d"));
				    else
				    	LL.setBackgroundColor(Color.parseColor("#e67e22"));
			        DisplayMetrics displaymetrics = new DisplayMetrics();
				    getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
				       
				    int width = displaymetrics.widthPixels;
				    LayoutParams LLParams = new LayoutParams(LayoutParams.FILL_PARENT,width);
				    RelativeLayout.LayoutParams ladderParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
				    t.setTextSize(100);
				    t.setMaxTextSize(100);
				   
				    t.setMinTextSize(27);
				    t.setAddEllipsis(true);
				    t.setLayoutParams(ladderParams);
				    LL.setLayoutParams(LLParams);
				  //  t.setGravity(Gravity.CENTER_VERTICAL);
					 //t.setPadding(10, 0, 0,0);
					 t.setText(s);
					 sharedwith.setId(1);
					 sharedwithname.setId(2);
					 sharedwith.setText("shared with ");
					 sharedwith.setTextSize(20);
					 sharedwithname.setText("@Lisa Mariam");
					 
					 sharedwithname.setTextSize(20);
					 sharedwithlayout.addView(sharedwith);
					 RelativeLayout.LayoutParams relativeLayoutParams; 
					 relativeLayoutParams = new RelativeLayout.LayoutParams(
						        RelativeLayout.LayoutParams.WRAP_CONTENT,
						        RelativeLayout.LayoutParams.WRAP_CONTENT);
					 relativeLayoutParams.addRule(RelativeLayout.RIGHT_OF,
						        sharedwith.getId());
					 
					 
					 RelativeLayout.LayoutParams relativeLayoutParams1; 
					 relativeLayoutParams1 = new RelativeLayout.LayoutParams(
						        RelativeLayout.LayoutParams.WRAP_CONTENT,
						        RelativeLayout.LayoutParams.WRAP_CONTENT);
					 relativeLayoutParams1.addRule(RelativeLayout.BELOW,
						        sharedwithname.getId());
					 sharedwithlayout.addView(sharedwithname,relativeLayoutParams);
					 sharedwithlayout.addView(editicon,relativeLayoutParams);
					 LL.addView(t);
					 LL.addView(sharedwithlayout);
					 ll.addView(LL);
					 RelativeLayout.LayoutParams layoutParams = 
						    (RelativeLayout.LayoutParams)t.getLayoutParams();
					 layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
					 t.setLayoutParams(layoutParams);
					 llist.add(i,LL);
					 LL.setOnClickListener(new OnClickListener()
					 {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							 Intent i = new Intent(HomeActivity.this, MainActivity.class);
							 Toast.makeText(getApplicationContext(),uuid.get(llist.indexOf(l))+" ", Toast.LENGTH_SHORT).show();
							 i.putExtra("uuid", uuid.get(llist.indexOf(l)));
							 startActivity(i);
						}
						
					});
				}
			 }
			 db.close();
	   }
	   @Override
	   public boolean onMenuItemSelected(int featureId, MenuItem item) {

	       int itemId = item.getItemId();
	       switch (itemId) {
	       case R.id.action_home:
	    	   		Intent i = new Intent(HomeActivity.this, MainActivity.class);
				    i.putExtra("uuid",0);
	                startActivity(i);
	    	        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
	    	        break;
	       }
	       return true;
	   }
}
