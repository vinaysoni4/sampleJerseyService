import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class database {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Database creation");
		try{
		 Connection connection = getConnection();
	        
	        Statement stmt = connection.createStatement();
	        
	      //  String custassign = "CREATE TABLE customerassign (saleempid int, custid int, starttime timestamp,endtime timestamp)";
	    //   String values = "INSERT INTO customerassign VALUES(2,1, now())";
	   //     stmt.executeUpdate("DROP TABLE customerassign");
	      // stmt.executeUpdate(values);
//	        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
	        //stmt.executeUpdate("UPDATE customerassign set endtime=NULL WHERE saleempid=2");
	        stmt.executeUpdate("UPDATE salesemployee set status=1 where saleempid=2");
//	        while (rs.next()) {
//	            System.out.println("employeeid:  "+rs.getString("name") +"   "+ rs.getString("number"));
//	        }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	    }
	    
	    private static Connection getConnection() throws Exception {
	    	Class.forName("org.postgresql.Driver");
	        URI dbUri = new URI("postgres://rjewavevkcfjxg:0MWDh6J_IgcLldtc7cuLJ9CHTz@ec2-54-204-13-220.compute-1.amazonaws.com:5432/d5q3egpp6ad5");

	        String username = dbUri.getUserInfo().split(":")[0];
	        String password = dbUri.getUserInfo().split(":")[1];
	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath()+"?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
System.out.println(username+"  "+password+"   "+dbUrl);
	        return DriverManager.getConnection(dbUrl, username, password);
	    }
}


