package at.fellnertroyer.raven.data;

import java.util.GregorianCalendar;

public class IncomeMessage extends Message{
	private Contact contact;

	public IncomeMessage(String msg, GregorianCalendar date, Contact contact) {
		super(msg, date);
		this.contact = contact;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
