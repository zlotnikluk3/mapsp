package com.zlotluk.MaPSP.Notifications;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.zlotluk.MaPSP.model.Tokenn;
import com.zlotluk.MaPSP.service.TokenService;

@RestController
public class FbController {

	@Autowired
	private AndroidPushNotificationsService androidPushNotificationsService;

	@Autowired
	private TokenService tokenService;

	public ResponseEntity<String> send(String zd, String opis, String lat, String lng) throws JSONException {

		JSONObject json = new JSONObject();

		JSONArray ja = new JSONArray();
		for (Tokenn t : tokenService.listAll()) {
			ja.put(t.getToken());
		}

		if (ja.length() == 0)
			return null;

		try {

			String op = Znaki.bezZn(opis);

			json.put("registration_ids", ja);

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
}
