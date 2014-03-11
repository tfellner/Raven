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
import at.fellnertroyer.raven.data.Contact;

public class ContactListAdapter extends ArrayAdapter<Contact>{
	
	Context context;
	int layoutResourceId;
	
	public ContactListAdapter(Context context, int resource,
			int textViewResourceId) {
		super(context, resource, textViewResourceId);
		
		this.context = context;
		layoutResourceId = resource;
	}
	
	public ContactListAdapter(Context context, int resource,
			Contact[] objects) {
		super(context, resource, objects);
		
		this.context = context;
		layoutResourceId = resource;
	}



	public ContactListAdapter(Context context, int resource,
			int textViewResourceId, Contact[] objects) {
		super(context, resource, textViewResourceId, objects);
		
		this.context = context;
		layoutResourceId = resource;
	}



	public ContactListAdapter(Context context, int resource,
			int textViewResourceId, List<Contact> objects) {
		super(context, resource, textViewResourceId, objects);
		
		this.context = context;
		layoutResourceId = resource;
	}



	public ContactListAdapter(Context context, int resource,
			List<Contact> objects) {
		super(context, resource, objects);
		
		this.context = context;
		layoutResourceId = resource;
	}



	public ContactListAdapter(Context context, int resource) {
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
	        v = vi.inflate(R.layout.contact_list_row, null);
	        
	        Contact contact = getItem(position);
	        
	        if(contact != null){
	        	ImageView img = (ImageView)v.findViewById(R.id.img_contactlist_icon);
	        	TextView lblName = (TextView)v.findViewById(R.id.lbl_contactlist_name);
	        	TextView lblInfo = (TextView)v.findViewById(R.id.lbl_contactlist_info);
	        	TextView lblStatus = (TextView)v.findViewById(R.id.lbl_contactlist_status);
	        	
	        	if(img != null){
	        		img.setImageResource(R.drawable.ic_launcher);
	        	}
	        	if(lblName != null){
	        		lblName.setText(contact.getName());
	        	}
	        	if(lblInfo != null){
	        		lblInfo.setVisibility(View.GONE);
	        	}
	        	if(lblStatus != null){
	        		lblStatus.setText(contact.getStatus());
	        	}
	        }
		}
		return v;
	}
	

}
