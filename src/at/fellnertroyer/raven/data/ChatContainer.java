package at.fellnertroyer.raven.data;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class ChatContainer {
	private String name;
	private GregorianCalendar lastMsgDate;
	private GregorianCalendar createDate;
	private int unseen = 0;
	private boolean notification;
	
	private ArrayList<ChatEntity> chatEntityList = new ArrayList<ChatEntity>();
	private ArrayList<ChatEntity> chatEntityUnseenList = new ArrayList<ChatEntity>();
	
	
	public ChatContainer(String name, GregorianCalendar lastMsgDate, int unseen) {
		super();
		this.name = name;
		this.lastMsgDate = lastMsgDate;
		this.unseen = unseen;
	}
	
	public ChatContainer(String name, GregorianCalendar lastMsgDate) {
		super();
		this.name = name;
		this.lastMsgDate = lastMsgDate;
	}
	
	public void addChatEntity(ChatEntity chatEntity){
		chatEntityList.add(chatEntity);
	}
	
	public void addUnseenChatEntity(ChatEntity chatEntity){
		chatEntityUnseenList.add(chatEntity);
		
		unseen = 0;
		for(ChatEntity e : chatEntityUnseenList){
			if(e instanceof IncomeMessage){
				unseen++;
			}
		}
	}
	
	public void setAllUnseenToSeen(){
		chatEntityList.addAll(chatEntityUnseenList);
		chatEntityUnseenList = new ArrayList<ChatEntity>();
		unseen = 0;
	}

	public Message getLastMsg(){
		if(chatEntityUnseenList.size() > 0 && chatEntityList != null){
			for(int i = chatEntityUnseenList.size()-1; i >= 0; i--){
				ChatEntity ce = chatEntityUnseenList.get(i);
				if(ce instanceof Message){
					return (Message)ce;
				}
			}
		} else if(chatEntityList.size() > 0 && chatEntityList != null){
			for(int i = chatEntityList.size()-1; i >= 0; i--){
				ChatEntity ce = chatEntityList.get(i);
				if(ce instanceof Message){
					return (Message)ce;
				}
			}
		}
		return null;
	}
	
	public String getLastMsgString(){
		return getLastMsg().getMsg();
	}
	
	// Getter & Setter
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GregorianCalendar getLastMsgDate() {
		return lastMsgDate;
	}

	public void setLastMsgDate(GregorianCalendar lastMsgDate) {
		this.lastMsgDate = lastMsgDate;
	}

	public GregorianCalendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(GregorianCalendar createDate) {
		this.createDate = createDate;
	}

	public int getUnseen() {
		return unseen;
	}

	public void setUnseen(int unseen) {
		this.unseen = unseen;
	}

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}

	public ArrayList<ChatEntity> getChatEntityList() {
		return chatEntityList;
	}

	public void setChatEntityList(ArrayList<ChatEntity> chatEntityList) {
		this.chatEntityList = chatEntityList;
	}

	public ArrayList<ChatEntity> getChatEntityUnseenList() {
		return chatEntityUnseenList;
	}

	public void setChatEntityUnseenList(ArrayList<ChatEntity> chatEntityUnseenList) {
		this.chatEntityUnseenList = chatEntityUnseenList;
	}
	
}
