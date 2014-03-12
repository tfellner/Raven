package at.fellnertroyer.raven.layout;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.data.ChatContainer;
import at.fellnertroyer.raven.data.Contact;
import at.fellnertroyer.raven.data.GlobalInformation;
import at.fellnertroyer.raven.data.GroupChatContainer;

public class GroupInfo extends Activity {

	private TextView lblBack;
	private ImageView imgGroupPic;
	private EditText txtGroupName;
	private Switch switchNotification;
	private TextView lblMemberCount;
	private LinearLayout layoutMembers;
	private Button btnLeave;
	private Button btnDelete;
	private TextView lblDateInfo;
	
	private ContactListAdapter adapter;
	private int chatContainerIndex = -1;
	private GroupChatContainer chatContainer;
	private int selectedIndexAlert = -1;
	private ArrayList<Contact> allContacts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_group_info);
		init();
		
		Intent intent = getIntent();
		Bundle params = intent.getExtras();
		if(params != null){
			chatContainerIndex = params.getInt(ChatView.EXTRA_INDEX);
		}
		if(chatContainerIndex == -1){
			dummyEntities();
		} else {
			loadChatContainer();
		}
	}
	
	private void reloadMembers(){
		ArrayList<ContactRowView> members = new ArrayList<ContactRowView>();
		for(Contact c : chatContainer.getMembers()){
			members.add(new ContactRowView(this, c, null));
		}
		Contact admin = chatContainer.getAdmin();
		if(admin != null){
			members.add(new ContactRowView(this,admin.getName(),admin.getStatus(),"Admin"));
		}
		
		Collections.sort(members);
		
		layoutMembers.removeAllViews();
		if(admin == null){
			ContactRowView you = new ContactRowView(this, "Du",GlobalInformation.you.getStatus(),"Admin");
			you.setBorderBottom();
			layoutMembers.addView(you);
		} else {
			ContactRowView you = new ContactRowView(this, "Du",GlobalInformation.you.getStatus());
			you.setBorderBottom();
			layoutMembers.addView(you);
		}
		
		for(ContactRowView crv : members){
			crv.setBorderBottom();
			layoutMembers.addView(crv);
		}
	}
	
	private void loadChatContainer(){
		Log.d(Main.TAG,"size: " + GlobalInformation.allContacts.size());
		allContacts = new ArrayList<Contact>(GlobalInformation.allContacts);
		
		chatContainer = (GroupChatContainer)GlobalInformation.allChats.get(chatContainerIndex);
		txtGroupName.setText(chatContainer.getName());
		lblMemberCount.setText(chatContainer.getMemberCount() + " MITGLIEDER");
		switchNotification.setChecked(chatContainer.isNotification());
		
		reloadMembers();
	}
	
	private void init(){
		lblBack = (TextView)findViewById(R.id.lbl_groupinfo_header_back);
		imgGroupPic = (ImageView)findViewById(R.id.img_group_info_pic);
		txtGroupName = (EditText)findViewById(R.id.txt_group_info_name);
		switchNotification = (Switch)findViewById(R.id.switch_group_info_notification);
		lblMemberCount = (TextView)findViewById(R.id.lbl_group_info_membercount);
		layoutMembers = (LinearLayout)findViewById(R.id.llayout_group_info_members);
		btnLeave = (Button)findViewById(R.id.btn_group_info_leavegroup);
		btnDelete = (Button)findViewById(R.id.btn_group_info_delete_group);
		lblDateInfo = (TextView)findViewById(R.id.lbl_group_info_date);
		switchNotification.setChecked(true);
		
		switchNotification.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				GlobalInformation.allChats.get(chatContainerIndex).setNotification(isChecked);
			}
		});
	}
	
	public void btnGroupInfoBackClicked(final View v){
		if(!txtGroupName.getText().toString().equals(chatContainer.getName())){
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setCancelable(false);
			alert.setTitle("Wollen Sie den Gruppenname wirklich ändertn?");
			alert.setPositiveButton("Ja", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					GlobalInformation.allChats.get(chatContainerIndex).setName(txtGroupName.getText().toString());
					finish();
				}
			});
			alert.setNegativeButton("Nein", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
			alert.show();
		} else {
			finish();
		}
		
	}
	
	public void btnGroupInfoAddMemberClicked(final View v){
	
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		//final LinearLayout vDialog = (LinearLayout)getLayoutInflater().inflate(R.layout.contact_dialog, null);
		//ListView list = (ListView)vDialog.findViewById(R.id.list_contact_dialog);
		
		
		
		ArrayList<Contact> contacts = new ArrayList<Contact>(allContacts);
		//Log.d(Main.TAG,"contacts.size: " + contacts.size());
		//Log.d(Main.TAG,"chatContainer.getMembers"+ chatContainer.getMembers());
		for(Contact contact : chatContainer.getMembers()){
			contacts.remove(contact);
		}
		if(chatContainer.getAdmin() != null){
			contacts.remove(chatContainer.getAdmin());
		}
		
		//Log.d(Main.TAG,"contacts: "+ contacts);
		ListView list = new ListView(this);
		ContactListAdapter a = new ContactListAdapter(this,R.layout.contact_list_row,contacts);
		list.setAdapter(a);
		alert.setView(list);
		
		selectedIndexAlert = -1;
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {
				selectedIndexAlert = pos;
			}
		});
		
//		Contact selected = null;
//		if(selectedIndexAlert < 0 || selectedIndexAlert >= contacts.size()){
//			selected = contacts.get(selectedIndexAlert);
//		}
//		
//		if(selected !=  null){
//			addMember(selected);
//		}
		alert.setNegativeButton("Cancle", null);
		alert.show();

	}
	
	private void addMember(Contact contact){
		chatContainer.addMember(contact);
		reloadMembers();
	}
	
	private void dummyEntities(){
		adapter.add(new Contact("Du", "😝😂"));
		adapter.add(new Contact("Tobias","Yo!"));
		adapter.add(new Contact("Quaxi","Halloooo"));
		adapter.add(new Contact("M.Mustermann","😜😝😎🔜🔞🔫🔪"));
	}
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {
	    ContactListAdapter listAdapter = (ContactListAdapter)listView.getAdapter();
	    if (listAdapter == null)
	        return;

	    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
	    int totalHeight = 0;
	    View view = null;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        view = listAdapter.getView(i, view, listView);
	        if (i == 0)
	            view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

	        view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
	        totalHeight += view.getMeasuredHeight();
	    }
	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	    listView.requestLayout();
	}
}
