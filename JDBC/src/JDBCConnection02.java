import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnection02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String sql = "select * from userinfo1";
		
		Connection con = null;
		Statement stmt = null;	
		ResultSet rs = null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, "system", "1234");
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("id \t 이름 \t 전화번호 \t 이메일 \t 날짜 ");
			System.out.println("======================================================");
			
			while (rs.next())
			{
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\t");
				System.out.print(rs.getString(4) + "\t");
				System.out.print(rs.getString(5) + "\n");
			}
		}
		catch (Exception e)
		{
			System.out.println("DB Connection failure!");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
					rs.close();
				
				if (stmt != null)
					stmt.close();
				
				if (con != null)
					con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
