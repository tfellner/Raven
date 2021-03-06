package at.fellnertroyer.raven.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GlobalInformation {
	
	public enum AutoBackupMode{
		OFF("Aus"),
		DAILY("Täglich"),
		WEEKLY("Wöchentlich"),
		MONTHLY("Monatlich");
		
		public final String text;
		AutoBackupMode(String text){
			this.text = text;
		}
	}
	
	public static String telNr;
	public static ArrayList<Contact> allContacts = new ArrayList<Contact>();
	public static ArrayList<ChatContainer> allChats = new ArrayList<ChatContainer>();
	public static ArrayList<Contact> blockedContacts = new ArrayList<Contact>();
	public static Contact you = new Contact("David", "Teststatus");
	
	public static boolean settingsNotifications = true;
	public static boolean settingsInAppVibration = true;
	
	public static AutoBackupMode autoBackupMode = AutoBackupMode.DAILY;
	
	private static String[] weekday = {"Sonntag","Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag"};
	
	public static String getDateString(GregorianCalendar date){
		GregorianCalendar now = new GregorianCalendar();
		if(now.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR) && now.get(Calendar.YEAR) == date.get(Calendar.YEAR)){
			return ((date.get(Calendar.HOUR_OF_DAY) < 10 ? "0" : "") + date.get(Calendar.HOUR_OF_DAY) + ":" + (date.get(Calendar.MINUTE) < 10 ? "0" : "") + (date.get(Calendar.MINUTE)));
		}
		if(now.get(Calendar.YEAR) == date.get(Calendar.YEAR) && now.get(Calendar.WEEK_OF_MONTH) == date.get(Calendar.WEEK_OF_MONTH)){
			return (weekday[date.get(Calendar.DAY_OF_WEEK)-1]);
		}
		
		return ((date.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "") + date.get(Calendar.DAY_OF_MONTH) + "." + ((date.get(Calendar.MONTH)+1) < 10 ? "0" : "") + (date.get(Calendar.MONTH)+1) + "." + (date.get(Calendar.YEAR)%100 < 10 ? "0" : "") + ((date.get(Calendar.YEAR)%100)));
	}
}
