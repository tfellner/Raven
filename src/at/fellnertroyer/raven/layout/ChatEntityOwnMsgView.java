package at.fellnertroyer.raven.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.data.OwnMessage;

public class ChatEntityOwnMsgView extends LinearLayout{

	
	private View view;
	private String msg;
	private String date;
	private OwnMessage.MsgStatus satusMode;
	
	private TextView lblMsg, lblDate;
	
	public ChatEntityOwnMsgView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		
		init(context);
	}

	public ChatEntityOwnMsgView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init(context);
	}

	public ChatEntityOwnMsgView(Context context) {
		super(context);
		
		init(context);
	}
	
	public ChatEntityOwnMsgView(Context context, String msg, String date, OwnMessage.MsgStatus statusMode) {
		super(context);
		this.msg = msg;
		this.date = date;
		this.satusMode = statusMode;
		init(context);
	}
	
	public void init(Context context){
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (layoutInflater != null) {
            view = layoutInflater.inflate(R.layout.chat_entity_own_msg, this, true);
        }
		
		lblMsg = (TextView)findViewById(R.id.lbl_chatentity_ownmsg_text);
		lblDate = (TextView)findViewById(R.id.lbl_chatentity_ownmsg_date);
		
		lblMsg.setText(msg);
		lblDate.setText(date);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
		lblMsg.setText(msg);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		lblDate.setText(date);
	}

	public OwnMessage.MsgStatus getSatusMode() {
		return satusMode;
	}

	public void setSatusMode(OwnMessage.MsgStatus satusMode) {
		this.satusMode = satusMode;
	}
}
