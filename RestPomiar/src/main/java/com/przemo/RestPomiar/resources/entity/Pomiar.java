package com.przemo.RestPomiar.resources.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;



@XmlRootElement
public class Pomiar 
{
	
	private ObjectId _id;
	private Date date;
	private long value;
	
	
	public Pomiar()
	{
		date = new Date();
	}
	public Pomiar(ObjectId _id,Date date, long value) {
		super();
		this._id = _id;
		this.date = date;
		this.value = value;
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
	@Override
	public String toString() {
		return "Pomiar [_id=" + _id + " date=" + date + ", value=" + value
				+ "]";
	}
	
	
}
