package at.fellnertroyer.raven.layout;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.SearchView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.data.Contact;
import at.fellnertroyer.raven.data.GlobalInformation;

public class ContactView extends Activity {

	private ListView list;
	private SearchView search;
	private ContactListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_contact_view);
		init();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_view, menu);
		return true;
	}
	
	public void init(){
		Log.d(Main.TAG,"initContactView");
		list = (ListView)findViewById(R.id.list_contactlist);
		adapter = new ContactListAdapter(this, R.layout.all_chats_list_row);
		list.setAdapter(adapter);
		search = (SearchView)findViewById(R.id.search_contact_view);
		adapter.addAll(GlobalInformation.allContacts);
		//search.setIconified(false);
	}
	
	public void btnContactsBackClicked(final View v){
		finish();
	}
}
