package com.cesar.os.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private Integer staus;
	private String error;
	
	public StandardError() {
		super();
	}

	public StandardError(Long timestamp, Integer staus, String error) {
		super();
		this.timestamp = timestamp;
		this.staus = staus;
		this.error = error;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStaus() {
		return staus;
	}

	public void setStaus(Integer staus) {
		this.staus = staus;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
