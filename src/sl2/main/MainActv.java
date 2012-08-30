package sl2.main;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

public class MainActv extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setTitle(this.getClass().getName());
        
    }//public void onCreate(Bundle savedInstanceState)
    
}//public class MainActv extends ListActivity
