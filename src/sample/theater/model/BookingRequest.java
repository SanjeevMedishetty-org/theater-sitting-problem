package sample.theater.model;

public class BookingRequest {

	private String name;
	private int numTicketsReq;
	private int row;
	private int section;

	public BookingRequest(String name, int numTicketsReq) {
		super();
		this.name = name;
		this.numTicketsReq = numTicketsReq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumTicketsReq() {
		return numTicketsReq;
	}

	public void setNumTicketsReq(int numTicketsReq) {
		this.numTicketsReq = numTicketsReq;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

}
