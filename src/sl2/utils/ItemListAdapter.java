package sl2.utils;

import java.util.List;

import sl2.items.SI;
import sl2.main.MainActv;
import sl2.main.R;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemListAdapter extends ArrayAdapter<SI> {

	//
	private int resourceId; 
	
	public ItemListAdapter(Context context, int textViewResourceId,
												List<SI> list) {
		super(context, textViewResourceId, list);
		// TODO 自動生成されたコンストラクター・スタブ
		
		this.resourceId = textViewResourceId;
		
		
	}//public ItemListAdapter()

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/*----------------------------
		 * Steps
		 * 1. Inflate
		 * 2. Get views
		 * 3. Get item
		 * 4. Set values
		 * 
		 * 5. Set background
			----------------------------*/
		
		// TODO 自動生成されたメソッド・スタブ
		
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resourceId, null);
        }

        //
        TextView tv_item_name = 
        				(TextView) convertView.findViewById(R.id.adapteritem_tv_item_name);
        TextView tv_store = 
				(TextView) convertView.findViewById(R.id.adapteritem_tv_store);
        TextView tv_price = 
				(TextView) convertView.findViewById(R.id.adapteritem_tv_price);
        TextView tv_genre = 
				(TextView) convertView.findViewById(R.id.adapteritem_tv_genre);

        //
        SI si = (SI) getItem(position);
        
//        // Log
//		Log.d("ItemListAdapter.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", 
//				"si.getName() => " + si.getName()+
//					"/" + "id=" + si.getId());
		
		/*----------------------------
		 * 4. Set values
			----------------------------*/
		//
		tv_item_name.setText(si.getName());
		tv_store.setText(si.getStore());
//		tv_price.setText(si.getPrice());
		tv_price.setText(String.valueOf(si.getPrice()));
		
		tv_genre.setText("(" + si.getGenre() + ")");
		
		/*----------------------------
		 * 5. Set background
			----------------------------*/
		if (MainActv.inBasketItem.contains((Integer) position)) {
			
			convertView.setBackgroundColor(Color.GREEN);
			
		} else if (MainActv.chosenItem.contains((Integer) position)) {
			
			convertView.setBackgroundColor(Color.BLUE);
			
		} else {//if (MainActv.checkedPositions.contains((Integer) position))
			
			convertView.setBackgroundColor(Color.BLACK);
			
		}//if (MainActv.checkedPositions.contains((Integer) position))
		

		
		
		return convertView;
//		return super.getView(position, convertView, parent);
	}//public View getView(int position, View convertView, ViewGroup parent)

}//public class ItemListAdapter extends ArrayAdapter<SI>
