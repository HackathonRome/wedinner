package it.monk.xmas.wedinner.ui;

import it.monk.xmas.wedinner.R;
import it.monk.xmas.wedinner.utils.UserPreferences;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#E67E22")));

		SharedPreferences settings = getSharedPreferences(UserPreferences.USER,
				0);
		String utente = settings.getString(UserPreferences.USER_KEY, null);
		((TextView) findViewById(R.id.textView1)).setText(utente);
		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView login = (TextView) findViewById(R.id.textView1);

				SharedPreferences settings = getSharedPreferences(
						UserPreferences.USER, MODE_PRIVATE);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString(UserPreferences.USER_KEY, login.getText()
						.toString());
				// anche qui i commit!
				editor.commit();

				// setta il nome utente nella SharedPreferences
				startActivity(new Intent(MainActivity.this,
						FriendsActivity.class));
			}
		});

	}

}
