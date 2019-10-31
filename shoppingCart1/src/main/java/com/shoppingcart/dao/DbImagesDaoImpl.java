package com.shoppingcart.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DbImagesDaoImpl implements DbImagesDao {

	private static SessionFactory factory;
	static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    static IdentifierLoadAccess identifierObj;
    static String url = "jdbc:oracle:thin:@localhost:1521:harjun250";
    static String username = "system";
    static String password = "Atram8585";
	private Properties sqlProperties=new Properties();
	final static Logger LOGGER = LogManager.getLogger(DbImagesDaoImpl.class);
	@Autowired
	JdbcTemplate template;
	private static SessionFactory buildSessionFactory() {
		if(factory != null) return factory;

        // Creating Configuration Instance & Passing Hibernate Configuration File.
		File f = new File("C:/Users/harjun250/Desktop/eclipseshoppingCart/shoppingCart1/src/main/resources/hibernate.cfg.xml"); 
        Configuration con = new Configuration().configure(f);
			 if(con.configure()==null) {
				 System.out.println("hibernate Configuration is null and cannot load");
			 }
        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used.
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build(); 
 
        // Creating Hibernate SessionFactory Instance.
        factory = con.buildSessionFactory(serviceRegistryObj);
        return factory;
    }

	
	
	public InputStream getDbImages() throws SQLException, IOException {
		InputStream images=null;
		InputStream iStream = null;
		try{
			  iStream = this.getClass().getClassLoader().
                      getResourceAsStream("queries.properties");
			    if(iStream == null){
			   throw new IOException("File not found");
			  }
			    sqlProperties.load(iStream);
			    Session session = buildSessionFactory().openSession();
			      Transaction tx = null;
			      tx = session.beginTransaction();
			      String queryString = sqlProperties.getProperty("GetDbImages");
			      String queryString1 = "select 'photo' from imagetable where name=?";
		Query query = session.createSQLQuery(queryString);
		System.out.println("+++++++++queryString 31 schedule install dao impl+++++++++"+queryString);
		System.out.println("+++++++++queryString 31 schedule install dao impl+++++++++"+query);
		Connection conn = DriverManager.getConnection(url, username, password);
		PreparedStatement stmt = conn.prepareStatement(queryString);
	    ResultSet resultSet = stmt.executeQuery();
	    while (resultSet.next()) {
	        String name = resultSet.getString(1);
	        String description = resultSet.getString(2);
	        File image = new File("C:/Users/harjun250/Desktop/eclipseshoppingCart/shoppingCart1/src/main/resources/javafile3.png");
	        FileOutputStream fos = new FileOutputStream(image);
	        byte[] buffer = new byte[1];
	        InputStream is = resultSet.getBinaryStream(3);
	        images=is;
	        while (is.read(buffer) > 0) {
	          fos.write(buffer);
	        }
	        fos.close();
	      }
	    conn.close();
	    }finally {
	    	
	    }
		return images;
	    }
	
		
/*some needed code for later implementation*/		
/*		
		query.setString(0, "'photo'");
		query.setString(0, photo);
		query1.setString(1, photo);
@SuppressWarnings("unchecked")
List<String>list=query.list();
for(String obj:list) {
	images=(String) obj;
}

List<String> list =query.list();		
for(String obj : list) {
	images = (String) obj;
}

List<String> list = query.list();			
for(String obj : list) {
	
	images = (String) obj;
	 File image = new File("C:/Users/harjun250/Desktop/eclipseshoppingCart/shoppingCart1/src/main/resources/javafile.png");
     FileOutputStream fos = new FileOutputStream(image);
     byte[] buffer = new byte[1];
       fos.write(buffer);
     fos.close();
     
   }
		}catch (Exception e) {
		}   
return  images;

	}
*/	


	public String getImagesSearchQuery(){
		return this.sqlProperties.getProperty("GetDbImages");
	}
}
