package com.codecoop.interact.web.carepath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldBean implements Serializable {
 
	private static final long serialVersionUID = 2917991176276196215L;
	
	private String fieldType;
	private String filedId;
	private String label;
	private String heading;
	private String value;
	private String title;
	private String text;
	private Map<String,String> property;
	private List<String> listItems;
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public List<String> getListItems() {
		if(this.listItems ==  null){
			this.listItems = new ArrayList<String>();
		}
		return listItems;
	}
	public void setListItems(List<String> listItems) {
		this.listItems = listItems;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFiledId() {
		return filedId;
	}
	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Map<String, String> getProperty() {
		if(this.property == null){
			this.property = new HashMap<String,String>();
		}
		return property;
	}
	public void setProperty(Map<String, String> property) {
		this.property = property;
	}
	
}
