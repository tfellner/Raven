package at.fellnertroyer.raven;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatEntityIncomeView extends LinearLayout{

	private View view;
	private String name, msg;
	private String date;
	private boolean group;
	private Color color;
	
	private TextView lblName, lblMsg, lblDate;
	
	public ChatEntityIncomeView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		
		init(context);
	}

	public ChatEntityIncomeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init(context);
	}

	public ChatEntityIncomeView(Context context) {
		super(context);
		
		init(context);
	}
	
	public ChatEntityIncomeView(Context context, String name, String msg, String date, boolean group, Color nameColor) {
		super(context);
		this.name = name;
		this.msg = msg;
		this.date = date;
		this.group = group;
		this.color = color;
		init(context);
	}
	
	public void init(Context context){
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (layoutInflater != null) {
            view = layoutInflater.inflate(R.layout.chat_entity_income_msg, this, true);
        }
		
		lblName = (TextView)view.findViewById(R.id.lbl_chatentity_income_name);
		lblMsg = (TextView)view.findViewById(R.id.lbl_chatentity_income_text);
		lblDate = (TextView)view.findViewById(R.id.lbl_chatentity_income_date);
		
		if(group){
			lblName.setText(name);
			lblName.setVisibility(View.VISIBLE);
		} else {
			lblName.setVisibility(View.GONE);
		}
		
		lblMsg.setText(msg);
		lblDate.setText(date);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isGroup() {
		return group;
	}

	public void setGroup(boolean group) {
		this.group = group;
		
		if(group){
			lblName.setText(name);
			lblName.setVisibility(View.VISIBLE);
		} else {
			lblName.setVisibility(View.GONE);
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
