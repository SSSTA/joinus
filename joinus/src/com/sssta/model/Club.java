package com.sssta.model;

import java.util.ArrayList;
import java.util.List;

import com.sssta.joinus.SocketClient;

public class Club {
	String id;//社团的用户名
	String name;//社团的名称
	String pwd;//社团账户密码
	String description;//社团的介绍
	
	public Club()
	{
		
	}
	
	public Club(String idString,String nameString,String pwdString,String descriptionString)
	{
		id=idString;
		name=nameString;
		pwd=pwdString;
		description=descriptionString;
	}
	
	public Club(String[] propertiesStrings)
	{
		this(propertiesStrings[0],propertiesStrings[1],propertiesStrings[2],propertiesStrings[3]);
	}
	
	//获取所有社团
	public static List<Club>GetAllClub()
	{
		String AllClubString=SocketClient.Execute("select * from club");
		String[] ClubStrings=AllClubString.split(ConstValue.DivRowString);
		List<Club> clubList=new ArrayList<Club>();
		for (String string : ClubStrings) {
			String[] fieldsstrings=string.split(ConstValue.DivFieldString);
			Club club=new Club(fieldsstrings);
			clubList.add(club);
		}
		return clubList;
	}
	
	//根据社团ID获取社团
	public static Club GetClub(String idString)
	{
		String clubString=SocketClient.Execute("select * from club where club_id = "+ConstValue.MakeString(idString));
		String[] fieldStrings=clubString.split(ConstValue.DivFieldString);
		return new Club(fieldStrings);
	}
	
	//根据社团名称获取社团
	public static Club GetClubByName(String clubnameString)
	{
		String clubString=SocketClient.Execute("select * from club where club_name = "+ConstValue.MakeString(clubnameString));
		String [] propertiesStrings=clubnameString.split(ConstValue.DivFieldString);
		return new Club(propertiesStrings);
	}
}
