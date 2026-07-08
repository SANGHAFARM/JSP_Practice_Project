package ch15;

import java.util.Comparator;

public class IdAscending implements Comparator {
	@Override
	public int compare(Object o1, Object o2)
	{
		if (o1 instanceof UserInfo && o2 instanceof UserInfo)
		{
			UserInfo u1 = (UserInfo)o1;
			UserInfo u2 = (UserInfo)o2;
			
			return (u1.id).compareTo(u2.id);
			
		}
		
		return -1;
	}
}
