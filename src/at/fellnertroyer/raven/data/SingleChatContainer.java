package at.fellnertroyer.raven.data;

import java.util.GregorianCalendar;

public class SingleChatContainer extends ChatContainer{

	private Contact partner;
	
	public SingleChatContainer(Contact partner, GregorianCalendar lastMsgDate) {
		super(partner.getName(), lastMsgDate);
		this.partner = partner;
	}

	public Contact getPartner() {
		return partner;
	}
	
	public void setName(Contact partner){
		setName(partner.getName());
	}
	
	public boolean isPartnerOnline(){
		return partner.isOnline();
	}
}
