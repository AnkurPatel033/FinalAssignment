package com.xworkz.assignment.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
//import java.util.Random;
@Service
public class SendingSMS {
	
	private static Logger logger=LoggerFactory.getLogger(SendingSMS.class); 
	
	public String sending(long pin) {
		logger.info("invoked SMS Application..");
		try {
			// Construct data
			String apiKey = "apikey=" + "GidHDwFlt0A-iRLWMOFiDa7poDz5ccuWZaxs6bSWvi";
			
			//Random rand=new Random(9999);
			
			//long OTP=rand.nextInt();
			
			String message = "&message=" + "Hey Ankur Your Pin:"+pin;
			String sender = "&sender=" + "X-workz_AMS";
			String numbers = "&numbers=" + "919737514379";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			logger.info("OTP send sucessfully..");
			return stringBuffer.toString();
		} catch (Exception e) {
			logger.info("Error SMS "+e);
			return "Error "+e;
		}
	}
}
	