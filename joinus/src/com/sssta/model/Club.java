package com.sssta.model;

import java.util.ArrayList;
import java.util.List;

import com.sssta.joinus.SocketClient;

public class Club {
	String id;//���ŵ��û���
	String name;//���ŵ�����
	String pwd;//�����˻�����
	String description;//���ŵĽ���
	
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
	
	//��ȡ��������
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
	
	//��������ID��ȡ����
	public static Club GetClub(String idString)
	{
		String clubString=SocketClient.Execute("select * from club where club_id = "+ConstValue.MakeString(idString));
		String[] fieldStrings=clubString.split(ConstValue.DivFieldString);
		return new Club(fieldStrings);
	}
	
	//�����������ƻ�ȡ����
	public static Club GetClubByName(String clubnameString)
	{
		String clubString=SocketClient.Execute("select * from club where club_name = "+ConstValue.MakeString(clubnameString));
		String [] propertiesStrings=clubnameString.split(ConstValue.DivFieldString);
		return new Club(propertiesStrings);
	}
}
