package at.fellnertroyer.raven.layout;

import java.util.GregorianCalendar;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.data.ChatContainer;
import at.fellnertroyer.raven.data.ChatEntity;
import at.fellnertroyer.raven.data.ChatInfo;
import at.fellnertroyer.raven.data.GlobalInformation;
import at.fellnertroyer.raven.data.GroupChatContainer;
import at.fellnertroyer.raven.data.IncomeMessage;
import at.fellnertroyer.raven.data.OwnMessage;
import at.fellnertroyer.raven.data.OwnMessage.MsgStatus;
import at.fellnertroyer.raven.data.SingleChatContainer;

public class ChatView extends Activity {

	private ScrollView scroll;
	private LinearLayout container;
	private TextView lblBack;
	private LinearLayout layoutInfo;
	private ImageView imgAdd;
	private EditText txtInput;
	private TextView lblChatInfo1;
	private TextView lblChatInfo2;
	private TextView lblName;
	private boolean group;
	
	private int chatContainerIndex = -1;
	private ChatContainer chatContainer = null;
	
	private static final int REQUEST_GROUP_INFO = 121;
	public static final String EXTRA_INDEX = "Index";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat_view);
		
		Intent intent = getIntent();
		Bundle params = intent.getExtras();
		if(params != null){
			chatContainerIndex = params.getInt(EXTRA_INDEX);
		}
		init();
		
		if(chatContainerIndex == -1){
			dummyEntries();
		} else {
			loadChatContainer();
		}
	}
	
	private void updateChatContainer(){
		
		chatContainer = GlobalInformation.allChats.get(chatContainerIndex);
		// Nachschauen ob neue nachrichten
	}
	
	private void loadChatContainer(){
		//Log.d(Main.TAG,"idx: " + chatContainerIndex + " size: " + GlobalInformation.allChats.size());
		chatContainer = GlobalInformation.allChats.get(chatContainerIndex);
		if(chatContainer instanceof GroupChatContainer){
			group = true;
			lblName.setText(chatContainer.getName());
			lblChatInfo1.setText(((GroupChatContainer)chatContainer).getMemberCount() + " Mitglieder");
			lblChatInfo2.setVisibility(View.VISIBLE);
			lblChatInfo2.setText(((GroupChatContainer)chatContainer).getMemberOnlineCount() + " online");
		}
		if(chatContainer instanceof SingleChatContainer){
			group = false;
			lblName.setText(chatContainer.getName());
			lblChatInfo1.setText((((SingleChatContainer)chatContainer).isPartnerOnline() ? "online" : "zuletzt online: 00:00"));
			lblChatInfo2.setVisibility(View.GONE);
		}
		
		container.removeAllViews();
		for(ChatEntity ce : chatContainer.getChatEntityList()){
			if(ce instanceof IncomeMessage){
				IncomeMessage im = (IncomeMessage)ce;
				int color = getResources().getColor(android.R.color.holo_purple);
				if(chatContainer instanceof GroupChatContainer){
					color = ((GroupChatContainer)chatContainer).getColor(im.getContact());
				}
				addIncomeMsg(im.getContact().getName(), im.getMsg(), im.getDateString(), group, color);
			}
			if(ce instanceof OwnMessage){
				OwnMessage om = (OwnMessage)ce;
				addOwnMsg(om.getMsg(), om.getDateString(), om.getStatus());
			}
			if(ce instanceof ChatInfo){
				ChatInfo ci = (ChatInfo)ce;
				addInfo(ci.getText());
			}
		}
	}
	
	private void init(){
		scroll = (ScrollView)findViewById(R.id.scroll_chat);
		
		container = (LinearLayout)findViewById(R.id.llayout_chat_container);
		
		txtInput = (EditText)findViewById(R.id.txt_chat_input);
		txtInput.setSelected(false);
		
		lblBack = (TextView)findViewById(R.id.lbl_chats_header_back);
		layoutInfo = (LinearLayout)findViewById(R.id.layout_chat_header_middle);
		imgAdd = (ImageView)findViewById(R.id.img_chat_add);
		
		lblName =  (TextView)findViewById(R.id.lbl_chat_header_name);
		lblChatInfo1 = (TextView)findViewById(R.id.lbl_chat_header_info);
		lblChatInfo2 = (TextView)findViewById(R.id.lbl_chat_header_info2);
		
		container.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				txtInput.setEnabled(false);
				txtInput.setEnabled(true);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		loadChatContainer();
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void btnChatSendClicked(final View v){
		String text = txtInput.getText().toString();
		if(!text.isEmpty()){
			GregorianCalendar now = new GregorianCalendar();
			GlobalInformation.allChats.get(chatContainerIndex).addChatEntity(new OwnMessage(text,now));
			addOwnMsg(text,GlobalInformation.getDateString(now),MsgStatus.STATUS_UNSENT);
			txtInput.setText("");
			//scroll.fullScroll(ScrollView.FOCUS_DOWN);
			
			scroll.post(new Runnable() { 
			     public void run() {
			    	 scroll.fullScroll(View.FOCUS_DOWN);
			     }
			 });
		}
		
	}
	
	public void btnChatBackClicked(final View v){
		Log.d(Main.TAG,"btnChatBackClicked");
		finish();
	}
	
	public void btnChatInfoClicked(final View v){
		Log.d(Main.TAG,"btnChatInfoClicked");
		
		if(group){
			Intent intent = new Intent(this,GroupInfo.class);
			intent.putExtra(EXTRA_INDEX, chatContainerIndex);
			startActivityForResult(intent, REQUEST_GROUP_INFO);
		} else {
			txtInput.setEnabled(false);
			txtInput.setEnabled(true);
		}
	}

	public void btnChatAddClicked(final View v){
		Log.d(Main.TAG,"btnChatAddClicked");
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setSingleChoiceItems(R.array.chatAddChoice, -1, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//
				dialog.cancel();
			}
		});
		alert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alert.show();
	} 

	private void dummyEntries(){
		addIncomeMsg("Max Mustermann", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut", "14:31", true, getResources().getColor(android.R.color.holo_blue_dark));
		addIncomeMsg("Tobias", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr", "14:32", true, getResources().getColor(android.R.color.holo_orange_light));
		
		addInfo("Max Mustermann hat die Gruppe verlassen");
		
		addOwnMsg("Lorem 😝", "14:34", MsgStatus.STATUS_SENT);
		
		addInfo("Quaxi wurde hinzugefügt");
		
		addIncomeMsg("Tobias", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod", "14:51", true, getResources().getColor(android.R.color.holo_orange_light));
		addIncomeMsg("Quaxi", "Lorem ipsum dolor", "16:10", true, getResources().getColor(android.R.color.holo_purple));
		
		addOwnMsg("Lorem ipsum dolor sit amet, consetetur", "16:41", MsgStatus.STATUS_SENT);
	}
	
	private void addInfo(String infotxt){
		ChatEntityInfoView infoEntity = new ChatEntityInfoView(this, infotxt);
		infoEntity.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		container.addView(infoEntity);
	}
	
	private void addIncomeMsg(String name, String text, String date, boolean group, int nameColor){
		ChatEntityIncomeView incomeMsg = new ChatEntityIncomeView(this, name, text, date, group, nameColor);
		incomeMsg.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		container.addView(incomeMsg);
	}
	
	private void addOwnMsg(String text, String date, OwnMessage.MsgStatus status){
		ChatEntityOwnMsgView ownMsg = new ChatEntityOwnMsgView(this, text, date, status);
		ownMsg.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		container.addView(ownMsg);
	}
}
