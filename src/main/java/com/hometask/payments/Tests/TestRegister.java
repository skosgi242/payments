package com.hometask.payments.Tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestRegister {

	
	public void testVerify() {
		try {

			URL url = new URL("http://localhost:8080/tutorial/rest/payments/accountdetails/1231232");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			//System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				//System.out.println(output);
			}
			
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

		}
	
	public void testRegister() {
		String input = "{\n" + 
				"	\"name\": \"sai12345\",\n" + 
				"	\"userId\": \"1231232\",\n" + 
				"	\"balance\": \"4000\"\n" + 
				"	\n" + 
				"}";
		System.out.println("Starting test Register. Account with following details is getting created:");
		System.out.println(input);
		 try {

	URL url = new URL("http://localhost:8080/tutorial/rest/payments/register");
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setDoOutput(true);
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Type", "application/json");
	
	
	OutputStream os = conn.getOutputStream();
	os.write(input.getBytes());
	os.flush();
	if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
		throw new RuntimeException("Failed : HTTP error code : "
			+ conn.getResponseCode());
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

	String output;
	
	
	
	conn.disconnect();
		 }
		 catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			 }
	}
	
}
