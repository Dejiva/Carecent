package com.codecoop.interact.web.carepath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FieldRow implements Serializable {

	private static final long serialVersionUID = -7917232780991688638L;

	private List<FieldBean> fields;

	public List<FieldBean> getFields() {
		if(this.fields == null){
			this.fields = new ArrayList<FieldBean>();
		}
		return fields;
	}

	public void setFields(List<FieldBean> fields) {
		this.fields = fields;
	}

}
