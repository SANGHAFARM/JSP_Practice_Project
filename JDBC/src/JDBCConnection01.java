import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCConnection01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		Connection con = null;
		Statement stmt = null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, "system", "1234");
			stmt = con.createStatement();
			System.out.println("DB Connection OK!");
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
