package com.example.jobportal.utility;

import java.util.ArrayList;
import java.util.List;

public class ResponseStructure<T> {
	
	private int statusCode;
	private String message;
	private List<T> data;

	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<T> getData() {
		return data;
	}

	public void setData(T data)
	{
		List<T> list= new ArrayList<T>();
		list.add(data);
		this.data = list;
	}
	
	public void setData(List<T> data) 
	{
		this.data = data;
	}
}
