package com.shoppingcart.controllers;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.configuration.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.beans.Emp;
import com.shoppingcart.beans.LoginBean;
import com.shoppingcart.constants.UserRolesConstants;
import com.shoppingcart.dao.EmpTablesDao;
import com.shoppingcart.dao.EmployeeServImpl;
import com.shoppingcart.dao.UserRoleDao;
import com.shoppingcart.generateEmployeeTables.EmployeeTables;
@Controller 
@Component
public class EmpController {
	/* static Log log = LogFactory.getLog(EmpController.class.getName()); */
	/*
	 * static final Logger LOGGER =
	 * LogManager.getLogger(EmpController.class.getName());
	 */
	private final String successMessage="You did a great service";
	final static Logger LOGGER = LogManager.getLogger(EmpController.class);
    @Autowired    
    EmpTablesDao empDao;
    @Autowired
    UserRoleDao userRole;
    @Autowired
    EmployeeServImpl empService;
    /*It displays a form to input data, here "command" is a reserved request attribute  
     *which is used to display object data into form  
     */    
    @RequestMapping("/empform")    
    public String showform(Model m){    
        m.addAttribute("command", new Emp());  
        return "empform";   
    }
    
   /*@RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }*/
    /*It saves object into database. The @ModelAttribute puts request data  
     *  into model object. You need to mention RequestMethod.POST method   
     *  because default request is GET*/    
    @RequestMapping(value="/save",method = RequestMethod.POST)    
    public String save(@ModelAttribute("emp") EmployeeTables emp){    
        empDao.save(emp);    
        return "redirect:/viewemp";//will redirect to viewemp request mapping    
    }    
    /* It provides list of employees in model object */    
    /*@RequestMapping("/viewemp")    
    public String viewemp(Model m){    
        List<Emp> list=empDao.getEmployees();    
        m.addAttribute("list",list);  
        return "viewemp";    
    }*/
    
    
	/* uncomment if you need the implementation*/
	
	 @RequestMapping(value="/viewemp",method = {RequestMethod.GET}) public String viewemp(Model m){
	 List<EmployeeTables> list=empDao.getEmployees(); m.addAttribute("list",list);
	  return "viewemp"; }
	 
    
	/* comment out if you dont need the default harcoded values */ 
    /*@RequestMapping(value="/viewemp")    
    public String viewemp(Model m){    
        EmployeeTables list=empDao.getEmployees();    
        m.addAttribute("usa",list.getDesignation());
        m.addAttribute("barry",list.getName());
        m.addAttribute("1",list.getId());
        m.addAttribute("1000",list.getSalary());
        return "viewemp";    
    }*/
    
    /* It displays object data into form for the given id.   
     * The @PathVariable puts URL data into variable.*/    
    @RequestMapping(value="/editemp/{id}")    
    public String edit(@PathVariable int id, Model m){    
    	EmployeeTables emp=empDao.getEmpById(id);    
        m.addAttribute("command",emp);  
        return "empeditform";    
    }    
    /* It updates model object. */    
    @RequestMapping(value="/editsave",method = RequestMethod.POST)    
    public String editsave(@ModelAttribute("emp") EmployeeTables emp){    
        empDao.update(emp);    
        return "redirect:/viewemp";    
    }    
    
