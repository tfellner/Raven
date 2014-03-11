package at.fellnertroyer.raven.data;

import java.util.GregorianCalendar;

public class SingleChatContainer extends ChatContainer{

	private Contact partner;
	
	public SingleChatContainer(Contact partner, String lastMsg, GregorianCalendar lastMsgDate) {
		super(partner.getName(), lastMsg, lastMsgDate);
		this.partner = partner;
	}

	public Contact getPartner() {
		return partner;
	}
}
