package somereturnstoimages;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SimpleBlobExample {

	public static void main(String[] args) {
		try {
			Connection con;
			PreparedStatement pre;
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:harjun250", "system", "Atram8585");

			File picfile = new File("C:/Users/harjun250/Desktop/eclipseshoppingCart/shoppingCart1/src/main/resources/inline.png");
			FileInputStream fis = new FileInputStream(picfile);

			pre = con
					.prepareStatement("insert into IMGTABLE (name,photo) values (?,?)");
		/*	pre = con
					.prepareStatement("SELECT photo FROM IMGTABLE");*/
			pre.setString(1, picfile.getName());
			pre.setBinaryStream(2, fis, (int) picfile.length());
			int count = pre.executeUpdate();

			System.out.println("isUpdated? " + count);

			pre.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}