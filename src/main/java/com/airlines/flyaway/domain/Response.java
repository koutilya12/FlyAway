package com.airlines.flyaway.domain;

public class Response {
	
	private String status;
	private String errorMessage;
	private Object data;
	
	
	public Response(String status, String errorMessage, Object data) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
		this.data = data;
	}
	
	
	public Response(String status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

	

	public Response(String status) {
		super();
		this.status = status;
	}


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "Response [status=" + status + ", errorMessage=" + errorMessage + ", data=" + data + "]";
	}

}
