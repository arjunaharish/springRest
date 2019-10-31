package somereturnstoimages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.jdbc.core.JdbcTemplate;

public class Main {
	private static JdbcTemplate jdbcTemp;

	
	 public static Blob getPhotoById(String name) {
		 name="tennis";
			String query = "select photo from IMGTABLE where name=?";
			Blob photo = jdbcTemp.queryForObject(query, new Object[] { "'"+name+"'" }, Blob.class);
			return photo;
		}
	
  static String url = "jdbc:oracle:thin:@localhost:1521:harjun250";
  static String username = "system";
  static String password = "Atram8585";
  public static void main(String[] args) throws Exception {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection conn = DriverManager.getConnection(url, username, password);

    String sql = "SELECT name, description, image FROM pictures ";
    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet resultSet = stmt.executeQuery();
    while (resultSet.next()) {
      String name = resultSet.getString(1);
      String description = resultSet.getString(2);
      File image = new File("C:/Users/harjun250/Desktop/eclipseshoppingCart/shoppingCart1/src/main/resources/inline2.png");
      FileOutputStream fos = new FileOutputStream(image);

      byte[] buffer = new byte[1];
      InputStream is = resultSet.getBinaryStream(3);
      while (is.read(buffer) > 0) {
        fos.write(buffer);
      }
      fos.close();
    }
    conn.close();
  }
}