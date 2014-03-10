package at.fellnertroyer.raven.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import at.fellnertroyer.raven.R;

public class ChatEntityInfoView extends LinearLayout{

	private View view;
	private String msg;
	private TextView lblMsg;
	
	public ChatEntityInfoView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		
		init(context);
	}

	public ChatEntityInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init(context);
	}

	public ChatEntityInfoView(Context context) {
		super(context);
		
		init(context);
	}
	
	public ChatEntityInfoView(Context context, String msg) {
		super(context);
		this.msg = msg;
		init(context);
	}
	
	public void init(Context context){
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (layoutInflater != null) {
            view = layoutInflater.inflate(R.layout.chat_entity_info, this, true);
        }
		lblMsg = (TextView)view.findViewById(R.id.lbl_chat_entity_info);
		lblMsg.setText(msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
		lblMsg.setText(msg);
	}
}
