package simplesolution.dev.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import simplesolution.dev.exporter.ExcelFileExporter;
import simplesolution.dev.model.Customer;

@Controller

public class DownloadExcelController {
	
	@RequestMapping
	public String index() {
		return "index";
	}
	@GetMapping("/download/customers.xlsx")
	public void downloadExcelFile(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Dispostion","attachment; filename=customers.xlsx");
	
		ByteArrayInputStream inputStream = ExcelFileExporter.exportCustomerListToExcelFile(createTestData());
		
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	
	
	private List<Customer>createTestData(){
		//Creating List of customers for testing
		List<Customer> customers= new ArrayList<Customer>();
		customers.add(new Customer("Narun", "Burma", "012345678", "test1@gamil.com"));
		customers.add(new Customer("Aarun", "Vurma", "0123456789", "test2@gamil.com"));
		customers.add(new Customer("Santhu", "Gowda", "012345668", "test3@gamil.com"));
		customers.add(new Customer("Hari", "Chalpathy", "0123456788", "test4@gamil.com"));
		customers.add(new Customer("Maya", "Anna", "0123456778", "test5@gamil.com"));
		return customers;
	}

}
