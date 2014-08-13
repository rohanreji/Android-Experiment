package com.example.textcheckbox;

import java.util.ArrayList;

import com.example.textcheckbox.R.id;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity  {
	ArrayList<LinearLayout> layoutList;
	ArrayList<EditText> editList;
	int count;
	int prev_val;
	CheckBox c;
	String s_prev;
	int fg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c=(CheckBox)findViewById(R.id.checkBox1);
        
        layoutList=new ArrayList<LinearLayout>();
        editList=new ArrayList<EditText>();
        count=0;
        fg=0;
        prev_val=-1;
        s_prev="";
        modifier();
   
    }
    void checkBoxAdder()
    {
    	
    	 LinearLayout LL = new LinearLayout(this);
	       CheckBox ch = new CheckBox(this);
	      final EditText edittext=new EditText(this);
	      edittext.setBackgroundColor(Color.WHITE);
	       LL.setOrientation(LinearLayout.HORIZONTAL);
	       LL.setPadding(20, 0, 0, 0);
	       LayoutParams LLParams = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
	       
	       LinearLayout.LayoutParams ladderParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

	       LinearLayout.LayoutParams dummyParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
	       ch.setLayoutParams(ladderParams);
	       edittext.setLayoutParams(dummyParams);
	       edittext.setText(s_prev);
	       LL.setLayoutParams(LLParams);
	       LL.addView(ch);
	       LL.addView(edittext);
	       layoutList.add(prev_val+1, LL);
	        editList.add(prev_val+1, edittext);
	        
	        if((prev_val+1)!=count)
	        {
	       for(LinearLayout l1:layoutList)
	       {
	    	   ((LinearLayout) findViewById(R.id.rl)).removeView(l1);   
	    	   ((LinearLayout) findViewById(R.id.rl)).addView(l1);
	       }
	        }
	        else
	        	
	        ((LinearLayout) findViewById(R.id.rl)).addView(LL);
	       
	        count++;
	             edittext.setFocusableInTouchMode(true);
	        edittext.requestFocus();
	        
	        
	        
	        edittext.setOnTouchListener(new OnTouchListener() {
	           

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub	
					prev_val=editList.indexOf(edittext)-1;
					return false;
				}
	        });
	        

	        edittext.addTextChangedListener(new TextWatcher() {
	        	  

	        	  @Override
	        	  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	        	    //nothing needed here...
	        	  }

	        	  @Override
	        	  public void onTextChanged(CharSequence s, int start, int before, int count) {
	        	    //nothing needed here...
	        		  
	        	  }

				@Override
				public void afterTextChanged(Editable e) {
					// TODO Auto-generated method stub
					 String textFromEditView = e.toString();
					if(textFromEditView.length()!=0)
					{
						fg=0;
					 if(textFromEditView.charAt(textFromEditView.length()-1)=='\n')
					 {
						
						 
						 if(textFromEditView.length()<=1)
						 {
							 int ac=editList.indexOf(edittext);
							 LinearLayout l2=(LinearLayout)layoutList.get(ac);
							
							 l2.setVisibility(View.INVISIBLE);
							 layoutList.remove(ac);
							 editList.remove(ac);
							 ((LinearLayout) findViewById(R.id.rl)).removeView(l2);
							
							 count--;
							 
							 if(ac>=1)
							 {
							 EditText e2=(EditText)editList.get(ac-1);
							 int cursorPosition1 = e2.getSelectionStart();
							 e2.setSelection(cursorPosition1);
							 e2.setFocusableInTouchMode(true);
						        e2.requestFocus();
						        prev_val=editList.indexOf(e2)-1;
							 }
							 s_prev="";
							 fg=1;
							 prev_val=ac-1;
							 editTextAdder();
						 }
						 else
						 {
						 int cursorPosition = edittext.getSelectionStart();
						 
						 edittext.setText(textFromEditView.replace('\n', ' ').trim());
						 
						 edittext.setSelection(cursorPosition-1);
						 prev_val=editList.indexOf(edittext);
					//	 Toast.makeText(getApplicationContext(), prev_val+"", Toast.LENGTH_SHORT).show();
						 
						// Toast.makeText(getApplicationContext(), textFromEditView, Toast.LENGTH_SHORT).show();
						 s_prev="";
						modifier();
						 }
						 
						 
}
					}
					else
					{
						 int ac=editList.indexOf(edittext);
						 LinearLayout l2=(LinearLayout)layoutList.get(ac);
						 
						 l2.setVisibility(View.INVISIBLE);
						 layoutList.remove(ac);
						 editList.remove(ac);
						 ((LinearLayout) findViewById(R.id.rl)).removeView(l2);
						
						 count--;
						 
						 if(ac>=1)
						 {
						 EditText e2=(EditText)editList.get(ac-1);
						 int cursorPosition1 = e2.getSelectionStart();
						 e2.setSelection(cursorPosition1);
						 e2.setFocusableInTouchMode(true);
					        e2.requestFocus();
					        prev_val=editList.indexOf(e2)-1;
						
						 }
						 s_prev="";
						 prev_val=ac-1;
						 fg=1;
						 editTextAdder();
					}
		        	   //
				}
	        	});
    }

    void editTextAdder()
    {
    	LinearLayout LL = new LinearLayout(this);
	      
	      final EditText edittext1=new EditText(this);
	      edittext1.setBackgroundColor(Color.WHITE);
	       LL.setOrientation(LinearLayout.HORIZONTAL);
	      // LL.setPadding(4, 0, 0, 0);
	       LayoutParams LLParams = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
	       
	     
	       LinearLayout.LayoutParams dummyParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
	       
	       edittext1.setLayoutParams(dummyParams);
	       String sw=" "+s_prev;
	       edittext1.setText(sw);
	      
	       if(count==0)
	        {
	        	edittext1.setHint("Enter your note!");
	        }
	       LL.setLayoutParams(LLParams);
	      
	       LL.addView(edittext1);
	       layoutList.add(prev_val+1, LL);
	        editList.add(prev_val+1,edittext1);
	       
	        if((prev_val+1)!=count)
	        {
	       for(LinearLayout l1:layoutList)
	       {
	    	   ((LinearLayout) findViewById(R.id.rl)).removeView(l1);   
	    	   ((LinearLayout) findViewById(R.id.rl)).addView(l1);
	       }
	        }
	        else
	        	
	        ((LinearLayout) findViewById(R.id.rl)).addView(LL);
	      
	        count++;
	        
	             edittext1.setFocusableInTouchMode(true);
	        edittext1.requestFocus();
	        
	        
	        edittext1.setOnTouchListener(new OnTouchListener() {
		           

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					prev_val=editList.indexOf(edittext1)-1;
					return false;
				}
	        });
	        
	        
	        
	        
	        edittext1.addTextChangedListener(new TextWatcher() {
	        	  

	        	  @Override
	        	  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	        	    //nothing needed here...
	        	  }

	        	  @Override
	        	  public void onTextChanged(CharSequence s, int start, int before, int count) {
	        	    //nothing needed here...
	        		  
	        	  }

				@Override
				public void afterTextChanged(Editable e) {
					// TODO Auto-generated method stub
					
					 String textFromEditView = e.toString();
					if(textFromEditView.length()>1)
					{
					 if(textFromEditView.charAt(textFromEditView.length()-1)=='\n')
					 {
						
						 
						 if(textFromEditView.length()<=2)
						 {
							 
							 int cursorPosition = edittext1.getSelectionStart();
							 
							 edittext1.setText(textFromEditView.replace('\n', ' ').trim());
							 
							// edittext1.setSelection(cursorPosition-1);
							 prev_val=editList.indexOf(edittext1);
						//	 Toast.makeText(getApplicationContext(), prev_val+"", Toast.LENGTH_SHORT).show();
							 
							// Toast.makeText(getApplicationContext(), textFromEditView, Toast.LENGTH_SHORT).show();
							 s_prev="";
							modifier();
							 
							
						 }
						 else
						 {
						 int cursorPosition = edittext1.getSelectionStart();
						 
						 edittext1.setText(textFromEditView.replace('\n', ' ').trim());
						 
						// edittext1.setSelection(cursorPosition-2);
						 prev_val=editList.indexOf(edittext1);
					//	 Toast.makeText(getApplicationContext(), prev_val+"", Toast.LENGTH_SHORT).show();
						 
						// Toast.makeText(getApplicationContext(), textFromEditView, Toast.LENGTH_SHORT).show();
						 s_prev="";
						modifier();
						 }
						 
						 
}
					}
					else
					{
						if(count!=1)
						{
						 int ac=editList.indexOf(edittext1);
						 LinearLayout l2=(LinearLayout)layoutList.get(ac);
						 
						 l2.setVisibility(View.INVISIBLE);
						 layoutList.remove(ac);
						 editList.remove(ac);
						 ((LinearLayout) findViewById(R.id.rl)).removeView(l2);
						
						 count--;
						 if(ac>=1)
						 {
						 EditText e2=(EditText)editList.get(ac-1);
						 int cursorPosition1 = e2.getSelectionStart();
						 e2.setSelection(cursorPosition1);
						 e2.setFocusableInTouchMode(true);
					        e2.requestFocus();
					        prev_val=editList.indexOf(e2)-1;
						
						 }
						}
						 else
						 {
							 edittext1.setHint("Enter Your Note");
						 }
					}
					
		        	   //
				}
	        	});
    }
	void modifier()
	{
		if(c.isChecked())
		{
			checkBoxAdder();
	    
	}
		else
		{
			editTextAdder();
		}

	}
	public void checked(View v)
	{
		
			
		 LinearLayout l2=(LinearLayout)layoutList.get(prev_val+1);
		 s_prev=(editList.get(prev_val+1)).getText().toString();
		 l2.setVisibility(View.INVISIBLE);
		 layoutList.remove(prev_val+1);
		 editList.remove(prev_val+1);
		 ((LinearLayout) findViewById(R.id.rl)).removeView(l2);
		
		 count--;
		 
		 if(prev_val+1>=1)
		 {
		 EditText e2=(EditText)editList.get(prev_val);
		 int cursorPosition1 = e2.getSelectionStart();
		 e2.setSelection(cursorPosition1);
		 e2.setFocusableInTouchMode(true);
	        e2.requestFocus();
	
		 }
		 
		
		modifier();
	}

}
