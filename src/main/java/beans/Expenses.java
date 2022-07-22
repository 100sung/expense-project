package beans;

import java.util.Objects;
//import beans.Status;
public class Expenses extends Status{ 

	private int idexpenses;
	private String name;
	private double amount;
	private String reason;
	private Boolean pending;
	private Boolean approved;
	private Boolean denied;
	
	public Expenses() {
		super();
	}

	public Expenses(int idexpenses, String name, double amount, String reason, Boolean pending,
			Boolean approved, Boolean denied) {
		super();
		this.idexpenses = idexpenses;
		this.name = name;
		this.amount = amount;
		this.reason = reason;
		this.pending = pending;
		this.approved = approved;
		this.denied = denied;
	}

	

	public Boolean getPending() {
		
		return pending;
		
	}

	public void setPending(Boolean pending) {
		this.pending = pending;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Boolean getDenied() {
		return denied;
	}

	public void setDenied(Boolean denied) {
		this.denied = denied;
	}

	public int getIdexpenses() {
		return idexpenses;
	}

	public void setIdexpenses(int idexpenses) {
		this.idexpenses = idexpenses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idexpenses);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expenses other = (Expenses) obj;
		return idexpenses == other.idexpenses;
	}

	@Override
	public String toString() {
		return "Expenses [id=" + idexpenses + ", name=" + name + ", amount=" + amount + ", reason=" + reason + "]";
	}
	
}
