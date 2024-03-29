package com.sssta.joinus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sssta.model.Activities;


public class MainActivity extends Activity{
	// ViewPager是google SDk中自带的一个附加包的一个类，可以用来实现屏幕间的切换。
		// android-support-v4.jar
	
		private Button activity_recent;
		private Button activity_search;
		private Button myActivity;
		private ViewPager mPager;//页卡内容
		private List<View> listViews=null; // Tab页面列表
		private List<Activities> activities=null;
		private ImageView cursor;// 动画图片
		private TextView t1, t2, t3;  // 页卡头标
		private int offset = 0;// 动画图片偏移量
		private int currIndex = 0;// 当前页卡编号
		private int bmpW;// 动画图片宽度
		private int count;
		private int temp_number=0;
		private TextView textView_name;
		private TextView textView_shetuan;
		private TextView textView_time;
		private TextView textView_place;
		//private Set<Activities> activities_set=null;
		
		 private View layout_temp = null;
		 private String text_activity; 
		 private String shetuan;
		 private String text_time;
		 private String text_place;
		 private Button refresh;
		 
		 
		 

	
	 public void onCreate(Bundle savedInstanceState) {
		
		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	        .detectDiskReads()
	        .detectDiskWrites()
	        .detectNetwork()   // or .detectAll() for all detectable problems
	        .penaltyLog()
	        .build());
	        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
	        .detectLeakedSqlLiteObjects()
	        .detectLeakedClosableObjects()
	        .penaltyLog()
	        .penaltyDeath()
	        .build());   
		 super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_main);
	        
	      /*  String string="select * from activities;";
	        Log.i("start", "success");
			//string=cinScanner.nextLine();
	        try {
	        	String m=SocketClient.Execute(string);
				System.out.println(m);
				Log.e("x", m);
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("x", "failed");
			}  */
	       activities=new ArrayList<Activities>();
	       Log.i("getall", "success2");
	       activities=Activities.GetAllActivities();
	     //  activities=Activities.FindActivitiesByName("这是活动名称");
	       Log.i("getall", "success1");
	     //  activities_set= new HashSet<Activities>();
	     //  activities_set.addAll(activities);
	        Log.w("activity", String.valueOf(activities.size()));
	       // Log.w("activity_set", String.valueOf(activities_set.size()));
	        if(activities.size()>=8)
	        	count =8;
	        else count=activities.size();
	        Log.d("count", String.valueOf(count));
	       
	        activity_search=(Button)findViewById(R.id.activity_search);
	        refresh =(Button)findViewById(R.id.refresh);
	        refresh.setVisibility(View.GONE);
	      //  activity_recent=(Button)findViewById(0x7f080002);
	        myActivity=(Button)findViewById(R.id.myActivity);
	        InitViewPager();
	        refresh.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				//	activities.clear();
				//	listViews.clear();
				//	mPager.n
				//	InitViewPager();
				}
			});
	        myActivity.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					/* 指定intent要启动的类 */
					intent.setClass(MainActivity.this, myActivity.class);
					/* 启动一个新的Activity */
					startActivity(intent);
				}
			});
	        activity_search.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					/* 指定intent要启动的类 */
					intent.setClass(MainActivity.this, activity_search.class);
					/* 启动一个新的Activity */
					startActivity(intent);
				
				}
			});
	        
	 
	        
	   }
	 /**
		 * 初始化ViewPager
		 */
		private void InitViewPager() {
			mPager = (ViewPager) findViewById(R.id.vPager);
			count =0 ;
			listViews = new ArrayList<View>();
			
			LayoutInflater mInflater = getLayoutInflater();
			
			for(Activities act: activities)
			{
				Log.d("tempnumber", String.valueOf(temp_number));
				Log.d("count", String.valueOf(count));
				if(temp_number>7)
					break;
				temp_number++;
				text_activity=act.getName();
		        shetuan=String.valueOf(act.getId());
		        text_time=act.getTime();
		        text_place=act.getPosition();
				layout_temp=mInflater.inflate(R.layout.list_for_viewpager, null);
				textView_name=(TextView)layout_temp.findViewById(R.id.textView1);
				textView_shetuan=(TextView)layout_temp.findViewById(R.id.shetuan_large3);
				textView_time=(TextView)layout_temp.findViewById(R.id.time_large2);
				textView_place=(TextView)layout_temp.findViewById(R.id.place_large2);
				textView_name.setText(text_activity);
				textView_shetuan.setText(shetuan);
				textView_place.setText(text_place);
				textView_time.setText(text_time);
				listViews.add(layout_temp);
			}
			
			//Log.e("test", testTextView.getText().toString());
			mPager.setAdapter(new MyPagerAdapter(listViews));
			mPager.setCurrentItem(0);
			//mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		}
	 /**
		 * ViewPager适配器
		 */
		public class MyPagerAdapter extends PagerAdapter {
			public List<View> mListViews;

			public MyPagerAdapter(List<View> mListViews) {
				this.mListViews = mListViews;
			}

			@Override
			public void destroyItem(View arg0, int arg1, Object arg2) {
				((ViewPager) arg0).removeView(mListViews.get(arg1));
			}

			@Override
			public void finishUpdate(View arg0) {
			}

			@Override
			public int getCount() {
				return mListViews.size();
			}

			@Override
			public Object instantiateItem(View arg0, int arg1) {
				((ViewPager) arg0).addView(mListViews.get(arg1), 0);
				return mListViews.get(arg1);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == (arg1);
			}

			@Override
			public void restoreState(Parcelable arg0, ClassLoader arg1) {
			}

			@Override
			public Parcelable saveState() {
				return null;
			}

			@Override
			public void startUpdate(View arg0) {
			}
			@Override  
			public int getItemPosition(Object object) {  
			    return POSITION_NONE;  
			}  
		}
		
}
