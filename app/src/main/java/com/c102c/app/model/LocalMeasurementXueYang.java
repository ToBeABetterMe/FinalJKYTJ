package com.c102c.app.model;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "LOCAL_MEASUREMENT_XUE_YANG".
 */
public class LocalMeasurementXueYang {

	private Long id;
	private String personId;
	private String name;
	private String idCard;
	private String xy;
	private String time;

	// KEEP FIELDS - put your custom fields here

	public LocalMeasurementXueYang() {
		id = System.currentTimeMillis();
		personId = "";
		name = "";
		idCard = "";
		xy = "";
		time = "";
	}

	// KEEP FIELDS END

	public LocalMeasurementXueYang(Long id) {
		this.id = id;
	}

	public LocalMeasurementXueYang(Long id, String personId, String name,
			String idCard, String xy, String time) {
		this.id = id;
		this.personId = personId;
		this.name = name;
		this.idCard = idCard;
		this.xy = xy;
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	// KEEP METHODS - put your custom methods here
	// KEEP METHODS END

}