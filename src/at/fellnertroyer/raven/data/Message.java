package at.fellnertroyer.raven.data;

import java.util.GregorianCalendar;

public abstract class Message extends ChatEntity{
	private String msg;
	private GregorianCalendar date;
	
	public Message(String msg, GregorianCalendar date) {
		super();
		this.msg = msg;
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	
	public String getDateString(){
		return GlobalInformation.getDateString(date);
	}
}
