package it.monk.xmas.wedinner.ui;

import java.util.List;

import it.monk.xmas.wedinner.R;
import it.monk.xmas.wedinner.asynctask.MenuAsyncTask;
import it.monk.xmas.wedinner.model.Dish;
import it.monk.xmas.wedinner.utils.MenuArrayAdapter;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

public class MenuActivity extends SherlockActivity {

	private MenuArrayAdapter adapter;
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
		progress.setMessage("Loading menu...");
		progress.show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		new MenuAsyncTask(this).execute();
	}
	
	public void showList(List<Dish> dishesList) {
		progress.dismiss();
		if (dishesList != null) {
			adapter = new MenuArrayAdapter(this,
					R.layout.dish_list_element, dishesList);
			list.setAdapter(adapter);
		} else {
			Toast.makeText(this, "No dishes found", Toast.LENGTH_LONG)
					.show();
		}
	}

}
