package com.sssta.model;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.sssta.joinus.SocketClient;
public class Activities {
	public int id; //活动的id(作为主键,无实际意义)
	public String name; //活动名称
	public String content; //活动内容
	public String publisher_id; //活动发布者id 可通过发布者id找到发布者
	public String position; //活动举行地点
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(String publisher_id) {
		this.publisher_id = publisher_id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getLove_count() {
		return love_count;
	}
	public void setLove_count(int love_count) {
		this.love_count = love_count;
	}
	public int getJoin_count() {
		return join_count;
	}
	public void setJoin_count(int join_count) {
		this.join_count = join_count;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public int love_count; //喜爱活动的人数
	public int join_count; //参加活动的人数
	public String time; //活动的举办时间

	public Activities(){}
	public Activities(String idString,String nameString,String contentString,String publisher_idString,String positionString,String love_countString,String join_countString,String timeString)
	{
		id=Integer.parseInt(idString);
		name=nameString;
		content=contentString;
		publisher_id=publisher_idString;
		position=positionString;
		love_count=Integer.parseInt(love_countString);
		join_count=Integer.parseInt(join_countString);
		time=timeString;
	}
	
	@Override
	public String toString() {
		String string= "values("
				+id+","
				+"'"+name+"'"+","
				+"'"+content+"'"+","
				+"'"+publisher_id +"'"+","
				+"'"+position+"'"+","
				+love_count+","
				+join_count+",";
		if(time!=null)
			string+="'"+time+"'"
				+")";
		else 
			string+="NULL"+")";
		return string;
	}
	
	public Activities(String [] propertiesStrings)
	{
		this(propertiesStrings[0],propertiesStrings[1],propertiesStrings[2],propertiesStrings[3],propertiesStrings[4],propertiesStrings[5],propertiesStrings[6],propertiesStrings[7]);
	}
	
	//获取所有活动
	static public List<Activities> GetAllActivities()
	{
		String AllActivities=SocketClient.Execute("select * from activities");
		 Log.i("execute", AllActivities);
		String[] ActivitiesListStrings=AllActivities.split(ConstValue.DivRowString);
		List<Activities> ActivitiesList=new ArrayList<Activities>();
		for (String string : ActivitiesListStrings) {
			String[] PropertyList=string.split(ConstValue.DivFieldString);
			Activities activities=new Activities(PropertyList);
			ActivitiesList.add(activities);
		}
		return ActivitiesList;
	}
	
	//通过活动id找到活动
	static public Activities FindActivities(int id)
	{
		String activitiesString=SocketClient.Execute("select * from activities where activities_id = "+id);
		String[] propertiesStrings=activitiesString.split(ConstValue.DivFieldString);
		Activities activities=new Activities(propertiesStrings);
		return activities;
	}
	
	//通过活动名称找到活动
	static public List<Activities> FindActivitiesByName(String name)
	{
		String AllActivitiesString=SocketClient.Execute("select * from activities where activities_name = "+ConstValue.MakeString(name));
		String[] activitiesStrings=AllActivitiesString.split(ConstValue.DivRowString);
		List<Activities> activitiesList=new ArrayList<Activities>();
		for (String string : activitiesStrings) {
			String[] propertiesString=string.split(ConstValue.DivFieldString);
			Activities activities=new Activities(propertiesString);
			activitiesList.add(activities);
		}
		return activitiesList;
	}
	
	//添加单个活动
	//成功返回true失败返回false
	static public Boolean AddActivities(Activities activities)
	{
		try{
		SocketClient.Execute("insert into activities "+activities.toString());
		return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
}
