package somereturnstoimages;

import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SomeReturnsToJava {
	private static final String driver = "oracle.jdbc.OracleDriver";
	private static final String url="jdbc:oracle:thin:@localhost:1521:harjun250";
	private static final String username = "system";
	private static final String password = "Atram8585";
	private static final String tennis="tennis";
	public static void main(String[] args) {  
		
		  
		try{  
			
			/*    String sql = "select 'photo' from imagetable where name="+"'"+tennis+"'";*/
			    String sql ="select * from filetable";
			    /*String sql ="SELECT name FROM filetable WHERE id = 1";*/
			    Class.forName(driver);
			    Connection con = DriverManager.getConnection(url,username,password);
		              
		PreparedStatement ps=con.prepareStatement(sql);  
		ResultSet rs=ps.executeQuery();  
		if(rs.next()){//now on 1st row  
            
			Blob b=rs.getBlob(2);//2 means 2nd column data
			//2 means 2nd column data
			/*	oracle.sql.BLOB blob= (oracle.sql.BLOB) ((OracleResultSet) rs).getBlob(2);*/
			byte barr[]=b.getBytes(1,(int)b.length());//1 means first image          
			FileOutputStream fout=new FileOutputStream("C:/Users/harjun250/Desktop/eclipseshoppingCart/shoppingCart1/src/main/resources/javafile.JPEG");  
			fout.write(barr);  
			              
			fout.close();  
		}
		              
		System.out.println("success");  
		}catch (Exception e) {
			e.printStackTrace();  }  
		}  
		}  
