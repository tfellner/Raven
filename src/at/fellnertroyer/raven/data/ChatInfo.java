package at.fellnertroyer.raven.data;

public class ChatInfo extends ChatEntity{
	private String text;

	public ChatInfo(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
