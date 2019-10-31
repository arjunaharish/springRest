package com.shoppingcart.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.createnewsurveyList.beans.CreateNewSurveyList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.beans.DataSource;
import com.shoppingcart.configuration.WebMvcConfig;
import com.shoppingcart.surveylistdao.mappers.SurveylistService;
@CrossOrigin
@Controller
@Component
@Scope("prototype")
@Import(WebMvcConfig.class)
@Configuration
@MapperScan("com.shoppingcart.surveylistdao.mappers")
public class Surveycontroller {
	@Autowired
	  private SurveylistService surveyListService;
	@Autowired
	DataSource datasource;
	 

		@RequestMapping(value = "/surveyList", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
		 public ResponseEntity<String> restApiForAngular(HttpServletResponse response) throws IOException {
		 datasource.getDataSource();
		 List<String> surveyList=surveyListService.getCreateNewSurveyList();	
		ObjectMapper Obj = new ObjectMapper(); 
		  String jsonStr = Obj.writeValueAsString(surveyList);
		  if (surveyList == null) {
			  return new ResponseEntity(HttpStatus.NOT_FOUND);
		    }
		  
		  return new ResponseEntity<String>(jsonStr, HttpStatus.OK); 
		
		/*return null;*/
			 /*String role = userRole.getRole(loginBean.getUserName(), loginBean.getPassword());*/
}
}