    /* It deletes record for the given id in URL and redirects to /viewemp */    
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)    
    public String delete(@PathVariable int id){    
        empDao.delete(id);    
        return "redirect:/viewemp";    
    } 
    
	/*
	 * @RequestMapping(value="/loginvalidation/{username}/{password}",method =
	 * RequestMethod.POST) public ModelAndView loginValidation(@PathVariable String
	 * username,String password){ ModelAndView modelAndView = new ModelAndView();
	 * if(empDao.getUserToValidate(username, password)!=0 ) {
	 * modelAndView.setViewName("redirect:/viewemp"); return modelAndView; }else {
	 * modelAndView.setViewName("logout"); return modelAndView; }
	 * 
	 * }
	 */
    @RequestMapping(value="/loginvalidation/{username}/{password}", method=RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int createLoginForm(@RequestBody EmployeeTables empTablesDao) {
        return empDao.save(empTablesDao);
    }
    
   /* @RequestMapping(value ={"/", "/login"},method = RequestMethod.GET)
    public String init(Model model) throws InterruptedException {
    	//log it via log4j
    	  if(LOGGER.isDebugEnabled()){
    	   LOGGER.debug("Start debug");
    	  }
    	  LOGGER.info("Going to run HelloLoggingController class");
    	  LOGGER.debug("Going to run HelloLoggingController class");
		 for(int i = 0; i < 2000; i++) { 
    	  LOGGER.fatal("Going to run HelloLoggingController class");
		
		 * Thread.sleep(100); }
		 
    	  LOGGER.warn("Going to run HelloLoggingController class");
      model.addAttribute("msg", "Please Enter Your Login Details");
      LOGGER.info("Exiting the program");
	  LOGGER.info("Going to run HelloLoggingController class");
	  LOGGER.debug("Going to run HelloLoggingController class");
	  LOGGER.fatal("Going to run HelloLoggingController class");
	  LOGGER.warn("Going to run HelloLoggingController class");
      return "login";
    }*/
  
    // Specify name of a specific view that will be used to display the error:
	
	 @ExceptionHandler({SQLException.class,DataAccessException.class}) public
	  String databaseError() { // Nothing to do. Returns the logical view name of an error page, passed // to the view-resolver(s) in usual way. 
		  // Note that the exception is NOT available to this view (it is not added // to the model) but see "Extending ExceptionHandlerExceptionResolver" 
		  // below. 
		  //log it via log4j
	  if(LOGGER.isDebugEnabled()){ 
      LOGGER.debug("Start debug"); }
	  LOGGER.info("Going to run HelloLoggingController class");
	  LOGGER.info("Exiting the program");
	  return "denied";
	  }
	 
    
	/* @RequestMapping(value="/login",method = RequestMethod.POST)
    public String submit(Model model, @ModelAttribute("loginBean") LoginBean loginBean,@ModelAttribute("userName") String userName,@ModelAttribute("password") String password,HttpServletRequest httpRequest) {
      if (loginBean != null && loginBean.getUserName() != null & loginBean.getPassword() != null) {
    	  if (empDao.getEmpByName(userName, password) != null) {
    		  httpRequest.getSession().setAttribute("LOGGEDIN_USER", loginBean);
    	  }
          return "redirect:/viewemp";
        }
      if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("Start debug");
    	  }
  	LOGGER.info("Going to run HelloLoggingController class");
  	LOGGER.info("Exiting the program");
      return "denied";
      
    }*/
    
	 
	 
	 @SuppressWarnings("unused")
	 /*@CrossOrigin(origins = "http://localhost:8080/shoppingCart1/login")*/
	 @CrossOrigin(origins = {"http://localhost:4200","http://localhost:4200/login","http://localhost:8080/shoppingCart1/viewemp?userName=&password="})
	 
	 
	@RequestMapping(value="/login",method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
	    public String submit(@ModelAttribute("loginBean") LoginBean loginBean,@ModelAttribute("userName") String userName,@ModelAttribute("password") String password,HttpServletRequest httpRequest,@RequestBody LoginBean userLoginBean) throws NumberFormatException, IOException {
	 /*public String submit(@RequestBody LoginBean loginBean,@RequestBody String userName,@RequestBody int password,HttpServletRequest httpRequest) throws NumberFormatException, IOException {*/
		     ModelAndView model = new ModelAndView();
	      if (loginBean != null && loginBean.getUserName() != null) {
	    	  /*if (empDao.getEmpByName(userName, password) != null) {
	    		  httpRequest.getSession().setAttribute("LOGGEDIN_USER", loginBean);
	    	  }*/
	    	  if(loginBean.getUserName()!=null && loginBean.getPassword() != "") {
	    		 String roleIs=userRole.getRole(userName, password);
	    		  if(roleIs!=null && roleIs.contains(UserRolesConstants.ADMINROLE)) {
	    				LOGGER.info("UserRolesConstants.ADMINROLE"+UserRolesConstants.ADMINROLE);
	    				LOGGER.info("line 206 role is"+roleIs);
		    			  return "redirect:/viewemp";  
	    		  }
	    		  if(roleIs==null) {
	    			  LOGGER.info("roleIs null trying to hard code the entrys");
	    				LOGGER.info("line 206 role is"+roleIs);
	    				userRole.getRoleId(userName, password);
	    				return "redirect:/viewEmp";  
	    		  }
	    	  }else {
	    		  return "denied";  
	    	  }
	          
	        }try {
	        	if(userLoginBean.getUserName()!=null && userLoginBean.getPassword() != "") {
	        		 String roleIs=userRole.getRole(userLoginBean.getUserName(), userLoginBean.getPassword());
		    		  if(roleIs!=null && roleIs.contains(UserRolesConstants.ADMINROLE)) {
		    				LOGGER.info("UserRolesConstants.ADMINROLE"+UserRolesConstants.ADMINROLE);
		    				LOGGER.info("line 206 role is"+roleIs);
			    			  return "redirect:/viewemp";  
		    		  }
		    		  if(roleIs==null) {
		    			  LOGGER.info("roleIs null trying to hard code the entrys");
		    				LOGGER.info("line 206 role is"+roleIs);
		    				userRole.getRoleId(userLoginBean.getUserName(), userLoginBean.getPassword());
		    				return "redirect:/viewEmp";  
		    		  }
		    	  }else {
		    		  return "denied";  
		    	  }
	        		
	        }catch(Exception  e) {
	        
	        	return e.getMessage();	
	        }
	      if(LOGGER.isDebugEnabled()){
	    		LOGGER.debug("Start debug");
	    	  }
	      LOGGER.info("UserRolesConstants.ADMINROLE"+UserRolesConstants.ADMINROLE);
	  	LOGGER.info("Going to run HelloLoggingController class");
	  	LOGGER.info("Exiting the program");
	  	return "denied";  
	      
	    }
	 
	 
	 @SuppressWarnings("unchecked")
	 @CrossOrigin
	@RequestMapping(value = "/angularPost", method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
	 public ResponseEntity<Object> restApiForAngular(@RequestBody LoginBean loginBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String role = userRole.getRole(loginBean.getUserName(), loginBean.getPassword());
		 ObjectMapper Obj = new ObjectMapper(); 
		 String jsonStr = Obj.writeValueAsString(role);
		  if (role == null) {
			  return new ResponseEntity(HttpStatus.NOT_FOUND);
		    }
		  
		  return new ResponseEntity<Object>(jsonStr, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/admin**", method = RequestMethod.GET)
		public ModelAndView adminPage() {

		  ModelAndView model = new ModelAndView();
		  model.addObject("title", "Spring Security Login Form - Database Authentication");
		  model.addObject("message", "This page is for ROLE_ADMIN only!");
		  model.setViewName("admin");
		  
		  return model;

		}
	 
	 	 
	 /*@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		  ModelAndView model = new ModelAndView();
		  if (error != null) {
			model.addObject("error", "Invalid username and password!");
		  }

		  if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		  }
		  model.setViewName("login");

		  return model;

		}
*/	 
	//for 403 access denied page
		@RequestMapping(value = "/403", method = RequestMethod.GET)
		public ModelAndView accesssDenied() {

		  ModelAndView model = new ModelAndView();
			
		  //check if user is login
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();	
			model.addObject("username", userDetail.getUsername());
		  }
			
		  model.setViewName("403");
		  return model;

		}
	 
	 
	 

/*// AuthenticationInterceptor.java
public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
 
	LOGGER.info("Interceptor: Pre-handle");
 
		// Avoid a redirect loop for some urls
		if( !request.getRequestURI().equals("/shoppingCart1") &&
		    !request.getRequestURI().equals("/shoppingCart1/") &&
		    !request.getRequestURI().equals("/shoppingCart1/editsave"))
		  {
			LoginBean loginBean= (LoginBean) request.getSession().getAttribute("LOGGEDIN_USER");
		   if(loginBean == null)
		   {
		    response.sendRedirect("/shoppingCart1/");
		    return false;
		   }   
		  }
		  return true;
}
    
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/imgages/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/resources/images/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }
    
    
    @RequestMapping(value = "/sid", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {

         ClassPathResource imgFile = new ClassPathResource("tennis.png");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
    */
	    // Method to test the angular fetch call.
	    @CrossOrigin(origins="http://localhost:4200")// @CrossOrigin is used to handle the request from a difference origin.
	    @RequestMapping(value= "/getemployees", method= RequestMethod.GET)
		public List<Employee> getAllEmployee(){
			return empService.getAllEmployees();
		}
		
		
    protected void configure(HttpSecurity http) throws Exception {

  	  http.authorizeRequests()
  		.antMatchers("/login/**").access("hasRole('ROLE_ADMIN')")
  		.and()
  		  .formLogin().loginPage("/login").failureUrl("/login?error")
  		  .usernameParameter("username").passwordParameter("password")
  		.and()
  		  .logout().logoutSuccessUrl("/login?logout")
  		.and()
  		  .exceptionHandling().accessDeniedPage("/403")
  		.and()
  		  .csrf();
  	}
    
}