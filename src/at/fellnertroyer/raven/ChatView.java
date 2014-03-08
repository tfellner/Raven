package at.fellnertroyer.raven;

import android.os.Bundle;
import android.app.Activity;
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
		container.addView(new ChatEntityIncomeView(this,"Max Mustermann","Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut","Diestag 14:31",true));
		container.addView(new ChatEntityIncomeView(this,"Max Mustermann","Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut","Diestag 14:31",true));
		container.addView(new ChatEntityIncomeView(this,"Max Mustermann","Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut","Diestag 14:31",true));
		container.addView(new ChatEntityIncomeView(this,"Max Mustermann","Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut","Diestag 14:31",true));
	}
}
