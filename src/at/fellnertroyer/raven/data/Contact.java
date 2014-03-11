package at.fellnertroyer.raven.data;

public class Contact {
	private String name;
	private String status;
	
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
	
	
}
