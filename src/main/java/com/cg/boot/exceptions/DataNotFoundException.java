package com.cg.boot.exceptions;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException  {
	public DataNotFoundException() {

	}

	public DataNotFoundException(String message) {
		super(message);
	}
}
