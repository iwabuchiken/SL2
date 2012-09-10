package sl2.listeners;

import sl2.main.R;
import sl2.utils.Methods;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class DialogOnItemClickListener implements OnItemClickListener {

	//
	Activity actv;
	Dialog dlg;
	Dialog dlg2;
	//
	Vibrator vib;
	
	//
//	Methods.DialogTags dlgTag = null;

	public DialogOnItemClickListener(Activity actv, Dialog dlg) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

//	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. Get tag
		 * 2. Vibrate
		 * 3. Switching
			----------------------------*/
		
		Methods.DialogItemTags tag = (Methods.DialogItemTags) parent.getTag();
		
		vib.vibrate(Methods.vibLength_click);
		
		/*----------------------------
		 * 3. Switching
			----------------------------*/
		switch (tag) {
		
		case dlg_register_main:
			/*********************************
			 * 1. Get item
			 * 2. Switch
			 *********************************/
			String choice = (String) parent.getItemAtPosition(position);

//			// debug
//			Toast.makeText(actv, choice, 2000).show();
//			
			/*********************************
			 * 2. Switch
			 * 	1. Item
			 * 	2. Store
			 * 	3. Genre
			 *********************************/
			/*********************************
			 * 2.1. Item
			 *********************************/
			if (choice.equals(actv.getString(R.string.dlg_register_main_items))) {
				
				dlg.dismiss();
				
				Methods.register_items(actv);
				
			} else if (choice.equals(actv.getString(R.string.dlg_register_main_stores))) {
			/*********************************
			 * 2.2. Store
			 *********************************/
			
			} else if (choice.equals(actv.getString(R.string.dlg_register_main_genres))) {
			/*********************************
			 * 2.3. Genre
			 *********************************/
				
			}//if (choice.equals(actv.getString(R.string.dlg_register_main_items)))
			
			
			
			
			
			
			break;
			
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)
}
