package com.przemo.MavenRaspberryMorphia;

import java.util.Date;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.entities.Pomiar;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Hello world!
 *
 */
public class App 
{
	static long startTime ;
	static long endTime ;
	static final GpioController gpio = GpioFactory.getInstance();
	static GpioPinDigitalOutput pin;
	static long elapsedTime;
	static MongoClient client = new MongoClient(new MongoClientURI("mongodb://przemek:przemek123@czujnik-shard-00-00-khw2l.mongodb.net:27017,czujnik-shard-00-01-khw2l.mongodb.net:27017,czujnik-shard-00-02-khw2l.mongodb.net:27017/test?ssl=true&replicaSet=czujnik-shard-0&authSource=admin&retryWrites=true"));
    static Morphia morphia = new Morphia();
    static Datastore ds = morphia.createDatastore(client, "pomiary_db");
    
	
    public static void main( String[] args ) throws InterruptedException
    {
    	morphia.map(Pomiar.class);
    	
        pin = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_05, PinMode.DIGITAL_OUTPUT);
    	pin.low();
    	Thread.sleep(500);
    	pin.setMode(PinMode.DIGITAL_INPUT);
    	pin.setPullResistance(PinPullResistance.PULL_DOWN);
    	startTime = System.currentTimeMillis();
    	
    	
    	pin.addListener(new GpioPinListenerDigital()
    			{

					@Override
					public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) 
					{
						if(event.getState() == PinState.HIGH)
						{
							endTime = System.currentTimeMillis();
							elapsedTime = endTime - startTime;
							System.out.println(elapsedTime);
							pin.setMode(PinMode.DIGITAL_OUTPUT);
							pin.low();
							Pomiar measure = new Pomiar( new Date(), elapsedTime);
					    	ds.save(measure);
							System.out.println("ustawione low");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.print("  poczekalo 0.5s");
							pin.setMode(PinMode.DIGITAL_INPUT);
							System.out.print("  ustawione na input");
							startTime = System.currentTimeMillis();
						}
					}
    		
    			});
    	
        
      
        while(true)
        {
        	
        }
    }
}