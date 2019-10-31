package javaRestApi;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@RequestMapping("/json/product")
public class JSONService {

	@RequestMapping(value="/get",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getProductInJSON() {

		Product product = new Product();
		product.setName("iPad 3");
		product.setQty(999);
		
		return product; 

	}

}
