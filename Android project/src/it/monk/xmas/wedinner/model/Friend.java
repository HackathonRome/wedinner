package it.monk.xmas.wedinner.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Friend {

	private Bitmap img;
	private String imgString;
	private String email;
	private String name;

	public Bitmap getImg() {
		return img;
	}

	public String getImgString() {
		return imgString;
	}

	public void setImgString(String imgString) {
		this.imgString = imgString;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Friend(JSONObject obj) {
		try {
			this.imgString = obj.getString("img");
			this.email = obj.getString("email");
			this.name = obj.getString("name");
		} catch (JSONException e) {
			Log.e(this.getClass().getCanonicalName(), "mapping doesn't exist");
		}

		try {
			this.img = BitmapFactory.decodeStream(new URL(imgString)
					.openConnection().getInputStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Friend> getFriends(JSONObject obj) {
		ArrayList<Friend> ret = new ArrayList<Friend>();

		JSONArray arr;
		try {
			arr = obj.getJSONArray("friends");
			if (arr != null) {
				for (int i = 0; i < arr.length(); i++)
					ret.add(new Friend(arr.getJSONObject(i)));
			}
		} catch (JSONException e) {
			Log.e(Friend.class.getSimpleName(),
					"get JSONObject from JSONArray error");
		}
		return ret;
	}

}
