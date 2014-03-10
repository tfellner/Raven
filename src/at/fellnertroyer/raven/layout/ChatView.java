package at.fellnertroyer.raven.layout;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import at.fellnertroyer.raven.R;

public class ChatView extends Activity {

	private ScrollView scroll;
	private LinearLayout container;
	private TextView lblBack;
	private LinearLayout layoutInfo;
	private ImageView imgAdd;
	private EditText txtInput;
	private TextView lblChatInfo1;
	private TextView lblChatInfo2;
	private boolean group;
	
	private static final int REQUEST_GROUP_INFO = 121;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat_view);
		init();
		dummyEntries();
	}
	
	private void init(){
		scroll = (ScrollView)findViewById(R.id.scroll_chat);
		container = (LinearLayout)findViewById(R.id.llayout_chat_container);
		txtInput = (EditText)findViewById(R.id.txt_chat_input);
		txtInput.setSelected(false);
		
		lblBack = (TextView)findViewById(R.id.lbl_chats_header_back);
		layoutInfo = (LinearLayout)findViewById(R.id.layout_chat_header_middle);
		imgAdd = (ImageView)findViewById(R.id.img_chat_add);
		
		lblChatInfo1 = (TextView)findViewById(R.id.lbl_chat_header_info);
		lblChatInfo2 = (TextView)findViewById(R.id.lbl_chat_header_info2);
		
	}
	
	public void btnChatSendClicked(final View v){
		String text = txtInput.getText().toString();
		if(!text.isEmpty()){
			addOwnMsg(text,"18:25",ChatEntityOwnMsgView.STATUS_SENT);
			txtInput.setText("");
			scroll.fullScroll(ScrollView.FOCUS_DOWN);
		}
//		txtInput.setEnabled(false);
//		txtInput.setEnabled(true);
		
	}
	
	public void btnChatBackClicked(final View v){
		Log.d(Main.TAG,"btnChatBackClicked");
		finish();
	}
	
	public void btnChatInfoClicked(final View v){
		Log.d(Main.TAG,"btnChatInfoClicked");
		Intent intent = new Intent(this,GroupInfo.class);
		startActivityForResult(intent, REQUEST_GROUP_INFO);
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
		
		addOwnMsg("Lorem 😝", "14:34", ChatEntityOwnMsgView.STATUS_SENT);
		
		addInfo("Quaxi wurde hinzugefügt");
		
		addIncomeMsg("Tobias", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod", "14:51", true, getResources().getColor(android.R.color.holo_orange_light));
		addIncomeMsg("Quaxi", "Lorem ipsum dolor", "16:10", true, getResources().getColor(android.R.color.holo_purple));
		
		addOwnMsg("Lorem ipsum dolor sit amet, consetetur", "16:41", ChatEntityOwnMsgView.STATUS_SENT);
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
	
	private void addOwnMsg(String text, String date, int statusMode){
		ChatEntityOwnMsgView ownMsg = new ChatEntityOwnMsgView(this, text, date, statusMode);
		ownMsg.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		container.addView(ownMsg);
	}
}
