package fr.eservices.drive.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class StatusHistory {
	@Id
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date statusDate;
	@Enumerated(EnumType.STRING)
	private Status status;
	public Long getId() {
		return id;
	}
	public Status getStatus() {
		return status;
	}
	public Date getStatusDate() {
		return statusDate;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	

}
