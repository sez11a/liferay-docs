package com.liferay.docs.guestbook.model;

public class Entry {

	public Entry() {
		this.name = null;
		this.message = null;
	}

	public Entry(String name, String message) {
		setName(name);
		setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public String getName() {
		return name;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
	private String message;

}
