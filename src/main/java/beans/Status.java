package beans;

import java.util.Objects;

public class Status {
	
	private int id;
	private int idexpenses;
	private Boolean pending = true;
	private Boolean approved = false;
	private Boolean denied = false;
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Status(int id, int idexpenses, Boolean pending, Boolean approved, Boolean denied) {
		super();
		this.id = id;
		this.idexpenses = idexpenses;
		this.pending = pending;
		this.approved = approved;
		this.denied = denied;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdexpenses() {
		return idexpenses;
	}

	public void setIdexpenses(int idexpenses) {
		this.idexpenses = idexpenses;
	}
	public void setPending(Boolean pending) {
		this.pending = pending;
	}
	public  Boolean getPending() {
		return pending;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "status [id=" + id + ", idexpenses=" + idexpenses + ", pending=" + pending + ", approved=" + approved
				+ ", denied=" + denied + "]";
	}
	

}
