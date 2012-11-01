package com.sssta.joinus;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class myActivity extends Activity {
	private TextView textView;
	private Button myActivity;
	private Button publishActivity;
	private ListView list;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);
        ListView list = (ListView) findViewById(R.id.listView_myActivity);
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Activity_name", "test1");
        map.put("Activity_shetuan", "test1");
        map.put("Activity_time", "test1");
        map.put("Activity_place", "test1");
        mylist.add(map);
        SimpleAdapter mSchedule = new SimpleAdapter(this, // 没什么解释
				mylist,// 数据来源
				R.layout.listitem_small,// ListItem的XML实现

				// 动态数组与ListItem对应的子项
				new String[] { "Activity_name", "Activity_shetuan","Activity_time","Activity_place" },

				// ListItem的XML文件里面的两个TextView ID
				new int[] { R.id.Activity_name_small, R.id.Activity_shetuan_small,R.id.Activity_time_small,R.id.Activity_place_small });
		// 添加并且显示
		list.setAdapter(mSchedule);
       publishActivity=(Button)findViewById(R.id.publishActivity);
       publishActivity.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent= new Intent();
			intent.setClass(myActivity.this, activity_publish.class);
			startActivity(intent);
			
		}
	});
	}
}
