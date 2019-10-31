package com.shoppingcart.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.shoppingcart.beans.Emp;
import com.shoppingcart.beans.LoginBean;
import com.shoppingcart.controllers.EmpController;
import com.shoppingcart.generateEmployeeTables.EmployeeTables;
import com.shoppingcart.mapper.UserRoleMapper;
@Repository
public class UserRoleDaoImpl implements UserRoleDao{
	private static SessionFactory factory;
	static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    static IdentifierLoadAccess identifierObj;
	private Properties sqlProperties=new Properties();
	final static Logger LOGGER = LogManager.getLogger(UserRoleDaoImpl.class);
	@Autowired
	JdbcTemplate template;
	@Autowired
	LoginBean loginBean;
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
	public String getRole(String username, String password) throws IOException {
		
		String roles=null;
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
			      String queryString = sqlProperties.getProperty("UserRoleQuery");
			     /* String queryString = "select role.x_role_name from TABLE_X_ROLE role,TABLE_WEB_USER webuser where role.x_role_name=? and webUser.objid=?";*/
		Query query = session.createSQLQuery(queryString);
		System.out.println("+++++++++queryString 31 schedule install dao impl+++++++++"+queryString);
		System.out.println("+++++++++queryString 31 schedule install dao impl+++++++++"+query);
		query.setString(0, username);
		query.setString(1, password);
@SuppressWarnings("unchecked")
List<String>list=query.list();
for(String obj:list) {
	roles=(String) obj;
}

		/*for(Object rows : query.list()){
		    Object[] row = (Object[]) rows;
		    roles = (String) row[0];
		    
		}
		*/
		
		
		}catch (Exception e) {
		}   
return  roles;
}
	
public LoginBean getRoleId(String username,int password){ 
	
	String UserRoleQuery="SELECT role FROM  user_roles,users where users.username= :username and password= :password and rownum <2";
	Query query = sessionObj.createSQLQuery(UserRoleQuery);
	/*query.setParameter("userName", username);
	query.setParameter("password", password);*/
	query.setString(0, username);
	query.setInteger(1, password);
	return (LoginBean) query;
	
}    


	      /* userName="'"+"mkyong"+"'";
		  password= 123456;
		  LOGGER.info("get roles by name executed and userName is"+userName);
		  LOGGER.info("get roles by name executed and password is"+password);
		  LOGGER.info("get roles by name executed"+sql);
		  return template.queryForObject(sql, new Object[]{userName,password},
				  new BeanPropertyRowMapper<LoginBean>(LoginBean.class));
	*/
	
		public String getRoleSearchQuery(String userName, String password){
			return this.sqlProperties.getProperty("UserRoleQuery");
		}
		/*public String getRole(String userName, String password) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}*/
		public LoginBean getRoleId(String userName, String password) {
			// TODO Auto-generated method stub
			return null;
		}
}


