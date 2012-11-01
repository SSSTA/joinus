package com.sssta.joinus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class activity_detail extends Activity {
	private TextView textView;
	 public void onCreate(Bundle savedInstanceState) {
		 	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_detail); 
	        textView=(TextView)findViewById(R.id.test_detail);
	 }
	
}
