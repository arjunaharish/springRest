package com.shoppingcart.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingcart.beans.Tennis;
import com.shoppingcart.configuration.WebMvcConfig;
import com.shoppingcart.dao.DbImagesDao;
import com.shoppingcart.dao.EmpTablesDao;
import com.shoppingcart.dao.TennisDao;

@Controller
@Component
@Scope("prototype")
@Import(WebMvcConfig.class)
public class SearchBarController {

	@Autowired
	EmpTablesDao empTablesDao;
	@Autowired
	DbImagesDao dbImagesDao;
	@Autowired
	Tennis tennis;
	@Autowired
	TennisDao tennisDao;

	/*	@RequestMapping(value="/searchBarFetchImages/name",method=RequestMethod.GET)
public void searchBar(HttpServletResponse response)throws Exception {
		response.setContentType("image/jpeg");
		Blob ph = empTablesDao.getPhotoById("tennis");

		byte[] bytes = ph.getBytes(1, (int) ph.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}*/
/*	@ResponseBody
	@RequestMapping (value="/fetch",method=RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ModelAndView listStudent(ModelAndView model,HttpServletResponse response)throws IOException, SQLException, ClassNotFoundException{
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		InputStream imagesIs=dbImagesDao.getDbImages();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(imagesIs));
	        StringBuilder out = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            out.append(line);
	        }
	        System.out.println(out.toString());
	        
   		response.setContentType("image/jpeg");
		model.addObject("line",line);
		model.setViewName("student");
		return model;
		
	}
	*/
	
	
/*	@RequestMapping(value = "/fetch")
	public ModelAndView listStudent(ModelAndView model) throws IOException {
		List<Tennis> listStu = tennisDao.tennisList();

		model.addObject("listStu", listStu);
		model.setViewName("student");

		return model;
	
	}*/
	
	@RequestMapping(value = "/fetch")
	public void consumeApi(ModelAndView model)throws IOException{
		try {

			URL url = new URL("http://96.118.212.186:9200/_search");
			System.out.print(url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setRequestProperty  ("Authorization", "Basic aGFyanVuMjUwOkF0cmFtODY4Ng==");
			/*conn.setRequestProperty("Accept", "application/json");*/

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
	}
	
	
	
	
	@RequestMapping(value = "/getStudentPhoto/{id}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") int id) throws Exception {
		response.setContentType("image/jpeg");
		Blob ph = tennisDao.getPhotoById(id);
        byte[] bytes = ph.getBytes(1, (int) ph.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
		}
}
