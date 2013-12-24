package it.monk.xmas.wedinner.utils;

import java.util.List;

import it.monk.xmas.wedinner.R;
import it.monk.xmas.wedinner.model.Dish;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuArrayAdapter extends ArrayAdapter<Dish> {

	private String[] courses;

	public MenuArrayAdapter(Context context, int resource, List<Dish> objects) {
		super(context, resource, objects);
		courses = context.getResources().getStringArray(R.array.courses);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView;
		TextView txt;

		Dish d = getItem(position);

		if (d != null) {

			rowView = inflater.inflate(R.layout.dishes_list_element, null);
			txt = (TextView) rowView.findViewById(R.id.dishNameText);
			txt.setText(d.getName());

			Bitmap img = d.getThumbnail();
			if (img != null) {
				ImageView imgView = (ImageView) rowView
						.findViewById(R.id.dishImageView);
				imgView.setImageBitmap(img);
			}
		} else {
			rowView = inflater.inflate(R.layout.dishes_list_separator, null);
			txt = (TextView) rowView.findViewById(R.id.dishesSeparatorTextView);
			txt.setText(courses[position / 4]);
		}

		return rowView;
	}

}
