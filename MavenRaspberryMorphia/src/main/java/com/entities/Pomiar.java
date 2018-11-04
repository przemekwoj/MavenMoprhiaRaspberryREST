package com.entities;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "pomiar")
public class Pomiar 
{
	@Id
	private ObjectId id;
	@Indexed
	@Property(value = "date")
	private Date date;
	@Property(value = "analogValue")
	private long value;
	
	public Pomiar( Date date, long value) {
		super();
		this.date = date;
		this.value = value;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}
