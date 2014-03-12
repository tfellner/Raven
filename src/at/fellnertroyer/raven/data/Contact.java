package at.fellnertroyer.raven.data;

public class Contact {
	private String name;
	private String status;
	private boolean online;
	
	public Contact(String name, String status){
		this.name = name;
		this.status = status;
	}

	// GETTER & SETTER
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
	
	

	@Override
	public String toString() {
		return name + " - " + status;
	}
	
}
