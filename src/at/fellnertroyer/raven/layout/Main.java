package at.fellnertroyer.raven.layout;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.data.GlobalInformation;
import at.fellnertroyer.raven.data.GroupChatContainer;

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
		GregorianCalendar c1 = new GregorianCalendar();
		c1.set(Calendar.DAY_OF_MONTH, 1);
		
		GregorianCalendar c2 = new GregorianCalendar();
		c1.set(Calendar.DAY_OF_WEEK, 3);
		
		adapter.add(new GroupChatContainer("M.Mustermann", 	"Lorem Ipsum Quaxi Günter", 			c1));
		adapter.add(new GroupChatContainer("David", 		"Lorem Ipsum Quaxi Jochen", 			c2));
		adapter.add(new GroupChatContainer("Tobias", 		"Lorem Ipsum Quaxi Rüdiger Peter ...", 	new GregorianCalendar()));
		adapter.add(new GroupChatContainer("Jochen", 		"Lorem Ipsum Quaxi Günter",			 	new GregorianCalendar()));
		adapter.add(new GroupChatContainer("Günter", 		"Lorem Ipsum Quaxi Jochen", 			new GregorianCalendar()));
		adapter.add(new GroupChatContainer("Quaxi", 		"Lorem Ipsum Quaxi Rüdiger Peter ...", 	new GregorianCalendar()));
		adapter.add(new GroupChatContainer("Rüdiger", 		"Lorem Ipsum Quaxi Günter",		 		new GregorianCalendar()));
		adapter.add(new GroupChatContainer("Peter", 		"Lorem Ipsum Quaxi Jochen", 			new GregorianCalendar()));
		
		
	}
	
	public void loadSettings(){
		// obtain telNr
//		TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
//		GlobalInformation.telNr = tMgr.getLine1Number();
		
		//read contacts
		
//		ContentResolver cr = getContentResolver();
//        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
//                null, null, null, null);
//        if (cur.getCount() > 0) {
//            while (cur.moveToNext()) {
//                  String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
//                  String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                  if (Integer.parseInt(cur.getString(
//                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
//                     Cursor pCur = cr.query(
//                               ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                               null,
//                               ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
//                               new String[]{id}, null);
//                     while (pCur.moveToNext()) {
//                         String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                         //Toast.makeText(NativeContentProvider.this, "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
//                     }
//                    pCur.close();
//                }
//            }
//        }
		
		
	}

}
