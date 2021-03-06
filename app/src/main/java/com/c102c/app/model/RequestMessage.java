package com.c102c.app.model;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "REQUEST_MESSAGE".
 */
public class RequestMessage {

	private String uuid;
	private String name;
	private String xml;
	private String state;

	// KEEP FIELDS - put your custom fields here

	public RequestMessage() {
		uuid = "";
		name = "";
		xml = "";
		state = "";

	}

	// KEEP FIELDS END

	public RequestMessage(String uuid) {
		this.uuid = uuid;
	}

	public RequestMessage(String uuid, String name, String xml, String state) {
		this.uuid = uuid;
		this.name = name;
		this.xml = xml;
		this.state = state;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	// KEEP METHODS - put your custom methods here
	// KEEP METHODS END

}
