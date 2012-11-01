package com.sssta.joinus;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sssta.model.Activities;

public class activity_search extends Activity{
	private TextView textView;
	private EditText editText;
	private ListView list;
	private List<Activities> activities=null;
	private String findString;
	private ImageButton search;
	private String name;
	private SimpleAdapter mSchedule;
	private ArrayList<HashMap<String, Object>> mylist;
	private Button showall;
//	private List<Map<String, Object>> mData; // listview数据文件
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_search);
	         
	     //   mData = null;
	     //   mData = new ArrayList<Map<String, Object>>();
	        ListView list = (ListView) findViewById(R.id.listView1);
	        editText=(EditText)findViewById(R.id.text_search);
	        search=(ImageButton)findViewById(R.id.search);
	        showall=(Button)findViewById(R.id.showall);
	        activities=new ArrayList<Activities>();
	        Log.d("activities_search", "success");
	    //    activities=Activities.FindActivitiesByName("这是活动名称");
	        activities=Activities.GetAllActivities();
		    Log.d("activities_search", String.valueOf(activities.size()));
	         mylist = new ArrayList<HashMap<String, Object>>();
	      
	        for(Activities act: activities)
			{
	        	 
	        	HashMap<String, Object> map = new HashMap<String, Object>();
	        	  map.put("Activity_name", act.getName());
	  	        map.put("Activity_shetuan", act.getPublisher_id());
	  	        map.put("Activity_time", act.getTime());
	  	        map.put("Activity_place", act.getPosition());
	  	      Log.i("activities_id", String.valueOf(act.getId()));
	  	        mylist.add(map);
			}
	       
	         mSchedule = new SimpleAdapter(this, // 没什么解释
					mylist,// 数据来源
					R.layout.listitem_small,// ListItem的XML实现

					// 动态数组与ListItem对应的子项
					new String[] { "Activity_name", "Activity_shetuan","Activity_time","Activity_place" },

					// ListItem的XML文件里面的两个TextView ID
					new int[] { R.id.Activity_name_small, R.id.Activity_shetuan_small,R.id.Activity_time_small,R.id.Activity_place_small });
			// 添加并且显示
			list.setAdapter(mSchedule);
			search.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					name=editText.getText().toString();
					activities.clear();
					mylist.clear();
					activities= Activities.FindActivitiesByName(name);
					 Log.i("activities_id_size",String.valueOf(activities.size()));
					 
					
					for(Activities act: activities)
					{
			        	 
						HashMap<String, Object> map = new HashMap<String, Object>();
			        	 map.put("Activity_name", act.getName());
			  	        map.put("Activity_shetuan", act.getPublisher_id());
			  	        map.put("Activity_time", act.getTime());
			  	        map.put("Activity_place", act.getPosition());
			  	      Log.i("activities_id", String.valueOf(act.getId()));
			  	        mylist.add(map);
			  	     // Log.i("activities_id", String.valueOf(act.getId()));
			  	     
					}
					 mSchedule.notifyDataSetChanged();
				}
			});
			showall.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					activities.clear();
					mylist.clear();
					activities= Activities.GetAllActivities();
					 Log.i("activities_id_size",String.valueOf(activities.size()));
					 
					
					for(Activities act: activities)
					{
			        	 
						HashMap<String, Object> map = new HashMap<String, Object>();
			        	 map.put("Activity_name", act.getName());
			  	        map.put("Activity_shetuan", act.getPublisher_id());
			  	        map.put("Activity_time", act.getTime());
			  	        map.put("Activity_place", act.getPosition());
			  	     // Log.i("activities_id", String.valueOf(act.getId()));
			  	        mylist.add(map);
			  	     // Log.i("activities_id", String.valueOf(act.getId()));
			  	     
					}
					 mSchedule.notifyDataSetChanged();
				}
			});
	 }

}
