package ch15;

import java.util.Comparator;

public class BonusPointDescending implements Comparator {
	
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		if (o1 instanceof UserInfo && o2 instanceof UserInfo)
		{
			UserInfo u1 = (UserInfo)o1;
			UserInfo u2 = (UserInfo)o2;
			
			if (u1.bonusPoint < u2.bonusPoint)
			{
				return 1;
			}
			else if (u1.bonusPoint == u2.bonusPoint)
			{
				return 0;
			}
			else
			{
				return -1;
			}
		}
		
		return -1;
	}

}
