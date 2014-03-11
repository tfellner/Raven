package at.fellnertroyer.raven.layout;

import java.util.ArrayList;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.data.ChatContainer;
import at.fellnertroyer.raven.data.ChatInfo;
import at.fellnertroyer.raven.data.Contact;
import at.fellnertroyer.raven.data.GlobalInformation;
import at.fellnertroyer.raven.data.GroupChatContainer;
import at.fellnertroyer.raven.data.IncomeMessage;
import at.fellnertroyer.raven.data.OwnMessage;
import at.fellnertroyer.raven.data.SingleChatContainer;

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
		loadData(true);
		
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
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long id) {
				startChatView(pos);
			}
		});
	}
	
	public void startChatView(int index){
		Intent intent = new Intent(this,ChatView.class);
		intent.putExtra(ChatView.EXTRA_INDEX, index);
		startActivityForResult(intent, REQUEST_CHAT_VIEW);
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		loadData(false);
		
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void dummyEntries(){
		Log.d(TAG,"dummyEntries");
		
		GlobalInformation.allChats = new ArrayList<ChatContainer>();
		GlobalInformation.allContacts = new ArrayList<Contact>();
		
		GlobalInformation.allContacts.add(new Contact("Tobias","Yo!"));
		GlobalInformation.allContacts.add(new Contact("Quaxi","Halloooo"));
		GlobalInformation.allContacts.add(new Contact("M.Mustermann","😜😝😎🔜🔞🔫🔪"));
		GlobalInformation.allContacts.add(new Contact("Günter","@Home"));
		GlobalInformation.allContacts.add(new Contact("Jochen","hat hunger"));
		GlobalInformation.allContacts.add(new Contact("Lintschi","bin müde *Gäähn* - will schlafen "));
		GlobalInformation.allContacts.add(new Contact("Ziegenpeter","hat irgendwer die Heidi gesehen?"));
		GlobalInformation.allContacts.add(new Contact("Heidi","Hoits ma den Peter vom Leib!! >.<"));
		GlobalInformation.allContacts.add(new Contact("Billy","Auf Geschäftsreise - Verhandeln ❤️"));
		GlobalInformation.allContacts.add(new Contact("Mes","OnePiece"));
		GlobalInformation.allContacts.add(new Contact("Aschauer","Ups jz ho is zbissn"));
		GlobalInformation.allContacts.add(new Contact("Paul","He du Topf!"));
		GlobalInformation.allContacts.add(new Contact("Luki","Hat irgendein Knabe lust auf interaktive Kommunkikation"));
		
		GregorianCalendar c1 = new GregorianCalendar();
		c1.set(Calendar.DAY_OF_MONTH, 1);
		
		GregorianCalendar c2 = new GregorianCalendar();
		c2.set(Calendar.DAY_OF_WEEK, 1);
		
		GlobalInformation.allChats.add(new SingleChatContainer(GlobalInformation.allContacts.get(0), c1));
		GlobalInformation.allChats.add(new SingleChatContainer(GlobalInformation.allContacts.get(1), c2));
		GlobalInformation.allChats.add(new GroupChatContainer("Quaxi Günter", new GregorianCalendar()));
		GlobalInformation.allChats.add(new SingleChatContainer(GlobalInformation.allContacts.get(2), new GregorianCalendar()));
		GlobalInformation.allChats.add(new SingleChatContainer(GlobalInformation.allContacts.get(3), new GregorianCalendar()));
		GlobalInformation.allChats.add(new SingleChatContainer(GlobalInformation.allContacts.get(4), new GregorianCalendar()));
		GlobalInformation.allChats.add(new SingleChatContainer(GlobalInformation.allContacts.get(5), new GregorianCalendar()));
		GlobalInformation.allChats.add(new SingleChatContainer(GlobalInformation.allContacts.get(6), new GregorianCalendar()));
		GlobalInformation.allChats.add(new SingleChatContainer(GlobalInformation.allContacts.get(7), new GregorianCalendar()));
		
		for(ChatContainer container : GlobalInformation.allChats){
			
			if(container instanceof SingleChatContainer){
				container.addChatEntity(new IncomeMessage("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut", c1, ((SingleChatContainer) container).getPartner()));
				container.addChatEntity(new IncomeMessage("Lorem ipsum dolor sit amet, consetetur sadipscing elitr", c2, ((SingleChatContainer) container).getPartner()));
				container.addChatEntity(new OwnMessage("Lorem 😝", new GregorianCalendar()));
				container.addChatEntity(new IncomeMessage("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod",  new GregorianCalendar(), ((SingleChatContainer) container).getPartner()));
				container.addChatEntity(new IncomeMessage("Lorem ipsum dolor", new GregorianCalendar(), ((SingleChatContainer) container).getPartner()));
				container.addChatEntity(new OwnMessage("Lorem ipsum dolor sit amet! :P", new GregorianCalendar()));
			} 
			if(container instanceof GroupChatContainer){
				((GroupChatContainer) container).addMember(GlobalInformation.allContacts.get(0));
				((GroupChatContainer) container).addMember(GlobalInformation.allContacts.get(1));
				((GroupChatContainer) container).addMember(GlobalInformation.allContacts.get(2));
				((GroupChatContainer) container).addMember(GlobalInformation.allContacts.get(3));
				
				container.addChatEntity(new IncomeMessage("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut", c1, ((GroupChatContainer)container).getMembers().get(3)));
				container.addChatEntity(new IncomeMessage("Lorem ipsum dolor sit amet, consetetur sadipscing elitr", c2, ((GroupChatContainer)container).getMembers().get(1)));
				container.addChatEntity(new ChatInfo("M.Mustermann hat die Unterhaltung verlassen"));
				container.addChatEntity(new OwnMessage("Lorem 😝", new GregorianCalendar()));
				container.addChatEntity(new IncomeMessage("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod",  new GregorianCalendar(), ((GroupChatContainer)container).getMembers().get(1)));
				container.addChatEntity(new ChatInfo("M.Mustermann wurde hinzugefügt"));
				container.addChatEntity(new IncomeMessage("Lorem ipsum dolor", new GregorianCalendar(), ((GroupChatContainer)container).getMembers().get(2)));
				container.addChatEntity(new OwnMessage("Lorem ipsum dolor sit amet! :P", new GregorianCalendar()));
			} 
		}
	}
	
	public void loadData(boolean dummy){
		if(dummy){
			dummyEntries();
		}
		adapter = new AllChatsListAdapter(this, R.layout.all_chats_list_row,GlobalInformation.allChats);
		list.setAdapter(adapter);
		Log.d(TAG, "letze Nachricht: für: " + GlobalInformation.allChats.get(0).getName() + ":" + GlobalInformation.allChats.get(0).getLastMsgString());
		
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
