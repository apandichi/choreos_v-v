package br.usp.ime.choreos.vvws.ws;

import javax.xml.ws.Endpoint;

public class RunServer {
	private static Endpoint storeEndpoint = null;
	private static Endpoint simpleStoreEndpoint = null;
	private final static String baseUrl = "http://localhost:1234/";
	private final static String storeUrl = baseUrl + "Store";
	private final static String simpleStoreUrl = baseUrl + "SimpleStore";

	private static void runStore(){
		StoreWS service = new StoreWS();
		storeEndpoint = Endpoint.create(service);
		storeEndpoint.publish(storeUrl);
	}
	
	private static void runSimpleStore(){
		SimpleStoreWS service = new SimpleStoreWS();
		simpleStoreEndpoint = Endpoint.create(service);
		simpleStoreEndpoint.publish(simpleStoreUrl);
	}
	
	public static void runServers(){
		runStore();
		runSimpleStore();
	}
	
	public static void killServers(){
		if (storeEndpoint != null){
			storeEndpoint.stop();
		}
		if (simpleStoreEndpoint != null){
			simpleStoreEndpoint.stop();
		}
	}
	
	public static void main(String [] args){
		runServers();
		System.out.println("Server running");
	}
	
}
