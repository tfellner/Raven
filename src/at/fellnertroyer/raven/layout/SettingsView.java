package at.fellnertroyer.raven.layout;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import at.fellnertroyer.raven.R;
import at.fellnertroyer.raven.R.layout;
import at.fellnertroyer.raven.R.menu;
import at.fellnertroyer.raven.data.Contact;
import at.fellnertroyer.raven.data.GlobalInformation;
import at.fellnertroyer.raven.data.GlobalInformation.AutoBackupMode;

public class SettingsView extends Activity {

	private ScrollView scroll;
	private LinearLayout container;
	private TextView lblTelNr;
	private ImageView imgProfilePic;
	private EditText txtProfileName;
	private TextView lblProfileStatus;
	private Switch switchNotifcation;
	private Switch switchInAppVibration;
	private TextView lblBackUpTime;
	private LinearLayout blockedContainer;
	private int selectedIndexAlert = -1;
	
	private AutoBackupMode tempMode = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_settings);
		init();
	}
	
	public void init(){
		scroll = (ScrollView)findViewById(R.id.scroll_settings);
		container = (LinearLayout)findViewById(R.id.llayout_settings_container);
		lblTelNr = (TextView)findViewById(R.id.lbl_settings_own_telnr);
		imgProfilePic = (ImageView)findViewById(R.id.img_settings_profile_pic);
		txtProfileName = (EditText)findViewById(R.id.txt_settings_profile_name);
		lblProfileStatus = (TextView)findViewById(R.id.lbl_settings_profile_status);
		switchNotifcation = (Switch)findViewById(R.id.switch_settings_notification);
		switchInAppVibration = (Switch)findViewById(R.id.switch_settings_inapp_vib);
		lblBackUpTime = (TextView)findViewById(R.id.lbl_settings_auto_backup_time);
		blockedContainer = (LinearLayout)findViewById(R.id.llayout_settings_blocked_contacts);
		
		lblTelNr.setText("MEINE NUMMER:  " + (GlobalInformation.telNr == null ? "-" : GlobalInformation.telNr));
		txtProfileName.setText(GlobalInformation.you.getName());
		lblProfileStatus.setText(GlobalInformation.you.getStatus());
		
		switchNotifcation.setChecked(GlobalInformation.settingsNotifications);
		switchInAppVibration.setChecked(GlobalInformation.settingsInAppVibration);
		
		switchNotifcation.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				GlobalInformation.settingsNotifications = isChecked;
			}
		});
		
		lblBackUpTime.setText(GlobalInformation.autoBackupMode.text);
		
		switchInAppVibration.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				GlobalInformation.settingsInAppVibration = isChecked;
			}
		});
		
		blockedContainer.removeAllViews();
		
		for(Contact c : GlobalInformation.blockedContacts){
			ContactRowView crv = new ContactRowView(this, c, "Blockiert");
			crv.setBorderBottom();
			blockedContainer.addView(crv);
		}
	}
	
	public void btnSettingsBackClicked(final View v){
		if(!(txtProfileName.getText().toString().equals(GlobalInformation.you.getName()) || txtProfileName.getText().toString().isEmpty())){
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setCancelable(false);
			alert.setTitle("Wollen Sie sich wirklich umbenennen?");
			alert.setPositiveButton("Ja", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					GlobalInformation.you.setName(txtProfileName.getText().toString());
					finish();
				}
				
			});
			alert.setNegativeButton("Nein", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
			alert.show();
		} else {
			finish();
		}
	}
	
	public void btnSettingsChatBackgroundClicked(final View v){
		
	}
	
	public void btnSettingsChatBackupClicked(final View v){
		final AutoBackupMode[] modes = GlobalInformation.AutoBackupMode.values();
		String[] modeNames = new String[modes.length];
		for(int i = 0; i < modes.length; i++){
			modeNames[i] = modes[i].text;
		}
		int index = -1;
		for(int i = 0; i < modes.length; i++){
			AutoBackupMode m = modes[i];
			if(m == GlobalInformation.autoBackupMode){
				index = i;
				break;
			}
		}
		tempMode = null;
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setSingleChoiceItems(modeNames, index, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				tempMode = modes[which];
				
			}
		});
		alert.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(tempMode != null){
					GlobalInformation.autoBackupMode = tempMode;
					lblBackUpTime.setText(tempMode.text);
				}
				tempMode = null;
			}
		});
		alert.setNegativeButton("Cancle", null);
		alert.show();
	}
	
	public void btnSettingsInstantBackupClicked(final View v){
		
	}
	
	public void btnSettingsAddBlockedContactClicked(final View v){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		ArrayList<Contact> allcontacts = new ArrayList<Contact>(GlobalInformation.allContacts);
		for(Contact c : GlobalInformation.blockedContacts){
			allcontacts.remove(c);
		}
		
		ListView list = new ListView(this);
		ContactListAdapter a = new ContactListAdapter(this,R.layout.contact_list_row,allcontacts);
		list.setAdapter(a);
		alert.setView(list);
		
		selectedIndexAlert = -1;
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {
				selectedIndexAlert = pos;
			}
		});
		
		alert.setNegativeButton("Cancle", null);
		alert.show();
	}
	
	public void btnSettingsProfileStatusClicked(final View v){
		final EditText txtNewStatus = new EditText(this);
		txtNewStatus.setText(GlobalInformation.you.getStatus());
		txtNewStatus.setMaxLines(1);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Status ändern");
		alert.setView(txtNewStatus);
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(!txtNewStatus.getText().toString().isEmpty()){
					GlobalInformation.you.setStatus(txtNewStatus.getText().toString());
					lblProfileStatus.setText(txtNewStatus.getText());
				}
			}
		});
		alert.setNegativeButton("Cancle", null);
		alert.show();
	}
	
	//btnSettingsProfileStatusClicked

}
