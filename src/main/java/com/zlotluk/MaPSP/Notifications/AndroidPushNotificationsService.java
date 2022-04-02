package com.zlotluk.MaPSP.Notifications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class AndroidPushNotificationsService {

	private static final String FIREBASE_API_URL = "xxx";
	private static final String FIREBASE_SERVER_KEY = "xxx";

	public String sendPushNotification(JSONObject json) throws IOException {

		String result = "";
		URL url = new URL(FIREBASE_API_URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + FIREBASE_SERVER_KEY);
		conn.setRequestProperty("Content-Type", "application/json");

		try {
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			result = "succcess";
		} catch (Exception e) {
			e.printStackTrace();
			result = "failure";
		}
		System.out.println("GCM Notification is sent successfully");

		return result;

	}
}
