package com.perpule.assignment.sample_webservice.customer;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

import com.perpule.assignment.sample_webservice.helpers.AuthenticationHelper;
import com.perpule.assignment.sample_webservice.helpers.DatabaseHelper;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("unchecked")
@Path("/customer")
public class Customer {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCustomer(@DefaultValue("-1") @QueryParam("id") Long customer_id) {
		// TODO : Call DatabaseHelper's read method
		String sql = ""; 
		if(customer_id != -1 ) {
			sql = "SELECT id, first_name, last_name, username, password from customer WHERE id = " + customer_id + ";";
		} else {
			sql = "SELECT id, first_name, last_name, username, password from customer;";
		}
		JSONObject response = new JSONObject();
		try {
			response.put("result", DatabaseHelper.getInstance().read(sql));
			return response.toJSONString();
		} catch(Exception ex) {
			ex.printStackTrace();
			response.put("error", ex.toString());
			return response.toJSONString();
		}
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createCustomer(String customer_string) {
		JSONObject customer;
		try {
			JSONParser parser = new JSONParser();
			customer = (JSONObject) parser.parse(customer_string);
		} catch(Exception ex) {
			ex.printStackTrace();
			customer = new JSONObject();
		}
		String sql = "INSERT INTO customer ";
		String values = " VALUES ( ";
		String columns = " ( ";
		if(customer.containsKey("first_name")) {
			columns = columns.concat("first_name,");
			values = values.concat("" + "'" + customer.get("first_name") + "'");
		}
		
		if(customer.containsKey("last_name")) {
			columns = columns.concat("last_name,");
			values = values.concat("," + "'" + customer.get("last_name") + "'");
		}
		
		if(customer.containsKey("username")) {
			columns = columns.concat("username,");
			values = values.concat("," + "'" + customer.get("username") + "'");
		}
		
		if(customer.containsKey("password")) {
			columns = columns.concat("password");
			values = values.concat("," + "'" + AuthenticationHelper.get_SHA_1_Secure((String)customer.get("password")) + "'");
		}
		
		if(!values.equalsIgnoreCase("VALUES ( ") && !columns.equalsIgnoreCase(" ( ")) {
			columns = columns.concat(") ");
			values = values.concat(")");
			sql = sql.concat(columns);
			sql = sql.concat(values + ";");
		}
		
		JSONObject response = new JSONObject();
		try {
			response.put("result", DatabaseHelper.getInstance().executeRawUpdate(sql));
			return response.toJSONString();
		} catch(Exception ex) {
			ex.printStackTrace();
			response.put("error", ex.toString());
			return response.toJSONString();
		}
	}
	
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteCustomer(Integer customer_id) {
		JSONObject response = new JSONObject();
		String sql = "DELETE FROM customer WHERE id = " + customer_id.toString();
		try {
			response.put("result", DatabaseHelper.getInstance().executeRawUpdate(sql));
		} catch(Exception ex) {
			ex.printStackTrace();
			response.put("error", ex.toString());
		}
		return response.toJSONString(); 
	}
	
}
