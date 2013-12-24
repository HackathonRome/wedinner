package it.monk.xmas.wedinner.asynctask;

import java.io.IOException;
import java.util.List;

import it.monk.xmas.wedinner.connect.JSONRequest;
import it.monk.xmas.wedinner.model.Dish;
import it.monk.xmas.wedinner.model.Menu;
import it.monk.xmas.wedinner.ui.MenuActivity;
import it.monk.xmas.wedinner.utils.Constants;
import android.os.AsyncTask;

public class MenuAsyncTask extends AsyncTask<Void, Void, List<Dish>> {

	private MenuActivity act;

	public MenuAsyncTask(MenuActivity act) {
		this.act = act;
	}

	@Override
	protected List<Dish> doInBackground(Void... arg0) {

		try {
			return new Menu(JSONRequest.getJSONObject(Constants.MENU_URL)).getAsDishList();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(List<Dish> ret) {
		act.showList(ret);
	}

}
