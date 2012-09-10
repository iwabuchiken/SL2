package sl2.main;

import sl2.utils.DBUtils;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterItemActv extends Activity {

	//
	Activity actv;

	//
	Spinner sp_store_name;
	Spinner sp_genre_name;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super, etc.
		 * 2. Set content
		 * 3. DBManager => Create table
		 * 3-2. Set spinner items
		 * 4. Set listener => Register
			----------------------------*/
		
		super.onCreate(savedInstanceState);

		this.actv = this;
		
		/*----------------------------
		 * 2. Set content
			----------------------------*/
		setContentView(R.layout.actv_register_item);
		
		setTitle(this.getClass().getName());
		
		/*----------------------------
		 * 3. DBManager => Create table
			----------------------------*/

		/*----------------------------
		 * 3-2. Set spinner items
			----------------------------*/
		set_spinner_stores();
//		
		set_spinner_genres();
		/*----------------------------
		 * 4. Set listener => Register
			----------------------------*/
		//
		setLister();
		
		
		
	}//public void onCreate(Bundle savedInstanceState)

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}

	void set_spinner_stores() {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Get store names from db
		 * 3. Set store data to adapter
		 * 3-1. setDropDownViewResource
		 * 3-2. Close db
		 * 4. Set adapter to spinner
			----------------------------*/
		
		// Resource => http://www.java2s.com/Open-Source/Android/Samples/techbooster/org/jpn/techbooster/sample/spinner/SpinnerActivity.java.htm
		sp_store_name = (Spinner) findViewById(R.id.v1_sp_store);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	              this, android.R.layout.simple_spinner_item);

		/*----------------------------
		 * 2. Get store names from db
			----------------------------*/
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		String sql = "SELECT * FROM " + MainActv.tableName_stores;
		
		//aaa
		Cursor c = rdb.rawQuery(sql, null);
		
		if (c == null) {
			
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c == null");
			
			rdb.close();
			
			return;
			
		} else if (c.getCount() < 1) {
			
			// debug
			Toast.makeText(this, "Store names => No data yet", 2000).show();
			
			rdb.close();
			
			return;
			
		}//if (c == null)

		// Log
		Log.d("RegisterItem.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount()" + c.getCount());
		
		c.moveToFirst();
		
		// Log
		for (int i = 0; i < c.getCount(); i++) {
			Log.d("RegisterItem.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getString(1) => " + c.getString(1));

			/*----------------------------
			 * 3. Set store data to adapter
				----------------------------*/
//			adapter.add("abc");
			adapter.add(c.getString(1));

			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 3-2. Close db
			----------------------------*/
		rdb.close();
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		sp_store_name.setAdapter(adapter);

	}//void set_spinner_stores()

	void set_spinner_genres() {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Get genre names from db
		 * 3. Set genre data to adapter
		 * 3-1. setDropDownViewResource
		 * 3-2. Close db
		 * 4. Set adapter to spinner
			----------------------------*/
		
		// Resource => http://www.java2s.com/Open-Source/Android/Samples/techbooster/org/jpn/techbooster/sample/spinner/SpinnerActivity.java.htm
		sp_genre_name = (Spinner) findViewById(R.id.v1_sp_genre);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	              this, android.R.layout.simple_spinner_item);
//
		/*----------------------------
		 * 2. Get genre names from db
			----------------------------*/
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		String sql = "SELECT * FROM " + MainActv.tableName_genres;
		
		//aaa
		Cursor c = rdb.rawQuery(sql, null);
		
		if (c == null) {
			
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c == null");
			
			rdb.close();
			
			return;
			
		} else if (c.getCount() < 1) {
			
			// debug
			Toast.makeText(this, "Store names => No data yet", 2000).show();
			
			rdb.close();
			
			return;
			
		}//if (c == null)

		// Log
		Log.d("RegisterItem.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount()" + c.getCount());
		
		c.moveToFirst();
		
		// Log
		for (int i = 0; i < c.getCount(); i++) {
			Log.d("RegisterItem.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getString(1) => " + c.getString(1));

			/*----------------------------
			 * 3. Set store data to adapter
				----------------------------*/
//			adapter.add("abc");
			adapter.add(c.getString(1));

			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 3-2. Close db
			----------------------------*/
		rdb.close();
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		sp_genre_name.setAdapter(adapter);
		
	}//void set_spinner_genres()

	void setLister() {
		//
		Button bt = (Button) findViewById(R.id.v1_btn_register);
		
		//
		bt.setOnClickListener(new OnClickListener(){

//			@Override
			public void onClick(View v) {
				/*********************************
				 * 1. Get views
				 * 2. Build data
				 * 3. Insert data
				 * 4. Check the return
				 * 
				 *********************************/
				/*********************************
				 * 1. Get views
				 *********************************/
				EditText et_name = (EditText) findViewById(R.id.v1_et_name);
				EditText et_price = (EditText) findViewById(R.id.v1_et_price);
				EditText et_yomi = (EditText) findViewById(R.id.v1_et_yomi);
				
				// All written?
				if(
					et_name.getText().toString().equals("") ||
					et_price.getText().toString().equals("")) {
					// debug
					Toast.makeText(actv, "Empty item exists", 2000)
							.show();
				}//if
				
				// Log
				Log.d("RegisterItem.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Spinner item => " + 
								sp_store_name.getSelectedItem().toString() +
								" / " + "position => " + sp_store_name.getSelectedItemPosition());
				
				/*********************************
				 * 2. Build data
				 *********************************/
				Object[] data = {										
						et_name.getText().toString(),		// name
						et_yomi.getText().toString(),		// yomi
						sp_store_name.getSelectedItem().toString(),	// store_name
						
//						et_price.getText().toString(),		// price
						Long.parseLong(et_price.getText().toString()),	// price
						
//						et_genre.getText().toString()
						sp_genre_name.getSelectedItem().toString()	// genre
				};//Object[] data
				
				
				/*********************************
				 * 3. Insert data
				 *********************************/
				DBUtils dbu = new DBUtils(actv, MainActv.dbName);
				
				SQLiteDatabase wdb = dbu.getWritableDatabase();

				boolean result = dbu.insertData_item(
										wdb, MainActv.tableName_shopping_item, 
										MainActv.cols_shopping_item, 
										data);
				
				/*********************************
				 * 4. Check the return
				 *********************************/
				if (result == true) {
					// Log
					Log.d("RegisterItemActv.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "Data stored");
					// debug
					Toast.makeText(actv, "Data stored", 2000)
							.show();
					
				} else {//if (result == true)
					// Log
					Log.d("RegisterItemActv.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "Data not stored");

					// debug
					Toast.makeText(actv, "Store data => Failed", 2000)
							.show();

				}//if (result == true)
				
				wdb.close();
				
			}//public void onClick(View v)
		});

		bt.setOnTouchListener(new OnTouchListener(){

//			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO 自動生成されたメソッド・スタブ
				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(Color.DKGRAY);
					break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(Color.WHITE);
					break;
				}//switch (event.getAction())
				
				return false;
			}});
	}//void setLister()

}//public class RegisterItemActv extends Activity
