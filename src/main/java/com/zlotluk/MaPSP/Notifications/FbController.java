package com.zlotluk.MaPSP.Notifications;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FbController {

	@Autowired
	private AndroidPushNotificationsService androidPushNotificationsService;

	private String deviceToken = "cUD25UORY0s:APA91bF0w7rfL0KyBi43NNJVH56QqZvr635llA531GAdCxuEYQibTbinlMapfFjYXGum0IV2FVB9iC5fUl"
			+ "-0rIjjMjPSRTgO3FZ3DcRI0dJcUitEyoaXfFE1bgS4qM0AnL3KAd9RMH6S";

	public ResponseEntity<String> send(String zd, String opis, String lat, String lng) throws JSONException {

		JSONObject json = new JSONObject();

		try {

			String op = Znaki.bezZn(opis);

			json.put("to", deviceToken.trim());

			JSONObject data = new JSONObject();
			data.put("lat", lat);
			data.put("lng", lng);
			data.put("op", op);
			json.put("data", data);
			JSONObject info = new JSONObject();
			info.put("title", zd); // Notification title
			info.put("body", op); // Notification
			info.put("message", op); // body
			json.put("notification", info);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		try {
			String pushNotification = androidPushNotificationsService.sendPushNotification(json);

			return new ResponseEntity<>(pushNotification, HttpStatus.OK);
		} catch (IOException e) {
		}
		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}

	public String convertStringToUTF8(String s) {
		String out = null;
		try {
			out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
		} catch (java.io.UnsupportedEncodingException e) {
			return null;
		}
		return out;
	}
}
