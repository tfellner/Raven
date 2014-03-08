package at.fellnertroyer.raven;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class ChatView extends Activity {

	ScrollView scroll;
	LinearLayout container;
	EditText txtInput;
	
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
	}
	
	public void btnChatSendClicked(final View v){
		
	}
	
	private void dummyEntries(){
		addIncomeMsg("Max Mustermann", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut", "14:31", true, null);
		addIncomeMsg("Tobias", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr", "14:32", true, null);
		
		addOwnMsg("msg", "14:34", ChatEntityOwnMsgView.STATUS_SENT);
		
		addIncomeMsg("Tobias", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod", "14:51", true, null);
		addIncomeMsg("Quaxi", "Lorem ipsum dolor", "16:10", true, null);
		
		addOwnMsg("Lorem ipsum dolor sit amet, consetetur", "16:41", ChatEntityOwnMsgView.STATUS_SENT);
	}
	
	private void addIncomeMsg(String name, String text, String date, boolean group, Color nameColor){
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
