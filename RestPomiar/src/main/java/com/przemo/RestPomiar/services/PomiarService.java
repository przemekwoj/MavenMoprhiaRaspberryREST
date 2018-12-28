package com.przemo.RestPomiar.services;


import java.util.ArrayList;

import javax.ws.rs.QueryParam;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.przemo.RestPomiar.database.DatabaseConfiguration;
import com.przemo.RestPomiar.exception.DataNotFoundException;
import com.przemo.RestPomiar.resources.entity.Pomiar;


public class PomiarService 
{
    private MongoDatabase database = DatabaseConfiguration.getMongoDatabase();
	private MongoCollection<Pomiar> collection = database.getCollection("pomiar", Pomiar.class);
	
	public ArrayList<Pomiar> getAllPomiars()
	{
		ArrayList<Pomiar> listPomiar = new ArrayList<Pomiar>();		
	
		
		MongoCursor<Pomiar> cursor = collection.find().iterator();
		try {
		    while (cursor.hasNext()) {
		        listPomiar.add(cursor.next());
		    }
		} finally {
		    cursor.close();
		}
		return listPomiar;
	}
	
	public Pomiar getOnePomiar(ObjectId id)
	{
		
		Pomiar pomiar = collection.find(Filters.eq("_id",id)).first();
		return pomiar;
	}
	
	public ArrayList<Pomiar> getScopePomiars( int from, int to) throws DataNotFoundException
	{
		ArrayList<Pomiar> listPomiar = new ArrayList<Pomiar>();	
		if(from >  to)
		{
			throw new DataNotFoundException("wrong scope");
		}
		MongoCursor<Pomiar> cursor = collection.find().skip(from-1).limit(to - from + 1).iterator();
		 while (cursor.hasNext()) 
		    {
			  listPomiar.add(cursor.next());
		    }
		return listPomiar;
	}
	
	public String deleteOnePomiar(ObjectId id)
	{
		collection.deleteOne(Filters.eq("_id",id));
		return "successfuly deleted";
	}
	
	public Pomiar updateOnePomiar(ObjectId id,long value)
	{
		collection.updateOne(Filters.eq("_id",id), new Document("$set", new Document("value", value)));
		return collection.find(Filters.eq("_id",id)).first();
	}
	
	public Pomiar addPomiar(Pomiar pomiar)
	{
		collection.insertOne(pomiar);
		return pomiar;
	}
	
}
