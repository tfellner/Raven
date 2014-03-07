package at.fellnertroyer.raven;

public class ChatContainer {
	String name;
	String lastMsg;
	String date;
	int unseen;
	
	
	public ChatContainer(String name, String lastMsg, String date, int unseen) {
		super();
		this.name = name;
		this.lastMsg = lastMsg;
		this.date = date;
		this.unseen = unseen;
	}
}
