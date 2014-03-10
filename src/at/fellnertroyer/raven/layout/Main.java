package at.fellnertroyer.raven.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.data.ChatContainer;

public class Main extends Activity {

	ListView list;
	SearchView search;
	AllChatsListAdapter adapter;
	public static final String TAG = "Raven";
	
	private static final int REQUEST_CHAT_VIEW = 111;
	private static final int REQUEST_CONTACT_VIEW = 222;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Log.d(TAG,"onCreate");
		init();
		dummyEntries();
	}
	
	public void init(){
		Log.d(TAG,"init");
		list = (ListView)findViewById(R.id.list_chatlist);
		adapter = new AllChatsListAdapter(this, R.layout.all_chats_list_row);
		list.setAdapter(adapter);
		
		search = (SearchView)findViewById(R.id.search_chatlist);
		search.setVisibility(View.GONE);
		
		//If the X is pressed the Searchview disapears
		search.setOnCloseListener(new SearchView.OnCloseListener() {

			@Override
			public boolean onClose() {
				search.clearFocus();
				search.setVisibility(View.GONE);
			    return false;
		    }
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		Intent intent;
		switch (id) {
		case R.id.action_contacts:
			intent = new Intent(this, ContactView.class);
			startActivityForResult(intent, REQUEST_CONTACT_VIEW);
			break;
		case R.id.action_newchat:
			
			break;
		case R.id.action_newgroup:
			
			break;
		case R.id.action_search:
			
			if(search.getVisibility() == View.VISIBLE){
				search.setVisibility(View.GONE);
			} else {
				search.setVisibility(View.VISIBLE);
				search.setIconified(false);
			}
			break;
		case R.id.action_settings: 
			
			//Just to debug - wrong position of ChatView
			intent = new Intent(this, ChatView.class);
			startActivityForResult(intent, REQUEST_CHAT_VIEW);
			break;
		case R.id.action_favorits:
			
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Log.d(TAG,"onCreateOptionsMenu");
		return true;
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
