package in.frodo.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class BaseEntity {
	
	@Column(nullable = false)
	private Date createdAt;
	@Column(nullable = false)
	private Date updatedAt;
	@Column(nullable = false)
	private boolean isDelete;
	
	
	@PrePersist
	protected void onCreate(){
		Date date = new Date();
		this.createdAt = date;
		this.updatedAt = date;
		this.isDelete = false;
	}
	
	@PreUpdate
	protected void onUpdate(){
		Date date = new Date();
		this.updatedAt = date;
	}
	
	protected void softDelete(){
		this.isDelete = true;
	}
	
	protected void undoSoftDelete(){
		this.isDelete = false;
	}
	
	
	@JsonIgnore
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@JsonIgnore
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@JsonIgnore
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}	

}
