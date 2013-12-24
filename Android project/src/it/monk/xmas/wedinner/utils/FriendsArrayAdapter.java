package it.monk.xmas.wedinner.utils;

import it.monk.xmas.wedinner.R;
import it.monk.xmas.wedinner.model.Friend;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendsArrayAdapter extends ArrayAdapter<Friend> {

	public FriendsArrayAdapter(Context context, int resource,
			List<Friend> objects) {
		super(context, resource, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.friends_list_element, null);
		
		Friend f = getItem(position);
		TextView txt = (TextView) rowView.findViewById(R.id.nameText);
		txt.setText(f.getName());
		txt = (TextView) rowView.findViewById(R.id.emailText);
		txt.setText(f.getEmail());
		
		Bitmap img = f.getImg();
		if(img != null) {
			ImageView imgView = (ImageView) rowView.findViewById(R.id.imageView1);
			imgView.setImageBitmap(img);
		}
		
		return rowView;
	}

}
