package ch15;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class UserInfoExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			String fileName = "UserInfo.txt";
			FileOutputStream fos = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			ObjectOutputStream out = new ObjectOutputStream(bos);
			
			UserInfo u1 = new UserInfo("Jiho", "1234", 5000);
			UserInfo u2 = new UserInfo("Sangha", "4321", 10000);
			UserInfo u3 = new UserInfo("Farmer", "2222", 15000);
			
			ArrayList<UserInfo> list = new ArrayList<>();
			list.add(u1);
			list.add(u2);
			list.add(u3);
			
			System.out.println("<list 출력>");
			showList(list);
			
			System.out.println("====================================");
			
			System.out.println("<id 순으로 오름차순 정렬 후 list 출력>");
			Collections.sort(list, new IdAscending());
			showList(list);
			
			System.out.println("====================================");
			
			System.out.println("<bonusPoint 순으로 내림차순 정렬 후 list 출력>");
			Collections.sort(list, new BonusPointDescending());
			showList(list);
			
			System.out.println("====================================");
			
			out.writeObject(u1);
			out.writeObject(u2);
			out.writeObject(u3);
			out.writeObject(list);
			out.close();
			System.out.println("직렬화가 잘 끝났습니다.");
			System.out.println("====================================");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			// TODO Auto-generated method stub
			String fileName = "UserInfo.txt";
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			ObjectInputStream in = new ObjectInputStream(bis);
			
			UserInfo u1 = (UserInfo)in.readObject();
			UserInfo u2 = (UserInfo)in.readObject();
			UserInfo u3 = (UserInfo)in.readObject();
			ArrayList list = (ArrayList)in.readObject();
			
			System.out.println(u1);
			System.out.println(u2);
			System.out.println(u3);
			System.out.println(list);
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void showList(ArrayList<UserInfo> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof UserInfo)
			{
				System.out.println(list.get(i));
			}
		}
	}
}