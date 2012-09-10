package sl2.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sl2.items.SI;
import sl2.utils.DBUtils;
import sl2.utils.ItemListAdapter;
import sl2.utils.Methods;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActv extends ListActivity {
	
	public static Vibrator vib;
	
	/*********************************
	 * DB
	 *********************************/
	public static String dbName = "sl2.db";

	// shopping_item
	public static String tableName_shopping_item
								= "shopping_item";
	static String[] cols_shopping_item = 
				{"name", "yomi", "store", "price", "genre"};
	static String[] col_types_shopping_item = 
				{"TEXT", "TEXT", "TEXT", "INTEGER", "TEXT"};
	
	// stores
	public static String tableName_stores =
					"stores";
	static String[] cols_stores = 
					{"store_name", 	"memo"};
	static String[] col_types_stores = 
					{"TEXT", 		"TEXT"};

	// genres
	public static String tableName_genres =
					"genres";
	static String[] cols_genres = 
					{"genre_name", 	"memo"};


	static String[] col_types_genres = 
					{"TEXT", 		"TEXT"};

	/*********************************
	 * List-related
	 *********************************/
	public static List<SI> list;
	
	public static ItemListAdapter adapter;

	/*********************************
	 * Request codes
	 *********************************/
	public final static int REQUEST_CODE_REGISTER_ITEMS = 0;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*********************************
		 * 1. Set up
		 * 2. Show list
		 *********************************/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setTitle(this.getClass().getName());
        
        vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        
        /*********************************
		 * 2. Show list
		 *********************************/
        setList();
        
        
        /*----------------------------
		 * 2. Add listeners
			----------------------------*/
//		add_listeners();
//        restore_db();
//        see_db();
//        get_column_names();
//        get_column_names(MainActv.tableName_stores);
//        get_column_names(MainActv.tableName_genres);
//      get_column_names(MainActv.tableName_shopping_item);
//        drop_table_shopping_item();
        

    }//public void onCreate(Bundle savedInstanceState)

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {

    	MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.opt_menu_main_actv, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自動生成されたメソッド・スタブ
		
		switch (item.getItemId()) {
		case R.id.opt_menu_main_actv_register://---------------
			
			Methods.dlg_register_main(this);
			
			break;// case R.id.opt_menu_main_actv_register
		
		}//switch (item.getItemId())

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
		
	}//protected void onResume()

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
    
    private void drop_table_shopping_item() {
    	// Setup db
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		String name = MainActv.tableName_shopping_item;
		
		boolean res = 
				dbu.dropTable(this, wdb, name);
		
		if (res == true) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table dropped: " + name);
		} else {//if (res == true)

			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Drop table => Failed: " + name);
			
		}//if (res == true)
		
		
		wdb.close();
		
		
	}//private void drop_table_shopping_item()

	private void get_column_names() {
    	// REF=> http://stackoverflow.com/questions/2382528/how-to-get-a-tables-columns-arraylist-on-android/2383705#2383705
    	// 		=> REF by => http://stackoverflow.com/questions/5838432/android-sqlite-get-the-column-names
    	//		=> Searched by => "android sqlite column names"
    	// Setup db
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

    	Cursor ti = rdb.rawQuery("PRAGMA table_info(" + MainActv.tableName_shopping_item + ")", null);
        if ( ti.moveToFirst() ) {
            do {
//                System.out.println("col: " + ti.getString(1));
            	// Log
				Log.d("MainActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "col: " + ti.getString(1));
            } while (ti.moveToNext());
        }
		
	}

	private void get_column_names(String tableName) {
    	// REF=> http://stackoverflow.com/questions/2382528/how-to-get-a-tables-columns-arraylist-on-android/2383705#2383705
    	// 		=> REF by => http://stackoverflow.com/questions/5838432/android-sqlite-get-the-column-names
    	//		=> Searched by => "android sqlite column names"
    	// Setup db
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

    	Cursor ti = rdb.rawQuery("PRAGMA table_info(" + tableName + ")", null);
    	
    	// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Table: " + tableName);
    	
        if ( ti.moveToFirst() ) {
            do {
//                System.out.println("col: " + ti.getString(1));
            	// Log
				Log.d("MainActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "col: " + ti.getString(1));
            } while (ti.moveToNext());
        }
		
	}//private void get_column_names(String tableName)

	private void see_db() {
		/*********************************
		 * memo
		 *********************************/
//    	// Setup db
//		DBUtils dbu = new DBUtils(this, MainActv.dbName);
//		
//		SQLiteDatabase rdb = dbu.getReadableDatabase();

    	List<String> tableList = this.getTableList(this);
    	
    	if (tableList != null) {
			
    		for (String item : tableList) {
				
    			// Log
				Log.d("MainActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "table=" + item);
    			
			}
    		
		} else {//if (tableList != null)
			
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "tableList == null");
			
		}//if (tableList != null)
		
		
		
		
	}//private void see_db()

	public static List<String> getTableList(Activity actv) {
//		DBUtils dbu = new DBUtils(actv, MainActv.dbName);
//		DBUtils dbu = new DBUtils(actv, "shoppinglist.db");
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "db name=" + "shoppinglist.db");
		
		//=> source: http://stackoverflow.com/questions/4681744/android-get-list-of-tables : "Just had to do the same. This seems to work:"
		String q = "SELECT name FROM " + "sqlite_master"+
						" WHERE type = 'table' ORDER BY name";
		
		Cursor c = null;
		try {
			c = rdb.rawQuery(q, null);
			
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getCount(): " + c.getCount());

		} catch (Exception e) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
		}
		
		// Table names list
		List<String> tableList = new ArrayList<String>();
		
		// Log
		if (c != null) {
			c.moveToFirst();
			
			for (int i = 0; i < c.getCount(); i++) {
				//
				tableList.add(c.getString(0));
				
				// Log
				Log.d("MainActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "c.getString(0): " + c.getString(0));
				
				
				// Next
				c.moveToNext();
				
			}//for (int i = 0; i < c.getCount(); i++)

		} else {//if (c != null)
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c => null");
		}//if (c != null)

//		// Log
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getCount(): " + c.getCount());
//		
		rdb.close();
		
		return tableList;
		
	}//public static List<String> getTableList()

	private void setList() {
		/*********************************
		 * 1. Set up db
		 * 1-2. Table exists?
		 * 1-3. Open db
		 * 
		 * 2. Prepare => List
		 * 3. Query
		 * 
		 * 4. Build list
		 * 5. Close db
		 * 
		 * 5-1. Sort list
		 * 
		 * 6. Set up adapter
		 * 7. Set adapter
		 * 
		 *********************************/
    	/*********************************
		 * 1. Set up db
		 *********************************/
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
//		SQLiteDatabase rdb = dbu.getReadableDatabase();

		/*********************************
		 * 1-2. Table exists?
		 *********************************/
		String name = MainActv.tableName_shopping_item;
		
		boolean res = table_exists(dbu, name);
		
		if (res == false) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "res == false");
			
			return;
			
		}
		
		/*********************************
		 * 1-3. Open db
		 *********************************/
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		
		/*********************************
		 * 2. Prepare => List
		 *********************************/
		list = new ArrayList<SI>();
		
		/*********************************
		 * 3. Query
		 *********************************/
		Cursor c = _query_items(rdb);
		
		if (c == null) {
			
			// debug
			Toast.makeText(this, "Query failed!", 2000).show();
			
			rdb.close();
			
			return;
			
		}//if (c == null)
		
		/*********************************
		 * 4. Build list
		 *********************************/
		list = _build_list(c);

		
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]",
				"MainActv.list.size(): " + MainActv.list.size());
		
		/*********************************
		 * 5. Close db
		 *********************************/
		rdb.close();
		
		
		/*********************************
		 * 5-1. Sort list
		 *********************************/
		
		this.sort_list(list);
		
		/*********************************
		 * 6. Set up adapter
		 *********************************/
		adapter = new ItemListAdapter(
				this,
				R.layout.adapteritem,
				list
				);

		/*********************************
		 * 7. Set adapter
		 *********************************/
		setListAdapter(adapter);
		
	}//private void setList()

	private void sort_list(List<SI> list) {
		/*********************************
		 * 1. Set up comparator
		 * 2. Sort list
		 *********************************/
		/*********************************
		 * 1. Set up comparator
		 *********************************/
		Comparator<SI> siComparator = new Comparator<SI>() {

			public int compare(SI s1, SI s2) {
				
//				if (s1.getYomi() != null && s2.getYomi() != null) {
//				if (s1.getYomi() != "" && s2.getYomi() != "") {
				
//				if (!s1.getYomi().equals("") && !s2.getYomi().equals("")) {
//				
//					
//					// Log
//					Log.d("Methods.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
////									.getLineNumber() + "]", "s1.getYomi() != null && s2.getYomi() != null");
//									.getLineNumber() + "]", "s1.getYomi() != \"\" && s2.getYomi() != \"\"");
//					
//					// Log
//					Log.d("Methods.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", "s1.getYomi().length()" + s1.getYomi().length());
//					
//					return s1.getYomi().compareTo(s2.getYomi());
//					
//				} else {//if (s1.getYomi() != null && s2.getYomi() != null)
					
					// Log
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "else");
					
					return s1.getName().compareTo(s2.getName());
					
//				}//if (s1.getYomi() != null && s2.getYomi() != null)
			
			}//public int compare(SI s1, SI s2)
			
		};//Comparator<SI> siComparator = new Comparator<SI>()
		
		/*********************************
		 * 2. Sort list
		 *********************************/
		Collections.sort(list, siComparator);
		
	}//private void sort_list(List<SI> list)

	private Cursor _query_items(SQLiteDatabase rdb) {
		
		String sql = "SELECT * FROM " + MainActv.tableName_shopping_item;
		
		Cursor c = rdb.rawQuery(sql, null);
		
		if (c == null) {
			
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c == null");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {
			
			// debug
			Toast.makeText(this, "No data yet", 2000).show();
			
			rdb.close();
			
			return null;
			
		}//if (c == null)

		return c;
		
	}//private Cursor _query_items()

	private List<SI> _build_list(Cursor c) {
		// TODO Auto-generated method stub
		List<SI> list = new ArrayList<SI>();
		
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			//{"name", "yomi", "store", "price", "genre"}
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]",
					"c.getInt(0): " + c.getInt(0) + "/" +		// _id
					"c.getLong(1): " + c.getLong(1) + "/" +		// created_at
					"c.getLong(2): " + c.getLong(2) + "/" +		// modified_at
					
					"c.getString(3): " + c.getString(3) + "/" +	// name
					"c.getString(4): " + c.getString(4) + "/" +	// yomi
					"c.getString(5): " + c.getString(5) + "/" +		// store
					
					"c.getInt(6): " + c.getInt(6) + "/" +		// price
					"c.getString(7): " + c.getString(7)			// genre
					);
			
