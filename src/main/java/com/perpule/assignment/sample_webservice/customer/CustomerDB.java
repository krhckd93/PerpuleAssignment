package com.perpule.assignment.sample_webservice.customer;

import java.util.ArrayList;

import com.perpule.assignment.sample_webservice.base.BaseDB;
import com.perpule.assignment.sample_webservice.base.DBColumn;

public class CustomerDB extends BaseDB{
	
	public CustomerDB() {
		
	}
	
	protected String getTableName() {
		return "customer";
	}
	
	public ArrayList<DBColumn> getModelColumns() {
		
		ArrayList<DBColumn> columns = new ArrayList<DBColumn> ();
		columns.add(new DBColumn("id", "SERIAL PRIMARY KEY"));
		columns.add(new DBColumn("first_name", "VARCHAR"));
		columns.add(new DBColumn("last_name", "VARCHAR"));
		
		return columns;
	}
}
