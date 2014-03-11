package at.fellnertroyer.raven.data;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class GroupChatContainer extends ChatContainer{

	private ArrayList<Contact> members = new ArrayList<Contact>();
	private HashMap<Contact, Integer> color = new HashMap<Contact, Integer>();
	private Contact admin;
	
	public GroupChatContainer(String name, String lastMsg, GregorianCalendar lastMsgDate) {
		super(name, lastMsg, lastMsgDate);
	}
	
	public GroupChatContainer(String name, String lastMsg, GregorianCalendar lastMsgDate, Contact admin, ArrayList<Contact> members) {
		super(name, lastMsg, lastMsgDate);
		this.admin = admin;
		this.members = (ArrayList<Contact>)members.clone();
	}
	
	public void addMember(Contact member){
		addChatEntity(new ChatInfo(member.getName() + " wurde hinzugefügt"));
		members.add(member);
	}
	
	public void removeMember(Contact member){
		addChatEntity(new ChatInfo(member.getName() + " hat die Unterhaltung verlassen"));
		members.remove(member);
	}
	
	//Getter & Setter

	public ArrayList<Contact> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Contact> members) {
		this.members = members;
	}

	public Contact getAdmin() {
		return admin;
	}

	public void setAdmin(Contact admin) {
		this.admin = admin;
	}
	
	
}
