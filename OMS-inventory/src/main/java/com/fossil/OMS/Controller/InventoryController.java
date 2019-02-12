package com.fossil.OMS.Controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fossil.OMS.Model.SFCCInventory;
import com.fossil.OMS.Service.InventoryService;

@RestController
public class InventoryController {

	@Autowired
	private  InventoryService invService;
	
	private static final Logger LOG = Logger.getLogger(InventoryController.class.getName());
	

	//@GetMapping("/fossil/sfcc_inventory/updateInventory" )
		@RequestMapping(value = "fossil/oms_inventory/updateInventory", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
	    private  String  updateInventoryForCatentryId(@RequestParam Map<String, String> params) {
			
			try {
				LOG.log(Level.INFO, "Updating Inventory at OMS!");
				SFCCInventory inv =new SFCCInventory();
				if(params.containsKey("catentryId") && params.containsKey("quantity")) {
					inv.setCatentryId(Long.valueOf(params.get("catentryId")));
					LOG.log(Level.INFO, "catentryId" + params.get("catentryId"));
					inv.setQuantity(Integer.valueOf(params.get("quantity")));
					LOG.log(Level.INFO, "quantity" + params.get("quantity"));
					if(params.containsKey("storeId") ){
					inv.setStoreId(Integer.valueOf(params.get("storeId")));
					LOG.log(Level.INFO, "storeId" + params.get("storeId"));
					}
				}
				return  invService.updateInventory(inv);
			}
			finally {
				
			}
	    }
	
	  @Autowired
	    RestTemplate restTemplete;
	 
	    @Bean
	    RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	
	  @RequestMapping(value = "/elk")
	    public String helloWorld1() {
	 
	        String response = (String) restTemplete.exchange("http://localhost:8080/elkdemo", HttpMethod.GET, null, new ParameterizedTypeReference() {
	        	
	        }).getBody();
	        LOG.log(Level.INFO, "/elk - &gt; " + response);
	 
	        try {
	            String exceptionrsp = (String) restTemplete.exchange("http://localhost:8080/exception", HttpMethod.GET, null, new ParameterizedTypeReference() {
	            }).getBody();
	            LOG.log(Level.INFO, "/elk trying to print exception - &gt; " + exceptionrsp);
	            response = response + " === " + exceptionrsp;
	        } catch (Exception e) {
	            // exception should not reach here. Really bad practice 🙂
	        }
	 
	        return response;
	    }
	 
	    @RequestMapping(value = "/exception")
	    public String exception() {
	        String rsp = "";
	        try {
	            int i = 1 / 0;
	            // should get exception
	        } catch (Exception e) {
	            e.printStackTrace();
	            //LOG.error(e);
	             
	            StringWriter sw = new StringWriter();
	            PrintWriter pw = new PrintWriter(sw);
	            e.printStackTrace(pw);
	            String sStackTrace = sw.toString(); // stack trace as a string
	            //LOG.error("Exception As String :: - &gt; "+sStackTrace);
	             
	            rsp = sStackTrace;
	        }
	 
	        return rsp;
	    }
	
}
