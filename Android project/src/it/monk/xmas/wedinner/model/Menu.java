package it.monk.xmas.wedinner.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Menu {

	private List<Dish> appetizers;
	private List<Dish> mainDishes;
	private List<Dish> sideDishes;
	private List<Dish> desserts;

	public Menu(JSONObject obj) {

		try {
			// prende l'oggetto che corrisponde al menu completo
			JSONObject JSONMenu = obj.getJSONObject("recipes");

			appetizers = new ArrayList<Dish>(getDishes(JSONMenu, "Appetizers"));
			mainDishes = new ArrayList<Dish>(getDishes(JSONMenu, "Main Dishes"));
			sideDishes = new ArrayList<Dish>(getDishes(JSONMenu, "Side Dishes"));
			desserts = new ArrayList<Dish>(getDishes(JSONMenu, "Desserts"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Dish> getDishes(JSONObject obj, String key) {
		ArrayList<Dish> ret = new ArrayList<Dish>();

		try {
			JSONArray dishes = obj.getJSONArray(key);
			for (int i = 0; i < dishes.length(); i++) {
				ret.add(new Dish(dishes.getJSONObject(i)));
			}
		} catch (JSONException e) {
			Log.e(this.getClass().getCanonicalName(),
					"getting JSONArray for ".concat(key));
		}

		return ret;
	}
	
	public List<Dish> getAsDishList() {
		ArrayList<Dish> ret = new ArrayList<Dish>();
		
		// elementi null per inserire i separatori
		ret.add(null);
		ret.addAll(appetizers);
		ret.add(null);
		ret.addAll(mainDishes);
		ret.add(null);
		ret.addAll(sideDishes);
		ret.add(null);
		ret.addAll(desserts);
		
		return ret;
	}

}
