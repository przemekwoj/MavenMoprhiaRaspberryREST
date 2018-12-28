package com.przemo.RestPomiar.database;


import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseConfiguration
{
	static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
			fromProviders(PojoCodecProvider.builder().automatic(true).build()));
	static MongoClient client = MongoClients.create("mongodb://przemek:przemek123@czujnik-shard-00-00-khw2l.mongodb.net:27017,czujnik-shard-00-01-khw2l.mongodb.net:27017,czujnik-shard-00-02-khw2l.mongodb.net:27017/test?ssl=true&replicaSet=czujnik-shard-0&authSource=admin&retryWrites=true");
    static MongoDatabase database = client.getDatabase("pomiary_database").withCodecRegistry(pojoCodecRegistry);
	static MongoCollection<Document> collection = database.getCollection("pomiar");
	
	
	public static MongoClient getMongoClient()
    {
    	return client;
    }
    
	public static MongoDatabase getMongoDatabase() 
	{
		return database;
	}
	
	public static MongoCollection<Document> getCollection()
	{
		return collection;
	}
}
