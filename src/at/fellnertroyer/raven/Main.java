package at.fellnertroyer.raven;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class Main extends Activity {

	ListView list;
	AllChatsListAdapter adapter;
	public static final String TAG = "Raven";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG,"onCreate");
		init();
		dummyEntries();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Log.d(TAG,"onCreateOptionsMenu");
		return true;
	}
	
	public void init(){
		Log.d(TAG,"init");
		list = (ListView)findViewById(R.id.list_chatlist);
		adapter = new AllChatsListAdapter(this, R.layout.all_chats_list_row);
		list.setAdapter(adapter);
		
	}
	
	public void dummyEntries(){
		Log.d(TAG,"dummyEntries");
		adapter.add(new ChatContainer("M.Mustermann", 	"Lorem Ipsum Quaxi Günter", 			"00:00", 0));
		adapter.add(new ChatContainer("David", 			"Lorem Ipsum Quaxi Jochen", 			"01:00", 0));
		adapter.add(new ChatContainer("Tobias", 		"Lorem Ipsum Quaxi Rüdiger Peter ...", 	"02:00", 0));
		adapter.add(new ChatContainer("Jochen", 		"Lorem Ipsum Quaxi Günter",			 	"03:00", 0));
		adapter.add(new ChatContainer("Günter", 		"Lorem Ipsum Quaxi Jochen", 			"05:00", 0));
		adapter.add(new ChatContainer("Quaxi", 			"Lorem Ipsum Quaxi Rüdiger Peter ...", 	"05:00", 0));
		adapter.add(new ChatContainer("Rüdiger", 		"Lorem Ipsum Quaxi Günter",		 		"06:00", 0));
		adapter.add(new ChatContainer("Peter", 			"Lorem Ipsum Quaxi Jochen", 			"07:00", 0));
		
	}

}
