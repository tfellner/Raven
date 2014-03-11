package at.fellnertroyer.raven.data;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class GroupChatContainer extends ChatContainer{

	private ArrayList<Contact> members = new ArrayList<Contact>();
	private HashMap<Contact, Integer> colorMap = new HashMap<Contact, Integer>();
	private ArrayList<Integer> colorList = new ArrayList<Integer>();
	private Contact admin = null;

	
	public GroupChatContainer(String name, String lastMsg, GregorianCalendar lastMsgDate) {
		super(name, lastMsgDate);
		fillColorMap();
	}
	
	public GroupChatContainer(String name, GregorianCalendar lastMsgDate) {
		super(name, lastMsgDate);
		fillColorMap();
	}
	
	public GroupChatContainer(String name, GregorianCalendar lastMsgDate, Contact admin, ArrayList<Contact> members) {
		super(name, lastMsgDate);
		this.admin = admin;
		this.members = (ArrayList<Contact>)members.clone();
		fillColorMap();
	}
	
	private void fillColorList(){
		colorList = new ArrayList<Integer>();
		colorList.add(0xff33b5e5);
		colorList.add(0xff99cc00);
		colorList.add(0xffff4444);
		colorList.add(0xff0099cc);
		colorList.add(0xff669900);
		colorList.add(0xffcc0000);
		colorList.add(0xffaa66cc);
		colorList.add(0xffffbb33);
		colorList.add(0xffff8800);
		colorList.add(0xff00ddff);
	}
	
	private void fillColorMap(){
		for(Contact c : members){
			putIntoColorMap(c);
		}
	}
	
	private void putIntoColorMap(Contact c){
		if(colorMap.containsKey(c)) return;
		
		if(colorList.size() == 0){
			fillColorList();
		}
		int random = (int)(Math.random()*colorList.size());
		colorMap.put(c,colorList.get(random));
		colorList.remove(random);
	}
	
	public void addMember(Contact member){
		addChatEntity(new ChatInfo(member.getName() + " wurde hinzugefügt"));
		members.add(member);
		putIntoColorMap(member);
	}
	
	public void removeMember(Contact member){
		addChatEntity(new ChatInfo(member.getName() + " hat die Unterhaltung verlassen"));
		members.remove(member);
	}
	
	public int getMemberCount(){
		return members.size() + 2; // Members + Admin + You
	}
	
	public int getMemberOnlineCount(){
		int i = 0;
		for(Contact c : members){
			if(c.isOnline()) i++;
		}
		if(admin != null && admin.isOnline()) i++;
		
		return i+1; // You are online
	}
	
	public int getColor(Contact member){
		if(colorMap.containsKey(member)){
			return colorMap.get(member);
		} 
		return 0x000000FF;
	}
	
	//Getter & Setter
	
	public HashMap<Contact, Integer> getColorMap() {
		return colorMap;
	}

	public void setColorMap(HashMap<Contact, Integer> colorMap) {
		this.colorMap = colorMap;
	}

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
