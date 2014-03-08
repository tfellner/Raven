package at.fellnertroyer.raven;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatEntityIncomeView extends LinearLayout{

	View v;
	TextView lblName, lblText, lblDate;
	String name, msg;
	String date;				// <-- Date date

	public ChatEntityIncomeView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ChatEntityIncomeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ChatEntityIncomeView(Context context) {
		super(context);
		init();
	}

	public ChatEntityIncomeView(Context context, String name, String msg, String time, boolean group){
		super(context);
		
		LayoutInflater  mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.chat_entity_income_msg, this, true);
        
		init();
		setMsg(msg);
		setName(name);
		setDate(time);
		
		lblName.setText(name);
		lblText.setText(msg);
		lblDate.setText(date);
	}
	
	private void init(){
		v = inflate(getContext(), R.layout.chat_entity_income_msg, null);
        addView(v);
		
		lblName = (TextView)v.findViewById(R.id.lbl_chatentity_income_name);
		lblText = (TextView)v.findViewById(R.id.lbl_chatentity_income_text);
		lblDate = (TextView)v.findViewById(R.id.lbl_chatentity_income_date);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		//lblName.setText(name);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
		//lblText.setText(msg);
	}
	
	// Date

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		//lblDate.setText(date);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
	}
}