//			09-09 22:44:23.136: D/Methods.java[85](6668): col: _id
//			09-09 22:44:23.136: D/Methods.java[85](6668): col: store
//			09-09 22:44:23.146: D/Methods.java[85](6668): col: name
//			09-09 22:44:23.146: D/Methods.java[85](6668): col: price
//			09-09 22:44:23.146: D/Methods.java[85](6668): col: genre
//			09-09 22:44:23.146: D/Methods.java[85](6668): col: yomi

			//
			SI item = new SI(
									c.getString(3),		// store
									c.getString(4),		// name
									c.getString(5),		// yomi
									
									c.getInt(6),			// price
									c.getString(7),		// genre
									
									c.getInt(0),				// _id
									c.getLong(1),				// _id
									c.getLong(2)				// _id
									
									);
			
			//
			list.add(item);
			
			//
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)

		return list;
	}

	private boolean table_exists(DBUtils dbu, String tableName) {
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		if(!dbu.tableExists(rdb, tableName)) {
			
			Log.d("MainActv.java" + "["
			+ Thread.currentThread().getStackTrace()[2].getLineNumber()
			+ "]", "Table doesn't exitst: " + tableName);
		
			/*----------------------------
			* 2. If no, create one
			----------------------------*/
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Create table");

			rdb.close();
			
			SQLiteDatabase wdb = dbu.getWritableDatabase();
			
			if(dbu.createTable(
					wdb, tableName, 
					MainActv.cols_shopping_item, 
					MainActv.col_types_shopping_item)) {
				
				//toastAndLog(actv, "Table created: " + tableName, 3000);
				
				// Log
				Log.d("MainActv.java"
				+ "["
				+ Thread.currentThread().getStackTrace()[2]
				.getLineNumber() + "]", "Table created: " + tableName);
				
				wdb.close();
				
				return true;
			
			} else {//if
				/*----------------------------
				* 2-2. Create table failed => Return
				----------------------------*/
				//toastAndLog(actv, "Create table failed: " + tableName, 3000);
				
				// Log
				Log.d("MainActv.java"
				+ "["
				+ Thread.currentThread().getStackTrace()[2]
				.getLineNumber() + "]", "Create table failed: " + tableName);
				
				wdb.close();
				
				return false;
			
			}//if
		
		} else {//if(dbu.tableExists(wdb, ImageFileManager8Activity.refreshLogTableName))
		
			//toastAndLog(actv, "Table exitsts: " + tableName, 2000);
			
			// Log
			Log.d("MainActv.java" + "["
			+ Thread.currentThread().getStackTrace()[2].getLineNumber()
			+ "]", "Table exitsts: " + tableName);
			
			rdb.close();
			
			return true;
		
		}//if(dbu.tableExists(wdb, ImageFileManager8Activity.refreshLogTableName))
		
	}//private boolean table_exists(DBUtils dbu, String tableName)

	private void restore_db() {
		
    	// Setup db
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
    	String src = 
//    			"/mnt/sdcard-ext/ShoppingList_backup/shoppinglist_backup_20120906_201402.bk";
    			"/mnt/sdcard-ext/ShoppingList_backup" +
    			"/shoppinglist_backup_20120909_174122.bk";
    	
    	String dst =
//    			"/data/data/test.main/databases/shoppinglist.db";
    			"/data/data/sl2.main/databases/" + dbName;

		File f_src = new File(src);
		File f_dst = new File(dst);

		/*----------------------------
		 * 3. Copy
			----------------------------*/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File copied");
			
			// debug
			Toast.makeText(this, "DB restoration => Done", 3000).show();

		} catch (FileNotFoundException e) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
		} catch (IOException e) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
		}//try
    	
	}//private void restore_db()

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "resultCode: " + resultCode);
		
		switch (requestCode) {
		
		case MainActv.REQUEST_CODE_REGISTER_ITEMS:
			
			switch (resultCode) {
			
			case RESULT_OK:
				
				// debug
				Toast.makeText(this, "RESULT_OK", 2000).show();
				
				this.setList();
				
				break;
			
			case RESULT_CANCELED:
				
				// debug
				Toast.makeText(this, "RESULT_CANCELED", 2000).show();
				
				this.setList();
				
				break;
				
				
			default:
				
				// debug
				Toast.makeText(this, "Not RESULT_OK: " + resultCode, 2000).show();
				
				break;
				
			}//switch (resultCode)
			
			break;
			
		default:
			break;
		
		}//switch (requestCode)
		
//		if (requestCode == MainActv.REQUEST_CODE_REGISTER_ITEMS) {
//			
//			// debug
//			Toast.makeText(this, "You're from 'RegisterItemActv'", 2000).show();
//			
//		} else {//if (requestCode == MainActv.REQUEST_CODE_REGISTER_ITEMS)
//
//			// debug
//			Toast.makeText(this, "You're from ?", 2000).show();
//			
//		}//if (requestCode == MainActv.REQUEST_CODE_REGISTER_ITEMS)
		
		
		
//		super.onActivityResult(requestCode, resultCode, data);
		
		
	}//protected void onActivityResult(int requestCode, int resultCode, Intent data)

}//public class MainActv extends ListActivity
