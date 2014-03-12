package at.fellnertroyer.raven.layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.data.Contact;

public class ContactRowView extends LinearLayout implements Comparable<ContactRowView>{

	private String name = null;
	private String status = null;
	private String info = null;
	private View view;
	
	private ImageView img = null;
	private TextView lblName = null;
	private TextView lblStatus = null;
	private TextView lblInfo = null;
	private LinearLayout layout = null;
	
	public ContactRowView(Context context, Contact contact) {
		super(context);
		name = contact.getName();
		status = contact.getStatus();
		
		init(context);
	}
	
	public ContactRowView(Context context, Contact contact, String info) {
		super(context);
		name = contact.getName();
		status = contact.getStatus();
		this.info = info;
		init(context);
	}
	
	public ContactRowView(Context context, String name, String status) {
		super(context);
		this.name = name;
		this.status = status;
		
		init(context);
	}
	
	public ContactRowView(Context context, String name, String status, String info) {
		super(context);
		this.name = name;
		this.status = status;
		this.info = info;
		init(context);
	}

	public ContactRowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		init(context);
	}

	public ContactRowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init(context);
	}
	
	private void init(Context context){
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (layoutInflater != null) {
            view = layoutInflater.inflate(R.layout.contact_list_row, this, true);
        }
		
		layout = (LinearLayout)view.findViewById(R.id.llayout_contact_row);
		img = (ImageView)view.findViewById(R.id.img_contactlist_icon);
		lblName = (TextView)view.findViewById(R.id.lbl_contactlist_name);
		lblStatus = (TextView)view.findViewById(R.id.lbl_contactlist_status);
		lblInfo = (TextView)view.findViewById(R.id.lbl_contactlist_info);
		
		if(name != null){
			lblName.setText(name);
		}
		if(status != null){
			lblStatus.setText(status);
		}
		if(info != null){
			lblInfo.setText(info);
			lblInfo.setVisibility(View.VISIBLE);
		} else {
			lblInfo.setVisibility(View.GONE);
		}
	}
	
	public void setBorderBottom(){
		if(layout != null){
			layout.setBackgroundResource(R.drawable.borderbottom);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		if(lblName != null){
			lblName.setText(name);
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		if(lblStatus != null){
			lblStatus.setText(status);
		}
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
		if(lblInfo != null){
			lblInfo.setText(info);
		}
	}

	public ImageView getImg() {
		return img;
	}

	public void setImg(ImageView img) {
		this.img = img;
	}

	@Override
	public int compareTo(ContactRowView another) {
		return name.compareTo(another.getName());
	}
	
	
}
