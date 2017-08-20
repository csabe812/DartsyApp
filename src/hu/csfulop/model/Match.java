package hu.csfulop.model;

import java.sql.Date;

public class Match {

	private int id;
	private Date dateOfMatch;

	public Match(int id, Date dateOfMatch) {
		this.id = id;
		this.dateOfMatch = dateOfMatch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfMatch() {
		return dateOfMatch;
	}

	public void setDateOfMatch(Date dateOfMatch) {
		this.dateOfMatch = dateOfMatch;
	}

}
