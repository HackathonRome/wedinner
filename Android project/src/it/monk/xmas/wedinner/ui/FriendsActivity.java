package it.monk.xmas.wedinner.ui;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import it.monk.xmas.wedinner.R;
import it.monk.xmas.wedinner.asynctask.FriendsAsyncTask;
import it.monk.xmas.wedinner.model.Friend;
import it.monk.xmas.wedinner.utils.FriendsArrayAdapter;

public class FriendsActivity extends SherlockActivity {
	
	private FriendsArrayAdapter adapter;
	private ListView list;
	private ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends_list);
		this.getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources().getColor(R.color.BarColor)));
		list = (ListView) findViewById(R.id.friendsList);
		
		progress = new ProgressDialog(this);
		progress.setMessage(getString(R.string.friends_progress));
		progress.show();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		new FriendsAsyncTask(this).execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// crea il pulsante sull'ActionBar
		SubMenu sub = menu.addSubMenu("Next");
		sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			startActivity(new Intent(FriendsActivity.this, MenuActivity.class));
			break;
		}
		return true;
	}
	
	public void showList(ArrayList<Friend> friendsList) {
		progress.dismiss();
		// TODO uno tra != null e isEmpty()
		if(friendsList != null && !friendsList.isEmpty()) {
			adapter = new FriendsArrayAdapter(this, R.layout.friends_list_element, friendsList);
			list.setAdapter(adapter);
		} else {
			Toast.makeText(this, "No friends found", Toast.LENGTH_LONG)
					.show();
		}
	}

}
