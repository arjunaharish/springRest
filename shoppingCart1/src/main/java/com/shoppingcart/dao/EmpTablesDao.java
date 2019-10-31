package com.shoppingcart.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shoppingcart.beans.Emp;
import com.shoppingcart.beans.Student;
import com.shoppingcart.generateEmployeeTables.DatabaseImages;
import com.shoppingcart.generateEmployeeTables.EmployeeTables;

public class EmpTablesDao {
	JdbcTemplate template;  

	
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}    
	public int save(EmployeeTables p){    
	    String sql="insert into Emp(name,salary,designation) values('"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";    
	    return template.update(sql);    
	}    
	public String update(EmployeeTables p){    
		p.setName("harry");
		p.setId(1);
		p.setSalary(2.356f);
		p.setName("barry");
		p.setId(1);
		p.setSalary(2.356f);
		String sql="update Emp set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";    
	    return sql;    
	}    
	public int delete(int id){    
	    String sql="delete from Emp where id="+id+"";    
	    return template.update(sql);    
	}    
	public EmployeeTables getEmpById(int id){    
	    String sql="select * from Emp where id=?";    
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<EmployeeTables>(EmployeeTables.class));
	}    
	
	/*remove the comment to run the implementation*/
	
	  public List<EmployeeTables> getEmployees() { 
		  return template.query("select * from Emp",new RowMapper<EmployeeTables>(){ public
	  EmployeeTables mapRow(ResultSet rs, int row) throws SQLException {
	  EmployeeTables e=new EmployeeTables(); 
	  e.setId(rs.getInt(1));
	  e.setName(rs.getString(2));
	  e.setSalary(rs.getFloat(3));
	  e.setDesignation(rs.getString(4)); return e; }
	  
	  }); }
	 
	

	  public List<EmployeeTables> getUserRoles() { 
		  return template.query("select * from user_roles users",new RowMapper<EmployeeTables>(){ public
	  EmployeeTables mapRow(ResultSet rs, int row) throws SQLException {
	  EmployeeTables e=new EmployeeTables(); 
	  e.setUserroleId(rs.getInt("USER_ROLE_ID"));
	  e.setRole(rs.getString("ROLE"));
	  e.setUsername(rs.getString("USERNAME"));
	  
	  return e;
	  }
	  
	  }); }
	  
	/*added some default hard coded entry*/
	/* public EmployeeTables getEmployees() {
              
			  EmployeeTables e=new EmployeeTables(0, null, 0, null); 
			  e.setId(1);
			  e.setName("harry"); 
			  e.setSalary(1000);
			  e.setDesignation("usa");
			return e;
			   
			  }
			  */
			  


	
	
	
	
	/*remove the comment to run the implementation*/
	
	  public Emp getEmpByName(String userName,String password){ 
		  String sql="select FIRST_NAME,PASSWORD from users where FIRST_NAME=? and password=?"; 
		  return template.queryForObject(sql, new Object[]{userName,password},
				  new BeanPropertyRowMapper<Emp>(Emp.class)); 
		  }
	 
	 
	 /* public Blob getPhotoById(String name) {
			String query="select 'photo' from imagetable where name=?";
			Blob photo = template.queryForObject(query, new Object[] { name }, Blob.class);
			return photo;
		}*/

	  
	  public  List<DatabaseImages>stuList() throws SQLException {
		  /*PreparedStatement ps=con.prepareStatement("select * from image_table"); 
			ResultSet rs=ps.executeQuery();*/
		  return template.query("select * from imagetable",new RowMapper<DatabaseImages>(){
		  public DatabaseImages mapRow(ResultSet rs, int row) throws SQLException {
			  DatabaseImages e=new DatabaseImages();
			  e.setName(rs.getString(1));
			  e.setPhoto(rs.getBlob(2));
			  while(rs.next()){
				    File file=new File("E:\\image1.png");
					FileOutputStream fos;
					try {
						fos = new FileOutputStream(file);
						byte b[];
						Blob blob;
						blob=rs.getBlob("photo");
						b=blob.getBytes(2,(int)blob.length());
						fos.write(b);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			  }
			  return e;			  
		  }
		  
			  }); 
		  }
				
				
				
				
	  public Blob getPhotoById(int id) {

			String query = "select 'photo' from imagetable where name=?";

			Blob photo = template.queryForObject(query, new Object[] { id }, Blob.class);

			return photo;
		}
	  
	/*added some default hard coded entry*/
	/*public String getEmpByName(String userName,String password){    
		userName="harry";
		password="geocode";
		String sql="select FIRST_NAME,PASSWORD from users where FIRST_NAME=? and password=?";
	    return sql;
	}    */
	
	  
	    @SuppressWarnings("deprecation")
	    public ArrayList<String> loadUserByUsername(String username,String password)
	            throws UsernameNotFoundException, DataAccessException
	    {
	        System.out.println("Getting access details from employee dao !!");
	        String sql="select role from user_roles users where users.username=?";
	        System.out.println("++++++++++++   +sql+   +++++++++++++++"+sql);
	        String userRoles=sql;
	        ArrayList<String>roles=new ArrayList<String>();
    		roles.add(userRoles);
			return roles;
	    }
	  
}