package it.monk.xmas.wedinner.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Dish {

	private ArrayList<String> ingredients;
	private Bitmap thumbnail;
	private String id;
	private String name;
	private String thumbnailString;
	
	public Dish(JSONObject obj) {
		try {
			this.id = obj.getString("id");
			this.name = obj.getString("name");
			this.thumbnailString = obj.getString("thumbnail");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.thumbnail = BitmapFactory.decodeStream(new URL(thumbnailString)
					.openConnection().getInputStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getIngredients() {
		return ingredients;
	}
	
	public Bitmap getThumbnail() {
		return thumbnail;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getThumbnailString() {
		return thumbnailString;
	}
	
}
