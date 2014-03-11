package at.fellnertroyer.raven.data;

import java.util.GregorianCalendar;

public class OwnMessage extends Message{
	
	public enum MsgStatus{
		STATUS_SENT,
		STATUS_UNSEEN,
		STATUS_SEEN;
	}
	
	private MsgStatus status;
	
	public OwnMessage(String msg, GregorianCalendar date) {
		super(msg, date);
	}
	
	public OwnMessage(String msg, GregorianCalendar date, MsgStatus status) {
		super(msg, date);
		this.status = status;
	}

	public MsgStatus getStatus() {
		return status;
	}

	public void setStatus(MsgStatus status) {
		this.status = status;
	}
}
