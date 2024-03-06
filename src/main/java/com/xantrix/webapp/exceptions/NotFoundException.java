package com.xantrix.webapp.exceptions;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = -7168287271375943562L;
	
	private final String messaggio;

	public NotFoundException() {
		super();
		this.messaggio = "Elemento ricercato non trovato!";
	}

	public NotFoundException(String message) {
		super(message);
		this.messaggio = message; 
	}

	public String getMessaggio() {
		return messaggio;
	}
	
}
