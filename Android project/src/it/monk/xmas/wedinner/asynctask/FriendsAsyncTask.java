package it.monk.xmas.wedinner.asynctask;

import it.monk.xmas.wedinner.connect.JSONRequest;
import it.monk.xmas.wedinner.model.Friend;
import it.monk.xmas.wedinner.ui.FriendsActivity;
import it.monk.xmas.wedinner.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;

import android.os.AsyncTask;

public class FriendsAsyncTask extends AsyncTask<Void, Void, ArrayList<Friend>> {
	
	private FriendsActivity act;
	
	public FriendsAsyncTask(FriendsActivity act) {
		this.act = act;
	}

	@Override
	protected ArrayList<Friend> doInBackground(Void... arg0) {
		/*
		SharedPreferences settings = act.getSharedPreferences(UserPreferences.USER,
				0);
		String utente = settings.getString(UserPreferences.USER_KEY, "clarauso@gmail.com");
		*/
		try {
			return Friend.getFriends(JSONRequest.getJSONObject(Constants.FRIENDS_URL));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(ArrayList<Friend> ret) {
		if(ret != null) {
			act.showList(ret);
		}
	}

}
