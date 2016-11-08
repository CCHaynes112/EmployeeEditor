import java.sql.*;

public class MySQL {

	public static Connection conn = null;
	public static Statement stmt = null;
	
	public static void connectMySQL() 
	{
		try
		{
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?autoReconnect=true&useSSL=false", "root", "root");

			if(conn != null)
			{
				System.out.println("Connected Successfully.");
			}
		}
		
		catch(Exception e)
		{
			System.out.println("Didn't connect to database");
		}
	}
}
