package at.fellnertroyer.raven.layout;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.data.Contact;

public class GroupInfo extends Activity {

	private TextView lblBack;
	private ImageView imgGroupPic;
	private EditText txtGroupName;
	private Switch switchNotification;
	private TextView lblMemberCount;
	private ListView listMembers;
	private Button btnLeave;
	private Button btnDelete;
	private TextView lblDateInfo;
	
	private ContactListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_group_info);
		init();
		dummyEntities();
	}
	
	private void init(){
		lblBack = (TextView)findViewById(R.id.lbl_groupinfo_header_back);
		imgGroupPic = (ImageView)findViewById(R.id.img_group_info_pic);
		txtGroupName = (EditText)findViewById(R.id.txt_group_info_name);
		switchNotification = (Switch)findViewById(R.id.switch_group_info_notification);
		lblMemberCount = (TextView)findViewById(R.id.lbl_group_info_membercount);
		listMembers = (ListView)findViewById(R.id.list_group_info_members);
		btnLeave = (Button)findViewById(R.id.btn_group_info_leavegroup);
		btnDelete = (Button)findViewById(R.id.btn_group_info_delete_group);
		lblDateInfo = (TextView)findViewById(R.id.lbl_group_info_date);
		
		adapter = new ContactListAdapter(this, R.layout.contact_list_row);
		listMembers.setAdapter(adapter);
		
		switchNotification.setChecked(true);
		
		setListViewHeightBasedOnChildren(listMembers);
	}
	
	public void btnGroupInfoBackClicked(final View v){
		finish();
	}
	
	private void dummyEntities(){
		adapter.add(new Contact("Du", "😝😂"));
		adapter.add(new Contact("Tobias","Yo!"));
		adapter.add(new Contact("Quaxi","Halloooo"));
		adapter.add(new Contact("M.Mustermann","😜😝😎🔜🔞🔫🔪"));
	}
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {
	    ContactListAdapter listAdapter = (ContactListAdapter)listView.getAdapter();
	    if (listAdapter == null)
	        return;

	    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
	    int totalHeight = 0;
	    View view = null;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        view = listAdapter.getView(i, view, listView);
	        if (i == 0)
	            view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

	        view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
	        totalHeight += view.getMeasuredHeight();
	    }
	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	    listView.requestLayout();
	}
}
