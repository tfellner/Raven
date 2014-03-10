package at.fellnertroyer.raven.data;

public class ChatContainer {
	public String name;
	public String lastMsg;
	public String date;
	public int unseen;
	
	
	public ChatContainer(String name, String lastMsg, String date, int unseen) {
		super();
		this.name = name;
		this.lastMsg = lastMsg;
		this.date = date;
		this.unseen = unseen;
	}
}
