package at.fellnertroyer.raven.layout;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.data.ChatContainer;
import at.fellnertroyer.raven.data.GlobalInformation;

public class AllChatsListAdapter extends ArrayAdapter<ChatContainer>{
	
	Context context;
	int layoutResourceId;
	
	public AllChatsListAdapter(Context context, int resource,
			int textViewResourceId) {
		super(context, resource, textViewResourceId);
		
		this.context = context;
		layoutResourceId = resource;
	}
	
	public AllChatsListAdapter(Context context, int resource,
			ChatContainer[] objects) {
		super(context, resource, objects);
		
		this.context = context;
		layoutResourceId = resource;
	}



	public AllChatsListAdapter(Context context, int resource,
			int textViewResourceId, ChatContainer[] objects) {
		super(context, resource, textViewResourceId, objects);
		
		this.context = context;
		layoutResourceId = resource;
	}



	public AllChatsListAdapter(Context context, int resource,
			int textViewResourceId, List<ChatContainer> objects) {
		super(context, resource, textViewResourceId, objects);
		
		this.context = context;
		layoutResourceId = resource;
	}



	public AllChatsListAdapter(Context context, int resource,
			List<ChatContainer> objects) {
		super(context, resource, objects);
		
		this.context = context;
		layoutResourceId = resource;
	}



	public AllChatsListAdapter(Context context, int resource) {
		super(context, resource);
		
		this.context = context;
		layoutResourceId = resource;
	}



	@Override
    public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		
		if(v == null){
			LayoutInflater vi;
	        vi = LayoutInflater.from(getContext());
	        v = vi.inflate(R.layout.all_chats_list_row, null);
	        
	        ChatContainer chat = getItem(position);
	        
	        if(chat != null){
	        	ImageView img = (ImageView)v.findViewById(R.id.img_chatlist_icon);
	        	TextView lblName = (TextView)v.findViewById(R.id.lbl_chatlist_name);
	        	TextView lblDate = (TextView)v.findViewById(R.id.lbl_chatlist_date);
	        	TextView lblLastMsg = (TextView)v.findViewById(R.id.lbl_chatlist_lastmsg);
	        	
	        	if(img != null){
	        		img.setImageResource(R.drawable.ic_launcher);
	        	}
	        	if(lblName != null){
	        		lblName.setText(chat.getName());
	        	}
	        	if(lblDate != null){
	        		lblDate.setText(GlobalInformation.getDateString(chat.getLastMsgDate()));
	        	}
	        	if(lblLastMsg != null){
	        		lblLastMsg.setText(chat.getLastMsgString());
	        	}
	        	Log.d(Main.TAG,"+ " + chat.getName());
	        }
		}
		return v;
	}
	

}
