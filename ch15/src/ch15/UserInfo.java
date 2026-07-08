package ch15;

public class UserInfo implements java.io.Serializable {
	String id;
	String password;
	int purchaseAmount;
	int bonusPoint;
	
	// 아이디순 정렬
	// 보너스포인트순 정렬
	
	public UserInfo()
	{
		this("None", "1111", 0);
	}
	
	public UserInfo(String id, String password, int purchaseAmount)
	{
		this.id = id;
		this.password = password;
		this.purchaseAmount = purchaseAmount;
		this.bonusPoint = (int)(this.purchaseAmount * 0.1); 
	}
	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", password=" + password + ", purchaseAmount=" + purchaseAmount + ", bonusPoint="
				+ bonusPoint + "]";
	}
}
